package com.csquard.mregister.model;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.csquard.mregister.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name = "tdrs")
public class Tdr extends DateAudit{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "first_name", nullable = false)
 private  String first_name;

@Column(name = "last_name", nullable = false)
 private  String last_name;

@Column(name = "id_no", nullable = false)
 private  Long id_no;

@Column(name = "user_id", nullable = false)
 private  Long userId;

@Column(name = "sales_area_id", nullable = false)
 private  Long salesAreaId;

@Column(name = "asm_id", nullable = false)
 private  Long asmId;

public Tdr() {
}public Tdr(String first_name, String last_name, Long id_no, Long user_id, Long sales_area_id, Long asm_id) {
    super();
    this.first_name = first_name;
    this.last_name = last_name;
    this.id_no = id_no;
    this.userId = user_id;
    this.salesAreaId = sales_area_id;
    this.asmId = asm_id;
}
public void setId_no(Long id_no){
    this.id_no = id_no;
}


public Long getId(){
    return id;
}


public Long getSales_area_id(){
    return salesAreaId;
}


public void setLast_name(String last_name){
    this.last_name = last_name;
}


public void setFirst_name(String first_name){
    this.first_name = first_name;
}


public Long getUser_id(){
    return userId;
}


public Long getAsm_id(){
    return asmId;
}


public void setUser_id(Long user_id){
    this.userId = user_id;
}


public String getLast_name(){
    return last_name;
}


public void setAsm_id(Long asm_id){
    this.asmId = asm_id;
}


public String getFirst_name(){
    return first_name;
}


public void setId(Long id){
    this.id = id;
}


public Long getId_no(){
    return id_no;
}


public void setSales_area_id(Long sales_area_id){
    this.salesAreaId = sales_area_id;
}


}