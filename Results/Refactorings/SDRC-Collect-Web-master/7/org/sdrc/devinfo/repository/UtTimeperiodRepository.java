import java.util.Date;
import java.util.List;
import org.sdrc.devinfo.domain.UtTimeperiod;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
public interface UtTimeperiodRepository {


public List<UtTimeperiod> findBySource_Nid(Integer iusNid,Integer SourceNid)


public Object findLatestTimePeriodNId(Integer iusNid,Integer[] areaNid)


public List<UtTimeperiod> findByPeriodicity(String string)


public UtTimeperiod findByStartDateAndEndDateAndPeriodicity(Date startDate,Date endDate,String string)


public List<UtTimeperiod> getTimeperiodByLimit(Pageable pageable)


@Transactional
public UtTimeperiod save(UtTimeperiod timeperiod)


public UtTimeperiod getByStartDateAndEndDate(Date startDate,Date endDate)


public List<UtTimeperiod> findAll()


}