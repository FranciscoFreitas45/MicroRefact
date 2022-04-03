package br.com.fatecmogidascruzes.employee.service.web;
 import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.devskiller.friendly_id.FriendlyId;
import br.com.fatecmogidascruzes.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class EmployeeTableRowDTO {

 private  UUID hash;

 private  String hashString;

 private  String name;

 private  String selectionType;

 private  String allocation;


public List<EmployeeTableRowDTO> listFrom(List<T> employees){
    List<EmployeeTableRowDTO> employeeTableRowDTOs = new ArrayList<>();
    employees.forEach(baseCategory -> employeeTableRowDTOs.add(EmployeeTableRowDTO.from(baseCategory)));
    return employeeTableRowDTOs;
}


public EmployeeTableRowDTO from(Employee employee){
    EmployeeTableRowDTO baseCategoryTableRowDTO = new EmployeeTableRowDTO();
    baseCategoryTableRowDTO.setHash(employee.getHash());
    baseCategoryTableRowDTO.setHashString(FriendlyId.toFriendlyId(employee.getHash()));
    baseCategoryTableRowDTO.setName(employee.getName());
    baseCategoryTableRowDTO.setSelectionType(employee.getSelectionType().getName());
    baseCategoryTableRowDTO.setAllocation(employee.getAllocation().getName());
    return baseCategoryTableRowDTO;
}


}