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
        print(classe.getExtends())
        for extend in classe.getExtends():
            '''nameOfClasse in classe.getImports() and '''
            if (re.search("^JpaRepository<"+typeof,extend) or 
                re.search("^PagingAndSortingRepository<"+typeof,extend) or 
                re.search("^CrudRepository<"+typeof,extend) or 
                re.search("^Repository<"+typeof,extend)):
                return classe, classe.getFull_Name()
            elif re.search("<"+typeof,extend): #   BaseRepository<Attention, String>  ou GenericDao<Article,
                    if find_repositoryClassExtraWork(extend.split("<")[0],Cluster):
                        return classe, classe.getFull_Name()
                     
        if  nameOfClasse in classe.getImports() and any(re.match("^@Repository",line) for line in classe.getAnnotations()) :  
            return classe, classe.getFull_Name()

    return ""

def find_repositoryClassExtraWork(nameOfClasse,Cluster): # o nome da classe nao está completo. É só o nome pela qual chamamos a class Ex : BaseRepository
    classes = Cluster.getClasses()
    for classe in classes.values():
        if re.search("\."+nameOfClasse + "$",classe.getFull_Name()):
            for extend in classe.getExtends():

                if re.search("^JpaSpecificationExecutor<",extend) or re.search("^JpaRepository<",extend) :
                    return True
            
    return False



def execute_parser(project_path):
    symbol_solver_path = f"{Settings.DIRECTORY}/javaParser/target/"

    command = f"java -cp symbolsolver-1.0.jar Main {Settings.PROJECT_PATH}"

    #print(command)
    #print(f"Invoking parsing: {command}")
    subprocess.call(command, cwd=symbol_solver_path, shell=True)


