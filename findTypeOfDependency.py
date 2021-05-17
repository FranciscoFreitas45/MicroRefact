
import networkx as nx
from random import randint
from random import random
import matplotlib.pyplot as plt
import argparse
import re
import os
import copy
import plotly.graph_objects as go
from shutil import copyfile
import shutil


import Utils as Utils
import createProjectDirectory as createDirectory
from database import Database
from Entities.MyInterface import MyInterface
from Entities.MyMethod import MyMethod
from Entities.Class import Class
from Entities.Cluster import Cluster

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
            #print("type of another cluster and the Type is " + returnType )
            #print ("ANNOTATIONS OF CLASS : " +  str(ast[classe]["annotations"]))
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
            print(classe.getFull_Name())
            if "@Entity" in classe.getAnnotations():
                continue
            instanceVariable = classe.getInstance_variable() # get das variaveis de instancia da classes
            new_Interfaces = []
            for variable in instanceVariable: # iterar sobre as variaveis de instancia 
                typeAux =  variable["type"] # get do tipo da variavel de instancia 
                for dependencie in classe.getDependencies(): # iterar sobre as classes que a classe em questao depende
                    if(re.search(typeAux+'$',dependencie)): # verificar se o tipo da variavel de instancia é do tipo de uma classe de que depende
                    
                        methodsInvocations=findMethodsInvocationsToInterface(classe,dependencie) # procura o nome dos metodos invocados da variavel                   if(len(methodsInvocations)>0):
                        methods = []
                        classDependent = ast[dependencie] # get da info da classe que depende
                        
                           
                        for met in  methodsInvocations: # iterar sobre os metodos invocados da classe que depende
                            if met in classDependent["myMethods"]: # condicão temporária  porque ha metodos que nao estão declarados na classe que depende
                               
                                methods.append(classDependent["myMethods"][met]) # adiciono ao array dos metodos declarados na interface
                                if len(classDependent["myMethods"][met]["identifier"]) == 2:
                                    print(classDependent["myMethods"][met]["identifier"])
                                    for dependency in classe.getDependencies(): # iterar sobre as classes que a classe em questao depende
                                        try:
                                            if(re.search(classDependent["myMethods"][met]["identifier"][1]+'$',dependency)):
                                                print("********* %s ********"%(classDependent["myMethods"][met]["identifier"][1]) )
                                                index = Utils.find_Cluster_with_Name_Class(dependency,Clusters)
                                                print(index)
                                                classDepend = copy.deepcopy(Clusters[index].getClasses()[dependency])
                                                name_depend = ".DTO." + classDepend.getFull_Name().split(".")[-1]
                                                if  name_depend  not in  [x.getFull_Name() for x in cluster.getNewClasses()]:
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
                                                    print(name_depend)
                                                    cluster.addNewClasses(classDepend)
                                                else:
                                                    print("JA TEM")    
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

                                    
                                    
                        
                        
                        i1 = MyInterface("public",Utils.lastWordOfString(dependencie),methods) # cria a interface a ser implementada com os metodos invocados do objeto externo
                        i1.create(cluster.getPathToDirectory() + "/Interface")
                        new_Interfaces.append(i1)
                        index = Utils.find_Cluster_with_Name_Class(dependencie,Clusters)
                        call = createCallInterface(i1,str(index))
                        controller = createClass_Controller(i1)
                       
                        cluster.addNewClasses(call)

                        index =Utils.find_Cluster_with_Name_Class(dependencie,Clusters)
                        
                        Clusters[index].addNewClasses(controller)
                        
                        classe.addMyInterfaces(i1)
                        
                                                


