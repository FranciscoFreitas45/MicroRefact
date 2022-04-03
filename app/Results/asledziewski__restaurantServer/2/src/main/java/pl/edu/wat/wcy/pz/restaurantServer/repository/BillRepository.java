package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import javax.transaction.Transactional;
@Transactional
@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{


}