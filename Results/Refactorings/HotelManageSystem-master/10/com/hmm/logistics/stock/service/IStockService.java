import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.room.dto.DailyNecessaryDto;
public interface IStockService {


public boolean existsById(Long id)


public Stock findById(Long id)


public Stock save(Stock entity)


public long count()


public void deleteById(Long id)


public void deleteAll(Long[] ids)


public List<DailyNecessaryDto> findByStockType()


public Page<Stock> findAll(Specification<Stock> spec,Pageable pageable)


public Stock findByGoodsNo(String goodsNo)


}