def find_methods_var_dependency():
    for cluster in Clusters:
        for i,classe in cluster.getClasses().items():
            if "@Entity" in classe.getAnnotations():
                continue
            for i,met in classe.getMethods().items():
                for metInvocations in met["methodInvocations"]:
                    if metInvocations["targetClassName"] in classe.getDependencies():
                        lastWordClass = Utils.lastWordOfString(metInvocations["targetClassName"])
                        index = Utils.find_Cluster_with_Name_Class(metInvocations["targetClassName"],Clusters)
                        classeDep = Clusters[index].getClasses()[metInvocations["targetClassName"]]  
                        instance_variables = [x["variable"] for x in classe.getInstance_variable()]
                        
                        if metInvocations["methodName"] in classeDep.getMethods() and metInvocations["scopeName"] not in instance_variables :
                            print("************")
                            print(classe.getFull_Name())
                            print(metInvocations["scopeName"])
                            print(metInvocations["methodName"])
                            print(metInvocations["targetClassName"])
                            print("************")
                            needDTO = True
                            for newCl in cluster.getNewClasses():
                                if re.search("DTO."+lastWordClass+'$',newCl.getFull_Name()):
                                    needDTO = False 
                                    if metInvocations["methodName"]not in [x.getName() for x in newCl.getMyMethods()]:
                                        print("NOT IN CLASSE "+ metInvocations["methodName"])
                                        print([x["type"] for x in newCl.getInstance_variable()])
                                        if "RestTemplate"  not in [x["type"] for x in newCl.getInstance_variable()]:
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
                                        print(metInvocations["methodName"])
                                        mymethod = copy.deepcopy(classeDep.findMyMethod(metInvocations["methodName"]))
                                        print(mymethod.getBody())
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
                                        newCl.addMyMethods(mymethod)
                                        break            
                            if(needDTO):
                                print("need dto")
                                index = Utils.find_Cluster_with_Name_Class(metInvocations["targetClassName"],Clusters)
                                classDepend = copy.deepcopy(Clusters[index].getClasses()[metInvocations["targetClassName"]])
                                name_depend = ".DTO." + classDepend.getFull_Name().split(".")[-1]
                                print(name_depend)
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
                                cluster.addNewClasses(classDepend)
                                
                                if metInvocations["methodName"]not in [x.getName() for x in classDepend.getMyMethods()]:
                                        print("NOT IN CLASSE "+ metInvocations["methodName"])
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
                                        print(metInvocations["methodName"])
                                        mymethod = copy.deepcopy(classeDep.findMyMethod(metInvocations["methodName"]))
                                        print(mymethod.getBody())
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
                            

     

def read_json_clusters(path_to_clusters, pathOfProject):
    
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
                pathToMicroservice = createDirectory.createFolderForMicroservice(pathToProject,str(numberOfMicroService))
                
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

                numberOfMicroService =numberOfMicroService +1    
    return data





def extract_Info_AST(path_to_ast):
    
        with open(path_to_ast) as json_file: # ler a AST
            ast = json.load(json_file) # load da AST

            merge_of_clusters = [] # guardar a info dos merges do clusters  Array com um tuplo (cluster1 , cluster2)
            for i, cluster in  enumerate (Clusters):
                
                
                for classe in cluster.getClasses().values():

                    '''
                        Parte de extração de info para a classe

                    '''     
                    classe.setBegin(ast[classe.getFull_Name()]["begin"])
                    classe.setEnd(ast[classe.getFull_Name()]["end"])
                    classe.setIsInterface(ast[classe.getFull_Name()]["isInterface"])
                    classe.setAnnotation(ast[classe.getFull_Name()]["annotations"])
                    classe.setInstance_variables(ast[classe.getFull_Name()]["instance_variables"])
                    classe.setMethods(ast[classe.getFull_Name()]["myMethods"]) 
                    classe.setImplements(ast[classe.getFull_Name()]["implementedTypes"])
                    classe.setExtends(ast[classe.getFull_Name()]["extendedTypes"]) 
                    classe.setImports(ast[classe.getFull_Name()]["imports"])
                    classe.setMethodsInvocations(ast[classe.getFull_Name()]["methodInvocations"])

                    for meth in ast[classe.getFull_Name()]["myMethods"].values():
                        name = meth["name"]
                        returnType = meth["returnDataType"][0]
                        parametersDataType = meth["parametersDataType"]
                        body = meth["body"].split("\n")
                        annotations = meth["annotations"]
                        method = MyMethod(name,returnType,parametersDataType,body,annotations)
                        classe.addMyMethods(method)
                    

                       

                    '''
                        Parte do Extend
                        
                    '''
                    extendedTypes = ast[classe.getFull_Name()]["extendedTypes"] # get dos extends 

                    for  clusterIndex,cl in enumerate(Clusters):
                        for extend in extendedTypes: # iterar sob os extends
                            if extend in cl.getClasses():
                                if extend not in cluster.getClasses(): # caso a class do extend nao pertence ao cluster
                                    print("NOT IN " +extend) 
                                    print(classe.getFull_Name())   
                            
                                    merge_of_clusters.append((i,clusterIndex))
                           
            
            if len(merge_of_clusters) > 0:
                raise Exception ("Bad code division ")
               

        return ast
                

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





