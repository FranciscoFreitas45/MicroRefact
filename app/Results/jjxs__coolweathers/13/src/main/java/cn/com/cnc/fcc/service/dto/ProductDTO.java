package cn.com.cnc.fcc.service.dto;
 public class ProductDTO {

 private  Long id;

 private  String productNum;

 private  String productBatch;

 private  Long materielId;

 private  String materielCd;

 private  String materielName;

 private  String remark;


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public Long getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public String getMaterielCd(){
    return materielCd;
}


public String getProductBatch(){
    return productBatch;
}


public String getMaterielName(){
    return materielName;
}


public String getProductNum(){
    return productNum;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setMaterielId(Long materielId){
    this.materielId = materielId;
}


public String getRemark(){
    return remark;
}


public void setProductNum(String productNum){
    this.productNum = productNum;
}


public void setId(Long id){
    this.id = id;
}


public void setProductBatch(String productBatch){
    this.productBatch = productBatch;
}


}