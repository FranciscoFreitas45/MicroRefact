package com.cg.oms.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.oms.converter.MedicineConverter;
import com.cg.oms.exception.MedicineNotFoundException;
import com.cg.oms.model.Medicine;
import com.cg.oms.repository.MedicineRepository;
import com.cg.oms.vo.MedicineVo;
@Service
public class MedicineServiceImpl implements MedicineService{

 public  String EXCEPTION_MESSAGE;

@Autowired
 private  MedicineRepository medicineRepository;

@Autowired
 private  MedicineConverter medicineConverter;


@Override
public MedicineVo getMedicineByName(String name){
    Medicine medicine = ((Optional<Medicine>) medicineRepository.findByMedicineName(name)).orElseThrow(() -> new MedicineNotFoundException(EXCEPTION_MESSAGE + name));
    return medicineConverter.modelToVo(medicine);
}


@Override
public List<MedicineVo> getAllMedicine(){
    List<Medicine> medicine = medicineRepository.findAll();
    return medicineConverter.modelToVo(medicine);
}


@Override
public MedicineVo getMedicineByManufacturerName(String name){
    Medicine medicine = ((Optional<Medicine>) medicineRepository.findByMedicineManufacturerName(name)).orElseThrow(() -> new MedicineNotFoundException(EXCEPTION_MESSAGE + name));
    return medicineConverter.modelToVo(medicine);
}


@Override
public String deleteMedicine(Long id){
    Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new MedicineNotFoundException(EXCEPTION_MESSAGE + id));
    medicineRepository.delete(medicine);
    return "Record Deleted Successfully!!";
}


@Override
public MedicineVo getMedicineById(Long id){
    Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new MedicineNotFoundException(EXCEPTION_MESSAGE + id));
    return medicineConverter.modelToVo(medicine);
}


@Override
public String updateMedicine(Long id,MedicineVo newMedicine){
    Medicine oldMedicine = medicineRepository.findById(id).orElseThrow(() -> new MedicineNotFoundException(EXCEPTION_MESSAGE + id));
    oldMedicine.setMedicineCategory(newMedicine.getMedicineCategory());
    oldMedicine.setMedicineDescription(newMedicine.getMedicineDescription());
    oldMedicine.setMedicineExpiryDate(newMedicine.getMedicineExpiryDate());
    oldMedicine.setMedicineManufactureDate(newMedicine.getMedicineManufacturerDate());
    oldMedicine.setMedicineManufacturerName(newMedicine.getMedicineManufacturerName());
    oldMedicine.setMedicineName(newMedicine.getMedicineName());
    oldMedicine.setMedicinePrice(newMedicine.getMedicinePrice());
    oldMedicine.setMedicineQuantity(newMedicine.getMedicineQuantity());
    Medicine updatedMedicine = medicineRepository.save(oldMedicine);
    MedicineVo savedVo = medicineConverter.modelToVo(updatedMedicine);
    return "Updated Successfully" + savedVo.toString();
}


@Override
public String saveMedicine(MedicineVo medicineVo){
    Medicine medicine = medicineConverter.voToModel(medicineVo);
    medicine = medicineRepository.save(medicine);
    MedicineVo savedVo = medicineConverter.modelToVo(medicine);
    return "Registered SuccessFully!!! " + savedVo.toString();
}


}