import java.util.List;
import org.sdrc.devinfo.domain.UtIndicatorClassificationsEn;
import org.sdrc.devinfo.repository.UtIndicatorClassificationsEnRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
public interface DevinfoUtIndicatorClassificationsEnRepository extends Repository<UtIndicatorClassificationsEn, Integer> {


@Override
@Query(value = "SELECT  * FROM ut_indicator_classifications_en" + " where IC_Parent_NId in (select IC_NId from ut_indicator_classifications_en " + "where IC_Name ='Program Monitoring')", nativeQuery = true)
public List<UtIndicatorClassificationsEn> getSubsector()


@Override
@Query(value = "SELECT IC_NId FROM ut_indicator_classifications_en where IC_Name =:subSectorName and IC_Type='SR'", nativeQuery = true)
public int getSubsectorId(String subSectorName)


}