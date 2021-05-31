import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import cn.offway.athena.domain.PhGoodsStock;
public interface PhGoodsStockRepository extends JpaRepository<PhGoodsStock, Long> {


public int countByGoodsIdAndColorAndSize(Long goodsId,String color,String size)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_goods_stock where goods_id in(?1)")
public int deleteByGoodsIds(List<Long> goodsIds)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "update ph_goods_stock s set s.stock =  s.stock+1 where  s.goods_id=?1  and s.color =?2 and s.size=?3")
public int updateStock(Long goodsId,String color,String size)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "update ph_goods_stock s set s.image = ?3 where  s.goods_id=?1  and s.color =?2 ")
public int updateImage(Long goodsId,String color,String image)


@Query(nativeQuery = true, value = "select s.image from ph_goods_stock s where  s.goods_id=?2  and s.color =?1 and s.image is not null  limit 1")
public String findImage(String color,Long goodsId)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_goods_stock where id in(?1)")
public int deleteByIds(List<Long> ids)


}