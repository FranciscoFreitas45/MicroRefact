
import copy

from Entities.Class import Class
from Entities.MyMethod import MyMethod
import Utils as Utils

class ControllerClass:
    
    @staticmethod  
    def create(methods,domainClass,typeOf1,typeOfN,pk,subDir):

        name =typeOfN + typeOf1 + "Controller"

        controller = Class( subDir +"NEW." +name,name)
        service = typeOfN +typeOf1

        instance_variables = [{
            "annotations" : ["@Autowired"],
            "modifier" : "private",
            "type" : service+"Service",
            "variable" : (service + "Service").lower()
        }]

        anotations = ["@RestController","@CrossOrigin"]
        imports = ["org.springframework.web.bind.annotation.*", "org.springframework.beans.factory.annotation.Autowired",domainClass]

        controller.setInstance_variables(instance_variables)
        controller.setAnnotation(anotations)
        controller.setImports(imports)
       
        for met in methods:
            body = []
            annotations = list()
            method_name = met["name"]
            route = "/%s/{id}/%s/%s" % (typeOf1,typeOfN,method_name)
            method_returnType = met["returnDataType"][0]
            method_parameters = met["parametersDataType"]
            callService = "{\n"

            if method_returnType == "void":
                annotations.append("@PutMapping")
                method_parameters[0]["type"] = "@RequestBody " + method_parameters[0]["type"]

            else:
                annotations.append("@GetMapping")
                callService = callService + "return "
                for mett in  method_parameters:
                    mett["type"] = "@RequestParam " + mett["type"]
                         

            
            method_parameters.insert(0,({"type" :"@PathVariable(name=\"id\") " + pk[0],"variable" : pk[1]}))

            callService = callService +(service + "Service").lower() +"."+method_name + "("
            for param in method_parameters[:-1]:
                callService = callService + "%s," %(param["variable"])
            callService = callService + "%s);\n}" %(method_parameters[-1]["variable"])        

            body.append(callService)

            m = MyMethod(method_name,method_returnType,method_parameters,[],body,annotations,route)
            controller.addMyMethods(m)
        
        return controller


    @staticmethod

    def createClass_Controller(interface,subDir):

        name =interface.getFull_Name().split(".")[-1]  + "Controller"

        controller = Class(subDir +"NEWInstance." + name,name)
        #service = typeOfN +typeOf1
        variable = interface.getFull_Name().split(".")[-1]
        
        instance_variables = [{
            "annotations" : [],
            "modifier" : "private",
            "type" : variable,
            "variable" : variable.lower()
        }]
        

        anotations = ["@RestController","@CrossOrigin"]
        imports = ["org.springframework.web.bind.annotation.*"]

        controller.setInstance_variables(instance_variables)
        controller.setAnnotation(anotations)
        controller.setImports(imports)

        
        
        for met in  interface.getMethods():
            body = []
            annotations = list()
            method_name = met["name"]
            route = "/%s" % (method_name)
            method_returnType = met["returnDataType"][0]
            method_parameters = copy.deepcopy(met["parametersDataType"])
                    
            for mett in  method_parameters:
                mett["type"] = "@RequestParam(name = \"%s\") "%(mett["variable"]) + mett["type"]     

            if method_returnType == "void": 
                annotations.append("@PutMapping")
                callService = "{\n" + variable.lower() +"."+method_name + "("
                    
            else:
                annotations.append("@GetMapping")
                callService = "{\n  return " + variable.lower() +"."+method_name + "("

            if len(method_parameters) > 0:    
                for param in method_parameters[:-1]:
                    callService = callService + "%s," %(param["variable"])
                callService = callService + "%s" %(method_parameters[-1]["variable"])        
            callService = callService + ");\n}"

            body.append(callService)
            
            m = MyMethod(method_name,method_returnType,method_parameters,[],body,annotations,route)
            controller.addMyMethods(m)
        
        return controller

    def addMethod_to_Controller(controller,method):

        body = []
        annotations = list()
        method_name = method["name"]
        route = "/%s" % (method_name)
        method_returnType = method["returnDataType"][0]
        method_parameters = copy.deepcopy(method["parametersDataType"])
        variable = controller.getInstance_variable()[0]["type"]            
        for mett in  method_parameters:
            mett["type"] = "@RequestParam(name = \"%s\") "%(mett["variable"]) + mett["type"]     

        if method_returnType == "void": 
            annotations.append("@PutMapping")
            callService = "{\n" + variable.lower() +"."+method_name + "("
                    
        else:
            annotations.append("@GetMapping")
            callService = "{\n  return " + variable.lower() +"."+method_name + "("

        if len(method_parameters) > 0:    
            for param in method_parameters[:-1]:
                callService = callService + "%s," %(param["variable"])
            callService = callService + "%s" %(method_parameters[-1]["variable"])        
        callService = callService + ");\n}"

        body.append(callService)
            
        m = MyMethod(method_name,method_returnType,method_parameters,[],body,annotations,route)
        controller.addMyMethods(m)        




    @staticmethod

    def createVARrequestController(classe,variable,method,targetClassName,cluster,subDir,ADDNEWCLASSE=True):
        
        print(")))) " +str(method))
        #print(variable)
        print("targetclasse " + targetClassName)
        instance_variables = {
            "annotations" : [],
            "modifier" : "private",
            "type" : variable,
            "variable" : variable.lower()
            }
        controller = classe
        
        if ADDNEWCLASSE:
            name = classe + "Controller"
            controller = Class(subDir +"NEWInstance." + name,name)

            

            anotations = ["@RestController","@CrossOrigin"]
            imports = ["org.springframework.web.bind.annotation.*"]

            controller.setInstance_variables([instance_variables])
            controller.setAnnotation(anotations)
            controller.setImports(imports)
            
        body = []
        annotations = list()
        method_name = method[0].getName()
        route = "/%s" % (method_name)
        method_returnType = method[0].getReturnType()
        method_parameters = copy.deepcopy(method[0].getParameters())

        repo = None
        for mett in  method_parameters:
            mett["type"] = "@RequestParam(name = \"%s\") "%(mett["variable"]) + mett["type"]     
        
                                   

        if method_returnType == "void": 
            annotations.append("@PutMapping")
            callService = "{\n" + variable.lower() +"."+method_name + "("
                    
        else:
            annotations.append("@GetMapping")
            callService = "{\n  return " + variable.lower() +"."+method_name + "("

        if method[1] != None:
            method_parameters.insert(0,{"type": "@PathVariable(name = \"id\") " + method[1][0] ,
                                        "variable" : "id" })
            route = route + "/{id}"
            print(" fffff " + targetClassName)                            
            repo = Utils.find_repositoryClass(targetClassName,cluster)
            inst= {
                "annotations" : [],
                "modifier" : "private",
                "type" : repo[0].getShort_Name(),
                "variable" : repo[0].getShort_Name().lower()
            }

            if ADDNEWCLASSE:
                controller.setInstance_variables([inst])
             
            if method_returnType == "void": 
                callService = "{\n " + repo[0].getShort_Name().lower() +"."+method_name + "("
            else:
                 callService = "{\n return " + repo[0].getShort_Name().lower() +"."+method_name + "("
        elif ADDNEWCLASSE:
            controller.addInstance_Variable(instance_variables)


        if len(method_parameters) > 0:    
            for param in method_parameters[:-1]:
                callService = callService + "%s," %(param["variable"])
            callService = callService + "%s" %(method_parameters[-1]["variable"])        
        
        callService = callService + ");\n}"

        body.append(callService)
            
        m = MyMethod(method_name,method_returnType,method_parameters,[],body,annotations,route)
        controller.addMyMethods(m)

        if repo!= None: # criar metodo na repository class
            parameters = []
            parameters.append({
                "type": method[1][0],
                "variable": "id"
            })

            for meth in copy.deepcopy(method[0].getParameters()):
                parameters.append({
                    "type" : meth["type"],
                    "variable" : meth["variable"]

                }) 
            met = MyMethod(method_name, method_returnType, parameters,[])
            repo[0].addMyMethods(met)
        return controller , repo    

