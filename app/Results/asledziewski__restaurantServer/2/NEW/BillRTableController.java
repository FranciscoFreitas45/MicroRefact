import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class BillRTableController {

 private BillRTableService billrtableservice;


@GetMapping
("/RTable/{id}/Bill/getBills")
public List<Bill> getBills(@PathVariable(name="id") Long rTableId){
return billrtableservice.getBills(rTableId);
}


@PutMapping
("/RTable/{id}/Bill/setBills")
public void setBills(@PathVariable(name="id") Long rTableId,@RequestBody List<Bill> bills){
billrtableservice.setBills(rTableId,bills);
}


@PutMapping
("/RTable/{id}/Bill/addBill")
public void addBill(@PathVariable(name="id") Long rTableId,@RequestBody Bill bill){
billrtableservice.addBill(rTableId,bill);
}


}