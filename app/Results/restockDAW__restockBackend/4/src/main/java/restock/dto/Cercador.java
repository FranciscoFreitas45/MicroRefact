package restock.dto;
 public class Cercador {

 private  long serialVersionUID;

 private  String camp;

 private  Integer orgId;

/**
 * Cercador.
 *
 * @param camp the camp
 * @param orgId the org id
 */
public Cercador(String camp, Integer orgId) {
    super();
    this.camp = camp;
    this.orgId = orgId;
}/**
 * Cercador.
 */
public Cercador() {
    super();
}
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