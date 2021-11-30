
from Settings import Settings
import json
from Entities.MyMethod import MyMethod
from Entities.Class import Class
from Entities.Cluster import Cluster
import copy
import re



"""
Function to create a object of type MyMethod
"""

def create_MyMethod(method_dict):

    name = method_dict["name"]
    returnType = method_dict["returnDataType"][0]
    parametersDataType = method_dict["parametersDataType"]
    body = method_dict["body"].split("\n")
    annotations = method_dict["annotations"]
    exceptions = method_dict["exception"]
    method = MyMethod(name,returnType,parametersDataType,exceptions,body,annotations)
    
    return method



def extract_Info_AST(Clusters):

        temp_json_location = f'{Settings.DIRECTORY}/javaParser/output.json' 
        with open(temp_json_location) as json_file: # ler a AST
            ast = json.load(json_file) # load da AST

            extends_to_add = []

            merge_of_clusters = [] # guardar a info dos merges do clusters  Array com um tuplo (cluster1 , cluster2)
            for i, cluster in  enumerate (Clusters):
                
                
                for i, classe in reversed(sorted(cluster.getClasses().items())):

                    '''
                        Parte de extração de info para a classe

                    '''
                    classe.setConstructor(ast[classe.getFull_Name()]["constructor"])     
                    classe.setBegin(ast[classe.getFull_Name()]["begin"])
                    classe.setEnd(ast[classe.getFull_Name()]["end"])
                    classe.setIsInterface(ast[classe.getFull_Name()]["isInterface"])
                    classe.setAnnotation(ast[classe.getFull_Name()]["annotations"])
                    classe.setInstance_variables(list(ast[classe.getFull_Name()]["instance_variables"]))
                    classe.setMethods(ast[classe.getFull_Name()]["myMethods"]) 
                    classe.setImplements(ast[classe.getFull_Name()]["implementedTypes"])
                    classe.setExtends(ast[classe.getFull_Name()]["extendedTypes"]) 
                    classe.setImports(ast[classe.getFull_Name()]["imports"])
                    classe.setMethodsInvocations(ast[classe.getFull_Name()]["methodInvocations"])
                    for meth in ast[classe.getFull_Name()]["myMethods"].values():
                        method = create_MyMethod(meth)
                        classe.addMyMethods(method)
                    

                    extendedTypes = ast[classe.getFull_Name()]["extendedTypes"] # get dos extends 
   

                    '''
                        Parte do Extend
                        
                    
                    extendedTypes = ast[classe.getFull_Name()]["extendedTypes"] # get dos extends 

                    for  clusterIndex,cl in enumerate(Clusters):
                        for extend in extendedTypes: # iterar sob os extends
                            if extend in cl.getClasses():
                                if extend not in cluster.getClasses(): # caso a class do extend nao pertence ao cluster
                                    #print("NOT IN " +extend) 
                                    #print(classe.getFull_Name())   
                                    # TESTE1 COPIAR A CLASSE PARA O CLUSTER
                                    extends_to_add.append((clusterIndex,extend,cluster))    
                                    #extendCopy = copy.deepcopy(cl.getClasses()[extend])
                                    #cluster.getClasses()[extend] = extendCopy
                                        
                                    #merge_of_clusters.append((i,clusterIndex))
                    '''
                    extends_to_add = extends_to_add +  find_extends(ast,classe,cluster,classe,extendedTypes,Clusters)
                    print("EXTENDSSSS TO ADD " +str(extends_to_add))

            
            if len(merge_of_clusters) > 0:
                raise Exception ("Bad code division ")
               

            for indexC,extend,cluster,classe  in extends_to_add:
                print( "%s    %s     %s    %s" %(indexC,extend,cluster.getPathToDirectory(),classe.getFull_Name()))
                extendCopy = copy.deepcopy(Clusters[indexC].getClasses()[extend])
                extendCopy.setIsOriginal(False)
                cluster.getClasses()[extend] = extendCopy
                # os extendes sao glues especiais tem que entrar no dict em vez do new classes
                classe.addClassGlue(extendCopy)




        return ast

def find_extends(ast,classe,cluster_of_Class,triggered_class,list_of_extends,Clusters):
    
    extends_to_add = []

    for  clusterIndex,cl in enumerate(Clusters):
        for extend in list_of_extends: # iterar sob os extends
            extended = extend   
            if "<" in extend:
                
                ext = extend.split("<")[0]
                for imp in ast[classe.getFull_Name()]["imports"]:
                      if(re.search("\." + ext +'$',imp)):
                          extended = imp
                          break
                   
            if extended in cl.getClasses():
                if extended not in cluster_of_Class.getClasses(): # caso a class do extend nao pertence ao cluster
                    #print("NOT IN " +extend) 
                    #print(classe.getFull_Name())   
                    # TESTE1 COPIAR A CLASSE PARA O CLUSTER

                    extends_to_add.append((clusterIndex,extended,cluster_of_Class,triggered_class))

                    classE = Clusters[clusterIndex].getClasses()[extended]
                    listE = ast[classE.getFull_Name()]["extendedTypes"]
                    extends_to_add =  extends_to_add + find_extends(ast,classE,cluster_of_Class,triggered_class,listE,Clusters)
    
    return extends_to_add                  





def find_implements(ast,Clusters,Classes):

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

        
        
                

