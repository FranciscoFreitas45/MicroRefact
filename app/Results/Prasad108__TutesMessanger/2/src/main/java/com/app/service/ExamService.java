package com.app.service;
 import java.util.List;
import com.app.pojo.Exam;
public interface ExamService {


public Exam edit(int id)
;

public Exam find(int id)
;

public void create(Exam exam)
;

public void update(Exam exam)
;

public void delet(Exam exam)
;

public List<Exam> getallOfParticularInstitute(int instituteId)
;

}