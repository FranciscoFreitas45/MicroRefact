package com.pl.edu.wat.wcy.pz.restaurantServer.repository;

import com.pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "Select * from bill b where b.bill_id = ?1", nativeQuery = true)
public List<Bill> getBills(Long rTableId);

    @Modifying
    @Query(value = "update dish d set d.price = 0 where d.dish_id = ?2", nativeQuery = true)

public void setBills(Long rTableId, List<Bill> bills);
    @Modifying
    @Query(value = "update dish d set d.price = 0 where d.dish_id = ?2", nativeQuery = true)
public void addBill(Long rTableId,Bill bill);

}