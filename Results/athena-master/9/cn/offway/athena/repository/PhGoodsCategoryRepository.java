import cn.offway.athena.domain.PhGoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
public interface PhGoodsCategoryRepository extends JpaSpecificationExecutor<PhGoodsCategory> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "DELETE FROM `ph_goods_category` WHERE (`goods_type` = ?1)")
public void deleteByPid(Long pid)


@Transactional
@Modifying
@Query(nativeQuery = true, value = "update `ph_goods_category` set `sort` = `sort` + 1 where `sort` >= ?1 and `goods_type` = ?2")
public void resort(Long sort,Long theId)


}