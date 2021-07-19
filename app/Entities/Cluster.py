from Entities.Class import Class

import Utils as Utils

from Entities.MyInterface import MyInterface



class Cluster:
    def __init__(self,pathToDirectory,extra=False):
        self.classes = dict()
        self.newClasses = list()
        self.pathToDirectory = pathToDirectory
        self.extra = extra


    def getClasses(self):
        return self.classes

    def getPathToDirectory(self):
        return self.pathToDirectory       

    def setClass(self,Class):
        if isinstance(Class,MyInterface):
            self.newClasses.append(Class)
        else:
            self.classes[Class.getFull_Name()] = Class

    def getNewClasses(self):
        return self.newClasses

    def setNewClasses(self,classes):
        self.newClasses = classes

    def addNewClasses(self,classe):
        self.newClasses.append(classe)

    def removeNewClasses(self,classe):
        self.newClasses.remove(classe)    

    def deleteClasse(self,classeName):
        self.classes.pop(classeName)

    def getIsExtra(self):
        return self.extra        

    def printName(self):
        print([x for  x in self.classes ])

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
            puml.write("Class %s {\n}\n"%(self.pathToDirectory.split("/")[-1] + "." +classe.getFull_Name().split(".")[-1])) 
        for classe in self.newClasses:
            puml.write("Class %s {\n}\n"%(self.pathToDirectory.split("/")[-1] + "."+ "/".join(classe.getFull_Name().split("."))))
            classe.create(self.pathToDirectory)

            if not isinstance(classe,MyInterface):
                for dragClass in classe.getClassDrag():
                    if not isinstance(dragClass, MyInterface):
                        puml.write("Class %s {\n}\n"%(self.pathToDirectory.split("/")[-1] + "."+ "/".join(dragClass.getFull_Name().split("."))))           
                        dragClass.create(self.pathToDirectory)
