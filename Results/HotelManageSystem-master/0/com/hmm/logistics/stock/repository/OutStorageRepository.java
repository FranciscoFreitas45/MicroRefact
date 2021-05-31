import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.logistics.stock.entity.OutStorage;
@Repository
public interface OutStorageRepository extends PagingAndSortingRepository<OutStorage, Long> {


}