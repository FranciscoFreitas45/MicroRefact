package org.gliderwiki.rest.ds;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.gliderwiki.rest.bean.Employee;
public class EmployeeDS {

 private  Map<Long,Employee> allEmployees;


public void add(Employee e){
    allEmployees.put(e.getId(), e);
}


public List<Employee> getAll(){
    List<Employee> employees = new ArrayList<Employee>();
    for (Iterator<Employee> it = allEmployees.values().iterator(); it.hasNext(); ) {
        Employee e = it.next();
        employees.add(e);
    }
    return employees;
}


public Employee get(long id){
    return allEmployees.get(id);
}


public void update(Employee e){
    allEmployees.put(e.getId(), e);
}


public void remove(long id){
    allEmployees.remove(id);
}


}