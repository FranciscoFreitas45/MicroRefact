import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import javax.transaction.Transactional;
@Transactional
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {


public List<Bill> getBills(Long rTableId)

public void setBills(Long rTableId,List<Bill> bills)

public void addBill(Long rTableId,Bill bill)

}