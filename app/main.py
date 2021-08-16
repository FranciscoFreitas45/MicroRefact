from random import randint
from random import random
import argparse
import re
import os
import copy
from shutil import copyfile
import shutil


import Utils as Utils
import createProjectDirectory as createDirectory
from database import Database
from Entities.MyInterface import MyInterface
from Entities.MyMethod import MyMethod
from Entities.Class import Class
from Entities.Cluster import Cluster
from Class_Creator.ControllerClass import ControllerClass
import Extraction_Information.structural as structural
import Extraction_Information.identifcation as identification


from Settings import Settings
import json




Clusters = list()

Classes = dict()




"""


This function looks for the methods invoked in a class that belong to another class


:parameter classAux: class under review
:parameter dependent: dependent class

:return list of invoked methods
"""

def findMethodsInvocationsToInterface(classe,dependent):

    methods = []
    methodInvocations = classe.getMethodsInvocations() # metodos invocados por classe
    for method in methodInvocations: # iterar sob os metodos invocados
        if method["targetClassName"] == dependent  and  method["methodName"] not in methods: # verifica se o metodo invocado é do tipo da classe que a classe depende
           methods.append(method["methodName"]) # se for adciona ao array

    return methods


"""
return type of return of method

:parameter ast: Abstract Syntatic Tree
:parameter method: method
:parameter dependecieClass: Type Class dependente class

"""

def checkReturnType(ast,method,dependencieClass):

    returnType = method["returnDataType"][0]
    cluster = dependencieClass.getClasses()

    
    for classe in cluster:
        if returnType == classe.split(".")[-1]:
            ##print("type of another cluster and the Type is " + returnType )
            ##print ("ANNOTATIONS OF CLASS : " +  str(ast[classe]["annotations"]))
            annotations = ast[classe]["annotations"]
            if "Entity" in annotations:
                   for instance_variable in ast[classe]["instance_variables"]:
                        if "Id" in instance_variable["annotations"]:
                            return instance_variable["type"]
            else:
                return "Object"

            
            break
    return returnType
    




"""


Function responsible for identifying the variables of instances.

:parameter ast: Abstract Syntatic Tree

"""


