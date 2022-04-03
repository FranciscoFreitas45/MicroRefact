package edu.xr.campusweibo.domain;
 import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
@Entity
@Table(name = "jhi_persistent_audit_event")
public class PersistentAuditEvent implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "event_id")
 private  Long id;

@NotNull
@Column(nullable = false)
 private  String principal;

@Column(name = "event_date")
 private  LocalDateTime auditEventDate;

@Column(name = "event_type")
 private  String auditEventType;

@ElementCollection
@MapKeyColumn(name = "name")
@Column(name = "value")
@CollectionTable(name = "jhi_persistent_audit_evt_data", joinColumns = @JoinColumn(name = "event_id"))
 private  Map<String,String> data;


public String getAuditEventType(){
    return auditEventType;
}


public String getPrincipal(){
    return principal;
}


public void setPrincipal(String principal){
    this.principal = principal;
}


public void setAuditEventDate(LocalDateTime auditEventDate){
    this.auditEventDate = auditEventDate;
}


public LocalDateTime getAuditEventDate(){
    return auditEventDate;
}


public void setData(Map<String,String> data){
    this.data = data;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public Map<String,String> getData(){
    return data;
}


public void setAuditEventType(String auditEventType){
    this.auditEventType = auditEventType;
}


}