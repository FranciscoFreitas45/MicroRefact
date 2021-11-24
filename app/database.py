
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
from Class_Creator.ServiceClass import ServiceClass
from Class_Creator.ControllerClass import ControllerClass
from Settings import Settings
import copy
import createProjectDirectory as createDirectory
import random
import string



relations_bet_entites_dif_ms = 0 

class Database:



    @staticmethod
    def find_logic_schema(ast,Clusters):
        numEntities = 0
        global relations_bet_entites_dif_ms
        relations_bet_entites_dif_ms = 0  
        for cluster in Clusters:
            #print(cluster.getPathToDirectory())
            #if  not re.search(r'\d+$',cluster.getPathToDirectory()): # verifica se é um cluster original
            #    #print("dddd")
            #    continue  
            #  clusterCopy = copy.deepcopy(cluster) # REMEMBER ISTO É UMA COPIA LOGO O QUE É ESCRITO É REFERENTE A OUTRO OBJECTO
            for k,v in reversed(sorted(cluster.getClasses().items())): 
                classe_annotations =v.getAnnotations()
                if any(re.match("^@Entity",line) for line in classe_annotations):
                #if "@Entity" in classe_annotations:
                    numEntities = numEntities + 1
                    instance_variables = ast[k]["instance_variables"]
                    for variable in instance_variables:
                        variable_annotations = variable["annotations"]
                        
                        #if "@OneToMany" in variable_annotations:
                        if any(re.match("^@OneToMany",line) for line in variable_annotations):
                            print("OneTOMANY")

                            param=v.primaryKeyVariableType(cluster)
                            classes_to_add = Database.handle_OneToMany_Relationship(v,variable["identifier"][1],variable,cluster,Clusters,param,False)
                           
                            for cl in classes_to_add:
                             
                                cluster.addNewClasses(cl)
                        
                        elif  any(re.match("^@ManyToMany",line) for line in variable_annotations):
                            print("MANYTOMANY")
                            isJoin = Database.handle_ManyToMany_Relationship(v,variable["identifier"][1],variable,cluster,Clusters)
                            if isJoin:
                                break
                        
                        elif any(re.match("^@OneToOne",line) for line in variable_annotations):
                            print("ONETONE")
                            classes_to_add = Database.handle_ManyToOne_Relationship(v,variable["type"],variable,cluster,Clusters)
                            for cl in classes_to_add:
                             
                                cluster.addNewClasses(cl)
                        
                        elif any(re.match("^@ManyToOne",line) for line in variable_annotations):
                            print("MANYTOONE")
                            classes_to_add = Database.handle_ManyToOne_Relationship(v,variable["type"],variable,cluster,Clusters)
                           
                            for cl in classes_to_add:
                             
                                cluster.addNewClasses(cl)
        return numEntities,relations_bet_entites_dif_ms                        
                        
                           
        
                            


    @staticmethod
    def handle_OneToMany_Relationship(classe1, typeOfN,variable,cluster,Clusters,param,MANY_TO_ONE):
        #print("inside of oneToMany")
        print(classe1.getFull_Name())
        
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
                global relations_bet_entites_dif_ms
                relations_bet_entites_dif_ms = relations_bet_entites_dif_ms +1
                print("#################################### " +classe1.getFull_Name() + " ########  "+ dependency ) 
                methods = []
                methods = Database.find_usages_methods(classe1.getMethods(),typeOfN,completeType)

                methodsAux= copy.deepcopy(methods)

                '''
                STEP 4: COPIAR PARA OS DTOS

                '''
                index = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                classDepend = copy.deepcopy(Clusters[index].getClasses()[dependency])
                print(cluster.getIsExtra())
                name_depend = cluster.getSubDirectories() +"DTO." + classDepend.getFull_Name().split(".")[-1]
                #print(name_depend)
                #print(classDepend.getInstance_variable())
                classDepend.setFull_Name(name_depend)
                classDepend.setShort_Name(classDepend.getShort_Name())
                #apagar anotaçoes da classe
                classDepend.setAnnotation([])
                # apagar anotaçoes das variaveis
                for instance_variable in  list(classDepend.getInstance_variable()):  
                    if "add" in  instance_variable:
                        classDepend.getInstance_variable().remove(instance_variable) 
                    #print(instance_variable["variable"]) 
                    ##print("INSTANCE VARIABLE "  +  str(instance_variable["identifier"]))
                    if "identifier" in instance_variable and  len(instance_variable["identifier"]) > 0:
                        for dep in classDepend.getDependencies():
                            if re.search("\."+ instance_variable["identifier"][1] +'$',dep):
                                #print("DTO need db")
                                i = Utils.find_Cluster_with_Name_Class(dep, Clusters)
                                dragClasse = copy.deepcopy(Clusters[i].getClasses()[dep])
                                name_dependAux = cluster.getSubDirectories()+ "DTO." + dragClasse.getFull_Name().split(".")[-1]
                                dragClasse.setFull_Name(name_dependAux)
                                dragClasse.setShort_Name(dragClasse.getShort_Name())
                                dragClasse.setAnnotation([])
                                for instance_variableAux in list(dragClasse.getInstance_variable()):
                                    if "add" in  instance_variable:
                                        dragClasse.getInstance_variable().remove(instance_variable) 
                                    instance_variableAux["annotations"] = []

                                classDepend.addClassDrag(dragClasse)
                    
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
                
                interface=Database.createInterface(methodsAux,typeOfN,param, [name_depend],cluster.getSubDirectories())

                classRequest = Database.createClass_callInterface(interface,classe1.getShort_Name(),typeOfN,index,[name_depend,interface.getFull_Name()],cluster.getSubDirectories())
                classRequest.addMyInterfaces(interface)
               
                classe1.addClassGlue(interface)
                classe1.addClassGlue(classRequest)
                classes_to_add_cluster.append(classRequest)
                classes_to_add_cluster.append(interface)
                #interface.create(cluster.getPathToDirectory() + "/Request")
                
                 
                '''
                STEP 6 : Limpar classe (tirar anotaçao da variavel typeN, adicionar variavel de instancia , modificar os metodos de get e set)
                '''
                
                
                variable["annotations"] = ["@Transient"]
                classe1.addInstance_Variable({"annotations" : ["@Transient"],
                                                "modifier" : "private", 
                                                "type" : interface.getShort_Name(),
                                                "variable" : interface.getShort_Name().lower() + " = new " + classRequest.getShort_Name() + "();",
                                                "add" : True})

                # adicionar novoos imports
                classe1.addImports(interface.getFull_Name())
                classe1.addImports(classRequest.getFull_Name())
                classe1.addImports(name_depend)


                for met in methods:
                    for myMet in classe1.getMyMethods():
                        body = "{\n"
                        if met["name"] == myMet.getName():
                            if (met["returnDataType"][0] =="void"):
                                if MANY_TO_ONE:
                                    body = body + "this.%s = %s.%s() ;\n"%(param[1],met["parametersDataType"][0]["variable"],"get"+met["parametersDataType"][0]["variable"].capitalize())
                                body = body + "%s.%s("%(interface.getShort_Name().lower(),met["name"])
                            else:    
                                body = "{\n  this.%s = %s.%s("%(variable["variable"],interface.getShort_Name().lower(),met["name"])
                            
                            for parameter in met["parametersDataType"]:
                                body = body + "%s,"%(parameter["variable"])
                            body = body + "this.%s);\n"%(param[1])    

                            if (met["returnDataType"][0] !="void"):
                                body = body + "return this.%s;\n}"%(variable["variable"])     
                            
                            elif re.search("^set",met["name"]):
                                for parameter in met["parametersDataType"]:
                                    body = body + " this.%s = %s;\n"%(parameter["variable"],parameter["variable"])
                            
                            body = body + "}\n"

                            myMet.setBody([body])
               

                '''
                STEP 7 :  DO LADO DO MICROSERVIÇO A QUE PERTENCE O TIPO DA VARIAVEL, CRIAR CONTROLLER E SERVICE PARA RECEBER OS PEDIDOS
        
                '''
                # procurar o cluster a que pertence a classe que nao necessita de modificações
                indexOfcluster = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                # é necessario criar um controller,service e ligar ao repository
                #print("index of cluster " + str(indexOfcluster))
                print("index of clustaer" + str(indexOfcluster))
                repositoryClass,repositoryClassName = Utils.find_repositoryClass(dependency ,Clusters[indexOfcluster])    

                classController = ControllerClass.create(copy.deepcopy(methods),dependency,classe1.getShort_Name(),typeOfN,param,Clusters[indexOfcluster].getSubDirectories())
                #classController.create(Clusters[indexOfcluster].getPathToDirectory())
                Clusters[indexOfcluster].addNewClasses(classController)

                classService = ServiceClass.create(copy.deepcopy(methods),repositoryClassName,dependency,classe1.getShort_Name(),typeOfN,param,Clusters[indexOfcluster].getSubDirectories())
                #classService.create(Clusters[indexOfcluster].getPathToDirectory())
                Clusters[indexOfcluster].addNewClasses(classService)

                ###########################################################TRUNCAR AQUI

                '''
                STEP 8 : ADICIONAR AO REPOSITORY OS METODOS   
                '''
                #print(repositoryClass.getFull_Name())
                #print(repositoryClass)
                for met in classService.getMyMethods():
                    copy_met = copy.deepcopy(met)
                    copy_met.setBody([])
                    repositoryClass.addMyMethods(copy_met)

                #repositoryClass.create(Clusters[indexOfcluster].getPathToDirectory())


        return classes_to_add_cluster   
               
                

                
        
        # procurar metodos das classes que pertecem  cluster que chamem as funçoes que vao ser removidas 
        # é necessario fazer modificaçoes nesses metodos aka chamadas remotas ao serviço a que o tipo da variavel pertence
                
       

          
                      
        
        


        # caso nao pertençam é necessario remover a dependencia

        # apagar os metodos relacionados com a variavel em questao 
        # marcar esses metodos como deleted para depois serem copiados para a classe nova
        # criar os metodos na classe que pertence ao seu dominio, invertendo a pesquisa 
        #print("ok")


    @staticmethod 
    def handle_ManyToMany_Relationship(classe1,typeOfN,variable,cluster,Clusters):
        
        isJoin = False
        completeType = variable["type"]
        '''
            Step 1: VERIFICAR SE PERTENCEM AO MESMO CLUSTER
        '''     
        dependencies = classe1.getDependencies()
        #print("INSIDE MANY TO MANY")
        print(classe1.getFull_Name())
        print(typeOfN)
        #print(str(dependencies))
        #print("******************") 
        #cluster.printName()
        #print(cluster.getPathToDirectory())
        #print("******************") 
        

        #repositoryClass,repositoryClassName = Utils.find_repositoryClass(classe1.getFull_Name(),cluster)
        for dependency in dependencies:
            
            '''
            STEP 2: CASO PERTENÇAM NAO FAZER NADA, A RELAÇÃO PODE CONTINUAR  A EXISTIR PORQUE A BASE DE DADOS DO MICRO-SERVIÇO TEM AS DUAS ENTIDADES
            ''' 
            if(re.search("\."+typeOfN+'$',dependency)):
                repositoryClass,repositoryClassName = Utils.find_repositoryClass(classe1.getFull_Name(),cluster)
                isJoin=True
                #print("match")
                '''
                STEP 3: CASO NAO PERTENCAM, PROCURAR AS CLASSES REPOSITORY E CRIAR UM NOVO CLUSTER COM MODELS E REPOSITORYS
                '''
                global relations_bet_entites_dif_ms
                relations_bet_entites_dif_ms = relations_bet_entites_dif_ms +1
                
                print("#################################### " +classe1.getFull_Name() + " ########  "+ dependency ) 
                indexOfcluster = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                #print(indexOfcluster)
                #print("IS A MS EXTRA: " + str(Clusters[indexOfcluster].getIsExtra()))
                if Clusters[indexOfcluster].getIsExtra():
                    Clusters[indexOfcluster].setClass(classe1)
                    Clusters[indexOfcluster].setClass(repositoryClass)

                    if  classe1.getFull_Name() in Clusters[indexOfcluster].getClasses()[dependency].getDependencies():
                         Clusters[indexOfcluster].getClasses()[dependency].getDependencies().remove(classe1.getFull_Name())
                    for cla in classe1.getClassGlue():
                        if cla.getFull_Name() in [x.getFull_Name() for x in cluster.getNewClasses()]:
                            cluster.removeNewClasses(cla)
                        Clusters[indexOfcluster].addNewClasses(cla)
                    

                else: 
                    repositoryClassD,repositoryClassNameD = Utils.find_repositoryClass(dependency ,Clusters[indexOfcluster])

                    nameOfMicroservice = "E" + typeOfN + classe1.getShort_Name()
                    pathToMicroservice = createDirectory.createFolderForMicroservice(Settings.PROJECT_PATH_MS,nameOfMicroservice,Settings.ORIGINAL_PROJECT_PATH)
                    clus = Cluster(pathToMicroservice,True)
                    clus.setClass(repositoryClassD)
                    clus.setClass(repositoryClass)
                    clus.setClass(classe1)
                    clus.setClass(Clusters[indexOfcluster].getClasses()[dependency])

                    if  classe1.getFull_Name() in clus.getClasses()[dependency].getDependencies():
                        clus.getClasses()[dependency].getDependencies().remove(classe1.getFull_Name())


                    #print(repositoryClassD.getFull_Name())
                    #print(repositoryClass.getFull_Name())
                    #print(classe1.getFull_Name())
                    #print(Clusters[indexOfcluster].getClasses()[dependency].getFull_Name())

                    for cla in classe1.getClassGlue():
                        if cla.getFull_Name() in [x.getFull_Name() for x in cluster.getNewClasses()]:
                            cluster.removeNewClasses(cla)
                        clus.addNewClasses(cla)


                    clus.setSubDirectories(Clusters[indexOfcluster].getSubDirectories())

                    Clusters.append(clus)

                    
                    Clusters[indexOfcluster].deleteClasse(repositoryClassNameD)
                    Clusters[indexOfcluster].deleteClasse(dependency)
                    for cl in Clusters[indexOfcluster].getClasses().values():
                        if dependency in cl.getImports() and dependency not in cl.getDependencies():
                            cl.addDependencie(dependency)
                        if repositoryClassNameD in cl.getImports() and repositoryClassNameD not in cl.getDependencies():
                            cl.addDependencie(repositoryClassNameD)    

                dependencies.remove(dependency)
                cluster.deleteClasse(repositoryClassName)
                cluster.deleteClasse(classe1.getFull_Name())
                
                
                for cl in cluster.getClasses().values():
                    if classe1.getFull_Name() in cl.getImports() and classe1.getFull_Name() not in cl.getDependencies():
                        cl.addDependencie(classe1.getFull_Name())
                    if repositoryClassName in cl.getImports() and repositoryClassName not in cl.getDependencies():
                        cl.addDependencie(repositoryClassName)     
                
                
                        
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
        #print(dependencies)

        for dependency in dependencies:
            '''
            STEP 2: CASO PERTENÇAM NAO FAZER NADA, A RELAÇÃO PODE CONTINUAR  A EXISTIR PORQUE A BASE DE DADOS DO MICRO-SERVIÇO TEM AS DUAS ENTIDADES
            ''' 
            if(re.search("\."+typeof1+'$',dependency)):
                #global relations_bet_entites_dif_ms 
                #relations_bet_entites_dif_ms = relations_bet_entites_dif_ms +1

                #print("#################################### " +classeN.getFull_Name() + " ########  "+ dependency )
                indexOfcluster = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                pk_of_1 = Clusters[indexOfcluster].getClasses()[dependency].primaryKeyVariableType(Clusters[indexOfcluster])
                print("---------" +  str(pk_of_1))
                
                #if(pk_of_1[1] == variable["variable"]):
                var = pk_of_1[1] +  ''.join(random.choice(string.ascii_uppercase + string.digits) for _ in range(4))
                aux = (pk_of_1[0],var)
                #else:
                #    var = pk_of_1[1]
                #    aux = (pk_of_1[0],pk_of_1[1])

                
                classeN.addInstance_Variable({#"annotations" :[re.sub("Join","",variable["annotations"][1])],
                                                "annotations" : ["@Column(name = \"" + var+"\")"],
                                                "modifier" : "private", 
                                                "type" : pk_of_1[0],
                                                "variable" : var})
                classes_to_add_cluster = Database.handle_OneToMany_Relationship(classeN,typeof1,variable,cluster,Clusters,aux,True)
        #print("ok2")
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
    Responsável por criar a interface 
    @params methods : Metodos que devem ser declarados na interface
    @params typeof : Tipo da variavel
    @params PK : parametro que é PK da classe1 
    '''    
    @staticmethod
    def createInterface(methods,typeof,pk,imports,subDir):


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
        interface = MyInterface("public",subDir +"Request." +name,methods_to_write,imports)

        return interface

    def createClass_callInterface(interface,typeOf1,typeOfN,endpoint,imports,subDir):


        name =  interface.getFull_Name().split(".")[-1] + "Impl" 
        c = Class(subDir + "Request.Impl." +name,name)

        instance_variables = [{
            "annotations" : [],
            "modifier" : "private",
            "type" : "RestTemplate",
            "variable" : "restTemplate = new RestTemplate();"
        }]
        methods = interface.getMethods()
      
        
        for method in methods:
            method_name = method["name"]
            method_returnType = method["returnDataType"][0]
            method_parameters = method["parametersDataType"]
            body = []
            request = ""
            if (method_returnType == "void"): # sigifica que é um post ou put
                request = "{\n restTemplate.put(\"http://%s/%s/{id}/%s/%s\",%s,%s);" %(endpoint,typeOf1,typeOfN,method_name,method_parameters[0]["variable"],method_parameters[1]["variable"])
                returnStatement = " return ;\n}"
                body.append(request)
                body.append(returnStatement)
            else:  
                request = "{\n "+method_returnType + " aux = restTemplate.getForObject(\"http://%s/%s/{id}/%s/%s"% (endpoint,typeOf1,typeOfN,method_name)
               
                if len (method_parameters) > 1:
                    request = request + "?"    
                    for param in method_parameters[:-1]:
                        request = request + "\'%s\'=%s&" %(param["variable"],param["variable"])                
                    request = request + "\'%s\'=%s\'," %(method_parameters[-1]["variable"],method_parameters[-1]["variable"])
                
                request = request + "\",%s.class,%s);" %(method_returnType,method_parameters[-1]["variable"])
                body.append(request)
                returnStatement = "return aux;\n}"
                body.append(returnStatement)        

            

            m = MyMethod(method_name,method_returnType,method_parameters,[],body)
            c.addMyMethods(m)
            

        c.setInstance_variables(instance_variables)
        c.setImplements([ interface.getFull_Name().split(".")[-1]])
        c.setImports(["org.springframework.web.client.RestTemplate","org.springframework.beans.factory.annotation.Autowired",imports[0],imports[1]])

        return c

  