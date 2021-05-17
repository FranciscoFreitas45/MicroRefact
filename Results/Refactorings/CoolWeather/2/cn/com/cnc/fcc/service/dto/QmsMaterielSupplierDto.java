public class QmsMaterielSupplierDto {

 private Long id;

 private Long materielId;

 private Long supplierId;

 private String materielCd;

 private String materielName;

 private String supplierCd;

 private String SupplierName;

 private String remark;


public void setSupplierId(Long supplierId){
    this.supplierId = supplierId;
}


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public String getSupplierName(){
    return SupplierName;
}


public Long getMaterielId(){
    return materielId;
}


public String getSupplierCd(){
    return supplierCd;
}


public void setSupplierCd(String supplierCd){
    this.supplierCd = supplierCd;
}


public Long getId(){
    return id;
}


public String getMaterielCd(){
    return materielCd;
}


public void setSupplierName(String supplierName){
    SupplierName = supplierName;
}


public String getMaterielName(){
    return materielName;
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


public Long getSupplierId(){
    return supplierId;
}


public void setId(Long id){
    this.id = id;
}


}