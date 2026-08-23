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

def get_leetcode_topic(title_slug):
    url = "https://leetcode.com/graphql"
    query = """
    query questionData($titleSlug: String!) {
      question(titleSlug: $titleSlug) {
        topicTags { name }
      }
    }
    """
    payload = json.dumps({"query": query, "variables": {"titleSlug": titleSlug}}).encode('utf-8')
    req = urllib.request.Request(url, data=payload, headers={'Content-Type': 'application/json', 'User-Agent': 'Mozilla/5.0'})
    
    try:
        with urllib.request.urlopen(req, timeout=5) as response:
            data = json.loads(response.read().decode())
            tags = data.get('data', {}).get('question', {}).get('topicTags', [])
            if tags:
                return tags[0]['name']
    except Exception:
        pass
    return "Others"

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

def update_readme():
    readme_path = "README.md"
    if not os.path.exists(readme_path):
        return

    with open(readme_path, "r", encoding="utf-8") as f:
        readme_content = f.read()

    readme_lines = readme_content.splitlines()

    for item in os.listdir("."):
        if os.path.isdir(item) and item not in IGNORE_DIRS:
            prob_num, slug = parse_folder_name(item)
            if prob_num and slug:
                # Check if folder name or problem ID already exists in README
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
                # Formats exactly like your current README style: - [1. two sum](./1-two-sum/) - java
                formatted_entry = f"- [{prob_num}. {title}](./{item}/) - {lang}"

                topic = get_leetcode_topic(slug)
                header_idx = match_topic_header(topic, readme_lines)

                if header_idx != -1:
                    insert_idx = header_idx + 1
                    # Find end of section before next # section
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

    with open(readme_path, "w", encoding="utf-8") as f:
        f.write("\n".join(readme_lines) + "\n")

if __name__ == "__main__":
    update_readme()
