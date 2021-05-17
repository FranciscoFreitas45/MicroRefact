import java.util.List;
import org.sdrc.devinfo.domain.UtIndicatorUnitSubgroup;
import org.springframework.dao.DataAccessException;
public interface UtIndicatorUnitSubgroupRepository {


public UtIndicatorUnitSubgroup findByIUSNId(int IUSNId)


public UtIndicatorUnitSubgroup findByIndicatorNIdAndSubgroupNidAndUnitNid(int indicatorNid,int subgroupNid,int unitNid)


public List<UtIndicatorUnitSubgroup> findByUnitNid(int unitNid)


public List<Object[]> getIUS(int id)


public List<UtIndicatorUnitSubgroup> findAll()


public UtIndicatorUnitSubgroup findByIUSNIdAndUnitNid(int IUSNId,int unitNid)


}