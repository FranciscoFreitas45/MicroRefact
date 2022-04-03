package com.easyshopping.entity;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.groups.Default;
import com.easyshopping.listener.EntityListener;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, creatorVisibility = Visibility.NONE)
@EntityListeners(EntityListener.class)
@MappedSuperclass
public class BaseEntity implements Serializable{

 private  long serialVersionUID;

 public  String ID_PROPERTY_NAME;

 public  String CREATE_DATE_PROPERTY_NAME;

 public  String MODIFY_DATE_PROPERTY_NAME;

 private  Long id;

 private  Date createDate;

 private  Date modifyDate;


@JsonProperty
@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@DateBridge(resolution = Resolution.SECOND)
@Column(nullable = false)
public Date getModifyDate(){
    return modifyDate;
}


@JsonProperty
@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@DateBridge(resolution = Resolution.SECOND)
@Column(nullable = false, updatable = false)
public Date getCreateDate(){
    return createDate;
}


public void setCreateDate(Date createDate){
    this.createDate = createDate;
}


@Override
public int hashCode(){
    int hashCode = 17;
    hashCode += null == getId() ? 0 : getId().hashCode() * 31;
    return hashCode;
}


@Override
public boolean equals(Object obj){
    if (obj == null) {
        return false;
    }
    if (this == obj) {
        return true;
    }
    if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
        return false;
    }
    BaseEntity other = (BaseEntity) obj;
    return getId() != null ? getId().equals(other.getId()) : false;
}


public void setId(Long id){
    this.id = id;
}


@JsonProperty
@DocumentId
@Id
// MySQL/SQLServer: @GeneratedValue(strategy = GenerationType.AUTO)
// Oracle: @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
public Long getId(){
    return id;
}


public void setModifyDate(Date modifyDate){
    this.modifyDate = modifyDate;
}


}