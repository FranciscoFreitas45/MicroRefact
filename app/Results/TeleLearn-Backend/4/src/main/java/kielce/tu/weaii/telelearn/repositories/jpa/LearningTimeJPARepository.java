package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.LearningTime;
import kielce.tu.weaii.telelearn.models.LearningTimeId;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LearningTimeJPARepository extends JpaRepository<LearningTime, LearningTimeId>{


}