package com.cg.oms.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.repository.MedicineRepository;
import com.cg.oms.model.Medicine;
@Service
public class MedicineOrderMedicineService {

@Autowired
 private MedicineRepository medicinerepository;


public List<Medicine> getMedicineList(Long orderMedicineId){
return medicinerepository.getMedicineList(orderMedicineId);
}


public void setMedicineList(Long orderMedicineId,List<Medicine> medicineList){
medicinerepository.setMedicineList(orderMedicineId,medicineList);
}


}