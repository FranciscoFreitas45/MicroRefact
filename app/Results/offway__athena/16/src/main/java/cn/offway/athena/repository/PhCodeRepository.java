package cn.offway.athena.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhCode;
public interface PhCodeRepository extends JpaRepository<PhCode, Long>, JpaSpecificationExecutor<PhCode>{


public PhCode findByCodeAndStatusAndPhoneAndPositionAndRealName(String code,String status,String phone,String position,String realName)
;

}