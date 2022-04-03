package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.repositories.jpa.LearningTimeJPARepository;
import kielce.tu.weaii.telelearn.models.LearningTime;
@Service
public class LearningTimeStudentService {

@Autowired
 private LearningTimeJPARepository learningtimejparepository;


}