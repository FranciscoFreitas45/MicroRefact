package com.easyshopping.listener;
 import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import com.easyshopping.entity.BaseEntity;
public class EntityListener {


@PrePersist
public void prePersist(BaseEntity entity){
    entity.setCreateDate(new Date());
    entity.setModifyDate(new Date());
}


@PreUpdate
public void preUpdate(BaseEntity entity){
    entity.setModifyDate(new Date());
}


}