def find_Instance_Variable_Dependency(ast):
    
    for cluster in Clusters:
        for i,classe in cluster.getClasses().items(): # iterar sobre as classes para encontrar as variaveis de instancia 
            print("CLASSE EM ANALISE " + classe.getFull_Name())
            if "@Entity" in classe.getAnnotations():
                continue
            instanceVariable = classe.getInstance_variable() # get das variaveis de instancia da classes
            new_Interfaces = []
            for variable in instanceVariable: # iterar sobre as variaveis de instancia 
                typeAux =  variable["type"] # get do tipo da variavel de instancia
                print("DEPENDENCIAS DA CLASSE EM ANALISE " + str(classe.getDependencies())) 
                for dependencie in classe.getDependencies(): # iterar sobre as classes que a classe em questao depende
                    print("######## " + typeAux)
                    if(re.search(re.escape(typeAux)+'$',dependencie)): # verificar se o tipo da variavel de instancia é do tipo de uma classe de que depende
                    
                        methodsInvocations=findMethodsInvocationsToInterface(classe,dependencie) # procura o nome dos metodos invocados da variavel                   if(len(methodsInvocations)>0):
                        print("METODOS INVOCADOS PELA A CLASSE EM ANALISE" + str(methodsInvocations))
                        methods = []
                        classDependent = ast[dependencie] # get da info da classe que depende
                        
                           
                        for met in  methodsInvocations: # iterar sobre os metodos invocados da classe que depende
                            if met in classDependent["myMethods"]: # condicão temporária  porque ha metodos que nao estão declarados na classe que depende
                               
                                methods.append(classDependent["myMethods"][met]) # adiciono ao array dos metodos declarados na interface
                                if len(classDependent["myMethods"][met]["identifier"]) == 2:
                                    #print(classDependent["myMethods"][met]["identifier"])
                                    for dependency in classe.getDependencies(): # iterar sobre as classes que a classe em questao depende
                                        try:
                                            if(re.search(classDependent["myMethods"][met]["identifier"][1]+'$',dependency)):
                                                #print("********* %s ********"%(classDependent["myMethods"][met]["identifier"][1]) )
                                                index = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                                                #print(index)
                                                classDepend = copy.deepcopy(Clusters[index].getClasses()[dependency])
                                                name_depend = "DTO." + classDepend.getFull_Name().split(".")[-1]
                                                if  name_depend  not in  [x.getFull_Name() for x in cluster.getNewClasses()]:
                                                    classDepend.setFull_Name(name_depend)
                                                    classDepend.setShort_Name(classDepend.getShort_Name())
                                                    inst = copy.deepcopy(ast[dependency]["instance_variables"])
                                                    for var in inst:
                                                        var["annotations"] = []

                                                    classDepend.setInstance_variables(inst)
                                                    #print("--"+classDepend.getInstance_variable())
                                                    #apagar anotaçoes da classe
                                                    classDepend.setAnnotation([])
                                                    # apagar anotaçoes das variaveis
                                                    for instance_variable in classDepend.getInstance_variable():
                                                        if "add" in  instance_variable:
                                                            #print("true3")
                                                            classDepend.getInstance_variable().remove(instance_variable)                                                         
                                                        #print("INSTANCE VARIABLE " + str(instance_variable["identifier"]))
                                                        if len(instance_variable["identifier"]) > 0:
                                                            for dep in classDepend.getDependencies():
                                                                if re.search("\."+ instance_variable["identifier"][1] +'$',dep):
                                                                    #print("DTO need")
                                                                    i = Utils.find_Cluster_with_Name_Class(dep, Clusters)
                                                                    dragClasse = copy.deepcopy(Clusters[i].getClasses()[dep])
                                                                    name_dependAux = "DTO." + dragClasse.getFull_Name().split(".")[-1]
                                                                    dragClasse.setFull_Name(name_dependAux)
                                                                    dragClasse.setShort_Name(dragClasse.getShort_Name())
                                                                    dragClasse.setAnnotation([])

                                                                    for instance_variableAux in list(dragClasse.getInstance_variable()):
                                                                        if "add" in  instance_variable:
                                                                            dragClasse.getInstance_variable().remove(instance_variable) 
                                                                        instance_variableAux["annotations"] = []

                                                                    classDepend.addClassDrag(dragClasse)
                                                                                                      
                                                    #apagar os metodos que nao sao gets
                                                    myMethods = list()
                                                    for meth in ast[dependency]["myMethods"].values():
                                                        method = structural.create_MyMethod(meth)
                                                        myMethods.append(method)
                                                    classDepend.setMyMethods(myMethods)

                                                    for met in myMethods:
                                                        met_name = met.getName()
                                                        met_returnType = met.getReturnType()

                                                        if  not re.search("^get",met_name) or met_returnType == "void":
                                                            classDepend.removeMyMethod(met)   
                                                    #print(name_depend)
                                                    cluster.addNewClasses(classDepend)
                                                #else:
                                                #    print("JA TEM")    
                                                break
                                        except:
                                            print("UnsuportedCharacter")             
                                        # criar dtos
                                        # ir para as variaveis dos metodos
                                        ''''''''''''''''''''''''''''''''''''''''''
                            else:
                                methods.append({"name" : met,
                                                "returnDataType" : ["Object"],
                                                "parametersDataType" :[{
                                                    "type" : "Object",
                                                    "variable" : "Object"}]
                                                })

                                    
                                    
                        
                        print("METODOS PARA ADICIONAR " + str(methods))
                        #verificar se ja existe uma interface deste tipo
                        name = "Interface." +Utils.lastWordOfString(dependencie)
                        if name in [x.getFull_Name() for x in cluster.getNewClasses()]:
                            print("JA EXISTE UMA INTERFACE COM ESTE TIPO")
                            for inter in cluster.getNewClasses():
                                if name == inter.getFull_Name():
                                    print("ok")
                                    inter.addMethods(methods)

                                if name + "Impl" == inter.getFull_Name():
                                    print("OK CONTROLLER")
                                    inter.add_Methods_to_Class(methods)
                        else:

                            i1 = MyInterface("public",name,methods) # cria a interface a ser implementada com os metodos invocados do objeto externo
                            #i1.create(cluster.getPathToDirectory())
                            new_Interfaces.append(i1)
                            index = Utils.find_Cluster_with_Name_Class(dependencie,Clusters)
                            call = createCallInterface(i1,str(index))
                            cluster.addNewClasses(call)
                            cluster.addNewClasses(i1)
                            classe.addMyInterfaces(i1)
                        exists =False
                        for newClass in Clusters[index].getNewClasses():
                            if re.search("NEWInstance."+Utils.lastWordOfString(dependencie)+ "Controller"+'$',newClass.getFull_Name()):
                                exists= True
                                #print("JÁ EXISTE")
                                for meth in methods:
                                    if meth["name"] not in [ x.getName() for x in newClass.getMyMethods()]:
                                        #print("Não tem metodo")
                                        #print(meth["name"])
                                        m = Clusters[index].getClasses()[dependencie].findMyMethod(meth["name"])
                                        ControllerClass.addMethod_to_Controller(newClass, meth)
                                    #else:
                                        #print("ja tem metodo")    



                        if not exists:        
                            controller = ControllerClass.createClass_Controller(i1,Clusters[index].getSubDirectories())
                            Clusters[index].addNewClasses(controller)
                        break    
                       
                        
                                                


