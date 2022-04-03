package lk.sliit.csse.procurementsystem.repositories;
 import lk.sliit.csse.procurementsystem.models.Site;
import lk.sliit.csse.procurementsystem.models.SiteManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface SiteRepository extends JpaRepository<Site, Long>{


@Modifying
@Query("update Site s set s.siteManager = ?1 where s.siteId = ?2")
public int setSiteManagerFor(SiteManager sitemanager,Long id)
;

}