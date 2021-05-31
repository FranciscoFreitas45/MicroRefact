import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.logistics.loseGoods.entity.LoseGoods;
import com.hmm.logistics.stock.entity.DoSend;
@Repository
public interface LoseGoodsRepository extends PagingAndSortingRepository<LoseGoods, Long> {


}