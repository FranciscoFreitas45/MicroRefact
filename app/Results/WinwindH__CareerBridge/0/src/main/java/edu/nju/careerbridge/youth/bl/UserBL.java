package edu.nju.careerbridge.youth.bl;
 import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.nju.careerbridge.youth.bean;
import edu.nju.careerbridge.youth.blservice.UserBLService;
import edu.nju.careerbridge.youth.dao;
import edu.nju.careerbridge.youth.model;
import java.util.ArrayList;
import java.util.List;
import edu.nju.careerbridge.Interface.UserBasicMessageRepository;
import edu.nju.careerbridge.Interface.EducationRepository;
import edu.nju.careerbridge.Interface.HonorRepository;
import edu.nju.careerbridge.Interface.ProjectExperienceRepository;
import edu.nju.careerbridge.Interface.SkillRepository;
@Service
public class UserBL implements UserBLService{

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  UserBasicMessageRepository userBasicMessageRepository;

@Autowired
 private  JobExperienceRepository jobExperienceRepository;

@Autowired
 private  EducationRepository educationRepository;

@Autowired
 private  HonorRepository honorRepository;

@Autowired
 private  ProjectExperienceRepository projectExperienceRepository;

@Autowired
 private  SkillRepository skillRepository;

@Autowired
 private  ExpectationRepository expectationRepository;

@Autowired
 private  ExpectLocationRepository expectLocationRepository;

@Autowired
 private  ExpectCompanyQualityRepository expectCompanyQualityRepository;

@Autowired
 private  ExpectCompanyLevelRepository expectCompanyLevelRepository;

@Autowired
 private  ExpectJobTypeRepository expectJobTypeRepository;


@Override
public ResultMessageBean saveEducation(EducationBean educationBean){
    String phone = educationBean.getPhone();
    educationRepository.deleteByPhone(phone);
    honorRepository.deleteByPhone(phone);
    Education education = new Education();
    BeanUtils.copyProperties(educationBean, education);
    try {
        educationRepository.save(education);
    } catch (Exception e) {
        return new ResultMessageBean(false, "填写教育经历信息失败");
    }
    // List<Honor> list=new ArrayList<Honor>();
    for (HonorBean honorBean : educationBean.getHonorBeans()) {
        Honor honor = new Honor();
        honor.setPhone(phone);
        honor.setHonorName(honorBean.getHonorName());
        honor.setLevel(honorBean.getLevel());
        try {
            honorRepository.save(honor);
        } catch (Exception e) {
            return new ResultMessageBean(false, "填写教育经历信息失败");
        }
    // list.add(honor);
    }
    return new ResultMessageBean(true);
}


@Override
public ResultMessageBean saveProjectExperience(List<ProjectExperienceBean> projectExperienceBeans){
    if (projectExperienceBeans.size() == 0) {
        return new ResultMessageBean(true);
    }
    String phone = projectExperienceBeans.get(0).getPhone();
    projectExperienceRepository.deleteByPhone(phone);
    for (ProjectExperienceBean projectExperienceBean : projectExperienceBeans) {
        ProjectExperience projectExperience = new ProjectExperience();
        BeanUtils.copyProperties(projectExperienceBean, projectExperience);
        try {
            projectExperienceRepository.save(projectExperience);
        } catch (Exception e) {
            return new ResultMessageBean(false, "填写项目经历信息失败");
        }
    }
    return new ResultMessageBean(true);
}


@Override
public ResultMessageBean saveJobExperience(List<JobExperienceBean> jobExperienceBeans){
    if (jobExperienceBeans.size() == 0) {
        return new ResultMessageBean(true);
    }
    String phone = jobExperienceBeans.get(0).getPhone();
    jobExperienceRepository.deleteByPhone(phone);
    for (JobExperienceBean jobExperienceBean : jobExperienceBeans) {
        JobExperience jobExperience = new JobExperience();
        BeanUtils.copyProperties(jobExperienceBean, jobExperience);
        try {
            jobExperienceRepository.save(jobExperience);
        } catch (Exception e) {
            return new ResultMessageBean(false, "填写职业经历信息失败");
        }
    }
    return new ResultMessageBean(true);
}


@Override
public ResultMessageBean saveExpectation(ExpectationBean expectationBean){
    // String phone=expectationBean.getPhone();
    // 
    // expectationRepository.deleteByPhone(phone);
    // 
    // Expectation expectation=new Expectation();
    // BeanUtils.copyProperties(expectationBean,expectation);
    // try {
    // expectationRepository.save(expectation);
    // } catch (Exception e) {
    // return new ResultMessageBean(false, "填写职业预期信息失败");
    // }
    // 
    // 
    // return new ResultMessageBean(true);
    String phone = expectationBean.getPhone();
    expectationRepository.deleteByPhone(phone);
    expectLocationRepository.deleteByPhone(phone);
    expectCompanyQualityRepository.deleteByPhone(phone);
    expectCompanyLevelRepository.deleteByPhone(phone);
    expectJobTypeRepository.deleteByPhone(phone);
    Expectation expectation = new Expectation();
    BeanUtils.copyProperties(expectationBean, expectation);
    try {
        expectationRepository.save(expectation);
    } catch (Exception e) {
        return new ResultMessageBean(false, "填写职业预期信息失败");
    }
    for (ExpectLocationBean expectLocationBean : expectationBean.getExpectLocationBeans()) {
        ExpectLocation expectLocation = new ExpectLocation();
        expectLocation.setPhone(phone);
        expectLocation.setExpectLocation(expectLocationBean.getExpectLocation());
        try {
            expectLocationRepository.save(expectLocation);
        } catch (Exception e) {
            return new ResultMessageBean(false, "填写职业预期信息失败");
        }
    }
    for (ExpectCompanyQualityBean expectCompanyQualityBean : expectationBean.getExpectCompanyQualityBeans()) {
        ExpectCompanyQuality expectCompanyQuality = new ExpectCompanyQuality();
        expectCompanyQuality.setPhone(phone);
        expectCompanyQuality.setExpectCompanyQuality(expectCompanyQualityBean.getExpectCompanyQuality());
        try {
            expectCompanyQualityRepository.save(expectCompanyQuality);
        } catch (Exception e) {
            return new ResultMessageBean(false, "填写职业预期信息失败");
        }
    }
    for (ExpectCompanyLevelBean expectCompanyLevelBean : expectationBean.getExpectCompanyLevelBeans()) {
        ExpectCompanyLevel expectCompanyLevel = new ExpectCompanyLevel();
        expectCompanyLevel.setPhone(phone);
        expectCompanyLevel.setExpectCompanyLevel(expectCompanyLevelBean.getExpectCompanyLevel());
        try {
            expectCompanyLevelRepository.save(expectCompanyLevel);
        } catch (Exception e) {
            return new ResultMessageBean(false, "填写职业预期信息失败");
        }
    }
    for (ExpectJobTypeBean expectJobTypeBean : expectationBean.getExpectJobTypeBeans()) {
        ExpectJobType expectJobType = new ExpectJobType();
        expectJobType.setPhone(phone);
        expectJobType.setExpectJobType(expectJobTypeBean.getExpectJobType());
        try {
            expectJobTypeRepository.save(expectJobType);
        } catch (Exception e) {
            return new ResultMessageBean(false, "填写职业预期信息失败");
        }
    }
    return new ResultMessageBean(true);
}


@Override
public EducationBean getEducation(String phone){
    EducationBean educationBean = new EducationBean();
    BeanUtils.copyProperties(educationRepository.findByPhone(phone), educationBean);
    List<HonorBean> list = new ArrayList<HonorBean>();
    for (Honor honor : honorRepository.findByPhone(phone)) {
        HonorBean honorBean = new HonorBean();
        BeanUtils.copyProperties(honor, honorBean);
        list.add(honorBean);
    }
    educationBean.setHonorBeans(list);
    return educationBean;
}


@Override
public ExpectationBean getExpectation(String phone){
    // ExpectationBean expectationBean=new ExpectationBean();
    // BeanUtils.copyProperties(expectationRepository.findByPhone(phone),expectationBean);
    // return expectationBean;
    ExpectationBean expectationBean = new ExpectationBean();
    BeanUtils.copyProperties(expectationRepository.findByPhone(phone), expectationBean);
    List<ExpectLocationBean> list1 = new ArrayList<ExpectLocationBean>();
    for (ExpectLocation expectLocation : expectLocationRepository.findByPhone(phone)) {
        ExpectLocationBean expectLocationBean = new ExpectLocationBean();
        BeanUtils.copyProperties(expectLocation, expectLocationBean);
        list1.add(expectLocationBean);
    }
    expectationBean.setExpectLocationBeans(list1);
    List<ExpectCompanyQualityBean> list2 = new ArrayList<ExpectCompanyQualityBean>();
    for (ExpectCompanyQuality expectCompanyQuality : expectCompanyQualityRepository.findByPhone(phone)) {
        ExpectCompanyQualityBean expectCompanyQualityBean = new ExpectCompanyQualityBean();
        BeanUtils.copyProperties(expectCompanyQuality, expectCompanyQualityBean);
        list2.add(expectCompanyQualityBean);
    }
    expectationBean.setExpectCompanyQualityBeans(list2);
    List<ExpectCompanyLevelBean> list3 = new ArrayList<ExpectCompanyLevelBean>();
    for (ExpectCompanyLevel expectCompanyLevel : expectCompanyLevelRepository.findByPhone(phone)) {
        ExpectCompanyLevelBean expectCompanyLevelBean = new ExpectCompanyLevelBean();
        BeanUtils.copyProperties(expectCompanyLevel, expectCompanyLevelBean);
        list3.add(expectCompanyLevelBean);
    }
    expectationBean.setExpectCompanyLevelBeans(list3);
    List<ExpectJobTypeBean> list4 = new ArrayList<ExpectJobTypeBean>();
    for (ExpectJobType expectJobType : expectJobTypeRepository.findByPhone(phone)) {
        ExpectJobTypeBean expectJobTypeBean = new ExpectJobTypeBean();
        BeanUtils.copyProperties(expectJobType, expectJobTypeBean);
        list4.add(expectJobTypeBean);
    }
    expectationBean.setExpectJobTypeBeans(list4);
    return expectationBean;
}


@Override
public ResultMessageBean login(String phone,String password){
    try {
        String realPassword = userRepository.findByPhone(phone).getPassword();
        System.out.println(realPassword);
        System.out.println(password);
        if (realPassword.equals(password)) {
            return new ResultMessageBean(true, "登陆成功");
        } else {
            return new ResultMessageBean(false, "登录失败");
        }
    } catch (Exception e) {
        return new ResultMessageBean(false, "登录失败");
    }
}


@Override
public ResultMessageBean signUp(String phone,String password,String mail,String name){
    User user = new User(phone, mail, password, name);
    try {
        userRepository.save(user);
    } catch (Exception e) {
        return new ResultMessageBean(false, "手机号已注册");
    }
    return new ResultMessageBean(true);
}


@Override
public UserBasicMessageBean getUserBasicMessage(String phone){
    UserBasicMessageBean userBasicMessageBean = new UserBasicMessageBean();
    BeanUtils.copyProperties(userBasicMessageRepository.findByPhone(phone), userBasicMessageBean);
    return userBasicMessageBean;
}


@Override
public ResultMessageBean editPassword(String phone,String password){
    try {
        userRepository.editPassword(phone, password);
        return new ResultMessageBean(true, "密码修改成功");
    } catch (Exception e) {
        return new ResultMessageBean(false, "密码修改失败");
    }
}


@Override
public ResultMessageBean saveUserBasicMessage(UserBasicMessageBean userBasicMessageBean){
    String phone = userBasicMessageBean.getPhone();
    UserBasicMessage userBasicMessage = new UserBasicMessage();
    BeanUtils.copyProperties(userBasicMessageBean, userBasicMessage);
    userBasicMessageRepository.deleteByPhone(phone);
    try {
        userBasicMessageRepository.save(userBasicMessage);
    } catch (Exception e) {
        return new ResultMessageBean(false, "填写用户基本信息失败");
    }
    return new ResultMessageBean(true);
}


@Override
public ResultMessageBean saveSkill(List<SkillBean> skillBeans){
    if (skillBeans.size() == 0) {
        return new ResultMessageBean(true);
    }
    String phone = skillBeans.get(0).getPhone();
    skillRepository.deleteByPhone(phone);
    for (SkillBean skillBean : skillBeans) {
        Skill skill = new Skill();
        BeanUtils.copyProperties(skillBean, skill);
        try {
            skillRepository.save(skill);
        } catch (Exception e) {
            return new ResultMessageBean(false, "填写技能信息失败");
        }
    }
    return new ResultMessageBean(true);
}


@Override
public List<JobExperienceBean> getJobExperience(String phone){
    List<JobExperienceBean> list = new ArrayList<JobExperienceBean>();
    for (JobExperience jobExperience : jobExperienceRepository.findByPhone(phone)) {
        JobExperienceBean jobExperienceBean = new JobExperienceBean();
        BeanUtils.copyProperties(jobExperience, jobExperienceBean);
        list.add(jobExperienceBean);
    }
    return list;
}


@Override
public List<ProjectExperienceBean> getProjectExperience(String phone){
    List<ProjectExperienceBean> list = new ArrayList<ProjectExperienceBean>();
    for (ProjectExperience projectExperience : projectExperienceRepository.findByPhone(phone)) {
        ProjectExperienceBean projectExperienceBean = new ProjectExperienceBean();
        BeanUtils.copyProperties(projectExperience, projectExperienceBean);
        list.add(projectExperienceBean);
    }
    return list;
}


@Override
public List<SkillBean> getSkill(String phone){
    List<SkillBean> list = new ArrayList<SkillBean>();
    for (Skill skill : skillRepository.findByPhone(phone)) {
        SkillBean skillBean = new SkillBean();
        BeanUtils.copyProperties(skill, skillBean);
        list.add(skillBean);
    }
    return list;
}


}