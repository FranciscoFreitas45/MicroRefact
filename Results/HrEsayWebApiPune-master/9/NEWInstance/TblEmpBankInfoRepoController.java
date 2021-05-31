import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class TblEmpBankInfoRepoController {

 private TblEmpBankInfoRepo tblempbankinforepo;


@GetMapping
("/deleteEmpBankInfo")
public int deleteEmpBankInfo(@RequestParam(name = "empId") int empId){
  return tblempbankinforepo.deleteEmpBankInfo(empId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return tblempbankinforepo.save(Object);
}


@GetMapping
("/findByEmpIdAndDelStatus")
public TblEmpBankInfo findByEmpIdAndDelStatus(@RequestParam(name = "empId") int empId,@RequestParam(name = "del") int del){
  return tblempbankinforepo.findByEmpIdAndDelStatus(empId,del);
}


}