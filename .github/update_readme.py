import os
import re
import json
import urllib.request
import urllib.error

README = "README.md"

# Map LeetCode tags to YOUR README topic names
TOPIC_MAP = {
    "array": "📊 Arrays & Array Manipulation",
    "string": "🔤 Strings",
    "linked-list": "🔗 Linked List",
    "tree": "🌳 Binary Tree & BST",
    "binary-search-tree": "🌳 Binary Tree & BST",
    "backtracking": "🔄 Backtracking & Recursion",
    "recursion": "🔄 Backtracking & Recursion",
    "binary-search": "🔍 Binary Search",
    "sliding-window": "🪟 Sliding Window",
    "math": "🧮 Math & Number Theory",
    "number-theory": "🧮 Math & Number Theory",
    "hash-table": "🧩 Hashing / Frequency Counting",
    "dynamic-programming": "📈 Dynamic Programming / Greedy",
    "greedy": "📈 Dynamic Programming / Greedy",
    "stack": "🧱 Stack / Parentheses",
    "geometry": "📐 Geometry / Simulation / Miscellaneous",
    "simulation": "📐 Geometry / Simulation / Miscellaneous",
}


def get_problem_folders():
    """
    Find LeetSync problem folders.
    Folder names look like:
    1-two-sum
    121-best-time-to-buy-and-sell-stock
    """

    folders = []

    for item in os.listdir("."):
        if not os.path.isdir(item):
            continue

        match = re.match(r"^(\d+)-(.+)$", item)

        if match:
            problem_number = match.group(1)
            slug = match.group(2)

            folders.append({
                "number": int(problem_number),
                "slug": slug,
                "folder": item
            })

    return folders


def get_slug_from_folder(folder):
    """
    Convert:
    1-two-sum
    into:
    two-sum
    """

    match = re.match(r"^\d+-(.+)$", folder)

    if match:
        return match.group(1)

    return None


def get_leetcode_data(slug):
    """
    Get title and topic tags from LeetCode GraphQL API.
    """

    url = "https://leetcode.com/graphql"

    query = {
        "operationName": "questionData",
        "variables": {
            "titleSlug": slug
        },
        "query": """
        query questionData($titleSlug: String!) {
            question(titleSlug: $titleSlug) {
                questionFrontendId
                title
                titleSlug
                topicTags {
                    name
                    slug
                }
            }
        }
        """
    }

    data = json.dumps(query).encode("utf-8")

    request = urllib.request.Request(
        url,
        data=data,
        headers={
            "Content-Type": "application/json",
            "User-Agent": "Mozilla/5.0"
        },
        method="POST"
    )

    try:
        with urllib.request.urlopen(request, timeout=20) as response:
            result = json.loads(response.read().decode("utf-8"))

        question = result.get("data", {}).get("question")

        if question:
            return question

    except Exception as e:
        print(f"Could not fetch LeetCode data for {slug}: {e}")

    return None


def find_topic(tags):
    """
    Find the first matching topic from our topic map.
    """

    for tag in tags:
        tag_slug = tag.get("slug", "").lower()

        if tag_slug in TOPIC_MAP:
            return TOPIC_MAP[tag_slug]

    return "📐 Geometry / Simulation / Miscellaneous"


def find_topic_section(readme, topic):
    """
    Find the section of the README belonging to a topic.
    Matches a level-1 markdown heading: "# <topic>"
    """

    pattern = re.escape("# " + topic)

    match = re.search(
        pattern + r".*?(?=\n# |\Z)",
        readme,
        re.DOTALL
    )

    return match


def add_problem_to_existing_topic(readme, topic, problem_line):
    """
    Add the new problem to an existing topic section.
    """

    section_match = find_topic_section(readme, topic)

    if not section_match:
        return None

    section = section_match.group(0)

    # Don't add duplicate
    if problem_line in section:
        return readme

    lines = section.splitlines()

    # Find "Problem Solution" heading, regardless of how many '#' it has
    insert_index = None

    for i, line in enumerate(lines):
        cleaned = line.strip().lstrip("#").strip().lower()
        if cleaned == "problem solution":
            insert_index = i + 1
            break

    if insert_index is None:
        return None

    # Keep a blank line between the heading and the list if one existed
    if insert_index < len(lines) and lines[insert_index].strip() == "":
        insert_index += 1

    lines.insert(insert_index, problem_line)

    new_section = "\n".join(lines)

    return (
        readme[:section_match.start()]
        + new_section
        + readme[section_match.end():]
    )


def create_new_topic(readme, topic, problem_line):
    """
    Create a new topic section if it doesn't exist.
    Uses proper markdown headings so future runs can find it again.
    """

    new_section = (
        f"\n\n# {topic}\n\n"
        f"## Problem Solution\n\n"
        f"{problem_line}\n"
    )

    # Add before Practice Areas
    marker = "🏆 Practice Areas"

    if marker in readme:
        return readme.replace(
            marker,
            new_section + "\n" + marker,
            1
        )

    # Otherwise append near the end
    return readme + new_section


def problem_already_exists(readme, number):
    """
    Check whether a problem is already present in README.
    """

    pattern = rf"(?m)^\s*{re.escape(str(number))}\.\s+"

    return re.search(pattern, readme) is not None


def main():

    print("Reading README...")

    if not os.path.exists(README):
        print("README.md not found.")
        return

    with open(README, "r", encoding="utf-8") as file:
        readme = file.read()

    folders = get_problem_folders()

    print(f"Found {len(folders)} problem folders.")

    changed = False

    for problem in folders:

        number = problem["number"]
        folder = problem["folder"]
        slug = problem["slug"]

        # Already in README?
        if problem_already_exists(readme, number):
            continue

        print(f"New problem detected: {number} - {slug}")

        data = get_leetcode_data(slug)

        if not data:
            print(f"Skipping {number}: LeetCode data unavailable.")
            continue

        title = data.get("title", slug)

        tags = data.get("topicTags", [])

        topic = find_topic(tags)

        problem_line = (
            f"- [{number}. {title}](./{folder}/) - java"
        )

        print(f"Topic: {topic}")

        updated_readme = add_problem_to_existing_topic(
            readme,
            topic,
            problem_line
        )

        if updated_readme is None:
            print(f"Creating new topic: {topic}")

            updated_readme = create_new_topic(
                readme,
                topic,
                problem_line
            )

        if updated_readme != readme:
            readme = updated_readme
            changed = True

    if changed:

        with open(README, "w", encoding="utf-8") as file:
            file.write(readme)

        print("README updated successfully.")

    else:
        print("No README changes required.")


if __name__ == "__main__":
    main()
