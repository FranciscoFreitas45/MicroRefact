package com.csquard.mregister.model;
 import javax.persistence;
import com.csquard.mregister.model.audit.DateAudit;
@Entity
@Table(name = "asms")
public class Asm extends DateAudit{

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

@Column(name = "sales_region_id", nullable = false)
 private  Long salesRegionId;

// @OneToOne(cascade = CascadeType.ALL,
// fetch = FetchType.LAZY,
// mappedBy = "asm")
// @JsonIgnore
// private Tdr tdr;
// 
// @OneToOne(fetch = FetchType.LAZY)
// @JoinColumn (name="user_id",unique=true,nullable=false)
// @OnDelete(action = OnDeleteAction.CASCADE)
// private User user;
// @OneToOne(fetch = FetchType.LAZY)
// @JoinColumn (name="sales_region_id",unique=true,nullable=false)
// @OnDelete(action = OnDeleteAction.CASCADE)
// private SalesRegion salesRegion;
public Asm() {
}public Asm(String first_name, String last_name, Long id_no, Long user_id, Long sales_region_id) {
    super();
    this.first_name = first_name;
    this.last_name = last_name;
    this.id_no = id_no;
    this.userId = user_id;
    this.salesRegionId = sales_region_id;
}
public void setFirst_name(String first_name){
    this.first_name = first_name;
}


public Long getUser_id(){
    return userId;
}


public void setId_no(Long id_no){
    this.id_no = id_no;
}


public void setUser_id(Long user_id){
    this.userId = user_id;
}


public String getLast_name(){
    return last_name;
}


public String getFirst_name(){
    return first_name;
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


public Long getId_no(){
    return id_no;
}


public void setLast_name(String last_name){
    this.last_name = last_name;
}


}