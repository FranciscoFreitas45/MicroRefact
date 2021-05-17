import com.ats.hrmgt.model.AccessRightModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface AccessRightModuleRepository extends JpaRepository<AccessRightModule, Integer> {


@Query(value = " SELECT * FROM m_module WHERE del_status =1 ORDER BY order_by ASC;", nativeQuery = true)
public List<AccessRightModule> getModule()


}