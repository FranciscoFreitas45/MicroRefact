package com.cg.oms.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.oms.exception.MedicineNotFoundException;
import com.cg.oms.service.MedicineServiceImpl;
import com.cg.oms.vo.MedicineVo;
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
@RestController
public class MedicineController {

@Autowired
 private  MedicineServiceImpl medicineServiceImpl;


@GetMapping("/medicine/getmedicine/name/{medicinename}")
public ResponseEntity<MedicineVo> getMedicineByName(String medicineName){
    return ResponseEntity.ok().body(medicineServiceImpl.getMedicineByName(medicineName));
}


@PostMapping("/medicine/addnewMedicine")
public ResponseEntity<MedicineVo> addMedicine(MedicineVo medicineVo){
    medicineServiceImpl.saveMedicine(medicineVo);
    return ResponseEntity.ok(medicineVo);
}


@GetMapping("/medicine/getallmedicine")
public ResponseEntity<List<MedicineVo>> getAllMedicine(){
    return ResponseEntity.ok(medicineServiceImpl.getAllMedicine());
}


@GetMapping("/medicine/getmedicine/manufacturername/{manufacturername}")
public ResponseEntity<MedicineVo> getMedicineByManufacturerName(String manufacturerName){
    return ResponseEntity.ok().body(medicineServiceImpl.getMedicineByManufacturerName(manufacturerName));
}


@DeleteMapping("/medicine/deletemedicine/{medicineid}")
public String deleteMedicine(long medicineid){
    return medicineServiceImpl.deleteMedicine(medicineid);
}


@GetMapping("/medicine/getmedicine/{id}")
public ResponseEntity<MedicineVo> getMedicineById(Long id){
    MedicineVo medicineVo = medicineServiceImpl.getMedicineById(id);
    return ResponseEntity.ok().body(medicineVo);
}


@PutMapping("/medicine/updatemedicine/{id}")
public String updateMedicine(long id,MedicineVo medicineVo){
    return medicineServiceImpl.updateMedicine(id, medicineVo);
}


}