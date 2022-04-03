package edu.nju.careerbridge.youth.controller;
 import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import edu.nju.careerbridge.youth.bean;
import edu.nju.careerbridge.youth.bean.JobExperienceBean;
import edu.nju.careerbridge.youth.bean.ResultMessageBean;
import edu.nju.careerbridge.youth.blservice.UserBLService;
import edu.nju.careerbridge.youth.model.ExpectCompanyLevel;
import edu.nju.careerbridge.youth.model.Skill;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Api(value = "用户模块", description = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

 private  UserBLService userBLService;

@Autowired
public UserController(UserBLService userBLService) {
    this.userBLService = userBLService;
}
@ResponseBody
@RequestMapping(value = "/education", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
public ResultMessageBean saveEducation(String param){
    JSONObject jo = new JSONObject();
    Map<String, String> m = (Map<String, String>) jo.parse(param);
    List<HonorBean> honorBeans = new ArrayList<HonorBean>();
    JSONArray ja = JSONArray.parseArray(m.get("honorBeans"));
    for (int i = 0; i < ja.size(); i++) {
        JSONObject temp = ja.getJSONObject(i);
        HonorBean honorBean = new HonorBean(m.get("phone"), temp.getString("honorName"), temp.getString("level"));
        honorBeans.add(honorBean);
    }
    EducationBean educationBean = new EducationBean(m.get("phone"), m.get("educationDegree"), m.get("school"), m.get("major"), m.get("fromTime"), m.get("toTime"), honorBeans);
    return userBLService.saveEducation(educationBean);
}


@ResponseBody
@RequestMapping(value = "/projectExperience", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
public ResultMessageBean saveProjectExperience(String param){
    JSONArray ja = JSONArray.parseArray(param);
    List<ProjectExperienceBean> projectExperienceBeans = new ArrayList<ProjectExperienceBean>();
    for (int i = 0; i < ja.size(); i++) {
        JSONObject temp = ja.getJSONObject(i);
        ProjectExperienceBean peb = new ProjectExperienceBean(temp.getString("phone"), temp.getString("name"), temp.getString("level"), temp.getString("fromTime"), temp.getString("toTime"), temp.getString("projectDescription"), temp.getString("mywork"));
        projectExperienceBeans.add(peb);
    }
    return userBLService.saveProjectExperience(projectExperienceBeans);
}


@ResponseBody
@RequestMapping(value = "/jobExperience", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
public ResultMessageBean saveJobExperience(String param){
    JSONArray ja = JSONArray.parseArray(param);
    List<JobExperienceBean> jobExperienceBeans = new ArrayList<JobExperienceBean>();
    for (int i = 0; i < ja.size(); i++) {
        JSONObject temp = ja.getJSONObject(i);
        JobExperienceBean jeb = new JobExperienceBean(temp.getString("phone"), temp.getString("Companyname"), temp.getString("companyQuality"), temp.getString("companyLevel"), temp.getString("job"), temp.getString("fromTime"), temp.getString("toTime"), temp.getString("description"));
        jobExperienceBeans.add(jeb);
    }
    return userBLService.saveJobExperience(jobExperienceBeans);
}


@ResponseBody
@RequestMapping(value = "/expectation", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
public ResultMessageBean saveExpectation(String param){
    try {
        JSONObject jo = new JSONObject();
        Map<String, String> m = (Map<String, String>) jo.parse(param);
        List<ExpectLocationBean> expectLocationBeans = new ArrayList<ExpectLocationBean>();
        JSONArray ja1 = JSONArray.parseArray(m.get("expectLocationBeans"));
        for (int i = 0; i < ja1.size(); i++) {
            JSONObject temp = ja1.getJSONObject(i);
            ExpectLocationBean expectLocationBean = new ExpectLocationBean(temp.getString("phone"), temp.getString("expectLocation"));
            expectLocationBeans.add(expectLocationBean);
        }
        List<ExpectCompanyQualityBean> expectCompanyQualityBeans = new ArrayList<ExpectCompanyQualityBean>();
        JSONArray ja2 = JSONArray.parseArray(m.get("expectCompanyQualityBeans"));
        for (int i = 0; i < ja2.size(); i++) {
            JSONObject temp = ja2.getJSONObject(i);
            ExpectCompanyQualityBean expectCompanyQualityBean = new ExpectCompanyQualityBean(temp.getString("phone"), temp.getString("expectCompanyQuality"));
            expectCompanyQualityBeans.add(expectCompanyQualityBean);
        }
        List<ExpectCompanyLevelBean> expectCompanyLevelBeans = new ArrayList<ExpectCompanyLevelBean>();
        JSONArray ja3 = JSONArray.parseArray(m.get("expectCompanyLevelBeans"));
        for (int i = 0; i < ja3.size(); i++) {
            JSONObject temp = ja3.getJSONObject(i);
            ExpectCompanyLevelBean expectCompanyLevelBean = new ExpectCompanyLevelBean(temp.getString("phone"), temp.getString("expectCompanyLevel"));
            expectCompanyLevelBeans.add(expectCompanyLevelBean);
        }
        List<ExpectJobTypeBean> expectJobTypeBeans = new ArrayList<ExpectJobTypeBean>();
        expectJobTypeBeans.add(new ExpectJobTypeBean(m.get("phone"), m.get("jobType")));
        ExpectationBean expectationBean = new ExpectationBean(m.get("phone"), m.get("salary"), Integer.parseInt(m.get("lowSalary").toString()), Integer.parseInt(m.get("highSalary").toString()), expectLocationBeans, expectCompanyQualityBeans, expectCompanyLevelBeans, expectJobTypeBeans);
        System.out.println(expectationBean.toString());
        return userBLService.saveExpectation(expectationBean);
    } catch (Exception e) {
        return new ResultMessageBean(false);
    }
}


@ApiOperation(value = "得到教育信息", notes = "可能状态码：45,44,34,35")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "MultipartFile") })
@PostMapping("/getEducation")
public EducationBean getEducation(String phone){
    return userBLService.getEducation(phone);
}


@RequestMapping(path = "/home", method = { RequestMethod.GET })
public String say(HttpServletResponse response){
    response.sendRedirect("/home.html");
    return "/home";
}


@ApiOperation(value = "得到用户职业预期", notes = "可能状态码：45,44,34,35")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "MultipartFile") })
@PostMapping("/getExpectation")
public ExpectationBean getExpectation(String phone){
    return userBLService.getExpectation(phone);
}


