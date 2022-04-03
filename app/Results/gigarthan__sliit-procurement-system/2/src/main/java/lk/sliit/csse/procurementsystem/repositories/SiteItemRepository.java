package lk.sliit.csse.procurementsystem.repositories;
 import java.util.List;
import lk.sliit.csse.procurementsystem.models.SiteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface SiteItemRepository extends JpaRepository<SiteItem, Long>{


public List<SiteItem> findAllBySiteSiteId(Long siteId)
;

}