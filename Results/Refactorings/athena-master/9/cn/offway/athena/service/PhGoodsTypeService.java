import cn.offway.athena.domain.PhGoodsType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PhGoodsTypeService {


public PhGoodsType save(PhGoodsType phGoodsType)


public PhGoodsType findOne(Long id)


public void del(Long id)


public List<PhGoodsType> findAll()


}