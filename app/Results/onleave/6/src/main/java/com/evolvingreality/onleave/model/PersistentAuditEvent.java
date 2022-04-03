package com.evolvingreality.onleave.model;
 import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
@Entity
@Table(name = "APP_PERSISTENT_AUDIT_EVENT")
public class PersistentAuditEvent {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "event_id")
 private  Long id;

@NotNull
@Column(nullable = false)
 private  String principal;

@Column(name = "event_date")
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
 private  LocalDateTime auditEventDate;

@Column(name = "event_type")
 private  String auditEventType;

@ElementCollection
@MapKeyColumn(name = "name")
@Column(name = "value")
@CollectionTable(name = "APP_PERSISTENT_AUDIT_EVT_DATA", joinColumns = @JoinColumn(name = "event_id"))
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