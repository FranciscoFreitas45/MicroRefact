public class SupplierPopDto {

 private Long id;

 private String supplierCd;

 private String supplierName;

 private String assRecord;

 private String remark;

 private String supplierClassName;


public String getSupplierClassName(){
    return supplierClassName;
}


public String getSupplierName(){
    return supplierName;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getSupplierCd(){
    return supplierCd;
}


public void setSupplierCd(String supplierCd){
    this.supplierCd = supplierCd;
}


public String getAssRecord(){
    return assRecord;
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


public void setSupplierName(String supplierName){
    this.supplierName = supplierName;
}


public void setSupplierClassName(String supplierClassName){
    this.supplierClassName = supplierClassName;
}


public void setAssRecord(String assRecord){
    this.assRecord = assRecord;
}


}