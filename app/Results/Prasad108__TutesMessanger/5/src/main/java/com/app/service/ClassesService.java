package com.app.service;
 import java.util.List;
import com.app.pojo.Branch;
import com.app.pojo.Classes;
public interface ClassesService {


public List<Classes> getall()
;

public Classes edit(int id)
;

public Classes find(int id)
;

public List<Classes> getallOfParticularBranch(Branch branch)
;

public void create(Classes classes)
;

public void update(Classes classes)
;

public void delet(int id)
;

}