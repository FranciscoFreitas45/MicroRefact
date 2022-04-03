package com.csquard.mregister.model;
 import javax.persistence;
import com.csquard.mregister.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "sales_areas")
public class SalesArea extends DateAudit{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "name", nullable = false)
 private  String name;

@Column(name = "sales_region_id", nullable = false)
 private  Long salesRegionId;

public SalesArea(String name, Long salesRegionId) {
    super();
    this.name = name;
    this.salesRegionId = salesRegionId;
}public SalesArea() {
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public Long getSales_region_id(){
    return salesRegionId;
}


public void setId(Long id){
    this.id = id;
}


public void setSales_region_id(Long sales_region_id){
    this.salesRegionId = sales_region_id;
}


public Long getId(){
    return id;
}


}