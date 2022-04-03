package com.cg.oms.repository;
 import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.oms.model.Medicine;
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{


public Optional<Medicine> findByMedicineManufacturerName(String medName)
;

public Optional<Medicine> findByMedicineName(String medName)
;

public List<Medicine> getMedicineList(Long cartId);

public void setMedicineList(Long cartId,List<Medicine> medicineList);

public List<Medicine> getMedicineList(Long orderMedicineId);

public void setMedicineList(Long orderMedicineId,List<Medicine> medicineList);

}