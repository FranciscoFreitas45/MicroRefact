package com.app.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.DAO.BranchDAO;
import com.app.pojo.Branch;
import com.app.pojo.Institute;
import com.app.service.BranchService;
@Service("branchService")
public class BranchServiceImpl implements BranchService{

@Autowired
 private BranchDAO branchDAO;


@Override
public List<Branch> getall(){
    return branchDAO.getall();
}


@Override
public Branch edit(int id){
    return branchDAO.edit(id);
}


@Override
public Branch find(int id){
    return branchDAO.find(id);
}


@Override
public void create(Branch branch){
    branchDAO.create(branch);
}


@Override
public void update(Branch branch){
    branchDAO.update(branch);
}


@Override
public void delet(int id){
    branchDAO.delet(id);
}


@Override
public List<Branch> getallOfParticularInstitute(Institute id){
    return branchDAO.getallOfParticularInstitute(id);
}


}