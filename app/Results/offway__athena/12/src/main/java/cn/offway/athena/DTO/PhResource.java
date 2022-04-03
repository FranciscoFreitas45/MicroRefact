package cn.offway.athena.DTO;
 import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import cn.offway.athena.utils.TreeEntity;
public class PhResource implements Serializable,TreeEntity<PhResource>{

 private  Long id;

 private  Date createdtime;

 private  String icon;

 private  String link;

 private  String name;

 private  Long sort;

 private  Long parentId;

 public  List<PhResource> childList;


@Column(name = "name", length = 255)
public String getName(){
    return name;
}


public void setChildList(List<PhResource> childList){
    this.childList = childList;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "createdtime")
public Date getCreatedtime(){
    return createdtime;
}


@Column(name = "icon", length = 255)
public String getIcon(){
    return icon;
}


public void setLink(String link){
    this.link = link;
}


@Column(name = "sort", length = 11)
public Long getSort(){
    return sort;
}


@Column(name = "link", length = 255)
public String getLink(){
    return link;
}


public void setParentId(Long parentId){
    this.parentId = parentId;
}


@Transient
public List<PhResource> getChildList(){
    return childList;
}


@Column(name = "parent_id", length = 20)
public Long getParentId(){
    return parentId;
}


}