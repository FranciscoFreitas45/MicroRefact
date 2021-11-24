from Entities.Class import Class
from Entities.MyMethod import MyMethod
import re


import Utils as Utils

from Entities.MyInterface import MyInterface



class Cluster:
    def __init__(self,pathToDirectory,extra=False):
        self.classes = dict()
        self.newClasses = list()
        self.pathToDirectory = pathToDirectory
        self.extra = extra
        self.subDirectories = None


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

    def getSubDirectories(self):
        return self.subDirectories
    
    def setSubDirectories(self,directories):
        self.subDirectories = directories


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
            if isinstance(classe,MyInterface):
                classe.create(self.pathToDirectory)
            else:    
                classe.create(self.pathToDirectory)

            if not isinstance(classe,MyInterface):
                for dragClass in classe.getClassDrag():
                    if not isinstance(dragClass, MyInterface):
                        puml.write("Class %s {\n}\n"%(self.pathToDirectory.split("/")[-1] + "."+ "/".join(dragClass.getFull_Name().split("."))))           
                        dragClass.create(self.pathToDirectory)

    def write_Main(self):

        c = Class(self.subDirectories +"Main", "Main")
        imports = ["org.springframework.boot.SpringApplication","org.springframework.context.annotation.Bean","org.springframework.boot.autoconfigure.SpringBootApplication","org.springframework.web.client.RestTemplate"]
        c.setAnnotation(["@SpringBootApplication"])
        
        m = MyMethod("restTemplate", "RestTemplate", [],[],["{\n "," return new RestTemplate();\n","  }\n"],["@Bean"])
        c.addMyMethods(m)

        main = MyMethod("main", "void", [{"type": "String[]","variable":"args"}],[],["{\n","SpringApplication.run(Main.class,args);\n","   }\n"],[],"","public static")
        c.addMyMethods(main)
        

        for classe in self.newClasses:
            
            if (re.search('Interface.',classe.getFull_Name()) and  not re.search("Impl$",classe.getFull_Name() )):
                imports.append(classe.getFull_Name())
                imports.append(classe.getFull_Name() + "Impl")

                body = ["{\n","return  new %s(); "%(classe.getShort_Name() + "Impl"),"    }\n"]
                
                c.addMyMethods(MyMethod(classe.getShort_Name().lower(), classe.getShort_Name(),[],[],body,["@Bean"]))

        
        c.setImports(imports)

        self.newClasses.append(c)




        
        
        '''

        public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
        f.write("   @Bean\n")
        f.write("   public RestTemplate restTemplate(){\n")
        f.write("       return new RestTemplate();\n    }\n")

        for classe in self.newClasses:
            if(re.search('Interface.(.)+Impl',classe.getFull_Name())):
                f.write("   @Bean\n")
                f.write("public %s %s ")
                f.write("%s\n"%(classe.getFull_Name()))

        f.write("    public static void main(String[] args) {Main.run(Main.class, args);}\n")

        f.write("}")
        f.close()
        '''
        