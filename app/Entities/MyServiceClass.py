class MyServiceClass:
    def __init__(self,name,anotations=[],instanceVariables="",methods=[]):
        self.name = name
        self.annotations = anotations
        self.instance_variables = instanceVariables
        self.methods = methods

    def getName(self):
        return self.name        


    def getAnotations(self):
        return self.anotations       

    def getInstanceVariables(self):
        return self.instance_variables        

    def getMethods(self):
        return self.methods

    def writeClasse(self,path):
        f = open( path + "/"+ self.name +"Service.java","w")
       
        for anotation in self.annotations:
            f.write("%s \n"%(anotation))
    
        f.write("public class %sService {\n\n" %(self.name))

        var = self.instance_variables.split(".")[-1]
        f.write("private %s %s;\n\n" %(var,var.lower()))
        
       
        for met in self.methods:
            f.write("%s %s %s(" %(met.getModifier(),met.getReturnType(),met.getName()))
            if "@PutMapping" in met.getAnnotations():
                f.write("%s %s , %s %s){\n " %(met.getParameters()[0]["type"],met.getParameters()[0]["variable"],
                "Object",met.getParameters()[1]["variable"]))
                # parte de chamar o metodo no repo
                f.write("%s.%s(" %(var.lower(),met.getName()))
                f.write("%s,%s);\n" %(met.getParameters()[0]["variable"],met.getParameters()[1]["variable"]))
                

            else:
                for param in met.getParameters()[:-1]:
                    f.write("%s %s," %(param["type"],param["variable"]))
                f.write("%s %s){\n" %(met.getParameters()[-1]["type"],met.getParameters()[-1]["variable"]))
                # parte de chamar o metodo no repo
                f.write("return %s.%s(" %(var.lower(),met.getName()))
                for param in met.getParameters()[:-1]:
                    f.write("%s,"%(param["variable"]))
                f.write("%s);\n"%(met.getParameters()[-1]["variable"]))
            

            f.write("}\n")        

        f.write("}\n")  
        f.close()
     
    