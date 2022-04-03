package kielce.tu.weaii.telelearn.servicedata;
 import kielce.tu.weaii.telelearn.models.LearningTime;
import lombok.Data;
import java.time.Duration;
import java.util.List;
@Data
public class LearningTimeData {

 private  Duration defaultLearningTime;

 private  List<LearningTime> learningTimeList;


}