import os
from shutil import copyfile
import re
import Utils as Utils
from Entities.MyMethod import MyMethod



class Class:
    def __init__(self,full_name,short_name,constructor=[],begin=0,end=0,annotations = [],dependencies = [],methods = [],instance_variables=[], implements = [],extends = [], imports = []): #pathToDirectory):
        self.full_name = full_name
        self.constructor = constructor
        self.package = ".".join(full_name.split(".")[:-1])
        self.begin = begin
        self.end = end
        self.short_name = short_name
        self.src = full_name
        self.annotations = list()
        self.dependencies = dependencies
        self.instance_variables = list()
        self.methods = list()
        self.implements = implements = list()
        self.extends = extends
        self.imports = imports
        self.methodsInvocations = []
        self.isInterface = False
        self.myInterfaces = list()
        self.myMethods = list()
        self.classGlue = list()
        self.classDrag = list()
        self.isOriginal = True
        
       
       

    def getFull_Name(self):
        return self.full_name

    def setFull_Name(self,full_name):
        self.full_name = full_name
        self.package = ".".join(full_name.split(".")[:-1])

    def getConstructor(self):
        return self.constructor

    def setConstructor(self,constructor):
        self.constructor = constructor 

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

    def addImports(self,impor):
        self.imports.append(impor)    

    def getMethodsInvocations(self):
        return self.methodsInvocations
    
    def setMethodsInvocations(self,methodsInvo):
        self.methodsInvocations = methodsInvo     

    def setIsInterface(self,isInterface):
        self.isInterface = isInterface    
    
    def getIsInterface(self):
        return self.isInterface

    def getMyMethods(self):
        return self.myMethods

    def setMyMethods(self,mymethods):
        self.myMethods = mymethods    

    def addMyMethods(self,method):
        self.myMethods.append(method)            
    
    def removeMyMethod(self,method): 
        self.myMethods.remove(method)
      

    def getClassGlue(self):
        return self.classGlue

    def addClassGlue(self,classe):
        self.classGlue.append(classe)

    def getClassDrag(self):
        return self.classDrag

    def addClassDrag(self,classe):
        self.classDrag.append(classe)
    
    def getIsOriginal(self):
        return self.isOriginal
    
    def setIsOriginal(self, value):
        self.isOriginal = value

    
    def primaryKeyVariableType (self,cluster):
        print(self.full_name)
        if any(re.match("^@PrimaryKeyJoinColumn",line) for line in self.annotations):
            print("kkkkk")
            print(self.extends)
            print(cluster.getPathToDirectory())
            print(cluster.getClasses())
            if "." in  self.extends[0]:
                return cluster.getClasses()[self.extends[0]].primaryKeyVariableType(cluster)
            else:
                return cluster.getClasses()[self.extends[1]].primaryKeyVariableType(cluster)

        for variable in self.instance_variables:
            anotations = variable["annotations"]
            if "@Id" in  anotations:
                return variable["type"] , variable["variable"]
        
        for mymethod in self.myMethods:
             if "@Id" in mymethod.getAnnotations():
                print(mymethod.getName())
                print(mymethod.getBody())
                for variable in self.instance_variables:
                    for bb in mymethod.getBody():
                        if re.search("return this." + variable["variable"],bb) or re.search("return " + variable["variable"],bb) :
                            print("ENCONTROU E É " + variable["variable"] )
                            return variable["type"] , variable["variable"]

        for extend in self.extends:
            if "." in extend:
                return cluster.getClasses()[extend].primaryKeyVariableType(cluster)  
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

        f.write("package %s;\n "%(self.package))
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
        ext = []
        if len(self.extends) > 0:
            for extend in self.extends:
                if '.' not in extend:
                    ext.append(extend)
            if len(ext)>0:
                f.write("extends ")
                for ex in ext[:-1]:
                    f.write("%s, "%(ex))
                f.write("%s"%(ext[-1]))

        imp = []    
        if len(self.implements) > 0:
            for implements in self.implements:
                if '.' not in implements:
                    imp.append(implements)
            if len(imp) > 0:
                f.write("implements ")
                for implement in imp[:-1]:
                    f.write("%s,"%(implement))
                f.write("%s"%(imp[-1]))              
        f.write("{\n\n")  
                
        for var in self.instance_variables:
            for anot in var["annotations"]:
                f.write(anot + "\n")
            f.write(" %s %s %s;\n\n" %(var["modifier"],var["type"],var["variable"]))

        for const in self.constructor:
            f.write(const)

        for met in self.myMethods:
            f.write("\n" +met.create())
            if self.isInterface:
                f.write(";")
            f.write("\n")        
        f.write("\n}")    
        f.close()

    def add_Methods_to_Class(self,methods):

        print("#######")
        for method in methods:
            exist = False
            for o_mets in self.myMethods:
                if method["name"] == o_mets.getName() and method["parametersDataType"] == o_mets.getParameters():
                    exist = True
            
            if not exist:
                method_name = method["name"]
                method_returnType = method["returnDataType"][0]
                method_parameters = method["parametersDataType"]
                body = []
                request = ""
                request = "{\n  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat(\"/%s\"))\n"%(method_name) 
        
                if len (method_parameters) > 0:
                    for param in method_parameters[:-1]:
                        request =request + "    .queryParam(\"%s\",%s)\n"%(param["variable"],param["variable"])
                    request =request + "    .queryParam(\"%s\",%s);\n"%(method_parameters[-1]["variable"],method_parameters[-1]["variable"])

                if method_returnType == "void": # sigifica que é um post ou put)
                    request = request + "\n  restTemplate.put(builder.toUriString(), null);\n}"
                    body.append(request)
                else:                         
                    request =request + "  %s aux = restTemplate.getForObject(builder.toUriString(), %s.class);\n"%(method_returnType,method_returnType)
                    body.append(request)
                    returnStatement = " return aux;\n}"
                    body.append(returnStatement)        
            
                m = MyMethod(method_name,method_returnType,method_parameters,[],body)
                self.myMethods.append(m)            
                   



    def myInformation(self):
        print(self.full_name)
        
        print("My full name is: " + self.full_name)
        print("My short name is: " + self.short_name)
        print("My Variables " + str(self.instance_variables))
        
        print("My Annotations is: " + str(self.annotations))
        print("My Implements " + str(self.implements ))
        print("My Extends " + str(self.extends))
        print("My Dependencies " + str(self.dependencies))
        print("My Interfaces " + str(self.myInterfaces))
       
        print("My methods " + str(self.myMethods))
       
        print("\n\n")
        #print("My path to my microservice Folder " + self.pathToDirectory)

   