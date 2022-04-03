package com.cg.oms.Request;
import com.cg.oms.DTO.Medicine;
public interface MedicineRequest {

   public List<Medicine> getMedicineList(Long orderMedicineId);
   public void setMedicineList(List<Medicine> medicineList,Long orderMedicineId);
}