@ApiOperation(value = "账号密码登录", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"), @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String") })
@PostMapping("/login")
public ResultMessageBean login(String param){
    JSONObject jo = new JSONObject();
    Map<String, String> m = (Map<String, String>) jo.parse(param);
    return userBLService.login(m.get("phone"), m.get("password"));
}


@ApiOperation(value = "注册", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"), @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"), @ApiImplicitParam(name = "mail", value = "邮箱", required = true, dataType = "String"), @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String") })
@PostMapping("/sign-up")
public ResultMessageBean signUp(String param){
    JSONObject jo = new JSONObject();
    Map<String, String> m = (Map<String, String>) jo.parse(param);
    return userBLService.signUp(m.get("phone"), m.get("password"), m.get("mail"), m.get("name"));
}


@ApiOperation(value = "得到用户基本信息", notes = "可能状态码：45,44,34,35")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "MultipartFile") })
@PostMapping("/getUserBasicMessage")
public UserBasicMessageBean getUserBasicMessage(String phone){
    return userBLService.getUserBasicMessage(phone);
}


@ApiOperation(value = "修改密码", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"), @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String") })
@PostMapping("/password")
public ResultMessageBean editPassword(String phone,String password){
    return userBLService.editPassword(phone, password);
}


@RequestMapping(path = "/sign-in", method = { RequestMethod.GET })
public String signin(){
    return "/page_signin.html";
}


@ResponseBody
@RequestMapping(value = "/userBasicMessage", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
public ResultMessageBean saveUserBasicMessage(String param){
    JSONObject jo = new JSONObject();
    Map<String, String> m = (Map<String, String>) jo.parse(param);
    UserBasicMessageBean userBasicMessageBean = new UserBasicMessageBean(m.get("phone"), m.get("realName"), m.get("birthday"), m.get("gender"), m.get("address"), Double.parseDouble(m.get("jobYear")), m.get("salary"), Integer.parseInt(m.get("lowSalary")), Integer.parseInt(m.get("highSalary")), Integer.parseInt(m.get("basicSalary")), Integer.parseInt(m.get("bonus")), Integer.parseInt(m.get("Commission")), Integer.parseInt(m.get("stockShareOption")));
    return userBLService.saveUserBasicMessage(userBasicMessageBean);
}


@ResponseBody
@RequestMapping(value = "/skill", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
public ResultMessageBean saveSkill(String param){
    JSONArray ja = JSONArray.parseArray(param);
    List<SkillBean> skillBeans = new ArrayList<SkillBean>();
    for (int i = 0; i < ja.size(); i++) {
        JSONObject temp = ja.getJSONObject(i);
        SkillBean sb = new SkillBean(temp.getString("phone"), temp.getString("skillName"), temp.getString("degree"), temp.getString("certificate"), temp.getString("description"));
        skillBeans.add(sb);
    }
    return userBLService.saveSkill(skillBeans);
}


@ApiOperation(value = "得到职业经历信息", notes = "可能状态码：45,44,34,35")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "MultipartFile") })
@PostMapping("/getJobExperience")
public List<JobExperienceBean> getJobExperience(String phone){
    return userBLService.getJobExperience(phone);
}


@ApiOperation(value = "得到项目经历信息", notes = "可能状态码：45,44,34,35")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "MultipartFile") })
@PostMapping("/getProjectExperience")
public List<ProjectExperienceBean> getProjectExperience(String phone){
    return userBLService.getProjectExperience(phone);
}


@ApiOperation(value = "得到用户技能", notes = "可能状态码：45,44,34,35")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "MultipartFile") })
@PostMapping("/getSkill")
public List<SkillBean> getSkill(String phone){
    return userBLService.getSkill(phone);
}


}