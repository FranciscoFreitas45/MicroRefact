import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhAuth;
public interface PhAuthRepository extends JpaRepository<PhAuth, Long> {


}