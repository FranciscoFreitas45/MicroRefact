package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.school.repository.GradeRepository;
import org.live.school.entity.Grade;
@Service
public class GradeMemberService {

@Autowired
 private GradeRepository graderepository;


public void setGrade(String id2FF1,Grade grade){
graderepository.setGrade(id2FF1,grade);
}


public Grade getGrade(String id2FF1){
return graderepository.getGrade(id2FF1);
}


}