def find_methods_var_dependency(ast):
    for cluster in Clusters:
        for i,classe in cluster.getClasses().items():
            if "@Entity" in classe.getAnnotations():
                continue
            print("------" + classe.getFull_Name())
            #print(type(classe.getMethods()))
            #print(classe.getMethods())
            for i,met in classe.getMethods().items():
                print("METHOD NAME" + met["name"])
                for metInvocations in met["methodInvocations"]:
                    print(metInvocations)
                    if metInvocations["targetClassName"] in classe.getDependencies():
                        lastWordClass = Utils.lastWordOfString(metInvocations["targetClassName"])
                        index = Utils.find_Cluster_with_Name_Class(metInvocations["targetClassName"],Clusters)
                        print("INDEXXXXXX" + str(index))
                        classeDep = Clusters[index].getClasses()[metInvocations["targetClassName"]]  
                        instance_variables = [x["variable"] for x in classe.getInstance_variable()]
                        
                        if metInvocations["methodName"] in classeDep.getMethods() and metInvocations["scopeName"] not in instance_variables :
                            #print( str(index) + "    " + metInvocations["targetClassName"] + "   " + metInvocations["methodName"] )
                            
                          
                            needDTO = True
                            newCl = None
                            for newClAux in cluster.getNewClasses():
                                if re.search("DTO."+lastWordClass+'$',newClAux.getFull_Name()):
                                    needDTO = False
                                    newCl = newClAux 
                                    break 
                            m= None           
                            if(needDTO):
                                #print("need dto")
                                index = Utils.find_Cluster_with_Name_Class(metInvocations["targetClassName"],Clusters)
                                classDepend = copy.deepcopy(Clusters[index].getClasses()[metInvocations["targetClassName"]])
                                name_depend = "DTO." + classDepend.getFull_Name().split(".")[-1]
                                classDepend.setFull_Name(name_depend)
                                classDepend.setShort_Name(classDepend.getShort_Name())
                                #apagar anotaçoes da classe
                                classDepend.setAnnotation([])
                                #classDepend.setInstance_variables(ast[metInvocations["targetClassName"]]["instance_variables"])
                                # apagar anotaçoes das variaveis
                                #print(classDepend.getDependencies())
                                for instance_variable in  list(classDepend.getInstance_variable()):
                                    ##print(instance_variable)
                                    if "add" in  instance_variable:
                                        classDepend.getInstance_variable().remove(instance_variable) 
                                        #print("true2")
                                        continue
                                    
                                    ##print("INSTANCE VARIABLE " + str(instance_variable["identifier"]))
                                    if "identifier" in instance_variable and  len(instance_variable["identifier"]) > 0:
                                        for dep in classDepend.getDependencies():
                                            if re.search("\."+ instance_variable["identifier"][1] +'$',dep):
                                                #print("DTO need")
                                                i = Utils.find_Cluster_with_Name_Class(dep, Clusters)
                                                dragClasse = copy.deepcopy(Clusters[i].getClasses()[dep])
                                                name_dependAux = "DTO." + dragClasse.getFull_Name().split(".")[-1]
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
                                    myMethods = list()
                                    for meth in ast[metInvocations["targetClassName"]]["myMethods"].values():
                                        method = structural.create_MyMethod(meth)
                                        myMethods.append(method)
                                    classDepend.setMyMethods(myMethods)
                                    #print(classDepend.getFull_Name())
                                    for met in list(myMethods):
                                       
                                        met_name = met.getName()
                                        met_returnType = met.getReturnType()

                                        if  not re.search("^get",met_name) or met_returnType == "void":
                                            classDepend.removeMyMethod(met)   

                                        m= add_Method_To_Class(classeDep, classDepend, metInvocations, index)
                                        

                                        cluster.addNewClasses(classDepend)
                                                
                            else:
                                m = add_Method_To_Class(classeDep, newCl, metInvocations, index)
                            
                            if m!= None:
                                exist = True    
                                for classes in Clusters[index].getNewClasses():
                                    if re.search("NEWInstance."+lastWordClass+ "Controller"+'$',classes.getFull_Name()):
                                        exist = False
                                        if m[0].getName() not in [ x.getName() for x in classes.getMyMethods()]:
                                            #adicionar so a função
                                            print("INDEX " + str(index))
                                            ControllerClass().createVARrequestController(classes,lastWordClass, m,metInvocations["targetClassName"],Clusters[index],"",False)  
                                if exist:
                                    controller,repo = ControllerClass.createVARrequestController(lastWordClass, lastWordClass, m,metInvocations["targetClassName"],Clusters[index])
                                    Clusters[index].addNewClasses(controller)
                                       




