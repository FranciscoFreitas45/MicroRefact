from shutil import copyfile
import os

#path = os.path.join(parent_dir, directory)
from Settings import Settings

def copyFile(classNameSrc, dst):
    try:
        x = dst.split("/")
        print(x)

        os.makedirs("/".join(x[:-1]))
    except OSError as error:  
        print(error)        
    srcOfclass = classNameSrc.replace(".","/")
    print( Settings.PROJECT_PATH + srcOfclass )
    copyfile(Settings.PROJECT_PATH+'/'+srcOfclass+".java", dst + ".java")
    print("File Copy")



def lastWordOfString (word):

    words = word.split(".")

    return (words[-1])


def find_Cluster_with_Name_Class(class_name,Clusters):
    c =-1
    for  i, cluster in  enumerate (Clusters):
        if class_name in cluster.getClasses():
            return i

    return c
