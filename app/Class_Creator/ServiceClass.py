
from Entities.Class import Class
from Entities.MyMethod import MyMethod

class ServiceClass:
    
    @staticmethod
    
    def create(methods,repositoryClass,typeOf1,typeOfN,pk):
        
        name =  typeOfN + typeOf1 + "Service"
        service = Class("NEW." +name,name)

        anotations = ["@AllArgsConstructor","@Service"]
        imports = ["lombok.AllArgsConstructor","org.springframework.stereotype.Service"]

        instance_variables = [{
            "annotations" : [],
            "modifier" : "private",
            "type" : repositoryClass,
            "variable" : repositoryClass.lower()
        }]

        service.setInstance_variables(instance_variables)
        service.setAnnotation(anotations)
        service.setImports(imports)

        for met in methods:
            method_name = met["name"]
            method_returnType = met["returnDataType"][0]
            method_parameters = met["parametersDataType"]
            method_parameters.insert(0,{"type" : pk[0],"variable" : pk[1]})  

            calRepo = "{\n"
            if method_returnType != "void":
                calRepo = calRepo + "return "

            body = []
            callRepo = calRepo + "%s.%s(" %(repositoryClass.lower(),met["name"])
            for param in method_parameters[:-1]:
                callRepo = callRepo + "%s," %(param["variable"])
            callRepo = callRepo + "%s);\n}" %(method_parameters[-1]["variable"])    
            
            body.append(callRepo)
            m = MyMethod(method_name,method_returnType,method_parameters,body)
            service.addMyMethods(m)


        


        return service