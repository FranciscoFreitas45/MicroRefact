package com.sobey.cmop.mvc.DTO;
 import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.google.common.collect.Lists;
public class StorageItem {

 private  Integer id;

 private  Apply apply;

 private  String identifier;

 private  Integer space;

 private  Integer storageType;

 private  String controllerAlias;

 private  String volume;

 private  String mountPoint;

 private  List<ComputeItem> computeItemList;

// Constructors
/**
 * default constructor
 */
public StorageItem() {
}/**
 * minimal constructor
 */
public StorageItem(Apply apply, String identifier, Integer space, Integer storageType) {
    this.apply = apply;
    this.identifier = identifier;
    this.space = space;
    this.storageType = storageType;
}/**
 * full constructor
 */
public StorageItem(Apply apply, String identifier, Integer space, Integer storageType, String controllerAlias, String volume, String mountPoint) {
    this.apply = apply;
    this.identifier = identifier;
    this.space = space;
    this.storageType = storageType;
    this.controllerAlias = controllerAlias;
    this.volume = volume;
    this.mountPoint = mountPoint;
}
public void setMountPoint(String mountPoint){
    this.mountPoint = mountPoint;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public void setControllerAlias(String controllerAlias){
    this.controllerAlias = controllerAlias;
}


@ManyToMany
@JoinTable(name = "compute_storage_item", joinColumns = { @JoinColumn(name = "storage_item_id") }, inverseJoinColumns = { @JoinColumn(name = "compute_item_id") })
// Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
// 集合按id排序.
@OrderBy("id")
// 集合中对象id的缓存.
@NotFound(action = NotFoundAction.IGNORE)
public List<ComputeItem> getComputeItemList(){
    return computeItemList;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public void setVolume(String volume){
    this.volume = volume;
}


public String extractToString(List<ComputeItem> computeItems){
    StringBuilder sb = new StringBuilder();
    for (ComputeItem computeItem : computeItems) {
        sb.append(computeItem.getIdentifier()).append("(").append(computeItem.getRemark() + " - " + computeItem.getInnerIp()).append(")").append(",");
    }
    String str = sb.toString();
    return str.length() > 0 ? str.substring(0, str.length() - 1) : "";
}


public void setSpace(Integer space){
    this.space = space;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id", nullable = false)
public Apply getApply(){
    return this.apply;
}


@Column(name = "volume", length = 45)
public String getVolume(){
    return this.volume;
}


public void setComputeItemList(List<ComputeItem> computeItemList){
    this.computeItemList = computeItemList;
}


@Transient
public String getMountComputes(){
    return extractToString(computeItemList);
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
}


@Column(name = "space", nullable = false)
public Integer getSpace(){
    return this.space;
}


public void setId(Integer id){
    this.id = id;
}


@Override
public String toString(){
    return ToStringBuilder.reflectionToString(this);
}


public void setApply(Apply apply){
    this.apply = apply;
}


public void setStorageType(Integer storageType){
    this.storageType = storageType;
}


@Column(name = "mount_point", length = 45)
public String getMountPoint(){
    return mountPoint;
}


@Column(name = "storage_type", nullable = false)
public Integer getStorageType(){
    return storageType;
}


@Column(name = "controller_alias", length = 45)
public String getControllerAlias(){
    return this.controllerAlias;
}


}