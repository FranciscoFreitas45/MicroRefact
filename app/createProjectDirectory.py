import os
from shutil import copyfile


from Settings  import Settings

path = os.getcwd()

def createFolderForProject ():
    pathToProject = path+"/Results/" + Settings.PROJECT_NAME 
    try:
        os.makedirs(pathToProject)
    except OSError:
        print ("Creation of the directory %s failed" % pathToProject)
    else:
        print ("Successfully created the directory %s " % pathToProject)
    
    return pathToProject
    
def createFolderForMicroservice (nameOfProject, nameOfMicroservice,OriginalPathOfProject):


    pathToMicroservice = nameOfProject + "/" + nameOfMicroservice + "/src/main/java"

    try:
        os.makedirs(pathToMicroservice)
    except OSError:
        print ("Creation of the directory %s failed" % pathToMicroservice)
    else:
        print ("Successfully created the directory %s " % pathToMicroservice)
        copyfile(OriginalPathOfProject + "/pom.xml", nameOfProject + "/" + nameOfMicroservice + "/pom.xml")


    return pathToMicroservice
