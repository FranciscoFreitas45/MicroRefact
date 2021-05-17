import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.calendars.entity.Calendar;
@Repository
public interface CalendarDao extends PagingAndSortingRepository<Calendar, Long> {


}