def merge_cluster(cluster_to_merge): #[(clusteri,Cluster j )]

    modify_clusters = {}  # representa os clusters modificados    {antigo cluster, novo cluster}
    group_of_merges = [] # grupo de clusters que tem que fazer o merge juntos Arrays de arrays

    a ,b = cluster_to_merge[0]
    group_of_merges.append([a,b])

    
    for index_i, index_j in cluster_to_merge[1:]:
        new_merge  = True
        add_to_merge = []
        print("INICIO DE ITERAÇÃO: " + str(index_i) + str(index_j))  
        for merges in group_of_merges:                
            if index_i in merges and index_j not in merges:
                merges.append(index_j)
                add_to_merge.append(merges)
                new_merge= False
            elif index_i not in merges and index_j in merges:
                merges.append(index_i)
                add_to_merge.append(merges)
                new_merge = False
            elif index_i in merges and index_j in merges: 
                new_merge = False
        if new_merge:
            group_of_merges.append([index_i,index_j])        
        
        print("MERGE OF" + str(add_to_merge))             
        
        merge = []
        for add in  add_to_merge:
            merge= merge + add

        if len(merge) > 0:
            group_of_merges.append(merge)
            for add in  add_to_merge:
                group_of_merges.remove(add)
      

        print("FIM DE ITERAÇÃO: " + str(index_i) + str(index_j))
        print(str(group_of_merges))

     
    print(str(group_of_merges))     
    final_merges = []
    for mer in group_of_merges: 
        final_merges.append(list(set(mer))) 

    print(str(final_merges)) # [[9, 12,6,7],[5,4]]

    for merge in final_merges: 
            cluster_0 = merge[0]
            Cluster_0 = Clusters[cluster_0]
            print(len(Cluster_0.getClasses()))
            for cluster_n in merge[1:]:
                classes = Clusters[cluster_n].getClasses().values()
                for classe in classes:
                    Cluster_0.setClass(classe)
                Clusters.remove(Clusters[cluster_n])  

            print("&&&&&&&&&&&&&&&&&&&&&&")
            print(len(Cluster_0.getClasses()))



    return final_merges    




