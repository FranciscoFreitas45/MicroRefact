import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import com.hmm.Work.entity.Work;
import com.hmm.Work.entity.BcardDTO;
import com.hmm.Work.entity.WorkEmpDTO;
import com.hmm.common.web.ExtAjaxResponse;
import com.hmm.employee.entity.Employee;
import com.hmm.leave.entity.LeaveDTO;
public interface workService {


public List<Map<Object,Object>> findByyearAndOntudytimeleaveEary(Integer year,String userName)


public List<Work> findByDto(Specification<Work> spec)


public void save(Work entity)


public List<Map<Object,Object>> findByyearAndOntudytimelate(Integer year,String userName)


public Page<Work> findAll(Specification<Work> spec,Pageable pageable)


public int findByEmployAndOntudytimeleaveEarly(String userName)


public List<Map<Object,Object>> findlate(Integer year)


public Integer findTatalPersonNomal()


public Page<WorkEmpDTO> findAllByDTO(Specification<Work> spec,Pageable pageable)


public Integer findTatalPersonleaveEarly()


public Optional<Work> findById(Long id)


public Page<WorkEmpDTO> findAllBydeptName(String deptName,Pageable pageable)


public float findattenceTotalovertime(String userbname)


public Work findByWorkDateAndEmploy(Date workDate,Employee employee)


public float findattenceTotalworktime(String userbname)


public Integer findTatalPersonOvertime()


public List<Map<Object,Object>> findByyearAndOntudytimelackcard(Integer year,String userName)


public List<Map<Object,Object>> findleaveEarly(Integer year)


public Integer findExactlyPerson()


public long count(Specification<Work> spec)


public int findByEmployAndOntudytimelackCard(String userName)


public Integer findTatalPersonLate()


public boolean existsById(Long id)


public ExtAjaxResponse UpdateWork(String userId)


public int findByEmployAndOntudytimenormal(String userName)


public void deleteById(Long id)


public int findByEmployAndOntudytimelate(String userName)


public List<Map<Object,Object>> findlackcard(Integer year)


public void deleteByIds(Long[] ids)


}