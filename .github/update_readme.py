import os
import re
import urllib.request
import json

IGNORE_DIRS = {'.git', '.github', '.vscode', 'node_modules'}
EXTENSION_MAP = {
    '.java': 'java',
    '.py': 'python',
    '.cpp': 'cpp',
    '.js': 'javascript',
    '.ts': 'typescript'
}

def get_leetcode_details(title_slug):
    url = "https://leetcode.com/graphql"
    query = """
    query questionData($titleSlug: String!) {
      question(titleSlug: $titleSlug) {
        difficulty
        topicTags { name }
      }
    }
    """
    payload = json.dumps({"query": query, "variables": {"titleSlug": title_slug}}).encode('utf-8')
    req = urllib.request.Request(url, data=payload, headers={'Content-Type': 'application/json', 'User-Agent': 'Mozilla/5.0'})
    
    try:
        with urllib.request.urlopen(req, timeout=5) as response:
            data = json.loads(response.read().decode())
            q = data.get('data', {}).get('question', {})
            difficulty = q.get('difficulty', 'Easy')
            tags = q.get('topicTags', [])
            topic = tags[0]['name'] if tags else 'Others'
            return topic, difficulty
    except Exception:
        pass
    return "Others", "Easy"

def parse_folder_name(dir_name):
    match = re.match(r'^0*(\d+)-(.*)$', dir_name)
    if match:
        return int(match.group(1)), match.group(2)
    return None, None

def match_topic_header(topic_name, readme_lines):
    aliases = {
        "array": ["array", "arrays"],
        "linked list": ["linked list", "linkedlist"],
        "string": ["string", "strings"],
        "math": ["math", "maths", "math & number theory"],
        "tree": ["tree", "binary tree & bst"],
        "depth-first search": ["binary tree & bst", "tree"],
        "breadth-first search": ["binary tree & bst", "tree"],
        "hash table": ["hashing / frequency counting", "hash table"],
        "two pointers": ["arrays & array manipulation", "two pointers"],
        "dynamic programming": ["dynamic programming / greedy"],
        "greedy": ["dynamic programming / greedy"],
        "stack": ["stack / parentheses"],
        "sliding window": ["sliding window"],
        "backtracking": ["backtracking & recursion"],
        "recursion": ["backtracking & recursion"]
    }

    targets = aliases.get(topic_name.lower(), [topic_name.lower()])

    for idx, line in enumerate(readme_lines):
        if line.strip().startswith('#'):
            header_text = re.sub(r'^[#\s\W]+', '', line).strip().lower()
            for target in targets:
                if target in header_text:
                    return idx
    return -1

def update_readme_and_stats():
    readme_path = "README.md"
    if not os.path.exists(readme_path):
        return

    with open(readme_path, "r", encoding="utf-8") as f:
        readme_content = f.read()

    readme_lines = readme_content.splitlines()

    easy_count = 0
    medium_count = 0
    hard_count = 0

    for item in os.listdir("."):
        if os.path.isdir(item) and item not in IGNORE_DIRS:
            prob_num, slug = parse_folder_name(item)
            if prob_num and slug:
                topic, difficulty = get_leetcode_details(slug)

                # Track difficulty counts
                if difficulty == "Easy":
                    easy_count += 1
                elif difficulty == "Medium":
                    medium_count += 1
                elif difficulty == "Hard":
                    hard_count += 1

                # If already logged in README, skip markdown insertion
                if f"./{item}/" in readme_content or re.search(rf'\[{prob_num}\.\s', readme_content):
                    continue 

                item_path = os.path.join(".", item)
                lang = "java"
                for file in os.listdir(item_path):
                    ext = os.path.splitext(file)[1]
                    if ext in EXTENSION_MAP:
                        lang = EXTENSION_MAP[ext]
                        break

                title = slug.replace('-', ' ')
                formatted_entry = f"- [{prob_num}. {title}](./{item}/) - {lang}"

                header_idx = match_topic_header(topic, readme_lines)

                if header_idx != -1:
                    insert_idx = header_idx + 1
                    while insert_idx < len(readme_lines) and not (readme_lines[insert_idx].strip().startswith('#') and not readme_lines[insert_idx].strip().startswith('## Problem Solution')):
                        insert_idx += 1
                    
                    while insert_idx > header_idx + 1 and readme_lines[insert_idx - 1].strip() == '':
                        insert_idx -= 1

                    readme_lines.insert(insert_idx, formatted_entry)
                else:
                    readme_lines.append("")
                    readme_lines.append(f"# {topic}")
                    readme_lines.append("## Problem Solution")
                    readme_lines.append(formatted_entry)

                readme_content = "\n".join(readme_lines)

    # Save README.md
    with open(readme_path, "w", encoding="utf-8") as f:
        f.write("\n".join(readme_lines) + "\n")

    # Save stats.json
    stats_data = {
        "solved": easy_count + medium_count + hard_count,
        "easy": easy_count,
        "medium": medium_count,
        "hard": hard_count
    }

    with open("stats.json", "w", encoding="utf-8") as sf:
        json.dump(stats_data, sf, indent=4)

if __name__ == "__main__":
    update_readme_and_stats()
