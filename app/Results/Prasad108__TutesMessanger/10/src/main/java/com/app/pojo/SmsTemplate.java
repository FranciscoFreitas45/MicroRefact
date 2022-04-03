package com.app.pojo;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "SmsTemplate", catalog = "tutesmessanger")
public class SmsTemplate {

 private  int id;

 private  String name;

 private  String description;

 private  String text;

 private  String intendedFor;

public SmsTemplate() {
}public SmsTemplate(int id, String name, String intendedFor) {
    this.id = id;
    this.name = name;
    this.intendedFor = intendedFor;
}public SmsTemplate(int id, String name, String description, String text, String intendedFor) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.text = text;
    this.intendedFor = intendedFor;
}
public void setName(String name){
    this.name = name;
}


@Column(name = "name", nullable = false, length = 45)
public String getName(){
    return this.name;
}


@Column(name = "intendedFor", nullable = false, length = 45)
public String getIntendedFor(){
    return this.intendedFor;
}


@Column(name = "text", nullable = false, length = 200)
public String getText(){
    return this.text;
}


public void setId(int id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public int getId(){
    return this.id;
}


public void setDescription(String description){
    this.description = description;
}


public void setIntendedFor(String intendedFor){
    this.intendedFor = intendedFor;
}


@Column(name = "description", nullable = false, length = 200)
public String getDescription(){
    return this.description;
}


public void setText(String text){
    this.text = text;
}


}