package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.models.LearningTime;
import kielce.tu.weaii.telelearn.models.LearningTimeId;
import kielce.tu.weaii.telelearn.models.Student;
import kielce.tu.weaii.telelearn.repositories.ports.LearningTimeRepository;
import kielce.tu.weaii.telelearn.requests.LearningTimeRequest;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.servicedata.LearningTimeData;
import kielce.tu.weaii.telelearn.services.ports.LearningTimeService;
import kielce.tu.weaii.telelearn.services.ports.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class LearningTimeServiceImpl implements LearningTimeService{

 private  LearningTimeRepository learningTimeRepository;

 private  StudentService studentService;

 private  UserServiceDetailsImpl userRepository;


@Override
@Transactional
public LearningTime setLearningTime(LearningTimeRequest request){
    checkAuthorization(request.getStudentId());
    LearningTime learningTime = new LearningTime();
    learningTime.setId(new LearningTimeId());
    learningTime.getId().setDate(request.getDate());
    learningTime.getId().setStudentId(request.getStudentId());
    learningTime.setStudent(studentService.getById(request.getStudentId()));
    learningTime.setTime(request.getTime().getTimeSpan());
    return learningTimeRepository.save(learningTime);
}


@Override
public LearningTimeData getForStudent(Long studentId){
    checkAuthorization(studentId);
    Student student = studentService.getById(studentId);
    LearningTimeData learningTimeData = new LearningTimeData();
    learningTimeData.setDefaultLearningTime(student.getDailyLearningTime());
    learningTimeData.setLearningTimeList(student.getLearningTime());
    return learningTimeData;
}


public void checkAuthorization(Long studentId){
    if (!userRepository.getCurrentUser().getId().equals(studentId)) {
        throw new AuthorizationException("Planowany czas ucznia", userRepository.getCurrentUser().getId(), studentId);
    }
}


}