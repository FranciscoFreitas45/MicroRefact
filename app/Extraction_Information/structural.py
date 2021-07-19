
from Settings import Settings
import json
from Entities.MyMethod import MyMethod
from Entities.Class import Class
from Entities.Cluster import Cluster
import copy



"""
Function to create a object of type MyMethod
"""

def create_MyMethod(method_dict):

    name = method_dict["name"]
    returnType = method_dict["returnDataType"][0]
    parametersDataType = method_dict["parametersDataType"]
    body = method_dict["body"].split("\n")
    annotations = method_dict["annotations"]
    method = MyMethod(name,returnType,parametersDataType,body,annotations)
    
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
                    

                       

                    '''
                        Parte do Extend
                        
                    '''
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
                           
            
            if len(merge_of_clusters) > 0:
                raise Exception ("Bad code division ")
               

            for indexC,extend,cluster in extends_to_add:
                extendCopy = copy.deepcopy(Clusters[indexC].getClasses()[extend])
                extendCopy.setIsOriginal(False)
                cluster.getClasses()[extend] = extendCopy      


        return ast


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

        
                

