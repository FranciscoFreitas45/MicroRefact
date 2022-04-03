package com.cg.oms.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.oms.model.OrderMedicine;
@Repository
public interface OrderMedicineRepository extends JpaRepository<OrderMedicine, Long>{


}