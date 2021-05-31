import com.ats.hrmgt.repository.DailyAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Schedular {

@Autowired
 private DailyAttendanceRepository dailyAttendanceRepository;


}