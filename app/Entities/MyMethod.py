
class MyMethod:

    def __init__(self,name,returnType,parameters,body=[],anotations =[],route = "",modifier="public"):
        self.name = name
        self.annotations = anotations
        self.route = route
        self.modifier = modifier
        self.returnType = returnType
        self.parameters = parameters
        self.body = body 
 
    def getName(self):
        return self.name

    def getAnnotations(self):
        return self.annotations    
    
    def getRoute(self):
        return self.route

    def getModifier(self):
        return self.modifier    
    
    def getReturnType(self):
        return self.returnType

    def getParameters(self):
        return self.parameters

    def getBody(self):
        return self.body

    def setBody(self,body):
        self.body = body
    
    def addToBody(self,body):
        self.body.append(body)    

        
    def create(self):
        if self.name == "getReservations":
            print("dddddddddddddddd " + str(self.body))
        method = ""
        if  len(self.annotations) > 0:
            for anot in self.annotations:
                method = method + anot + "\n" 

        if len(self.route) > 0:
            method = method + "(\"" + self.route + "\")\n"    

        method = method + self.modifier + " " + self.returnType + " " + self.name + "("
        parameters = ""

        if len(self.parameters) > 1:
            for param in self.parameters[:-1]:
                parameters = parameters + "%s %s,"%(param["type"],param["variable"])
            parameters = parameters + "%s %s" %(self.parameters[-1]["type"],self.parameters[-1]["variable"])
            parameters = parameters +")"
        else:
            if len(self.parameters) == 1 :
                parameters = parameters + "%s %s" %(self.parameters[0]["type"],self.parameters[0]["variable"])
            
            parameters = parameters +")"
 
        method = method + parameters 

        for statement in self.body:
            method = method + statement + "\n"
          
        return method      



    