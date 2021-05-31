import java.util.List;
import org.sdrc.devinfo.domain.UtIndicatorClassificationsEn;
import org.sdrc.devinfo.domain.UtIndicatorEn;
import org.sdrc.devinfo.repository.SourceRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface DevinfoSourceRepository extends JpaRepository<UtIndicatorEn, Long> {


@Override
@Query("SELECT utIc FROM UtIndicatorClassificationsEn utIc WHERE utIc.IC_NId " + " in (SELECT distinct utdata.source_NId FROM UtData utdata WHERE utdata.IUSNId = :iusNid )")
public List<UtIndicatorClassificationsEn> findByIUS_Nid(Integer iusNid)


@Override
@Query("SELECT utIc FROM UtIndicatorClassificationsEn utIc WHERE utIc.IC_NId " + "IN (SELECT distinct utdata.source_NId FROM UtData utdata WHERE utdata.IUSNId = :iusNid AND utdata.area_NId IN (SELECT area.area_NId FROM UtAreaEn area where area.area_Level= :level) )")
public List<UtIndicatorClassificationsEn> findByIUSandLevel_Nid(Integer iusNid,Integer level)


}