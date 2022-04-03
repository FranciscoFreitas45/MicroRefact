package com.app.service;
 import java.util.List;
import com.app.pojo.Subject;
import com.app.pojo.SubjectDivComposit;
public interface SubjectService {


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