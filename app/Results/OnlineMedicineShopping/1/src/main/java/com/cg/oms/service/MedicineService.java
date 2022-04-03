package com.cg.oms.service;
 import java.util.List;
import com.cg.oms.exception.MedicineNotFoundException;
import com.cg.oms.vo.MedicineVo;
public interface MedicineService {


public MedicineVo getMedicineByName(String name)
;

public List<MedicineVo> getAllMedicine()
;

public MedicineVo getMedicineByManufacturerName(String name)
;

public String deleteMedicine(Long id)
;

public MedicineVo getMedicineById(Long id)
;

public String updateMedicine(Long id,MedicineVo newMedicine)
;

public String saveMedicine(MedicineVo medicineVo)
;

}