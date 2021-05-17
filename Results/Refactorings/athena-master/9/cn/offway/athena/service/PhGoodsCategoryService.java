import cn.offway.athena.domain.PhGoodsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PhGoodsCategoryService {


public Page<PhGoodsCategory> findByPid(Long pid,Pageable pageable)


public PhGoodsCategory save(PhGoodsCategory phGoodsCategory)


public PhGoodsCategory findOne(Long id)


public void delByPid(Long pid)


public void del(Long id)


public void resort(Long sort,Long theId)


public List<PhGoodsCategory> findAll()


}