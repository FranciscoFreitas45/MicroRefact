package com.app.service;
 import java.util.List;
import com.app.pojo.Institute;
import com.app.pojo.Student;
import com.app.pojo.Teacher;
public interface InstituteService {


public List<Institute> getall()
;

public Institute edit(int id)
;

public void update(Institute institute)
;

public void delet(int id)
;

public String TreeStructureSujectsOFExam(int InstId,int ExamId)
;

public List<Student> getallStudentWhoAreNotInAnyDivision(Institute institute)
;

public String GetInstituteTree(int InstId)
;

public List<Student> getallPendingStudentForApproval(Institute institute)
;

public String TreeStructureSujectsNotInExam(int InstId,int ExamId)
;

public Institute find(String name)
;

public void create(Institute institute)
;

public String GetSubjectTree(int InstId)
;

public List<Teacher> getallPendingTeacherForApproval(Institute institute)
;

}