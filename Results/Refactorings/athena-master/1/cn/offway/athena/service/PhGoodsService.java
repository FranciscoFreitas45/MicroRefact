import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.offway.athena.domain.PhGoods;
public interface PhGoodsService {


public List<PhGoods> findByBrandId(Long brandId)


public List<PhGoods> save(List<PhGoods> phGoods)


public PhGoods findOne(Long id)


public boolean imagesDelete(Long goodsImageId)


public Page<PhGoods> findByPage(String name,Long brandId,String isOffway,List<Long> brandIds,String status,String type,String category,Pageable page)


public List<PhGoods> findAll(String name,Object value,String brandId)


public String delete(List<Long> goodsIds)


}