package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.servicedata.users.employees.CourierData;
import com.github.haseoo.courier.servicedata.users.employees.EmployeeAddOperationData;
import com.github.haseoo.courier.servicedata.users.employees.EmployeeEditOperationData;
import java.util.List;
public interface CourierService {


public CourierData add(EmployeeAddOperationData courierOperationData)
;

public CourierData getById(Long id)
;

public CourierData edit(Long id,EmployeeEditOperationData courierEditOperationData)
;

public List<CourierData> getList()
;

}