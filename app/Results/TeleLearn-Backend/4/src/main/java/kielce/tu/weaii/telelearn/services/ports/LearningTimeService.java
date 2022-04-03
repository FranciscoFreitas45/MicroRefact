package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.LearningTime;
import kielce.tu.weaii.telelearn.requests.LearningTimeRequest;
import kielce.tu.weaii.telelearn.servicedata.LearningTimeData;
public interface LearningTimeService {


public LearningTime setLearningTime(LearningTimeRequest request)
;

public LearningTimeData getForStudent(Long studentId)
;

}