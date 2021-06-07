import os


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
    
def createFolderForMicroservice (nameOfProject, nameOfMicroservice):


    pathToMicroservice = nameOfProject + "/" + nameOfMicroservice

    try:
        os.mkdir(pathToMicroservice)
    except OSError:
        print ("Creation of the directory %s failed" % pathToMicroservice)
    else:
        print ("Successfully created the directory %s " % pathToMicroservice)

    return pathToMicroservice
