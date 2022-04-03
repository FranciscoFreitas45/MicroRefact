package edu.nju.careerbridge.youth.blservice;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import edu.nju.careerbridge.youth.bean;
import edu.nju.careerbridge.youth.dao.UserRepository;
import java.util.List;
import java.util.Map;
public interface UserBLService {


public ResultMessageBean saveEducation(EducationBean educationBean)
;

public ResultMessageBean saveProjectExperience(List<ProjectExperienceBean> projectExperienceBeans)
;

public ResultMessageBean saveJobExperience(List<JobExperienceBean> jobExperienceBeans)
;

public ResultMessageBean saveExpectation(ExpectationBean expectationBean)
;

public EducationBean getEducation(String phone)
;

public ExpectationBean getExpectation(String phone)
;

public ResultMessageBean login(String username,String password)
;

public ResultMessageBean signUp(String phone,String password,String mail,String name)
;

public UserBasicMessageBean getUserBasicMessage(String phone)
;

public ResultMessageBean editPassword(String phone,String password)
;

public ResultMessageBean saveUserBasicMessage(UserBasicMessageBean userBasicMessageBean)
;

public ResultMessageBean saveSkill(List<SkillBean> skillBeans)
;

public List<JobExperienceBean> getJobExperience(String phone)
;

public List<ProjectExperienceBean> getProjectExperience(String phone)
;

public List<SkillBean> getSkill(String phone)
;

}