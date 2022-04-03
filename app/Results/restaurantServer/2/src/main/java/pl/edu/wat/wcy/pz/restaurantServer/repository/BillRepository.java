package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import javax.transaction.Transactional;
import java.util.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Transactional
@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{



@Query(value = "Select * from bill b  where b.bill_id = ?1", nativeQuery = true)
public List<Bill> getBills(Long rTableId);


@Modifying
@Query(value = "update bill b set b.bill_id = ?1 where b.bill_id = ?2", nativeQuery = true)
public void setBills(Long rTableId,List<Bill> bills);


//@Query(value = "insert into bill (commit_id, activity_id) VALUES (?1, ?2)", nativeQuery = true)
//public void addBill(Long rTableId,Bill bill){
//    this.save(bill);
//}
    
}