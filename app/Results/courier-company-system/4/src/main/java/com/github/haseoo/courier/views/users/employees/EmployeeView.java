package com.github.haseoo.courier.views.users.employees;
 import com.github.haseoo.courier.enums.EmployeeType;
import com.github.haseoo.courier.servicedata.users.employees.CourierData;
import com.github.haseoo.courier.servicedata.users.employees.EmployeeData;
import com.github.haseoo.courier.servicedata.users.employees.LogisticianData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel.PRIVATE;
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Builder
public class EmployeeView {

 private  Long id;

 private  EmployeeType employeeType;

 private  String pesel;

 private  String name;

 private  String surname;

 private  String phoneNumber;


public EmployeeView of(LogisticianData logisticianData){
    return EmployeeView.builder().id(logisticianData.getId()).name(logisticianData.getName()).surname(logisticianData.getSurname()).pesel(logisticianData.getPesel()).phoneNumber(logisticianData.getPesel()).employeeType(logisticianData.getEmployeeType()).build();
}


}