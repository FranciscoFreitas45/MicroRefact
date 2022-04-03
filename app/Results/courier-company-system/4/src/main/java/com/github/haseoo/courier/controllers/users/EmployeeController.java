package com.github.haseoo.courier.controllers.users;
 import com.github.haseoo.courier.commandsdata.users.employees.EmployeeAddCommandData;
import com.github.haseoo.courier.commandsdata.users.employees.EmployeeEditCommandData;
import com.github.haseoo.courier.services.ports.EmployeeService;
import com.github.haseoo.courier.views.users.employees.EmployeeView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus.CREATED;
import org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

 private  EmployeeService employeeService;


@PreAuthorize("hasRole('ADMIN')")
@PostMapping
public ResponseEntity<EmployeeView> add(EmployeeAddCommandData addCommandData){
    return new ResponseEntity<>(EmployeeView.of(employeeService.addEmployee(addCommandData)), CREATED);
}


@PreAuthorize("hasRole('ADMIN')")
@GetMapping("{id}")
public ResponseEntity<EmployeeView> getById(Long id){
    return new ResponseEntity<>(EmployeeView.of(employeeService.getById(id)), OK);
}


@PreAuthorize("hasAnyRole({'ADMIN', 'LOGISTICIAN', 'COURIER'})")
@PutMapping("{id}")
public ResponseEntity<EmployeeView> edit(Long id,EmployeeEditCommandData editOperationData){
    return new ResponseEntity<>(EmployeeView.of(employeeService.editEmployee(id, editOperationData)), OK);
}


@PreAuthorize("hasRole('ADMIN')")
@GetMapping
public List<EmployeeView> getList(){
    return employeeService.getList().stream().map(EmployeeView::of).collect(Collectors.toList());
}


}