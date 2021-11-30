import os
import subprocess
import csv
import numpy as np


results=dict()

children = []
line = 1


def refactoring_monolith(path_to_project,proposed_microservices):
    os.chdir(r"/home/fracisco/Desktop/MicroRefact/app")
    command = f"python3 main.py -c {proposed_microservices} -pp {path_to_project}  "
    print(f"Cmd : {command}")
    result = subprocess.call(command, shell=True)
    #results[path_to_project].append(result)
    print(result)

def candidate_microservices(path_to_project, writer):
    os.chdir(r"/home/fracisco/Desktop/Backups/microservice-identification/app")
    cwd = os.getcwd()
    print("Current working directory is:", cwd)
    results[path_to_project] = list()
    os.chdir(r"/home/fracisco/Desktop/Backups/microservice-identification/app")   
    command = f"python3 main.py -p {path_to_project} "
    print(f"Cmd : {command}")
    #subprocess.call(command, shell=True)
    refactoring_monolith(path_to_project, "/home/fracisco/Desktop/Backups/microservice-identification/micro_candidate/project_"+ str(path_to_project.split('/')[-1])+".json")
    
    results[path_to_project].insert(0, path_to_project)
    writer.writerow(results[path_to_project])
    


def deleteRepo(path_to_project):
    command = f"rm -r  -f {path_to_project} "
    subprocess.call(command, shell=True)




def download(repository):
    print("####"+repository)
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
            print(f'{row["Project"]}.')
            #download(row["Project"])
            user, repo = row["Project"].split('/')
           
            candidate_microservices(f"/home/fracisco/Desktop/MicroRefact/{user}__{repo}",file)
            #deleteRepo(f"/home/fracisco/Desktop/MicroRefact/{user}__{repo}")
            line_count += 1
        print(f'Processed {line_count} lines.')


def main():
    
    #with open('resultsJPA_final_thesis.csv', 'w+', newline='') as file:
    #    writer = csv.writer(file)
    #    writer.writerow(["Project", "Result"])
        #writer.writerow([1, "Linus Torvalds", "Linux Kernel"])
       
    
        #refactoring_monolith("/home/fracisco/Desktop/MicroRefact/514840279__danyuan-application","/home/fracisco/Desktop/Backups/microservice-identification/micro_candidate/project_514840279__danyuan-application.json")
        #candidate_microservices("/home/fracisco/Desktop/MiguelProjects/invoice",writer)
        #download("miansen/Roothub")
        #runTests("resultsJPA.csv",writer) 
        #print(results)
     with open('resultsJPA_final_thesis.csv') as csv_file:
        csv_reader = csv.DictReader(csv_file)
        line_count = 0
        for row in list(csv_reader):
            os.chdir(r"/home/fracisco/Desktop/MicroRefact")
            if line_count == 0:
                print(f'Column names are {", ".join(row)}')
                line_count += 1
            print(f'{row["Project"]}.')
            refactoring_monolith(row["Project"], '/home/fracisco/Desktop/Backups/microservice-identification/micro_candidate/project_'+row["Project"].split("/")[-1]+".json")

main()    
