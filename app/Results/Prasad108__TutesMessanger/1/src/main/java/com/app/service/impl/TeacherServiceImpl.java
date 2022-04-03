package com.app.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.DAO.TeacherDAO;
import com.app.pojo.Branch;
import com.app.pojo.Classes;
import com.app.pojo.Division;
import com.app.pojo.Institute;
import com.app.pojo.Login;
import com.app.pojo.Teacher;
import com.app.service.BranchService;
import com.app.service.ClassesService;
import com.app.service.DivisionService;
import com.app.service.InstituteService;
import com.app.service.TeacherService;
import com.app.Interface.BranchService;
import com.app.Interface.ClassesService;
import com.app.Interface.DivisionService;
import com.app.Interface.InstituteService;
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

@Autowired
 private TeacherDAO TeacherDAO;

@Autowired
 private BranchService branchService;

@Autowired
 private ClassesService classesService;

@Autowired
 private DivisionService divisionService;

@Autowired
 private InstituteService instituteService;


@Override
public List<Teacher> getall(){
    return TeacherDAO.getall();
}


@Override
public void changeUserName(String newUserName,Login login){
    TeacherDAO.changeUserName(newUserName, login);
}


@Override
public Teacher edit(int id){
    return TeacherDAO.edit(id);
}


@Override
public Boolean checkPassword(String oldPassword,Integer id){
    return TeacherDAO.checkPassword(oldPassword, id);
}


@Override
public Teacher find(int id){
    return TeacherDAO.find(id);
}


@Override
public Institute GetInstitute(int id){
    return TeacherDAO.GetInstitute(id);
}


@Override
public void create(Teacher teacher){
    TeacherDAO.create(teacher);
}


@Override
public void update(Teacher teacher){
    TeacherDAO.update(teacher);
}


@Override
public void delet(Teacher teacher){
    TeacherDAO.delet(teacher);
}


@Override
public Login getLoginIdByEmail(String email){
    return TeacherDAO.getLoginIdByEmail(email);
}


@Override
public Teacher findByLoginId(int id){
    return TeacherDAO.findByLoginId(id);
}


@Override
public void changePassword(String newPassword,Login login){
    TeacherDAO.changePassword(newPassword, login);
}


}