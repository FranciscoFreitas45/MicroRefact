public class QmsMaterielDetailsPopupDTO {

 private  Long id;

 private  Integer entryId;

 private  String goodsCd;

 private  String madeFactoryCd;

 private  Integer entryQuantity;

 private  String entryType;

 private  String madeYMD;

 private  String materielCd;

 private  String materielName;

 private  String supplierCd;

 private  String supplierName;


public void setGoodsCd(String goodsCd){
    this.goodsCd = goodsCd;
}


public void setMadeFactoryCd(String madeFactoryCd){
    this.madeFactoryCd = madeFactoryCd;
}


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public void setEntryType(String entryType){
    this.entryType = entryType;
}


public String getSupplierName(){
    return supplierName;
}


public String getGoodsCd(){
    return goodsCd;
}


public void setMadeYMD(String madeYMD){
    this.madeYMD = madeYMD;
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


public void setEntryId(Integer entryId){
    this.entryId = entryId;
}


public String getMaterielCd(){
    return materielCd;
}


public void setSupplierName(String supplierName){
    this.supplierName = supplierName;
}


public String getEntryType(){
    return entryType;
}


public String getMaterielName(){
    return materielName;
}


public String getMadeYMD(){
    return madeYMD;
}


public Integer getEntryQuantity(){
    return entryQuantity;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public void setEntryQuantity(Integer entryQuantity){
    this.entryQuantity = entryQuantity;
}


public Integer getEntryId(){
    return entryId;
}


public void setId(Long id){
    this.id = id;
}


public String getMadeFactoryCd(){
    return madeFactoryCd;
}


}