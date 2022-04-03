package lk.sliit.csse.procurementsystem.repositories;
 import lk.sliit.csse.procurementsystem.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import lk.sliit.csse.procurementsystem.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{


public List<Supplier> findByBlackListedFalse()
;

@Modifying
@Query("update Supplier s set s.blackListed = ?1 where s.name = ?2")
public int setBlackListedFor(Boolean isBlacklisted,String name)
;

}