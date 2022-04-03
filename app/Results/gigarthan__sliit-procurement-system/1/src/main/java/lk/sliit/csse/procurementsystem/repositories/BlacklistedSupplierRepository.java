package lk.sliit.csse.procurementsystem.repositories;
 import lk.sliit.csse.procurementsystem.models.BlacklistedSupplier;
import lk.sliit.csse.procurementsystem.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface BlacklistedSupplierRepository extends JpaRepository<BlacklistedSupplier, Long>{


@Modifying
@Query("update BlacklistedSupplier s set s.supplier = ?1 where s.id = ?2")
public int setBlackListedFor(Supplier supplier,Long id)
;

}