import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.finance.logisticst.domain.InStorage;
import com.hmm.finance.logisticst.domain.InStorageDetailedDTO;
import com.hmm.logistics.stock.entity.InDetailed;
@Repository
public interface InDetailedRepository extends JpaSpecificationExecutor<InDetailed> {


@Query(value = "select new com.hmm.finance.logisticst.domain.InStorageDetailedDTO(i.id,i.goodsName,i.unit,i.price,i.amount)" + "  from InDetailed i where inAll=?1")
public Page<InStorageDetailedDTO> findInStorageDetailedByInAll(InStorage inStorageId,Pageable pageable)


}