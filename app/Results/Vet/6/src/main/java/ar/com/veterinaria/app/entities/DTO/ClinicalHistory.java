package ar.com.veterinaria.app.entities.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
public class ClinicalHistory implements Serializable{

 private  long serialVersionUID;

 private  Integer id;

 private  String clinicalHistory;

 private  boolean deleted;

public ClinicalHistory() {
    super();
}public ClinicalHistory(String clinicalHistory) {
    super();
    this.clinicalHistory = clinicalHistory;
    this.deleted = false;
}
public String getClinicalHistory(){
    return clinicalHistory;
}


public Integer getId(){
    return id;
}


}