def add_Method_To_Class(orgClasse,classe,metInvocations,index):
 print(str(orgClasse.getFull_Name()))
 print(classe.getFull_Name())   
 if metInvocations["methodName"]not in [x.getName() for x in classe.getMyMethods()]:
    if "RestTemplate"  not in [x["type"] for x in classe.getInstance_variable()]:
        instance_variable = {
            "annotations" : [],
            "modifier" : "private",
            "type" : "RestTemplate",
            "variable" : "restTemplate = new RestTemplate()"
            }
        url = {
            "annotations" : [],
            "modifier" : "",
            "type" :"String",
            "variable" : "url = \"http://" + str(index) + "\""
            }
        classe.addInstance_Variable(instance_variable)
        classe.addInstance_Variable(url)
    #print(metInvocations["methodName"])
    mymethod = copy.deepcopy(orgClasse.findMyMethod(metInvocations["methodName"]))
    #print(mymethod.getBody())
    pk = orgClasse.primaryKeyVariableType(Clusters[index])
    #print(str(orgClasse.getFull_Name()))
    #print(orgClasse.getInstance_variable())
    #print("                    "+ str(pk))
    body = mymethod.getBody()
    newBody = body[-1].replace("}"," ")
    del(body[-1])
    mymethod.addToBody(newBody)
    
    if pk is None:
        mymethod.addToBody("\n  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat(\"/%s\"))\n"%(metInvocations["methodName"] ))
    else:
        mymethod.addToBody("\n  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat(\"/\"+ %s).concat(\"/%s\"))\n"%(pk[1],metInvocations["methodName"] ))
    for param in mymethod.getParameters():                           
        mymethod.addToBody(".queryParam(\"%s\",%s);"%(param["variable"],param["variable"]))
                                        
    if mymethod.getReturnType() == "void":
        mymethod.addToBody("restTemplate.put(builder.toUriString(),null);\n}")
    else:
        mymethod.addToBody( "%s aux = restTemplate.getForObject(builder.toUriString(),%s.class);"%(mymethod.getReturnType(),mymethod.getReturnType()))
        mymethod.addToBody("return aux;\n}")         
    classe.addMyMethods(mymethod)    
    
    return mymethod,pk
    


     

