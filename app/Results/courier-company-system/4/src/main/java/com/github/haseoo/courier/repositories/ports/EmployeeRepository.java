package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.EmployeeModel;
import java.util.List;
import java.util.Optional;
public interface EmployeeRepository {


public Optional<EmployeeModel> getById(Long id)
;

public List<EmployeeModel> getList()
;

public List<EmployeeModel> findActiveByPesel(String pesel)
;

}