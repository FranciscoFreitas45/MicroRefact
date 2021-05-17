import com.ats.hrmgt.model.AccessRightSubModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AccessRightSubModuleRepository extends JpaRepository<AccessRightSubModule, Integer> {


@Query(value = " SELECT * FROM m_module_sub WHERE  is_delete =1  ORDER BY order_by ASC; ", nativeQuery = true)
public List<AccessRightSubModule> getSubModuleAll()


@Query(value = " SELECT * FROM m_module_sub WHERE  is_delete =1 AND module_id=:moduleId ORDER BY order_by ASC; ", nativeQuery = true)
public List<AccessRightSubModule> getSubModule(int moduleId)


}