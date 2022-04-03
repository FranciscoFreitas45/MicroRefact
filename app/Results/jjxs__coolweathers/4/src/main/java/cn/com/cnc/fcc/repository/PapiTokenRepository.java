package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.PapiToken;
import org.springframework.data.jpa.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface PapiTokenRepository extends JpaRepository<PapiToken, Long>{


@Query(value = "select * from papi_token where api_code = :apiCode and del_flag = 0", nativeQuery = true)
public PapiToken findByCode(String apiCode)
;

}