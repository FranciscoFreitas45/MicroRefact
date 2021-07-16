from Settings import Settings
import json
from Entities.MyMethod import MyMethod
from Entities.Class import Class
from Entities.Cluster import Cluster




def find_dependencies(ast,Clusters):

    for cluster in Clusters:  
        for k,v in cluster.getClasses().items():      
            dependencies = ast[k]["dependencies"]
          
            depends = [] 
            for dependencie in dependencies:
                if dependencie not in cluster.getClasses():
                    if dependencie not in v.getDependencies() and dependencie not in depends:
                        depends.append(dependencie)
            v.setDependecies(depends)





