package org.gliderwiki.rest.bean;
 import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "employees")
public class EmployeeList {

 private  int count;

 private  List<Employee> employees;

public EmployeeList() {
}public EmployeeList(List<Employee> employees) {
    this.employees = employees;
    this.count = employees.size();
}
@XmlElement(name = "employee")
public List<Employee> getEmployees(){
    return employees;
}


public void setEmployees(List<Employee> employees){
    this.employees = employees;
}


public int getCount(){
    return count;
}


public void setCount(int count){
    this.count = count;
}


}