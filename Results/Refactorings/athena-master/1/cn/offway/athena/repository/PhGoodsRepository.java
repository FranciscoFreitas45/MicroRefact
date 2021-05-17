import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import cn.offway.athena.domain.PhGoods;
public interface PhGoodsRepository extends JpaRepository<PhGoods, Long> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_goods where id in(?1)")
public int deleteByGoodsIds(List<Long> goodsIds)


@Query(nativeQuery = true, value = "select count(*) from ph_goods where id in(?1) and status='1'")
public int countByGoodsIds(List<Long> goodsIds)


public List<PhGoods> findByBrandId(Long brandId)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "update ph_goods_stock set goods_name=?2 where goods_id=?1")
public int updateGoodsStock(Long id,String name)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "update ph_order_goods set goods_name=?2 where goods_id=?1")
public int updateOrderGoods(Long id,String name)


}