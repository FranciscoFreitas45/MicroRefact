package cn.com.cnc.fcc.service.dto;
 public class QmsMaterielEntryEditDto {

 private  Long id;

 private  String materielId;

 private  String materielCd;

 private  String materielName;

 private  String specificationType;

 private  String figureNumber;

 private  String packingQuantity;

 private  String supplierId;

 private  String supplierCd;

 private  String supplierName;

 private  String entryQuantity;

 private  String entryType;

 private  String purchaseOrderNumber;

 private  String batchNumber;

 private  String madeYmd;

 private  String madeFactoryCd;

 private  String texTure;

 private  String castingNum;

 private  String entryDate;


public void setMadeFactoryCd(String madeFactoryCd){
    this.madeFactoryCd = madeFactoryCd;
}


public void setPackingQuantity(String packingQuantity){
    this.packingQuantity = packingQuantity;
}


public void setPurchaseOrderNumber(String purchaseOrderNumber){
    this.purchaseOrderNumber = purchaseOrderNumber;
}


public String getCastingNum(){
    return castingNum;
}


public void setMadeYmd(String madeYmd){
    this.madeYmd = madeYmd;
}


public String getMaterielId(){
    return materielId;
}


public String getSupplierCd(){
    return supplierCd;
}


public Long getId(){
    return id;
}


public String getMaterielCd(){
    return materielCd;
}


public String getEntryType(){
    return entryType;
}


public String getSpecificationType(){
    return specificationType;
}


public String getMadeYmd(){
    return madeYmd;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public void setTexTure(String texTure){
    this.texTure = texTure;
}


public void setMaterielId(String materielId){
    this.materielId = materielId;
}


public String getEntryDate(){
    return entryDate;
}


public void setEntryDate(String entryDate){
    this.entryDate = entryDate;
}


public void setId(Long id){
    this.id = id;
}


public void setBatchNumber(String batchNumber){
    this.batchNumber = batchNumber;
}


public void setCastingNum(String castingNum){
    this.castingNum = castingNum;
}


public String getMadeFactoryCd(){
    return madeFactoryCd;
}


public void setSupplierId(String supplierId){
    this.supplierId = supplierId;
}


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public String getPackingQuantity(){
    return packingQuantity;
}


public String getSupplierName(){
    return supplierName;
}


public void setEntryType(String entryType){
    this.entryType = entryType;
}


public void setSpecificationType(String specificationType){
    this.specificationType = specificationType;
}


public void setSupplierCd(String supplierCd){
    this.supplierCd = supplierCd;
}


public void setSupplierName(String supplierName){
    this.supplierName = supplierName;
}


public String getMaterielName(){
    return materielName;
}


public String getEntryQuantity(){
    return entryQuantity;
}


public String getPurchaseOrderNumber(){
    return purchaseOrderNumber;
}


public void setFigureNumber(String figureNumber){
    this.figureNumber = figureNumber;
}


public void setEntryQuantity(String entryQuantity){
    this.entryQuantity = entryQuantity;
}


public String getFigureNumber(){
    return figureNumber;
}


public String getSupplierId(){
    return supplierId;
}


public String getTexTure(){
    return texTure;
}


public String getBatchNumber(){
    return batchNumber;
}


}