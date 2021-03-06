package com.app.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.DAO.ExamSubjectStudentCompositTableDAO;
import com.app.DAO.SubjectDivCompositDAO;
import com.app.pojo.ExamSubjectStudentCompositTable;
import com.app.pojo.Student;
import com.app.pojo.SubjectDivComposit;
import com.app.service.ExamSubjectStudentCompositTableService;
@Service("examSubjectStudentCompositTableService")
public class ExamSubjectStudentCompositTableServiceImpl implements ExamSubjectStudentCompositTableService{

@Autowired
 private ExamSubjectStudentCompositTableDAO examSubStudCompDAO;


@Override
public String examSubjectStudentResult(int ExamId,int SubdivId){
    // TODO Auto-generated method stub
    return examSubStudCompDAO.examSubjectStudentResult(ExamId, SubdivId);
}


@Override
public List<ExamSubjectStudentCompositTable> getall(){
    return examSubStudCompDAO.getall();
}


@Override
public ExamSubjectStudentCompositTable edit(int id){
    return examSubStudCompDAO.edit(id);
}


@Override
public ExamSubjectStudentCompositTable find(int id){
    return examSubStudCompDAO.find(id);
}


@Override
public ExamSubjectStudentCompositTable findByExamSubDivId(int examId,int subDivId){
    return examSubStudCompDAO.findByExamSubDivId(examId, subDivId);
}


@Override
public void create(ExamSubjectStudentCompositTable examSubjectStudentComp){
    examSubStudCompDAO.create(examSubjectStudentComp);
}


@Override
public void update(ExamSubjectStudentCompositTable examSubjectStudentComp){
    examSubStudCompDAO.update(examSubjectStudentComp);
}


@Override
public void delet(int id){
    examSubStudCompDAO.delet(id);
}


@Override
public void deletStudentFromExam(int StudId,int subDivId,int examId){
    examSubStudCompDAO.deletStudentFromExam(StudId, subDivId, examId);
}


@Override
public void deletSubjectFromExam(int ExamId,int SubdivId){
    examSubStudCompDAO.deletSubjectFromExam(ExamId, SubdivId);
}


@Override
public List<Student> findByExamId(int examId,int subDivId){
    return examSubStudCompDAO.findByExamId(examId, subDivId);
}


}