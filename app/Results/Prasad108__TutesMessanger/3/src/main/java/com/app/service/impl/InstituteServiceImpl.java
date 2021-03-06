package com.app.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.DAO.InstituteDAO;
import com.app.pojo.Institute;
import com.app.pojo.Student;
import com.app.pojo.Teacher;
import com.app.service.InstituteService;
@Service("instituteService")
public class InstituteServiceImpl implements InstituteService{

@Autowired
 private InstituteDAO InstituteDAO;


@Override
public List<Institute> getall(){
    return InstituteDAO.getall();
}


@Override
public Institute edit(int id){
    return InstituteDAO.edit(id);
}


@Override
public void update(Institute institute){
    InstituteDAO.update(institute);
}


@Override
public void delet(int id){
    InstituteDAO.delet(id);
}


@Override
public String TreeStructureSujectsOFExam(int InstId,int ExamId){
    return InstituteDAO.TreeStructureSujectsOFExam(InstId, ExamId);
}


@Override
public List<Student> getallStudentWhoAreNotInAnyDivision(Institute institute){
    // TODO Auto-generated method stub
    return InstituteDAO.getallStudentWhoAreNotInAnyDivision(institute);
}


@Override
public String GetInstituteTree(int InstId){
    return InstituteDAO.GetInstituteTree(InstId);
}


@Override
public List<Student> getallPendingStudentForApproval(Institute institute){
    // TODO Auto-generated method stub
    return InstituteDAO.getallPendingStudentForApproval(institute);
}


@Override
public String TreeStructureSujectsNotInExam(int InstId,int ExamId){
    return InstituteDAO.TreeStructureSujectsNotInExam(InstId, ExamId);
}


@Override
public Institute find(String name){
    return InstituteDAO.find(name);
}


@Override
public void create(Institute institute){
    InstituteDAO.create(institute);
}


@Override
public String GetSubjectTree(int InstId){
    return InstituteDAO.GetSubjectTree(InstId);
}


@Override
public List<Teacher> getallPendingTeacherForApproval(Institute institute){
    // TODO Auto-generated method stub
    return InstituteDAO.getallPendingTeacherForApproval(institute);
}


}