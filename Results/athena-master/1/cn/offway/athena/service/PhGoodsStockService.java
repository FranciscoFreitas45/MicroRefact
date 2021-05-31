import cn.offway.athena.domain.PhGoodsStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface PhGoodsStockService {


public int countByGoodsIdAndColorAndSize(Long goodsId,String color,String size)


public List<PhGoodsStock> findByPid(Long goodsId)


public int deleteByGoodsIds(List<Long> goodsIds)


public boolean updateStock(String orderNo)


public PhGoodsStock save(PhGoodsStock phGoodsStock)


public PhGoodsStock findOne(Long id)


public int updateImage(Long goodsId,String color,String image)


public String findImage(String color,Long goodsId)


public Page<PhGoodsStock> findByPage(String sku,Long brandId,String brandName,Long goodsId,String goodsName,String isOffway,String color,String size,List<Long> brandIds,Pageable page)


public int deleteByIds(List<Long> ids)


}