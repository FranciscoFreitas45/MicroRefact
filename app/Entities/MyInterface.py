import os

'''
args:
name
Lista de metodos:
    metodo : name, returnType, lista de parametros
        parametros : type e variavel
'''

class MyInterface:
    def __init__(self,modifier,name,methods,imports=[]):
        self.modifier = modifier
        self.name = name
        self.short_name = name.split(".")[-1]
        self.methods = methods
        self.imports = imports

    def getFull_Name(self):
        return self.name 

    def getMethods(self):
        return self.methods    

    def getShort_Name(self):
        return self.short_name

    def create(self, pathToCreateFile):
        #print(pathToCreateFile)
       
        try:
            os.makedirs(pathToCreateFile + "/" + "/".join(self.name.split(".")[:-1]))

        except OSError:
            print ("Creation of the directory failed" )

        f = open(pathToCreateFile + "/" +"/".join(self.name.split(".")) +".java", "w")
        f.write("package %s;\n"%(".".join(self.name.split(".")[:-1])))
        for imp in self.imports:
            f.write("import %s;\n" %(imp))
        f.write(self.modifier+ " interface " + self.short_name + " {\n\n")
        for method in self.methods:
            f.write("   public " + method["returnDataType"][0] + " " + method["name"] + "(")
            if len(method["parametersDataType"]) > 0:
                for var in method["parametersDataType"][:-1]:
                    f.write(var["type"] + " " + var["variable"] +  ",")
            
                f.write(method["parametersDataType"][-1]["type"] + " " + method["parametersDataType"][-1]["variable"])        
            f.write(");\n")        
        f.write("}")
        f.close()

    def addMethods(self,methods):
        for met in methods:
            if met not in self.methods:
               self.methods.append(met)

       