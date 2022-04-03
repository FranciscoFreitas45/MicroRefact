package com.uec.imonitor.es.bean.index;
 public class IndexConfig {

 private  String indexName;

 private  String typeName;

 private  int shardsNum;

 private  int replicasNum;


public int getReplicasNum(){
    return replicasNum;
}


public void setIndexName(String indexName){
    this.indexName = indexName;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


public int getShardsNum(){
    return shardsNum;
}


public void setShardsNum(int shardsNum){
    this.shardsNum = shardsNum;
}


public String getTypeName(){
    return typeName;
}


public String getIndexName(){
    return indexName;
}


public void setReplicasNum(int replicasNum){
    this.replicasNum = replicasNum;
}


}