package org.gliderwiki.rest.bean;
 import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "employee")
public class Employee {

 private  long id;

 private  String name;

 private  String email;

public Employee() {
}public Employee(long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setEmail(String email){
    this.email = email;
}


public void setId(long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public long getId(){
    return id;
}


}