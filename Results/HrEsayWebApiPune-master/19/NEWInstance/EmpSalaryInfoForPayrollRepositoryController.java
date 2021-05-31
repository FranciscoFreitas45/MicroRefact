import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class EmpSalaryInfoForPayrollRepositoryController {

 private EmpSalaryInfoForPayrollRepository empsalaryinfoforpayrollrepository;


@GetMapping
("/getListForfixunfixAttendance")
public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendance(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "isFixed") int isFixed,@RequestParam(name = "sts") String sts,@RequestParam(name = "locId") List<Integer> locId,@RequestParam(name = "typeId") int typeId,@RequestParam(name = "deptId") int deptId){
  return empsalaryinfoforpayrollrepository.getListForfixunfixAttendance(month,year,isFixed,sts,locId,typeId,deptId);
}


@GetMapping
("/getListForfixunfixAttendanceDeptId")
public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendanceDeptId(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "isFixed") int isFixed,@RequestParam(name = "sts") String sts,@RequestParam(name = "locId") List<Integer> locId,@RequestParam(name = "deptId") int deptId){
  return empsalaryinfoforpayrollrepository.getListForfixunfixAttendanceDeptId(month,year,isFixed,sts,locId,deptId);
}


@GetMapping
("/getListForfixunfixAttendanceTypeId")
public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendanceTypeId(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "isFixed") int isFixed,@RequestParam(name = "sts") String sts,@RequestParam(name = "locId") List<Integer> locId,@RequestParam(name = "typeId") int typeId){
  return empsalaryinfoforpayrollrepository.getListForfixunfixAttendanceTypeId(month,year,isFixed,sts,locId,typeId);
}


}