package com.sobey.cmop.mvc.DTO;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class ChangeItem {

 private  Integer id;

 private  Change change;

 private  String fieldName;

 private  String oldValue;

 private  String oldString;

 private  String newValue;

 private  String newString;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

// Constructors
/**
 * default constructor
 */
public ChangeItem() {
}/**
 * full constructor
 */
public ChangeItem(Change change, String fieldName, String oldValue, String newValue) {
    this.change = change;
    this.fieldName = fieldName;
    this.oldValue = oldValue;
    this.newValue = newValue;
}
@Column(name = "old_string", length = 500)
public String getOldString(){
    return oldString;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "change_id", nullable = false)
public Change getChange(){
    return this.change;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "old_value", nullable = false, length = 200)
public String getOldValue(){
    return oldValue;
}


@Column(name = "new_value", nullable = false, length = 200)
public String getNewValue(){
    return newValue;
}


@Column(name = "new_string", length = 500)
public String getNewString(){
    return newString;
}


@Column(name = "field_name", nullable = false, length = 45)
public String getFieldName(){
    return this.fieldName;
}


public void setNewValue(String newValue){
    this.newValue = newValue;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNewValue"))

.queryParam("newValue",newValue)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNewString(String newString){
    this.newString = newString;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNewString"))

.queryParam("newString",newString)
;
restTemplate.put(builder.toUriString(),null);
}


public void setChange(Change change){
    this.change = change;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChange"))

.queryParam("change",change)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFieldName(String fieldName){
    this.fieldName = fieldName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFieldName"))

.queryParam("fieldName",fieldName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOldValue(String oldValue){
    this.oldValue = oldValue;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOldValue"))

.queryParam("oldValue",oldValue)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOldString(String oldString){
    this.oldString = oldString;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOldString"))

.queryParam("oldString",oldString)
;
restTemplate.put(builder.toUriString(),null);
}


}