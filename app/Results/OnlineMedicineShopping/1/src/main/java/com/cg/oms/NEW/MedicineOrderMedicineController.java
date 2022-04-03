package com.cg.oms.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.model.Medicine;
@RestController
@CrossOrigin
public class MedicineOrderMedicineController {

@Autowired
 private MedicineOrderMedicineService medicineordermedicineservice;


@GetMapping
("/OrderMedicine/{id}/Medicine/getMedicineList")
public List<Medicine> getMedicineList(@PathVariable(name="id") Long orderMedicineId){
return medicineordermedicineservice.getMedicineList(orderMedicineId);
}


@PutMapping
("/OrderMedicine/{id}/Medicine/setMedicineList")
public void setMedicineList(@PathVariable(name="id") Long orderMedicineId,@RequestBody List<Medicine> medicineList){
medicineordermedicineservice.setMedicineList(orderMedicineId,medicineList);
}


}