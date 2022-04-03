package org.jugbd.mnet.domain;
 import org.jugbd.mnet.domain.enums.AuditAction;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
public class AuditLog extends PersistentObject{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long auditLogId;

@Column(length = 20)
@Enumerated(EnumType.STRING)
 private  AuditAction action;

@Column(columnDefinition = "TEXT")
 private  String detail;

 private  Long entityId;

 private  String entityName;


public void setAction(AuditAction action){
    this.action = action;
}


public void setDetail(String detail){
    this.detail = detail;
}


public String getDetail(){
    return detail;
}


public void setEntityId(long entityId){
    this.entityId = entityId;
}


public AuditAction getAction(){
    return action;
}


public long getEntityId(){
    return entityId;
}


public String getEntityName(){
    return entityName;
}


public void setEntityName(String entityName){
    this.entityName = entityName;
}


public Long getAuditLogId(){
    return auditLogId;
}


public void setAuditLogId(Long auditLogId){
    this.auditLogId = auditLogId;
}


}