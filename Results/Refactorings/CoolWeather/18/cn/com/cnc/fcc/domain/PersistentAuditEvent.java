import javax.persistence;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map;
@Entity
@Table(name = "jhi_persistent_audit_event")
public class PersistentAuditEvent implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "event_id")
 private  Long id;

@NotNull
@Column(nullable = false)
 private  String principal;

@Column(name = "event_date")
 private  Instant auditEventDate;

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


public Instant getAuditEventDate(){
    return auditEventDate;
}


public void setData(Map<String,String> data){
    this.data = data;
}


public Long getId(){
    return id;
}


public String getPrincipal(){
    return principal;
}


public void setPrincipal(String principal){
    this.principal = principal;
}


public void setAuditEventDate(Instant auditEventDate){
    this.auditEventDate = auditEventDate;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    PersistentAuditEvent persistentAuditEvent = (PersistentAuditEvent) o;
    return !(persistentAuditEvent.getId() == null || getId() == null) && Objects.equals(getId(), persistentAuditEvent.getId());
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    return "PersistentAuditEvent{" + "principal='" + principal + '\'' + ", auditEventDate=" + auditEventDate + ", auditEventType='" + auditEventType + '\'' + '}';
}


public Map<String,String> getData(){
    return data;
}


public void setAuditEventType(String auditEventType){
    this.auditEventType = auditEventType;
}


}