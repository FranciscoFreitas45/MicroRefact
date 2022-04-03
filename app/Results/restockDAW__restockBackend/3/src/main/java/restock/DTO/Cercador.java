package restock.DTO;
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
public Integer getOrgId(){
    return orgId;
}


public String getCamp(){
    return camp;
}


}