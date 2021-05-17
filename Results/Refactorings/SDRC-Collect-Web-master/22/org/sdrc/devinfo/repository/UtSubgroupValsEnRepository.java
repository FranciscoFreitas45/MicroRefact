import java.util.List;
import org.sdrc.devinfo.domain.UtSubgroupValsEn;
import org.springframework.dao.DataAccessException;
public interface UtSubgroupValsEnRepository {


public List<Object[]> fetchSubgroupByIndicatorAndUnit(Integer IndicatorNid,Integer UnitNId)


public List<UtSubgroupValsEn> findAll()


public List<Object[]> fetchIndicatorAndUnitBySectorNId(Integer SectorNid)


}