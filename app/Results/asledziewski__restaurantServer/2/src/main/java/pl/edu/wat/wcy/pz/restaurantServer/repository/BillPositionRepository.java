package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 import pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
 import pl.edu.wat.wcy.pz.restaurantServer.entity.BillPosition;
import javax.transaction.Transactional;
 import java.util.List;

@Transactional
@Repository
public interface BillPositionRepository extends JpaRepository<BillPosition, Long>{


public List<Bill> getBills(Long rTableId);

public void setBills(Long rTableId, List<Bill> bills);

public void addBill(Long rTableId,Bill bill);

}