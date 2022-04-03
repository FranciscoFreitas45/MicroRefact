package cn.offway.athena.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhShowImage;
public interface PhShowImageRepository extends JpaSpecificationExecutor<PhShowImage>, JpaRepository<PhShowImage, Long>{


public PhShowImage findByOrderNo(String orderNo)
;

}