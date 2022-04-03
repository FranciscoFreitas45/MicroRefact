package cn.offway.athena.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.offway.athena.domain.PhFeedback;
public interface PhFeedbackRepository extends JpaSpecificationExecutor<PhFeedback>, JpaRepository<PhFeedback, Long>{


}