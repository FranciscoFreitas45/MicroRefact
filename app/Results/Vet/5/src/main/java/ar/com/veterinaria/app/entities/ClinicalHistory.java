package ar.com.veterinaria.app.entities;
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
@Entity
@Table(name = "clinicalhistory")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ClinicalHistory implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idClinicalHistory", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@Column(name = "clinicalHistory", nullable = true, length = 50)
 private  String clinicalHistory;

@Column(name = "deleted", length = 10)
@JsonIgnore
 private  boolean deleted;

public ClinicalHistory() {
    super();
}public ClinicalHistory(String clinicalHistory) {
    super();
    this.clinicalHistory = clinicalHistory;
    this.deleted = false;
}
public void setClinicalHistory(String numberClinicalHistory){
    this.clinicalHistory = numberClinicalHistory;
}


public String getClinicalHistory(){
    return clinicalHistory;
}


public boolean isDeleted(){
    return deleted;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}