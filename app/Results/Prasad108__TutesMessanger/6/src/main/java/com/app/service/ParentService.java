package com.app.service;
 import java.util.List;
import com.app.pojo.Parent;
public interface ParentService {


public List<Parent> getall()
;

public Parent edit(int id)
;

public Parent find(int id)
;

public void create(Parent parent)
;

public void update(Parent parent)
;

public void delet(Parent parent)
;

public Parent findByStudentId(int studId)
;

}