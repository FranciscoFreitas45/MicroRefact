package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.commandsdata.users.employees.EmployeeAddCommandData;
import com.github.haseoo.courier.commandsdata.users.employees.EmployeeEditCommandData;
import com.github.haseoo.courier.servicedata.users.employees.EmployeeData;
import java.util.List;
public interface EmployeeService {


public EmployeeData editEmployee(Long id,EmployeeEditCommandData employeeEditOperationData)
;

public EmployeeData getById(Long id)
;

public List<EmployeeData> getList()
;

public EmployeeData addEmployee(EmployeeAddCommandData employeeAddOperationData)
;

}