package com.app.DAO;
 import java.util.List;
import com.app.pojo.Institute;
import com.app.pojo.Student;
import com.app.pojo.Subject;
import com.app.pojo.SubjectDivComposit;
public interface SubjectDAO {


public List<Subject> getall()
;

public Subject edit(int id)
;

public Subject find(int id)
;

public void create(Subject subject)
;

public void update(Subject subject)
;

public List<Subject> getallOfInstitute(int instituteId)
;

public void delet(int id)
;

public void deleteFromInstitute(int subId)
;

}