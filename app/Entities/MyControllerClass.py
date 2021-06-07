
class MyControllerClass:

    def __init__(self,name,anotations=[],instanceVariables=[],methods=[]):
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

        f = open( path + "/"+ self.name +"Controller.java","w")
       
        for anotation in self.annotations:
            f.write("%s \n"%(anotation))
        
        f.write("public class %sController {\n\n" %(self.name))

        for variable in self.instance_variables:
            f.write("private %sService %sService;\n " %(variable["type"],variable["variable"]))

        f.write("\n")

        for met in  self.methods:
            f.write("%s" %(met.getAnnotations()[0]))        
            f.write("(\"%s\")\n" %(met.getRoute()))
   
            f.write("%s %s %s(" %(met.getModifier(),met.getReturnType(),met.getName())) 
            

            if "@PutMapping" in  met.getAnnotations():
                f.write("@PathVariable(\"id\") %s %s, @RequestBody %s %s){\n"
                %(met.getParameters()[0]["type"],met.getParameters()[0]["variable"],
                "Object",met.getParameters()[1]["variable"]))
                f.write("%sService.%s(" %(self.instance_variables[0]["variable"],met.getName()))

                f.write("%s,%s);" %(met.getParameters()[0]["variable"],met.getParameters()[1]["variable"]))

            else:
                for param in met.getParameters()[:-1]:
                    f.write("@PathVariable(\"%s\") %s %s," %(param["variable"],param["type"],param["variable"]))
                f.write("@PathVariable(\"%s\") %s %s){\n" %(met.getParameters()[-1]["variable"],met.getParameters()[-1]["type"],met.getParameters()[-1]["variable"]))
                f.write("return %sService.%s(" %(self.instance_variables[0]["variable"],met.getName()))
                
                for param in met.getParameters()[:-1]:
                    f.write("%s,"%(param["variable"]))
                f.write("%s);"%(met.getParameters()[-1]["variable"]))



            f.write("\n\n")
            f.write("}\n")
        f.write("}\n")    
        f.close()


