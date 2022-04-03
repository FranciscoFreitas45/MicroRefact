package com.app.service.impl;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.pojo.Institute;
import com.app.pojo.Login;
import com.app.pojo.Permissions;
import com.app.pojo.Role;
import com.app.pojo.Teacher;
import com.app.service.AppAdminService;
import com.app.service.InstituteService;
import com.app.service.LoginService;
import com.app.service.PermissionsService;
import com.app.service.RoleService;
import com.app.service.TeacherService;
import com.app.Interface.RoleService;
import com.app.Interface.TeacherService;
import com.app.Interface.LoginService;
import com.app.Interface.PermissionsService;
import com.app.DTO.Teacher;
import com.app.DTO.Login;
@Service("appAdminService")
public class AppAdminServiceImpl implements AppAdminService{

@Autowired
 private InstituteService instituteService;

@Autowired
 private RoleService RoleService;

@Autowired
 private TeacherService teacherService;

@Autowired
 private LoginService loginService;

@Autowired
 private PermissionsService permissionsService;


@Override
@Transactional
public void AddInstituteWithAdmin(Institute inst,Teacher teacher){
    // save institute
    instituteService.create(inst);
    System.out.println("institue saved");
    // got the role of institute admin
    Role r = RoleService.findByName("ROLE_INSTITUTE_ADMIN");
    // created the object of login with username as email-id and pwd as contact no
    Login login = new Login(r, teacher.getEmail(), teacher.getContactno());
    login.setEnableInstitute(true);
    // save login credentials
    loginService.create(login);
    // create permission for admin he has all the permissions and SAVE them
    Permissions permissions = new Permissions(true, true, true, true, true, true, true, true, true, true, true, true, true);
    permissionsService.create(permissions);
    Institute i = instituteService.find(inst.getName());
    Teacher teach = new Teacher(teacher.getFname(), teacher.getLname(), teacher.getEmail(), teacher.getContactno());
    // set institute
    teach.setInstitute(i);
    // set Login
    teach.setLogin(login);
    // set permissions
    teach.setPermissions(permissions);
    // create teacher
    teacherService.create(teach);
}


}