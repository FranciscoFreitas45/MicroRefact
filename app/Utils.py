from shutil import copyfile
import os
import subprocess
import re


#path = os.path.join(parent_dir, directory)
from Settings import Settings

def copyFile(classNameSrc, dst):
    try:
        x = dst.split("/")
        #print(x)

        os.makedirs("/".join(x[:-1]))
    except OSError as error:  
        print(error)        
    srcOfclass = classNameSrc.replace(".","/")
    #print( Settings.PROJECT_PATH + srcOfclass )
    copyfile(Settings.PROJECT_PATH+'/'+srcOfclass+".java", dst + ".java")
    #print("File Copy")



def lastWordOfString (word):

    words = word.split(".")

    return (words[-1])


def find_Cluster_with_Name_Class(class_name,Clusters):
    c =-1
    for  i, cluster in  enumerate (Clusters):
        for key, value in cluster.getClasses().items():
            if class_name == key and value.getIsOriginal():
                return i

    return c

'''
 Dado uma classe entidade procurar qual é classe repository que a representa
 '''

def find_repositoryClass(nameOfClasse,Cluster):
    classes = Cluster.getClasses()
    print(nameOfClasse)
    typeof = lastWordOfString(nameOfClasse)
    #print("INSIDE OF UTILS")
    for classe in classes.values():
        print("..... " + classe.getFull_Name())
        for extend in classe.getExtends():
            if nameOfClasse in classe.getImports() and (re.search("^JpaRepository<"+typeof,extend) or re.search("^PagingAndSortingRepository<"+typeof,extend) or re.search("^CrudRepository<"+typeof,extend) or re.search("^Repository<"+typeof,extend) ):
        #if nameOfClasse in classe.getImports() and ("@Repository" in classe.getAnnotations() or ("org.springframework.data.jpa.repository.JpaRepository" in classe.getImports() or 
        #    "org.springframework.data.repository.PagingAndSortingRepository" in classe.getImports())):    
                return classe, classe.getFull_Name()

    return ""


def execute_parser(project_path):
    symbol_solver_path = f"{Settings.DIRECTORY}/javaParser/target/"

    command = f"java -cp symbolsolver-1.0.jar Main {Settings.PROJECT_PATH}"

    #print(command)
    #print(f"Invoking parsing: {command}")
    subprocess.call(command, cwd=symbol_solver_path, shell=True)


