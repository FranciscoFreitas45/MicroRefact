import os
from shutil import copyfile
import re
import Utils as Utils


class Class:
    def __init__(self,full_name,short_name,begin=0,end=0,annotations = [],dependencies = [],methods = [],instance_variables=[], implements = [],extends = [], imports = []): #pathToDirectory):
        self.full_name = full_name
        self.begin = begin
        self.end = end
        self.short_name = short_name
        self.src = full_name
        self.annotations = annotations
        self.dependencies = dependencies
        self.instance_variables = instance_variables
        self.methods = list()
        self.implements = implements = list()
        self.extends = extends
        self.imports = imports
        self.methodsInvocations = []
        self.isInterface = False
        self.myInterfaces = list()
        self.myMethods = list()
        self.classDrag = list()
       
       

    def getFull_Name(self):
        return self.full_name

    def setFull_Name(self,full_name):
        self.full_name = full_name

    def setBegin(self,begin):
        self.begin = begin
    
    def setEnd(self,end):
        self.end = end    

    def getShort_Name(self):
        return self.short_name

    def setShort_Name(self,short_name):
        self.short_name = short_name

    def getAnnotations(self):
        return self.annotations
    
    def setAnnotation(self,annotations):
        self.annotations = annotations

    def getDependencies(self):
        return self.dependencies 

    def setDependecies(self,dependencies):
        self.dependencies = dependencies

    def addDependencie(self,dependencie):  
        self.dependencies.append(dependencie)   
    
    def getInstance_variable(self):
        return self.instance_variables

    def setInstance_variables(self,instance_variables):
        self.instance_variables = instance_variables
    
    def addInstance_Variable(self,instance_variable):
        self.instance_variables.append(instance_variable)

    def delInstance_Variable(self,instance_variable):
        self.instance_variables.remove(instance_variable)    


    def getMethods(self):
        return self.methods

    def setMethods(self,methods):
        self.methods = methods

    def addMethods(self,method):
        self.methods.append(method)    
    
    def getImplements(self):
        return self.implements

    def setImplements(self,implements):
        self.implements = implements
    
    def getExtends(self):
        return self.extends

    def setExtends(self,extends):
        self.extends = extends 

    def getMyInterfaces(self):
        return self.myInterfaces    
    
    def setMyInterfaces(self,interfaces):
        self.myInterfaces = interfaces 


    def addMyInterfaces(self,interface):
        self.myInterfaces.append(interface)    


    def getImports(self):
        return self.imports

    def setImports(self,imports):
        self.imports = imports 

    def getMethodsInvocations(self):
        return self.methodsInvocations
    
    def setMethodsInvocations(self,methodsInvo):
        self.methodsInvocations = methodsInvo     

    def setIsInterface(self,isInterface):
        self.isInterface = isInterface    

    def getMyMethods(self):
        return self.myMethods

    def addMyMethods(self,method):
        self.myMethods.append(method)            
    
    def removeMyMethod(self,method):
         self.myMethods.remove(method)

    def getClassDrag(self):
        return self.classDrag

    def addClassDrag(self,classe):
        self.classDrag.append(classe)
    
    def primaryKeyVariableType (self,cluster):

        if any(re.match("^@PrimaryKeyJoinColumn",line) for line in self.annotations):
            print("kkkkk")
            return cluster.getClasses()[self.extends[1]].primaryKeyVariableType(cluster)

        for variable in self.instance_variables:
            anotations = variable["annotations"]
            if "@Id" in  anotations:
                return variable["type"] , variable["variable"]    
        return None

    def findMyMethod(self,name):
        for mymethod in self.myMethods:
            if mymethod.getName() == name:
                return mymethod
        return None        


    def write_Interfaces (self,pathToDirectory):

        for interface in self.myInterfaces:

            x = self.full_name.split(".")
            dst = pathToDirectory + "/" + "/".join(x[:-1])
            interface.create(dst) 

    def copy(self,pathToDirectory):
        if self.full_name == self.short_name:
            self.create(pathToDirectory)
        else:    
            dst = pathToDirectory + "/"+ self.full_name.replace(".","/")
                 
            Utils.copyFile(self.src,dst)
            self.src = dst

         


            '''
            for met in self.methods_to_add_db:
                for anot in met.getAnnotations():
                    string_list.insert(index-1,anot) 
                    index = index+1

                string_list.insert(index-1,met.getModifier() + " " + met.getReturnType() + " " + met.getName() + "(")
                
                for parameter in met.getParameters()[:-1]:
                    string_list[index -1] = string_list[index -1] + parameter["type"] + " " + parameter["variable"] +"," 
                string_list[index -1] = string_list[index -1] + met.getParameters()[-1]["type"] + " " + met.getParameters()[-1]["variable"] +")\n" 

                index = index+1
                
            f = open(dst+".java","w")
            new_file_contents = "".join(string_list)
            f.write(new_file_contents)
            '''
            f.close()           
            
 


    def create(self,pathToCreateFile):
       
        try:
            print(pathToCreateFile + "/" + "/".join(self.full_name.split(".")[:-1]))
            #os.mkdir(pathToCreateFile)
            os.makedirs(pathToCreateFile + "/" + "/".join(self.full_name.split(".")[:-1]))


        except OSError:
            print ("Creation of the directory failed" )


        f = open(pathToCreateFile + "/" +"/".join(self.full_name.split(".")) +".java", "w")

        if len(self.imports) > 0 :
            for imp in self.imports:
                f.write("import " + imp + ";\n" )
        if len(self.annotations) > 0 :
            for anot in self.annotations:
                f.write(anot + "\n")
        if self.isInterface:
            f.write("public interface %s " %(self.short_name))
        else:    
            f.write("public class %s " %(self.short_name))
        
        if len(self.extends) > 0:
            f.write("extends %s " %(self.extends[0]))   

        if len(self.implements) > 0:
            f.write("implements ")
            for implement in self.implements[:-1]:
                f.write("%s,"%(implement))
            f.write("%s"%(self.implements[-1]))              
        f.write("{\n\n")     
                
        for var in self.instance_variables:
            for anot in var["annotations"]:
                f.write(anot + "\n")
            f.write(" %s %s %s;\n\n" %(var["modifier"],var["type"],var["variable"]))

        for met in self.myMethods:
            f.write("\n" +met.create() + "\n")
        f.write("\n}")    
        f.close()

       

    def myInformation(self):
        print(self.full_name)
        if len(self.methods_to_add_db) > 0:
            print("metodos to add " + str(self.methods_to_add_db))
        if len(self.methods_to_remove_db) > 0:
            print("metodos to remove " + str(self.methods_to_remove_db))    
        if len(self.methods_to_change_db) > 0:
            print("metodos to change " + str(self.methods_to_change_db))
        
        
        print("My full name is: " + self.full_name)
        print("My short name is: " + self.short_name)
        print("My Variables " + str(self.instance_variables))
        '''
        print("My Annotations is: " + str(self.annotations))
        print("My Implements " + str(self.implements ))
        print("My Extends " + str(self.extends))
        print("My Dependencies " + str(self.dependencies))
        print("My Interfaces " + str(self.myInterfaces))
       
        print("My methods " + str(self.methods))
        print("My Methods to remove because DB " + str(self.methods_to_remove_db) )
        print("My Instance Variables to remove because DB " + str(self.instance_variables_to_remove_db) )
        '''
        print("\n\n")
        #print("My path to my microservice Folder " + self.pathToDirectory)

   