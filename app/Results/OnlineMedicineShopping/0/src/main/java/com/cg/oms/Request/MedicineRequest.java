package com.cg.oms.Request;
import com.cg.oms.DTO.Medicine;
import java.util.*;
public interface MedicineRequest {

   public List<Medicine> getMedicineList(Long cartId);
   public void setMedicineList(List<Medicine> medicineList,Long cartId);
}