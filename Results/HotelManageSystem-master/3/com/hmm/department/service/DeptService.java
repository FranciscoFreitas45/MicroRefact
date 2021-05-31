import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import com.hmm.department.entity.Department;
import com.hmm.department.entity.DeptDTO;
import com.hmm.department.entity.DeptDTOCombox;
public interface DeptService {


public Department findByDeptNoAndDeptName(String deptNo,String deptName)


public boolean existsById(Integer id)


public Page<DeptDTO> findAllByDTO(DeptDTO deptDTO,Pageable pageable)


public Optional<Department> findById(Integer id)


public void save(Department department)


public void deleteById(Integer id)


public long count(Specification<Department> spec)


public void deleteAll(Integer[] ids)


public Department findByDeptNo(String deptNo)


public Department findByDeptName(String deptName)


public List<DeptDTOCombox> findDeptCombox()


public Page<DeptDTO> findAll(Specification<Department> spec,Pageable pageable)


}