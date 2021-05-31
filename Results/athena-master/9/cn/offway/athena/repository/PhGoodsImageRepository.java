import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import cn.offway.athena.domain.PhGoodsImage;
public interface PhGoodsImageRepository extends JpaSpecificationExecutor<PhGoodsImage> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_goods_image where goods_id in(?1)")
public int deleteByGoodsIds(List<Long> goodsIds)


@Query(nativeQuery = true, value = "select * from ph_goods_image where goods_id=?1 order by image_url")
public List<PhGoodsImage> findByGoodsId(Long goodsId)


}