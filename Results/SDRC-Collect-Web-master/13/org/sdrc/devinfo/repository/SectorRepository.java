import java.util.List;
import org.sdrc.devinfo.domain.UtIndicatorClassificationsEn;
import org.springframework.dao.DataAccessException;
public interface SectorRepository {


public int findIcNIdbySourceType(String IC_Type)


public List<Object[]> findByIC_Type(String IC_Type)


public List<UtIndicatorClassificationsEn> findByIC_Parent_NId(int IC_Parent_NId)


public List<UtIndicatorClassificationsEn> findByIC_NId(int IC_NId)


}