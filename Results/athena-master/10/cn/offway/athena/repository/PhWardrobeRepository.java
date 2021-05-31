import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import cn.offway.athena.domain.PhWardrobe;
public interface PhWardrobeRepository extends JpaRepository<PhWardrobe, Long> {


@Transactional
@Modifying
@Query(nativeQuery = true, value = "delete from ph_wardrobe where goods_id in(?1)")
public int deleteByGoodsIds(List<Long> goodsIds)


}