import java.util.List;
import org.sdrc.devinfo.domain.UtIndicatorClassificationsEn;
import org.springframework.dao.DataAccessException;
public interface SourceRepository {


public List<UtIndicatorClassificationsEn> findByIUS_Nid(Integer iusNid)


public List<UtIndicatorClassificationsEn> findByIUSandLevel_Nid(Integer iusNid,Integer levelNid)


}