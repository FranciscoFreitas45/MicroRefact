import re
import json
import time
import requests
import subprocess

from bs4 import BeautifulSoup

# TODO: REVOKE TOKEN BEFORE GOING PUBLIC
ACCESS_TOKEN = "e7efd34a919adcb98d680a47677d4968b5953a2b"

def find_repositories_containing_keyword(keyword, language='java'):
        # 35 is the max number of pages for the limit of 1000 results for a search established by GitHub
        for i in range(1, 10):
            url = f"https://api.github.com/search/code?q={keyword}+language:{language}&access_token={ACCESS_TOKEN}&page={i}"
            req = requests.get(url)
            print(f"Requesting {url}")
            if req.ok:
                data = req.json()
                print(f"Request status code: {req.status_code}, page {i}")
                if len(data['items']) == 0:
                    break
                yield req.json()
            else:
                break


def get_repos(file_path):
    repositories = {}
    final_list = set()
    for data in find_repositories_containing_keyword('org.springframework.data.jpa+org.springframework.data.jpa.repository.JpaRepository'):
        items = data['items']
        repositories = {item["repository"]['full_name'] for item in items}
        

        with open(file_path, "a+") as f:
            for key in repositories:
                if key not in final_list:    
                    print(f"Found repo: {key}")
                    final_list.add(key)
                    f.write(key+"\n") 


     

    return repositories



def filter_found_projects(file_path):
    ignore_words = {'release', 'framework',
                    'learn', 'source', 'spring', 'study', 'demo', 'test', 'practice', 'practise'}
    filtered_projects = set()
    with open(file_path, 'r') as f:
        line = f.readline()
        while line:
            print(f"Filtering {line}")
            line = line.strip()
            ignore = False
            for word in ignore_words:
                if word in line.lower():
                    ignore = True

            if not ignore:
                filtered_projects.add(line)
            line = f.readline()

    # First filter
    with open(f"{file_path}_filtered", 'w+') as f:
        for project in filtered_projects:
            f.write(f"{project}\n")


def get_repos_info(file_path):
    with open(f"{file_path}_filtered", 'r') as f:
        line = f.readline()
        while line:
            print(f"Filtering size {line}")
            line = line.strip()
            info_repo = get_info_repo(line)
            time.sleep(3)
            print(f"{line} -> {info_repo}")

            with open(f"./repos_data.txt", 'a+') as fp:
                try:
                    write_line = f"{line}, {info_repo['size']}, {info_repo['stars']}, {info_repo['forks']}, {info_repo['contributors']}, {info_repo['is_fork']}, {info_repo['language']}, {info_repo['subscribers']}, {info_repo['commit_count']}, {info_repo['open_issues']}\n"
                    fp.write(write_line)
                except KeyError:
                    print(f"Failed to fetch some data, ignoring this repo")
            line = f.readline()



def get_info_repo(repo_full_name):
    repository = {}
    user, repo = repo_full_name.split("/")
    r = requests.get(
        f"https://api.github.com/repos/{user}/{repo}?access_token={ACCESS_TOKEN}")
    if(r.ok):
        repo_info = json.loads(r.text or r.content)
        repository['size'] = repo_info['size']
        repository['forks'] = repo_info['forks_count']
        repository['stars'] = repo_info['stargazers_count']
        repository['open_issues'] = repo_info['open_issues']
        repository['subscribers'] = repo_info['subscribers_count']
        repository['is_fork'] = True if repo_info['fork'] == 'true' else False
        repository['language'] = repo_info['language']

    repository['commit_count'] = get_commit_count(user, repo) 

    time.sleep(0.5)

    req_contributors = requests.get(f"https://api.github.com/repos/{user}/{repo}/contributors?access_token={ACCESS_TOKEN}")
    if (req_contributors.ok):
        repository['contributors'] = len(json.loads(req_contributors.text or req_contributors.content))

    return repository


def get_commit_count(user, repo): 

    headers = {"Authorization": f"token {ACCESS_TOKEN}"} 
    query = f"""
        {{
            repository(owner: "{user}", name: "{repo}") {{
                name
                refs(first:100, refPrefix: "refs/heads/", query:"master") {{
                    edges {{
                        node {{
                            name
                            target {{
                                ... on Commit {{
                                    history(first: 0) {{
                                        totalCount
                                    }}
                                }}
                            }}
                        }}
                    }}
                }}
            }}
        }}"""

    request = requests.post("https://api.github.com/graphql", json={"query":query}, headers=headers)
    commit_count = 0
    if request.ok:
        try:
            data = json.loads(request.text)
            commit_count = data['data']['repository']['refs']['edges'][0]['node']['target']['history']['totalCount']
            print(f"Total Commits: {commit_count}")
        except IndexError:
            print(f"Failed to fetch some data, ignoring this repo")
    else:
        print("Failed to request commit data")

    return commit_count

if __name__ == "__main__":
    repos = get_repos("repos_backend.txt")
    filter_found_projects('./repos_backend.txt')
    get_repos_info('./repos_backend.txt')
