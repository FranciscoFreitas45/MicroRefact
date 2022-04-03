package cn.com.cnc.fcc.domain;
 public class materialTypeSelectionDto {

 private  Long id;

 private  String materielTypeCd;

 private  String materielTypeName;

 private  String remark;


public String getMaterielTypeCd(){
    return materielTypeCd;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setMaterielTypeName(String materielTypeName){
    this.materielTypeName = materielTypeName;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setMaterielTypeCd(String materielTypeCd){
    this.materielTypeCd = materielTypeCd;
}


public String getMaterielTypeName(){
    return materielTypeName;
}


}