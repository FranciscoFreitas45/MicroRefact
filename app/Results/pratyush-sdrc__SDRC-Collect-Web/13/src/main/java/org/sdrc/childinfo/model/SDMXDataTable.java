package org.sdrc.childinfo.model;
 public class SDMXDataTable {

 private  int pk_datatable;

 private  String obs_value;

 private  String obs_status;

 private  String fk_dataset;

 private  String obs_unit;

 private  String indicator_code;


public void setPk_datatable(int pk_datatable){
    this.pk_datatable = pk_datatable;
}


public void setObs_unit(String obs_unit){
    this.obs_unit = obs_unit;
}


public String getIndicator_code(){
    return indicator_code;
}


public void setObs_value(String obs_value){
    this.obs_value = obs_value;
}


public String getObs_status(){
    return obs_status;
}


public void setObs_status(String obs_status){
    this.obs_status = obs_status;
}


public String getFk_dataset(){
    return fk_dataset;
}


public void setFk_dataset(String fk_dataset){
    this.fk_dataset = fk_dataset;
}


public String getObs_unit(){
    return obs_unit;
}


public void setIndicator_code(String indicator_code){
    this.indicator_code = indicator_code;
}


public String getObs_value(){
    return obs_value;
}


public int getPk_datatable(){
    return pk_datatable;
}


}