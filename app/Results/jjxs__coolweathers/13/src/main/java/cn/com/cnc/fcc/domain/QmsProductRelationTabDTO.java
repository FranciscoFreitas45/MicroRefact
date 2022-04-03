package cn.com.cnc.fcc.domain;
 public class QmsProductRelationTabDTO {

 private  Long id;

 private  String productBatch;

 private  String materielCd;

 private  String figureNumber;

 private  String weight;

 private  String texTure;

 private  String prjName;

 private  String materielName;

 private  String specificationType;

 private  String density;

 private  String assemblyCount;


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public void setSpecificationType(String specificationType){
    this.specificationType = specificationType;
}


public String getWeight(){
    return weight;
}


public void setWeight(String weight){
    this.weight = weight;
}


public Long getId(){
    return id;
}


public void setPrjName(String prjName){
    this.prjName = prjName;
}


public String getProductBatch(){
    return productBatch;
}


public String getMaterielCd(){
    return materielCd;
}


public String getDensity(){
    return density;
}


public String getMaterielName(){
    return materielName;
}


public void setDensity(String density){
    this.density = density;
}


public String getAssemblyCount(){
    return assemblyCount;
}


public String getSpecificationType(){
    return specificationType;
}


public void setFigureNumber(String figureNumber){
    this.figureNumber = figureNumber;
}


public void setAssemblyCount(String assemblyCount){
    this.assemblyCount = assemblyCount;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public void setTexTure(String texTure){
    this.texTure = texTure;
}


public String getFigureNumber(){
    return figureNumber;
}


public String getPrjName(){
    return prjName;
}


public void setId(Long id){
    this.id = id;
}


public String getTexTure(){
    return texTure;
}


public void setProductBatch(String productBatch){
    this.productBatch = productBatch;
}


}