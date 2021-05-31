import cn.offway.athena.domain.PhOrderGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PhOrderGoodsService {


public int getMaxBatch(String oid)


public List<PhOrderGoods> findByOrderNo(String orderNo)


public List<PhOrderGoods> findNormalByOrderNo(String orderNo)


public Page<PhOrderGoods> findByBrandId(String brandId,Pageable page)


public PhOrderGoods save(PhOrderGoods phOrderGoods)


public PhOrderGoods findOne(Long id)


public int getRest(String oid)


}