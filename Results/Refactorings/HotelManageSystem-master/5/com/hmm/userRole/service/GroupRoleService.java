import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import com.hmm.userRole.entity.GroupRole;
import com.hmm.userRole.entity.GroupRoleDTO;
public interface GroupRoleService {


public boolean existsById(Integer id)


public Page<GroupRoleDTO> findAllByDTO(GroupRoleDTO deptDTO,Pageable pageable)


public GroupRole findByGroupName(String groupName)


public Optional<GroupRole> findById(Integer id)


public void save(GroupRole entity)


public void deleteById(Integer id)


public long count(Specification<GroupRole> spec)


public void deleteAll(Integer[] ids)


public Page<GroupRoleDTO> findAll(Specification<GroupRole> spec,Pageable pageable)


}