def read_json_clusters(path_to_clusters, pathOfProject):
    
    Settings.ORIGINAL_PROJECT_PATH = pathOfProject
    with open(path_to_clusters) as json_file: # ler a proposta de micro-serviços
        data = json.load(json_file) # load o json

        Settings.PROJECT_NAME = data[0]["relativePath"]
        pathToProject=createDirectory.createFolderForProject()
        Settings.PROJECT_PATH_MS = pathToProject
        clusters = data[0]["clusterString"] # get da info dos clusters que está no clusterString 
        # 5 linhas de limpeza dos dados e preparação
        clusters=re.sub(', \d*: ','', clusters)
        clusters=re.sub('{\d: ','', clusters)
        clusters=re.sub('}','', clusters)
        clusters=re.sub(' ','', clusters)

        numberOfMicroService = 0

        clusterList =re.split('\[(.*?)\]',clusters)
        for cluster in clusterList:# iterar sobre cada cluster

            if len(cluster)!=0:
                pathToMicroservice = createDirectory.createFolderForMicroservice(pathToProject,str(numberOfMicroService),pathOfProject)

                clus = Cluster(pathToMicroservice)
                
                clusterClasses = cluster.split(',') # dividir o cluster por classes

                for Classe in clusterClasses: # cria a classe classe com a info de cada classe do programa
                    Classe=re.sub('\'','', Classe)              
                    x = Classe.split(".")

                    classShortName =  x[-1]
                    print(Classe)
                    c=Class(Classe,classShortName)

                    clus.setClass(c)
                    Classes[Classe]= c

                Clusters.append(clus)

                #comparar nome das classes para identificar a sub directory pai

                class0 = re.sub('\'','', clusterClasses[0])
                class1 = re.sub('\'','', clusterClasses[1])
                print(class0)
                print(class1)
                
                count = -1
                checkpoint = -1
                for i, v  in enumerate(class0):
                    if v == ".":
                        checkpoint = count
                    if i+1 > len(class0)  or i+1 > len(class1) :
                        break;      
                    if v == class1[i]:
                        count = count +1 
                    else:
                        break;    
                    
                print(checkpoint)
                clus.setSubDirectories(class0[0:count+1])
                
                    

                numberOfMicroService =numberOfMicroService +1    
    return data, 






'''

find the cluster of Class

parameter Class

return Cluster

'''
def find_Cluster(Class):

    c =-1
    for  i, cluster in  enumerate (Clusters):
        if Class.getFull_Name() in cluster.getClasses():
            return i

    return c


