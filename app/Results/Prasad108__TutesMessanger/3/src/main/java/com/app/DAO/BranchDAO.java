package com.app.DAO;
 import java.util.List;
import com.app.pojo.Branch;
import com.app.pojo.Institute;
public interface BranchDAO {


public List<Branch> getall()
;

public Branch edit(int id)
;

public Branch find(int id)
;

public void create(Branch branch)
;

public void update(Branch branch)
;

public void delet(int id)
;

public List<Branch> getallOfParticularInstitute(Institute id)
;

}