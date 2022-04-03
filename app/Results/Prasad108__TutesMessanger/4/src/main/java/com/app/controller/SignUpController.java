package com.app.controller;
 import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.app.pojo.Institute;
import com.app.pojo.Login;
import com.app.pojo.Parent;
import com.app.pojo.Permissions;
import com.app.pojo.Role;
import com.app.pojo.Teacher;
import com.app.pojo.Student;
import com.app.service.InstituteService;
import com.app.service.LoginService;
import com.app.service.ParentService;
import com.app.service.PermissionsService;
import com.app.service.RoleService;
import com.app.service.TeacherService;
import com.app.service.StudentService;
import com.app.Interface.LoginService;
import com.app.Interface.TeacherService;
import com.app.Interface.InstituteService;
import com.app.Interface.RoleService;
import com.app.Interface.PermissionsService;
import com.app.Interface.ParentService;
import com.app.DTO.Teacher;
import com.app.DTO.Parent;
@Controller
@SessionAttributes({ "teacher", "institute" })
public class SignUpController {

@Autowired
 private LoginService loginService;

@Autowired
 private TeacherService teacherService;

@Autowired
 private InstituteService instituteService;

@Autowired
 private RoleService RoleService;

@Autowired
 private PermissionsService permissionsService;

@Autowired
 private StudentService StudentService;

@Autowired
 private ParentService parentService;

 private  Logger LOGGER;


@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
public String SignUp(Model model){
    LOGGER.info("**********this is SignUp controller**********");
    return "signup/Choice";
}


@Transactional
@RequestMapping(value = "/SaveTeacher", method = RequestMethod.POST)
public String SaveTeacher(Model model,Teacher teacher){
    LOGGER.info("**********this is save Teacher controller**********");
    // got the role of institute admin
    Role r = RoleService.findByName("ROLE_TEACHER");
    // created the object of login with username as email-id and pwd as contact no
    Login login = new Login(r, teacher.getEmail(), teacher.getContactno());
    // save login credentials
    loginService.create(login);
    Institute i = instituteService.find(teacher.getInstitute().getId());
    Teacher teach = new Teacher(teacher.getFname(), teacher.getLname(), teacher.getEmail(), teacher.getContactno());
    // set institute
    teach.setInstitute(i);
    // set Login
    teach.setLogin(login);
    // teach.setPermissions(permissions); //set permissions
    // create teacher
    teacherService.create(teach);
    model.addAttribute("msg", "user is registerd wait till approval");
    return "login";
}


@RequestMapping(value = "/RegisterStudent", method = RequestMethod.GET)
public String RegisterStudent(Model model){
    LOGGER.info("**********this is RegisterStudent controller**********");
    Student s = new Student();
    model.addAttribute("Student", s);
    List<Institute> institutelist = instituteService.getall();
    model.addAttribute("institutelist", institutelist);
    return "signup/StudentSignUpForm";
}


@Transactional
@RequestMapping(value = "/SaveStudent", method = RequestMethod.POST)
public String SaveStudent(Model model,Student student){
    LOGGER.info("**********this is SaveStudent controller**********");
    String result = "";
    try {
        LOGGER.info("student is " + student);
        // got the role of institute admin
        Role r = RoleService.findByName("ROLE_STUDENT");
        // created the object of login with username as email-id and pwd as contact no
        Login login = new Login(r, student.getEmail(), student.getContactno());
        // save login credentials
        loginService.create(login);
        Institute i = instituteService.find(student.getInstitute().getId());
        student.getParent().setFname(student.getFather());
        student.getParent().setLname(student.getLname());
        student.setLogin(login);
        student.setInstitute(i);
        Parent p = student.getParent();
        parentService.create(p);
        student.setParent(p);
        StudentService.create(student);
        LOGGER.info("student created");
        model.addAttribute("SuccessMessage", "Student Saved succesffully. Please wait till Institute sanctions your account");
        result = "signup/SaveStudentResult";
    } catch (Exception e) {
        LOGGER.info(e.getMessage());
        LOGGER.info(e);
        LOGGER.info("error in saving student ");
        model.addAttribute("ErrorMessage", "Error in Saaving Student...!!!");
        result = "signup/SaveStudentResult";
    }
    return result;
}


@RequestMapping(value = "/RegisterTeacher", method = RequestMethod.GET)
public String RegisterTeacher(Model model){
    LOGGER.info("**********this is Register Teacher controller**********");
    Teacher t = new Teacher();
    model.addAttribute("Teacher", t);
    List<Institute> teacherlist = instituteService.getall();
    model.addAttribute("teacherlist", teacherlist);
    // model.put("inf", teacherlist);
    LOGGER.info("institutes" + teacherlist.toString());
    return "signup/addTeacher";
}


}