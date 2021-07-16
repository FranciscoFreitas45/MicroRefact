import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.RTable;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface RTableRepository extends JpaRepository<RTable, Long> {


public List<RTable> findAllBySize(int size)


public boolean existsBySize(int size)


public boolean existsByNumber(int number)


public void setStatus(Long id,String status)

}