def createCallInterface(interface,endpoint):

        name = interface.getFull_Name().split(".")[-1] + "Impl"
        c = Class("Interface." + name,name)
        url = "\"http://"+endpoint+"\""

        instance_variables = [{
            "annotations" : ["@Autowired"],
            "modifier" : "private",
            "type" : "RestTemplate",
            "variable" : "restTemplate"
        },{
            "annotations" : [],
            "modifier" : "",
            "type" : "String",
            "variable" : "url = " + url
        }]


        methods = interface.getMethods()

        for method in methods:
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
           
            m = MyMethod(method_name,method_returnType,method_parameters,body)
            c.addMyMethods(m)
            
        c.setImports(["org.springframework.web.client.RestTemplate","org.springframework.web.util.UriComponentsBuilder","org.springframework.beans.factory.annotation.Autowired"])    
        c.setInstance_variables(instance_variables)
        c.setImplements([interface.getFull_Name().split(".")[-1]])

        return c

def main():
    
    parser = argparse.ArgumentParser()
    #parser.add_argument("--ast", "-a",
    #                    help="File with AST of project",required=True)
    parser.add_argument("--clusters", "-c",
                        help="File with clusters",required=True) 
    parser.add_argument("--projectPath","-pp",
                        help="Path to monolith",required=True)
    args = parser.parse_args()

    if args.projectPath:
        Settings.PROJECT_PATH = args.projectPath

    

    #print(args.projectPath)
    Utils.execute_parser(args.projectPath)
        

    clustersInfo = read_json_clusters(args.clusters,args.projectPath)

    subDir = Clusters[0].getSubDirectories()
    print("--------------- " + subDir)

    for x in Clusters[:-1]:
        count=-1
        for ii, v in enumerate(x.getSubDirectories()):
            if ii+1 > len(x.getSubDirectories())  or ii+1 > len(subDir):
                break;      
            if v == subDir[ii]:
                count = count +1 
            else:
                break
        subDir = x.getSubDirectories()[0:count+1]
        print(subDir)            
                        
    for x in Clusters:
        x.setSubDirectories(subDir)



    ast=structural.extract_Info_AST(Clusters)
    structural.find_implements(ast,Clusters,Classes)

    identification.find_dependencies(ast,Clusters)
    
    
    Database.find_logic_schema(ast,Clusters)
    find_Instance_Variable_Dependency(ast)


    find_methods_var_dependency(ast)

    f = open("domain" + Settings.PROJECT_NAME+".puml", "w")
    f.write("@startuml\n")
    for x in Clusters:
        f.write("package %s <<Folder>> {\n"%(x.getPathToDirectory().split("/")[-1]))
        ##print(x.#printInformation())
        x.write_Main()
        x.write_classes(f)
        f.write("}\n")
       
        #x.write_Interfaces()

    #Clusters[10].getClasses()["com.ats.hrmgt.service.CommonFunctionService"].myInformation()
    #Clusters[10].getClasses()["com.ats.hrmgt.service.CommonFunctionServiceImpl"].myInformation()    
         
    
    #Utils.copyFile("/home/fracisco/Desktop/Dissertação/Dissertacao/GithubExtraction/ProjetosParaAnalisar/asledziewski__restaurantServer/src/main/java
    #/pl/edu/wat/wcy/pz/restaurantServer/controller/UserController.java", "Teste/UserController.java")
    
    ##print(len(Clusters))
    f.write("@enduml\n")
    f.close()

    #writeMain
    
    
main()           

# TODO VERIFICAR DEPENDENCIAS DE HERANÇA PARA AGRUPAR ms 
# TODO VERIFICAR DEPENDENCIAS DE INTERFACE PARA AGRUPAR ms 
# TODO VERIFICAR DEPENDENCIAS DE BASE DE DADOS  para descobrir o modelo relacional
# TODO criar script para correr tudo



