import cn.offway.athena.domain.PhConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
public interface PhConfigRepository extends JpaSpecificationExecutor<PhConfig> {


@Query(nativeQuery = true, value = "select content from ph_config where name=?1")
public String findContentByName(String name)


}