def createCallInterface(interface,endpoint):

        name = interface.getName() + "Impl" 
        c = Class("Interface." + name,name)
        url = "\"http://"+endpoint+"\""

        instance_variables = [{
            "annotations" : [],
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
                request = request + "\n  restTemplate.put(builder.toUriString(), null)\n"
                body.append(request)
            else:                         
                request =request + "  %s aux = restTemplate.getForObject(builder.toUriString(), %s.class)\n"%(method_returnType,method_returnType)
                body.append(request)
                returnStatement = " return aux;\n}"
                body.append(returnStatement)        
           
            m = MyMethod(method_name,method_returnType,method_parameters,body)
            c.addMyMethods(m)
            
        c.setImports(["org.springframework.web.client.RestTemplate","org.springframework.web.util.UriComponentsBuilder"])    
        c.setInstance_variables(instance_variables)
        c.setImplements([interface.getName()])

        return c



def createClass_Controller(interface):

        name =interface.getName() + "Controller"

        controller = Class("NEWInstance." + name,name)
        #service = typeOfN +typeOf1
        variable = interface.getName()
        
        instance_variables = [{
            "annotations" : [],
            "modifier" : "private",
            "type" : variable,
            "variable" : variable.lower()
        }]
        

        anotations = ["@AllArgsConstructor","@RestController","@CrossOrigin"]
        imports = ["org.springframework.web.bind.annotation.*","lombok.AllArgsConstructor"]

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
            
            m = MyMethod(method_name,method_returnType,method_parameters,body,annotations,route)
            controller.addMyMethods(m)
        
        return controller






def find_implements(ast):

    for cluster in Clusters:
                
        addToCluster = []  # tem que ser assim para adicionar classes ao cluster senao dá RuntimeError: dictionary changed size during iteration
        for classe in cluster.getClasses().values():
            '''
                Parte do IMPLEMENT
            '''
            implementedTypes = ast[classe.getFull_Name()]["implementedTypes"] # buscar as interfaces que implementa 

            for interface in  implementedTypes: # iterar sobre as interfaces
                if interface not in cluster.getClasses() and interface in Classes: # se a interface nao pertencer ao cluster
                    # entao é necessario adicionar  
                    c = Classes[interface]
                    addToCluster.append(c)
        
        for c in addToCluster:
            cluster.setClass(c)                     



def find_dependencies(ast):

    for cluster in Clusters:  
        for k,v in cluster.getClasses().items():      
            dependencies = ast[k]["dependencies"]
          
            depends = [] 
            for dependencie in dependencies:
                if dependencie not in cluster.getClasses():
                    if dependencie not in v.getDependencies() and dependencie not in depends:
                        depends.append(dependencie)
            v.setDependecies(depends)







                

    #print(str(belong_to_db))            

            



def main():
    
    parser = argparse.ArgumentParser()
    parser.add_argument("--ast", "-a",
                        help="File with AST of project",required=True)
    parser.add_argument("--instanceVariable", "-iv",
                        help="File with clusters")
    parser.add_argument("--clusters", "-c",
                        help="File with clusters",required=True) 
    parser.add_argument("--projectPath","-pp",
                        help="Path to monolith",required=True)
    args = parser.parse_args()

    if args.projectPath:
        Settings.PROJECT_PATH = args.projectPath


    clustersInfo = read_json_clusters(args.clusters,args.projectPath)

    

    
    ast=extract_Info_AST(args.ast)
    find_implements(ast)
    find_dependencies(ast)

    Database.find_logic_schema(ast,Clusters)

    find_Instance_Variable_Dependency(ast)

    find_methods_var_dependency()

    f = open("../Py2puml/py2puml/domain.puml", "w")
    f.write("@startuml\n")
    for x in Clusters:
        f.write("package %s <<Folder>> {\n"%(x.getPathToDirectory()))
        #print(x.printInformation())
        x.write_classes(f)
        f.write("}\n")
        
        #x.write_Interfaces()

    #Clusters[10].getClasses()["com.ats.hrmgt.service.CommonFunctionService"].myInformation()
    #Clusters[10].getClasses()["com.ats.hrmgt.service.CommonFunctionServiceImpl"].myInformation()    
         
    
    #Utils.copyFile("/home/fracisco/Desktop/Dissertação/Dissertacao/GithubExtraction/ProjetosParaAnalisar/asledziewski__restaurantServer/src/main/java
    #/pl/edu/wat/wcy/pz/restaurantServer/controller/UserController.java", "Teste/UserController.java")
    
    print(len(Clusters))
    f.write("@enduml\n")
    f.close()
main()           

# TODO VERIFICAR DEPENDENCIAS DE HERANÇA PARA AGRUPAR ms 
# TODO VERIFICAR DEPENDENCIAS DE INTERFACE PARA AGRUPAR ms 
# TODO VERIFICAR DEPENDENCIAS DE BASE DE DADOS  para descobrir o modelo relacional
# TODO criar script para correr tudo


