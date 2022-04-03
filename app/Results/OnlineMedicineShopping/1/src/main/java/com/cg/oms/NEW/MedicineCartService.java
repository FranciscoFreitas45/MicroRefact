package com.cg.oms.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.repository.MedicineRepository;
import com.cg.oms.model.Medicine;
@Service
public class MedicineCartService {

@Autowired
 private MedicineRepository medicinerepository;


public List<Medicine> getMedicineList(Long cartId){
return medicinerepository.getMedicineList(cartId);
}


public void setMedicineList(Long cartId,List<Medicine> medicineList){
medicinerepository.setMedicineList(cartId,medicineList);
}


}