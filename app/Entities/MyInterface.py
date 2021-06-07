import os

'''
args:
name
Lista de metodos:
    metodo : name, returnType, lista de parametros
        parametros : type e variavel
'''

class MyInterface:
    def __init__(self,modifier,name,methods):
        self.modifier = modifier
        self.name = name
        self.methods = methods

    def getName(self):
        return self.name 

    def getMethods(self):
        return self.methods    


    def create(self, pathToCreateFile):
        print(pathToCreateFile)
       
        try:
           os.mkdir(pathToCreateFile)

        except OSError:
            print ("Creation of the directory failed" )

        f = open(pathToCreateFile  +"/" +self.name +".java", "w")

        f.write(self.modifier+ " interface " + self.name + " {\n\n")
        for method in self.methods:
            f.write("   public " + method["returnDataType"][0] + " " + method["name"] + "(")
            if len(method["parametersDataType"]) > 0:
                for var in method["parametersDataType"][:-1]:
                    f.write(var["type"] + " " + var["variable"] +  ",")
            
                f.write(method["parametersDataType"][-1]["type"] + " " + method["parametersDataType"][-1]["variable"])        
            f.write(");\n")        
        f.write("}")
        f.close()

