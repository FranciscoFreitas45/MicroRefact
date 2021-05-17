public class QmsProductRelationTabThreeDTO {

 private  Long id;

 private  String supplierCd;

 private  String prjName;

 private  String purchaseOrderNumber;

 private  String madeYmd;

 private  String supplierName;

 private  String entryQuantity;

 private  String batchNumber;

 private  String madeFactoryCd;


public void setMadeFactoryCd(String madeFactoryCd){
    this.madeFactoryCd = madeFactoryCd;
}


public void setPurchaseOrderNumber(String purchaseOrderNumber){
    this.purchaseOrderNumber = purchaseOrderNumber;
}


public String getSupplierName(){
    return supplierName;
}


public void setMadeYmd(String madeYmd){
    this.madeYmd = madeYmd;
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


public void setPrjName(String prjName){
    this.prjName = prjName;
}


public void setSupplierName(String supplierName){
    this.supplierName = supplierName;
}


public String getMadeYmd(){
    return madeYmd;
}


public String getPurchaseOrderNumber(){
    return purchaseOrderNumber;
}


public String getEntryQuantity(){
    return entryQuantity;
}


public void setEntryQuantity(String entryQuantity){
    this.entryQuantity = entryQuantity;
}


public String getPrjName(){
    return prjName;
}


public void setId(Long id){
    this.id = id;
}


public void setBatchNumber(String batchNumber){
    this.batchNumber = batchNumber;
}


public String getMadeFactoryCd(){
    return madeFactoryCd;
}


public String getBatchNumber(){
    return batchNumber;
}


}