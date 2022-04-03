package com.csquard.mregister.payload;
 import com.csquard.mregister.model.SalesRegion;
public class SalesAreaResponse {

 private  String name;

 private  SalesRegion salesRegion;

 private  Long id;

public SalesAreaResponse() {
}public SalesAreaResponse(String name, SalesRegion salesRegion, Long id) {
    super();
    this.name = name;
    this.salesRegion = salesRegion;
    this.id = id;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setSalesRegion(SalesRegion salesRegion){
    this.salesRegion = salesRegion;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public SalesRegion getSalesRegion(){
    return salesRegion;
}


}