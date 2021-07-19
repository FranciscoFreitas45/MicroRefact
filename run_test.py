import os
import subprocess
import csv
import numpy as np


results=dict()

children = []
line = 1


def refactoring_monolith(path_to_project,proposed_microservices):
    os.chdir(r"/home/fracisco/Desktop/MicroRefact/app")
    command = f"python3 main.py -c {proposed_microservices} -pp {path_to_project} >> some.txt"
    print(f"Cmd : {command}")
    result = subprocess.call(command, shell=True)
    results[path_to_project].append(result)
    print(result)

def candidate_microservices(path_to_project,writer, k_topics):
    os.chdir(r"/home/fracisco/Desktop/Backups/microservice-identification/app")
    cwd = os.getcwd()
    print("Current working directory is:", cwd)
    results[path_to_project] = list()
    #for resolution in np.arange(0.3,1.0,0.1):
    #    for k_topics in range(5,20,5):  
    os.chdir(r"/home/fracisco/Desktop/Backups/microservice-identification/app")   
    command = f"python3 main.py -p {path_to_project} "
        #command = f"python3 main.py -p {path_to_project} -k {k_topics}  -r {resolution} "
    print(f"Cmd : {command}")
    subprocess.call(command, shell=True)
    refactoring_monolith(path_to_project, "/home/fracisco/Desktop/Backups/microservice-identification/projects.json")
        
    results[path_to_project].insert(0, path_to_project)
    writer.writerow(results[path_to_project])
    


def deleteRepo(path_to_project):
    command = f"rm -r  -f {path_to_project} "
    subprocess.call(command, shell=True)




def download(repository):
    user, repo = repository.split('/')
    command = f"git clone https://github.com/{user}/{repo}.git {user}__{repo}"
    print(f"Cmd : {command}")
    result = subprocess.call(command, shell=True)
    print(result)




def runTests(csv_name,file):

    with open(csv_name) as csv_file:
        csv_reader = csv.DictReader(csv_file)
        line_count = 0
        for row in list(csv_reader):
            os.chdir(r"/home/fracisco/Desktop/MicroRefact")
            if line_count == 0:
                print(f'Column names are {", ".join(row)}')
                line_count += 1
            print(f'{row["name"]}.')
            download(row["name"])
            user, repo = row["name"].split('/')
            '''
            class_count = int(row["classes"])
            k = 10 
            if class_count < 50:
                k = 10
            elif class_count < 100:
                k = 13
            elif class_count < 200:
                k = 17
            elif class_count < 500:
                k = 22
            elif class_count < 1000:
                k = 26
            else:
                k = 32

            '''
            candidate_microservices(f"/home/fracisco/Desktop/MicroRefact/{user}__{repo}",file,0)

            deleteRepo(f"/home/fracisco/Desktop/MicroRefact/{user}__{repo}")
            line_count += 1
        print(f'Processed {line_count} lines.')


def main():
    
    with open('resultsJPA.csv', 'w+', newline='') as file:
        writer = csv.writer(file)
        writer.writerow(["Project", "k5_r0.3","k10_r0.3","k15_0.3", "k5_r0.4","k10_r0.4","k15_0.4", "k5_r0.5","k10_r0.5","k15_0.5", "k5_r0.6","k10_r0.6","k15_0.6","k5_r0.7","k10_r0.7","k15_0.7", "k5_r0.8","k10_r0.8","k15_0.8", "k5_r0.9","k10_r0.9","k15_0.9"])
        #writer.writerow([1, "Linus Torvalds", "Linux Kernel"])
       
    
        #refactoring_monolith("/home/fracisco/Desktop/MiguelProjects/invoice","/home/fracisco/Desktop/microservice-identification/projects.json")
        #candidate_microservices("/home/fracisco/Desktop/MiguelProjects/invoice",writer)
        #download("miansen/Roothub")
        runTests("MiguelJPA.csv",writer) 
        print(results)     

main()    
