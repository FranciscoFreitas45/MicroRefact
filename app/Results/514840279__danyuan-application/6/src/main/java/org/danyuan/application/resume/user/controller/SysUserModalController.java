package org.danyuan.application.resume.user.controller;
 import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.common.base.BaseResult;
import org.danyuan.application.resume.modal.po.SysModalInfo;
import org.danyuan.application.resume.modal.service.SysModalInfoService;
import org.danyuan.application.resume.user.po.SysUserBaseInfo;
import org.danyuan.application.resume.user.po.SysUserEducation;
import org.danyuan.application.resume.user.po.SysUserEvaluate;
import org.danyuan.application.resume.user.po.SysUserModal;
import org.danyuan.application.resume.user.po.SysUserProject;
import org.danyuan.application.resume.user.po.SysUserSkill;
import org.danyuan.application.resume.user.po.SysUserWorkExpreience;
import org.danyuan.application.resume.user.service.SysUserBaseService;
import org.danyuan.application.resume.user.service.SysUserEducationService;
import org.danyuan.application.resume.user.service.SysUserEvaluateService;
import org.danyuan.application.resume.user.service.SysUserModalService;
import org.danyuan.application.resume.user.service.SysUserProjectService;
import org.danyuan.application.resume.user.service.SysUserSkillService;
import org.danyuan.application.resume.user.service.SysUserWorkExpreienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.danyuan.application.Interface.SysUserModalService;
import org.danyuan.application.Interface.SysUserBaseService;
import org.danyuan.application.Interface.SysUserEducationService;
import org.danyuan.application.Interface.SysUserSkillService;
import org.danyuan.application.Interface.SysUserWorkExpreienceService;
import org.danyuan.application.Interface.SysUserProjectService;
import org.danyuan.application.Interface.SysUserEvaluateService;
import org.danyuan.application.Interface.SysModalInfoService;
import org.danyuan.application.DTO.SysUserBaseInfo;
@RestController
@RequestMapping("/sysUserModal")
public class SysUserModalController extends BaseControllerImpl<SysUserModal>implements BaseController<SysUserModal>{

@Autowired
 private SysUserModalService sysUserModalService;

@Autowired
 private SysUserBaseService sysUserBaseService;

@Autowired
 private SysUserEducationService sysUserEducationService;

@Autowired
 private SysUserSkillService sysUserSkillService;

@Autowired
 private SysUserWorkExpreienceService sysUserWorkExpreienceService;

@Autowired
 private SysUserProjectService sysUserProjectService;

@Autowired
 private SysUserEvaluateService sysUserEvaluateService;

@Autowired
 private SysModalInfoService sysModalInfoService;


@RequestMapping(path = "/writeResume", method = RequestMethod.POST)
public BaseResult<String> writeResume(SysUserModal info) throws IllegalArgumentException{
    BaseResult<String> result = new BaseResult<>();
    try {
        // ??????????????????
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        // ????????????????????????????????????classloader???classpath???
        resolver.setPrefix("templates/");
        // ??????????????????
        resolver.setSuffix(".html");
        // resolver.setTemplateMode("HTML5");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        // ???????????????(Model)
        Context context = new Context();
        // ??????
        SysUserBaseInfo base = sysUserBaseService.findById(info.getUserUuid());
        context.setVariable("base", base);
        // ??????
        SysUserEducation education = new SysUserEducation();
        education.setUserUuid(info.getUserUuid());
        List<SysUserEducation> educations = sysUserEducationService.findAll(education);
        context.setVariable("educations", educations);
        // ??????
        SysUserSkill skill = new SysUserSkill();
        skill.setUserUuid(info.getUserUuid());
        List<SysUserSkill> skills = sysUserSkillService.findAll(skill);
        context.setVariable("skills", skills);
        // ????????????
        SysUserWorkExpreience workExpreience = new SysUserWorkExpreience();
        workExpreience.setUserUuid(info.getUserUuid());
        List<SysUserWorkExpreience> workExpreiences = sysUserWorkExpreienceService.findAll(workExpreience);
        context.setVariable("workExpreiences", workExpreiences);
        // ????????????
        SysUserProject project = new SysUserProject();
        project.setUserUuid(info.getUserUuid());
        List<SysUserProject> projects = sysUserProjectService.findAll(project);
        context.setVariable("projects", projects);
        // ??????
        SysUserEvaluate evaluate = new SysUserEvaluate();
        evaluate.setUserUuid(info.getUserUuid());
        evaluate.setType("????????????");
        evaluate = sysUserEvaluateService.findOne(evaluate);
        context.setVariable("evaluate", evaluate);
        // ????????????
        String dirString = System.getProperty("user.dir");
        FileWriter write = new FileWriter(dirString + "/file/" + info.getUserUuid() + ".html");
        SysModalInfo sysModalInfo = sysModalInfoService.findOne(new SysModalInfo(info.getModalUuid()));
        templateEngine.process("resume/modal/" + sysModalInfo.getModalFilePath(), context, write);
        // ??????????????????
        base.setResumePath("/file/" + info.getUserUuid() + ".html");
        sysUserBaseService.save(base);
        result.setData("/" + info.getUserUuid() + ".html");
        result.setCode(200);
    } catch (IOException e) {
        System.err.println(e.getMessage());
        result.setCode(-100);
        result.setMsg(e.getMessage());
    }
    return result;
}


@RequestMapping(path = "/showResume/{userUuid}/{resume}")
public ModelAndView showResume(String userid,String resume){
    // ??????????????????
    ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
    // ????????????????????????????????????classloader???classpath???
    resolver.setPrefix("templates/");
    // ??????????????????
    resolver.setSuffix(".html");
    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(resolver);
    // ???????????????(Model)
    ModelAndView view = new ModelAndView("resume/modal/" + resume + ".html");
    // ??????
    view.addObject("base", sysUserBaseService.findById(userid));
    // ??????
    SysUserEducation education = new SysUserEducation();
    education.setUserUuid(userid);
    List<SysUserEducation> educations = sysUserEducationService.findAll(education);
    view.addObject("educations", educations);
    // ??????
    SysUserSkill skill = new SysUserSkill();
    skill.setUserUuid(userid);
    List<SysUserSkill> skills = sysUserSkillService.findAll(skill);
    view.addObject("skills", skills);
    // ??????
    SysUserWorkExpreience workExpreience = new SysUserWorkExpreience();
    workExpreience.setUserUuid(userid);
    List<SysUserWorkExpreience> workExpreiences = sysUserWorkExpreienceService.findAll(workExpreience);
    view.addObject("workExpreiences", workExpreiences);
    // ??????
    SysUserProject project = new SysUserProject();
    project.setUserUuid(userid);
    List<SysUserProject> projects = sysUserProjectService.findAll(project);
    view.addObject("projects", projects);
    // ??????
    SysUserEvaluate evaluate = new SysUserEvaluate();
    evaluate.setUserUuid(userid);
    evaluate.setType("????????????");
    evaluate = sysUserEvaluateService.findOne(evaluate);
    view.addObject("evaluate", evaluate);
    // ????????????
    return view;
}


}