'''
 if metInvocations["methodName"]not in [x.getName() for x in newClAux.getMyMethods()]:
                                        #print("NOT IN CLASSE "+ metInvocations["methodName"])
                                        #print([x["type"] for x in newClAux.getInstance_variable()])
                                        if "RestTemplate"  not in [x["type"] for x in newClAux.getInstance_variable()]:
                                            instance_variable = {
                                            "annotations" : [],
                                            "modifier" : "private",
                                            "type" : "RestTemplate",
                                            "variable" : "restTemplate = new RestTemplate()"
                                        }
                                            url = {
                                            "annotations" : [],
                                            "modifier" : "",
                                            "type" :"String",
                                            "variable" : "url = \"http://" + str(index) + "\""
                                            }
                                            newCl.addInstance_Variable(instance_variable)
                                            newCl.addInstance_Variable(url)
                                        #print(metInvocations["methodName"])
                                        mymethod = copy.deepcopy(classeDep.findMyMethod(metInvocations["methodName"]))
                                        #print(mymethod.getBody())
                                        pk = classeDep.primaryKeyVariableType(Clusters[index])
                                        body = mymethod.getBody()
                                        newBody = body[-1].replace("}"," ")
                                        del(body[-1])
                                        mymethod.addToBody(newBody)
                                        if pk is None:
                                            mymethod.addToBody("\n  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat(\"/%s\"))\n"%(metInvocations["methodName"] ))
                                        else:
                                            mymethod.addToBody("\n  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat(\"/\"+ %s).concat(\"/%s\"))\n"%(pk[1],metInvocations["methodName"] ))
                                        for param in mymethod.getParameters():                           
                                            mymethod.addToBody(".queryParam(\"%s\",%s);"%(param["variable"],param["variable"]))
                                        
                                        if mymethod.getReturnType() == "void":
                                            mymethod.addToBody("restTemplate.put(builder.toUriString(),null);\n}")
                                        else:
                                            mymethod.addToBody( "%s aux = restTemplate.getForObject(builder.toUriString(),%s.class);"%(mymethod.getReturnType(),mymethod.getReturnType()))
                                            mymethod.addToBody("return aux;\n}")         
                                        newCl.addMyMethods(mymethod)
'''                                        
'''
if metInvocations["methodName"]not in [x.getName() for x in classDepend.getMyMethods()]:
                                        #print("NOT IN CLASSE "+ metInvocations["methodName"])
                                        if "RestTemplate" not in [x["type"] for x in classDepend.getInstance_variable()]:
                                            instance_variable = {
                                            "annotations" : [],
                                            "modifier" : "private",
                                            "type" : "RestTemplate",
                                            "variable" : "restTemplate = new RestTemplate()"
                                        }
                                            url = {
                                            "annotations" : [],
                                            "modifier" : "",
                                            "type" :"String",
                                            "variable" : "url = \"http://" + str(index) + "\""
                                            }
                                            classDepend.addInstance_Variable(instance_variable)
                                            classDepend.addInstance_Variable(url)
                                        #print(metInvocations["methodName"])
                                        mymethod = copy.deepcopy(classeDep.findMyMethod(metInvocations["methodName"]))
                                        #print(mymethod.getBody())
                                        pk = classeDep.primaryKeyVariableType(Clusters[index])
                                        body = mymethod.getBody()
                                        newBody = body[-1].replace("}"," ")
                                        del(body[-1])
                                        mymethod.addToBody(newBody)
                                        if pk is None:
                                            mymethod.addToBody("\n  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat(\"/%s\"));\n"%(metInvocations["methodName"] ))
                                        else:
                                            mymethod.addToBody("\n  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat(\"/\"+ %s).concat(\"/%s\"));\n"%(pk[1],metInvocations["methodName"] ))
                                        for param in mymethod.getParameters():                           
                                            mymethod.addToBody(".queryParam(\"%s\",%s);"%(param["variable"],param["variable"]))
                                        
                                        if mymethod.getReturnType() == "void":
                                            mymethod.addToBody("restTemplate.put(builder.toUriString(),null);\n}")
                                        else:
                                            mymethod.addToBody( "%s aux = restTemplate.getForObject(builder.toUriString(),%s.class);"%(mymethod.getReturnType(),mymethod.getReturnType()))
                                            mymethod.addToBody("return aux;\n}")         
                                        classDepend.addMyMethods(mymethod)
'''                                        