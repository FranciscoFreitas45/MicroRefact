public class QmsMaterielEntryDto {

 private Long id;

 private String materielCd;

 private String materielName;

 private String supplierCd;

 private String SupplierName;

 private String figureNumber;

 private String specificationType;

 private String purchaseOrderNumber;

 private String flagInspect;

 private String enclosure;

 private String entryDate;

 private String checkType;


public void setPurchaseOrderNumber(String purchaseOrderNumber){
    this.purchaseOrderNumber = purchaseOrderNumber;
}


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public String getCheckType(){
    return checkType;
}


public void setCheckType(String checkType){
    this.checkType = checkType;
}


public String getSupplierName(){
    return SupplierName;
}


public void setFlagInspect(String flagInspect){
    this.flagInspect = flagInspect;
}


public void setSpecificationType(String specificationType){
    this.specificationType = specificationType;
}


public String getSupplierCd(){
    return supplierCd;
}


public void setSupplierCd(String supplierCd){
    this.supplierCd = supplierCd;
}


public String getEnclosure(){
    return enclosure;
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


public String getSpecificationType(){
    return specificationType;
}


public String getFlagInspect(){
    return flagInspect;
}


public String getPurchaseOrderNumber(){
    return purchaseOrderNumber;
}


public void setFigureNumber(String figureNumber){
    this.figureNumber = figureNumber;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public String getFigureNumber(){
    return figureNumber;
}


public void setEnclosure(String enclosure){
    this.enclosure = enclosure;
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


}