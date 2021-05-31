import java.util.List;
import cn.offway.athena.domain.PhGoodsImage;
public interface PhGoodsImageService {


public int deleteByGoodsIds(List<Long> goodsIds)


public List<PhGoodsImage> save(List<PhGoodsImage> phGoodsImages)


public PhGoodsImage findOne(Long id)


public List<PhGoodsImage> findByGoodsId(Long goodsId)


public void delete(List<PhGoodsImage> phGoodsImages)


}