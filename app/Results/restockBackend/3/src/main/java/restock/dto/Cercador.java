package restock.dto;
 public class Cercador {

 private  long serialVersionUID;

 private  String camp;

 private  Integer orgId;


public void setOrgId(Integer orgId){
    this.orgId = orgId;
}


public Integer getOrgId(){
    return orgId;
}


public String getCamp(){
    return camp;
}


public void setCamp(String camp){
    this.camp = camp;
}


}