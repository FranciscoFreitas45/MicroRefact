package cn.offway.athena.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhUserInfo;
public interface PhUserInfoRepository extends JpaRepository<PhUserInfo, Long>, JpaSpecificationExecutor<PhUserInfo>{


public PhUserInfo findByUnionid(String unionid)
;

}