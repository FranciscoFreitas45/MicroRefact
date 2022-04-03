package run.halo.app.model.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.utils.DateUtils;
@Data
@ToString
@MappedSuperclass
@EqualsAndHashCode
public class BaseEntity {

@Column(name = "create_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date createTime;

@Column(name = "update_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date updateTime;


@PrePersist
public void prePersist(){
    Date now = DateUtils.now();
    if (createTime == null) {
        createTime = now;
    }
    if (updateTime == null) {
        updateTime = now;
    }
}


@PreRemove
public void preRemove(){
    updateTime = new Date();
}


@PreUpdate
public void preUpdate(){
    updateTime = new Date();
}


}