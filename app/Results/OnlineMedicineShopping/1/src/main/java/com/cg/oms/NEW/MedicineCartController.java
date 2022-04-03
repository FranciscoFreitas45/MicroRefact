package com.cg.oms.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.model.Medicine;
@RestController
@CrossOrigin
public class MedicineCartController {

@Autowired
 private MedicineCartService medicinecartservice;


@GetMapping
("/Cart/{id}/Medicine/getMedicineList")
public List<Medicine> getMedicineList(@PathVariable(name="id") Long cartId){
return medicinecartservice.getMedicineList(cartId);
}


@PutMapping
("/Cart/{id}/Medicine/setMedicineList")
public void setMedicineList(@PathVariable(name="id") Long cartId,@RequestBody List<Medicine> medicineList){
medicinecartservice.setMedicineList(cartId,medicineList);
}


}