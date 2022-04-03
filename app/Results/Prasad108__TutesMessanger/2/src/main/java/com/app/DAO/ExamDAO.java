package com.app.DAO;
 import java.util.List;
import com.app.pojo.Classes;
import com.app.pojo.Division;
import com.app.pojo.Exam;
public interface ExamDAO {


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