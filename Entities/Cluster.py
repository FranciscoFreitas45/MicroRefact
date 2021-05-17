from Entities.Class import Class

import Utils as Utils



class Cluster:
    def __init__(self,pathToDirectory):
        self.classes = dict()
        self.newClasses = list()
        self.pathToDirectory = pathToDirectory


    def getClasses(self):
        return self.classes

    def getPathToDirectory(self):
        return self.pathToDirectory       

    def setClass(self,Class):
        self.classes[Class.getFull_Name()] = Class

    def getNewClasses(self):
        return self.newClasses

    def setNewClasses(self,classes):
        self.newClasses = classes

    def addNewClasses(self,classe):
        self.newClasses.append(classe)

    def deleteClasse(self,classeName):
        self.classes.pop(classeName)   

    def printInformation(self):

        print ("CLUSTER OF PATH " + self.pathToDirectory)
        for classe in self.classes.values():
            classe.myInformation()    

    def write_Interfaces(self):
        for classe in self.classes.values():
            classe.write_Interfaces(self.pathToDirectory)            

    def write_classes(self,puml):
        for classe in self.classes.values():
            classe.create(self.pathToDirectory)
            puml.write("Class %s {\n}\n"%(".".join(self.pathToDirectory.split("/")) + "."+ classe.getFull_Name())) 
        for classe in self.newClasses:
            puml.write("Class %s {\n}\n"%(".".join(self.pathToDirectory.split("/")) + "." +classe.getFull_Name()))
            classe.create(self.pathToDirectory) 
            
