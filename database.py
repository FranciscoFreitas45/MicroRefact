
'''
Annotations

@OneToOne 
@ManyToOne
@OneToMany
@ManyToMany
@PrimaryKeyJoinColumn
@JoinColumn
@JoinTable
@MapsId





'''

import re
import Utils as Utils
from Entities.MyControllerClass import MyControllerClass
from Entities.MyServiceClass import MyServiceClass
from Entities.MyMethod import MyMethod
from Entities.MyInterface import MyInterface
from Entities.Class import Class
from Entities.Cluster import Cluster
from Settings import Settings
import copy
import createProjectDirectory as createDirectory




class Database:



    @staticmethod
    def find_logic_schema(ast,Clusters):

        for cluster in Clusters:
            print(cluster.getPathToDirectory())
            #if  not re.search(r'\d+$',cluster.getPathToDirectory()): # verifica se é um cluster original
            #    print("dddd")
            #    continue  
            #  clusterCopy = copy.deepcopy(cluster) # REMEMBER ISTO É UMA COPIA LOGO O QUE É ESCRITO É REFERENTE A OUTRO OBJECTO
            for k,v in reversed(sorted(cluster.getClasses().items())): 
                classe_annotations =v.getAnnotations()
                if "@Entity" in classe_annotations:
                    instance_variables = ast[k]["instance_variables"]
                    for variable in instance_variables:
                        variable_annotations = variable["annotations"]
                        
                        #if "@OneToMany" in variable_annotations:
                        if any(re.match("^@OneToMany",line) for line in variable_annotations):

                            param=v.primaryKeyVariableType(cluster)
                            classes_to_add = Database.handle_OneToMany_Relationship(v,variable["identifier"][1],variable,cluster,Clusters,param)
                           
                            for cl in classes_to_add:
                             
                                cluster.addNewClasses(cl)
                        
                        elif  any(re.match("^@ManyToMany",line) for line in variable_annotations):
                            isJoin = Database.handle_ManyToMany_Relationship(v,variable["identifier"][1],variable,cluster,Clusters)
                            if isJoin:
                                break
                        
                        elif "OneToOne" in  variable_annotations:
                            print("ONETONE")
                            classes_to_add = Database.handle_ManyToOne_Relationship(v,variable["type"],variable,cluster,Clusters)
                            for cl in classes_to_add:
                             
                                cluster.addNewClasses(cl)
                        
                        elif any(re.match("^@ManyToOne",line) for line in variable_annotations):
                            print("MANYTOONE")
                            classes_to_add = Database.handle_ManyToOne_Relationship(v,variable["type"],variable,cluster,Clusters)
                           
                            for cl in classes_to_add:
                             
                                cluster.addNewClasses(cl)
                        
                           
        
                            


    @staticmethod
    def handle_OneToMany_Relationship(classe1, typeOfN,variable,cluster,Clusters,param):
        print("inside of oneToMany")
        
        classes_to_add_cluster = []

        completeType = variable["type"]
        '''
            Step 1: VERIFICAR SE PERTENCEM AO MESMO CLUSTER
        '''     
        dependencies = classe1.getDependencies()

        for dependency in dependencies:
            '''
            STEP 2: CASO PERTENÇAM NAO FAZER NADA, A RELAÇÃO PODE CONTINUAR  A EXISTIR PORQUE A BASE DE DADOS DO MICRO-SERVIÇO TEM AS DUAS ENTIDADES
            ''' 
            if(re.search("\."+typeOfN+'$',dependency)):
                '''
                STEP 3: CASO NAO PERTENCAM , PROCURAR QUAIS OS METODOS DA ENTIDADE QUE UTILIZAM A VARIAVEL (deverão ser os gets e sets)
                ''' 
                methods = []
                methods = Database.find_usages_methods(classe1.getMethods(),typeOfN,completeType)

                methodsAux= copy.deepcopy(methods)

                '''
                STEP 4: COPIAR PARA OS DTOS

                '''
                index = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                classDepend = copy.deepcopy(Clusters[index].getClasses()[dependency])

                name_depend = ".DTO." + classDepend.getFull_Name().split(".")[-1]
                
                classDepend.setFull_Name(name_depend)
                classDepend.setShort_Name(classDepend.getShort_Name())
                #apagar anotaçoes da classe
                classDepend.setAnnotation([])
                # apagar anotaçoes das variaveis
                for instance_variable in  classDepend.getInstance_variable():
                    instance_variable["annotations"] = []
                #apagar os metodos que nao sao gets
                myMethods = classDepend.getMyMethods().copy()

                for met in myMethods:
                    met_name = met.getName()
                    met_returnType = met.getReturnType()

                    if  not re.search("^get",met_name) or met_returnType == "void":
                        classDepend.removeMyMethod(met)


                classes_to_add_cluster.append(classDepend)

                  

                '''
                STEP 5: CRIAR INTERFACE E ClASSE QUE FAZ AS CALLS 
                ''' 
                
                interface=Database.createInterface(methodsAux,typeOfN,param)

                classRequest = Database.createClass_callInterface(interface,classe1.getShort_Name(),typeOfN,index)
                classRequest.addMyInterfaces(interface)
               
                classe1.addClassDrag(interface)
                classe1.addClassDrag(classRequest)
                classes_to_add_cluster.append(classRequest)
                interface.create(cluster.getPathToDirectory() + "/Request")
                
                 
                '''
                STEP 6 : Limpar classe (tirar anotaçao da variavel typeN, adicionar variavel de instancia , modificar os metodos de get e set)
                '''
                
                
                variable["annotations"] = ["@Transient"]
                classe1.addInstance_Variable({"annotations" : ["@Transient"],
                                                "modifier" : "private", 
                                                "type" : classRequest.getShort_Name(),
                                                "variable" : classRequest.getShort_Name().lower()})



                for met in methods:
                    for myMet in classe1.getMyMethods():
                        if met["name"] == myMet.getName():
                            if (met["returnDataType"][0] =="void"):
                                body = "{\n %s.%s("%(classRequest.getShort_Name().lower(),met["name"])
                            else:    
                                body = "{\n  this.%s = %s.%s("%(variable["variable"],classRequest.getShort_Name().lower(),met["name"])
                            
                            for parameter in met["parametersDataType"]:
                                body = body + "%s,"%(parameter["variable"])
                            body = body + "this.%s);\n"%(param[1])    

                            if (met["returnDataType"][0] !="void"):
                                body = body + "return this.%s;\n}"%(variable["variable"])     


                            myMet.setBody([body])
                #classes_to_add_cluster.append(classe1)

                '''
                STEP 7 :  DO LADO DO MICROSERVIÇO A QUE PERTENCE O TIPO DA VARIAVEL, CRIAR CONTROLLER E SERVICE PARA RECEBER OS PEDIDOS
        
                '''
                # procurar o cluster a que pertence a classe que nao necessita de modificações
                indexOfcluster = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                # é necessario criar um controller,service e ligar ao repository
                print("index of cluster " + str(indexOfcluster))
                print(dependency)
                repositoryClass,repositoryClassName = Database.find_repositoryClass(dependency ,Clusters[indexOfcluster])    

                classController = Database.createClass_Controller(copy.deepcopy(methods),classe1.getShort_Name(),typeOfN,param)
                classController.create(Clusters[indexOfcluster].getPathToDirectory())

                classService = Database.createClass_Service(copy.deepcopy(methods),repositoryClassName.split(".")[-1],classe1.getShort_Name(),typeOfN,param)
                classService.create(Clusters[indexOfcluster].getPathToDirectory())
                ###########################################################TRUNCAR AQUI

                '''
                STEP 8 : ADICIONAR AO REPOSITORY OS METODOS   
                '''
                print(repositoryClass.getFull_Name())
                print(repositoryClass)
                for met in classService.getMyMethods():
                    met.setBody([])
                    repositoryClass.addMyMethods(met)

                #repositoryClass.create(Clusters[indexOfcluster].getPathToDirectory())


        return classes_to_add_cluster   
               
                

                
        
        # procurar metodos das classes que pertecem  cluster que chamem as funçoes que vao ser removidas 
        # é necessario fazer modificaçoes nesses metodos aka chamadas remotas ao serviço a que o tipo da variavel pertence
                
       

          
                      
        
        


        # caso nao pertençam é necessario remover a dependencia

        # apagar os metodos relacionados com a variavel em questao 
        # marcar esses metodos como deleted para depois serem copiados para a classe nova
        # criar os metodos na classe que pertence ao seu dominio, invertendo a pesquisa 
        print("ok")


    @staticmethod 
    def handle_ManyToMany_Relationship(classe1,typeOfN,variable,cluster,Clusters):
        
        isJoin = False
        completeType = variable["type"]
        '''
            Step 1: VERIFICAR SE PERTENCEM AO MESMO CLUSTER
        '''     
        dependencies = classe1.getDependencies()
        print(str(dependencies))

        repositoryClass,repositoryClassName = Database.find_repositoryClass(classe1.getFull_Name(),cluster)
        for dependency in dependencies:
            '''
            STEP 2: CASO PERTENÇAM NAO FAZER NADA, A RELAÇÃO PODE CONTINUAR  A EXISTIR PORQUE A BASE DE DADOS DO MICRO-SERVIÇO TEM AS DUAS ENTIDADES
            ''' 
            if(re.search("\."+typeOfN+'$',dependency)):
                isJoin=True
                print("match")
                '''
                STEP 3: CASO NAO PERTENCAM, PROCURAR AS CLASSES REPOSITORY E CRIAR UM NOVO CLUSTER COM MODELS E REPOSITORYS
                ''' 
                indexOfcluster = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                
                repositoryClassD,repositoryClassNameD = Database.find_repositoryClass(dependency ,Clusters[indexOfcluster])

                nameOfMicroservice = "E" + typeOfN + classe1.getShort_Name()
                pathToMicroservice = createDirectory.createFolderForMicroservice(Settings.PROJECT_PATH_MS,nameOfMicroservice)
                clus = Cluster(pathToMicroservice)
                clus.setClass(repositoryClassD)
                clus.setClass(repositoryClass)
                clus.setClass(classe1)
                clus.setClass(Clusters[indexOfcluster].getClasses()[dependency])

                for cla in classe1.getClassDrag():
                    clus.setClass(cla)

                
                dependencies.remove(dependency)

                Clusters.append(clus)

                cluster.deleteClasse(repositoryClassName)
                cluster.deleteClasse(classe1.getFull_Name())
                Clusters[indexOfcluster].deleteClasse(repositoryClassNameD)
                Clusters[indexOfcluster].deleteClasse(dependency)
                for cl in cluster.getClasses().values():
                    if classe1.getFull_Name() in cl.getImports():
                        cl.addDependencie(classe1.getFull_Name())
                    if repositoryClassName in cl.getImports():
                        cl.addDependencie(repositoryClassName)     
                
                for cl in Clusters[indexOfcluster].getClasses().values():
                    if dependency in cl.getImports():
                        cl.addDependencie(dependency)
                    if repositoryClassNameD in cl.getImports():
                        cl.addDependencie(repositoryClassNameD)
        return isJoin        
    
    @staticmethod
    def handle_ManyToOne_Relationship(classeN,typeof1,variable,cluster,Clusters):
        
        print(classeN.getFull_Name())
        print(1)
        print(typeof1)
        print(2)    
        print(variable)
        classes_to_add_cluster = []

        completeType = variable["type"]
        '''
            Step 1: VERIFICAR SE PERTENCEM AO MESMO CLUSTER
        '''     
        dependencies = classeN.getDependencies()
        print(dependencies)

        for dependency in dependencies:
            '''
            STEP 2: CASO PERTENÇAM NAO FAZER NADA, A RELAÇÃO PODE CONTINUAR  A EXISTIR PORQUE A BASE DE DADOS DO MICRO-SERVIÇO TEM AS DUAS ENTIDADES
            ''' 
            if(re.search("\."+typeof1+'$',dependency)):
                indexOfcluster = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                pk_of_1 = Clusters[indexOfcluster].getClasses()[dependency].primaryKeyVariableType(Clusters[indexOfcluster])
                print("---------" +  str(pk_of_1))
                
                if(pk_of_1[1] == variable["variable"]):
                    var = pk_of_1[1] + "v2"
                    aux = (pk_of_1[0],var)
                else:
                    var = pk_of_1[1]
                    aux = (pk_of_1[0],pk_of_1[0])

                
                classeN.addInstance_Variable({#"annotations" :[re.sub("Join","",variable["annotations"][1])],
                                                "annotations" : ["@Column(name = " + var+")"],
                                                "modifier" : "private", 
                                                "type" : pk_of_1[0],
                                                "variable" : var})
                classes_to_add_cluster = Database.handle_OneToMany_Relationship(classeN,typeof1,variable,cluster,Clusters,aux)
        print("ok2")
        return classes_to_add_cluster

        
   


    '''
    Retorna os metodos que utilizam variaveis de um determinado tipo
    @params methods : Lista de metodos
    @objectType : Tipo da variavel
    @completeType : Collection do tipo da variavel

    '''
    @staticmethod
    def find_usages_methods(methods,objectType,completeType):

        methods_to_remove = []
        
        for method in methods.values():
            variables = []
            parametersDataType = []
            for variable in method["variables"]:
                    variables.append(variable["type"])

            for parameter in  method["parametersDataType"]:
                    parametersDataType.append(parameter["type"])        

            if objectType in variables or completeType in variables:
                methods_to_remove.append(method)
                
            elif objectType in method["returnDataType"] or completeType in method["returnDataType"] :
                methods_to_remove.append(method)
            
            elif objectType in parametersDataType or completeType in parametersDataType:
                methods_to_remove.append(method)
            
        return methods_to_remove    

    '''
    Retorna a lista de metodos de uma determinada classe que invoca um ou mais metodos 
    @classe : Classe
    @className: Nome da classe que a lista de metodos pertence
    @methods :  Lista de metodos 
    '''

    def find_methods_invocation(classe,classeName,methodsToRemove):
        methods_to_change = []
        methods = classe.getMethods()
        removeNames =[x["name"] for x in methodsToRemove]
        
        for method in methods.values():
            methodsInvocations = method["methodInvocations"]
            for methodInvocate in methodsInvocations:
                if methodInvocate["targetClassName"] == classeName and methodInvocate["methodName"] in removeNames:
                    methods_to_change.append(method)

        return methods_to_change

    '''
    Dado uma classe entidade procurar qual é classe repository que a representa
    '''
    @staticmethod
    def find_repositoryClass(nameOfClasse,Cluster):
        classes = Cluster.getClasses()
        
        

        for classe in classes.values():
            if nameOfClasse in classe.getImports() and "@Repository" in classe.getAnnotations():    
                return classe, classe.getFull_Name()

        return ""
     
        
        
    '''
    Responsável por criar a interface 
    @params methods : Metodos que devem ser declarados na interface
    @params typeof : Tipo da variavel
    @params PK : parametro que é PK da classe1 
    '''    
    @staticmethod
    def createInterface(methods,typeof,pk):


        methods_to_write = []
        for method in methods:
            #returnType = method["returnDataType"][0]
            #if returnType not in Settings.PrimitiveTypes:
            #    method["returnDataType"][0] = "Object"
            
            parametersDataType = method["parametersDataType"]
            
            #for parameter in parametersDataType:
            #    if parameter["type"] not in Settings.PrimitiveTypes:
            #        parameter["type"] = "Object"

            parametersDataType.append({"type" : pk[0],"variable" : pk[1]})        
            methods_to_write.append(method)

        name = typeof + "Request"
        interface = MyInterface("public",name,methods_to_write)

        return interface

    def createClass_callInterface(interface,typeOf1,typeOfN,endpoint):


        name =  interface.getName() + "Impl" 
        c = Class("Request.Impl." +name,name)

        instance_variables = [{
            "annotations" : [],
            "modifier" : "private",
            "type" : "RestTemplate",
            "variable" : "restTemplate"
        }]
        methods = interface.getMethods()
      
        
        for method in methods:
            method_name = method["name"]
            method_returnType = method["returnDataType"][0]
            method_parameters = method["parametersDataType"]
            body = []
            request = ""
            if (method_returnType == "void"): # sigifica que é um post ou put
                request = "{\n restTemplate.put(\'http://%s/%s/{id}/%s/%s\',%s,%s);" %(endpoint,typeOf1,typeOfN,method_name,method_parameters[0]["variable"],method_parameters[1]["variable"])
                returnStatement = " return ;\n}"
                body.append(request)
                body.append(returnStatement)
            else:  
                request = "{\n "+method_returnType + " aux = restTemplate.getForObject(\'http://%s/%s/{id}/%s/%s"% (endpoint,typeOf1,typeOfN,method_name)
               
                if len (method_parameters) > 1:
                    request = request + "?"    
                    for param in method_parameters[:-1]:
                        request = request + "\'%s\'=%s&" %(param["variable"],param["variable"])                
                    request = request + "\'%s\'=%s\'," %(method_parameters[-1]["variable"],method_parameters[-1]["variable"])
                
                request = request + "\',%s.class,%s);" %(method_returnType,method_parameters[-1]["variable"])
                body.append(request)
                returnStatement = "return aux;\n}"
                body.append(returnStatement)        

            

            m = MyMethod(method_name,method_returnType,method_parameters,body)
            c.addMyMethods(m)
            

        c.setInstance_variables(instance_variables)
        c.setImplements([interface.getName()])
        c.setImports(["org.springframework.web.client.RestTemplate"])

        return c

    def createClass_Controller(methods,typeOf1,typeOfN,pk):

        name =typeOfN + typeOf1 + "Controller"

        controller = Class("NEW." +name,name)
        service = typeOfN +typeOf1

        instance_variables = [{
            "annotations" : [],
            "modifier" : "private",
            "type" : service+"Service",
            "variable" : (service + "Service").lower()
        }]

        anotations = ["@AllArgsConstructor","@RestController","@CrossOrigin"]
        imports = ["lombok.AllArgsConstructor","org.springframework.web.bind.annotation.*"]

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

            if method_returnType == "void":
                annotations.append("@PutMapping")
                method_parameters[0]["type"] = "@RequestBody " + method_parameters[0]["type"]

            else:
                annotations.append("@GetMapping")
                for mett in  method_parameters:
                    mett["type"] = "@RequestParam " + mett["type"]     

            
            method_parameters.insert(0,({"type" :"@PathVariable(name=\"id\") " + pk[0],"variable" : pk[1]}))

            callService = "{\n" +(service + "Service").lower() +"."+method_name + "("
            for param in method_parameters[:-1]:
                callService = callService + "%s," %(param["variable"])
            callService = callService + "%s);\n}" %(method_parameters[-1]["variable"])        

            body.append(callService)

            m = MyMethod(method_name,method_returnType,method_parameters,body,annotations,route)
            controller.addMyMethods(m)
        
        return controller

    def createClass_Service(methods,repositoryClass,typeOf1,typeOfN,pk):
        
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

            

            body = []
            callRepo = "{\n%s.%s(" %(repositoryClass.lower(),met["name"])
            for param in method_parameters[:-1]:
                callRepo = callRepo + "%s," %(param["variable"])
            callRepo = callRepo + "%s);\n}" %(method_parameters[-1]["variable"])    
            
            body.append(callRepo)
            m = MyMethod(method_name,method_returnType,method_parameters,body)
            service.addMyMethods(m)


        


        return service
