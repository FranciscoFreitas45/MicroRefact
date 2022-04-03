package com.csquard.mregister.model;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence;
import javax.validation.constraints.NotBlank;
import com.csquard.mregister.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "sales_regions")
public class SalesRegion extends DateAudit{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotBlank
 private  String name;

public SalesRegion(String name) {
    super();
    this.name = name;
}public SalesRegion() {
    super();
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


}