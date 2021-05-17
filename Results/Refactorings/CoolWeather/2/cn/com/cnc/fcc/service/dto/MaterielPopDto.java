public class MaterielPopDto {

 private Long id;

 private String materielCd;

 private String MaterielName;

 private Long supplierId;

 private String supplierCd;

 private String supplierName;

 private String type;

 private String figureNumber;


public void setSupplierId(Long supplierId){
    this.supplierId = supplierId;
}


public void setMaterielName(String materielName){
    MaterielName = materielName;
}


public String getSupplierName(){
    return supplierName;
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
    this.supplierName = supplierName;
}


public void setType(String type){
    this.type = type;
}


public String getMaterielName(){
    return MaterielName;
}


public void setFigureNumber(String figureNumber){
    this.figureNumber = figureNumber;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public String getType(){
    return type;
}


public String getFigureNumber(){
    return figureNumber;
}


public Long getSupplierId(){
    return supplierId;
}


public void setId(Long id){
    this.id = id;
}


}