import cn.com.cnc.fcc.domain.PapiTokenSlave;
import org.springframework.data.jpa.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface PapiTokenSlaveRepository extends JpaRepository<PapiTokenSlave, Long> {


@Query(value = "select * from papi_token_slave where dis_type = :distType and del_flag = 0", nativeQuery = true)
public PapiTokenSlave findByType(String distType)


}