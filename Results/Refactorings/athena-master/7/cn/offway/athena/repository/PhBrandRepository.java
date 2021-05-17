import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import cn.offway.athena.domain.PhBrand;
import java.lang.Long;
import java.util.List;
public interface PhBrandRepository extends JpaRepository<PhBrand, Long> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "update ph_goods set brand_logo=?2,brand_name=?3 where brand_id=?1")
public int updateGoods(Long id,String logo,String name)


@Query(nativeQuery = true, value = "select * from ph_brand where id in(?1)")
public List<PhBrand> findByIds(List<Long> ids)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "update ph_goods_stock set brand_logo=?2,brand_name=?3 where brand_id=?1")
public int updateGoodsStock(Long id,String logo,String name)


@Query(nativeQuery = true, value = "select * from ph_brand where id in (select brand_id from ph_goods where id in(select goods_id from ph_order_goods where order_no=(select order_no from ph_show_image where id=?1)))")
public List<PhBrand> findByShowImgId(Long showImgId)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "update ph_order_goods set brand_logo=?2,brand_name=?3 where brand_id=?1")
public int updateOrderGoods(Long id,String logo,String name)


}