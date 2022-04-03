package com.app.service.impl;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.DAO.BranchDAO;
import com.app.DAO.ClassesDAO;
import com.app.pojo.Branch;
import com.app.pojo.Classes;
import com.app.service.ClassesService;
@Service("classesService")
public class ClassesServiceImpl implements ClassesService{

@Autowired
 private ClassesDAO classesDAO;


@Override
public List<Classes> getall(){
    return classesDAO.getall();
}


@Override
public Classes edit(int id){
    return classesDAO.edit(id);
}


@Override
public Classes find(int id){
    return classesDAO.find(id);
}


@Override
public List<Classes> getallOfParticularBranch(Branch branch){
    return classesDAO.getallOfParticularBranch(branch);
}


@Override
public void create(Classes classes){
    classesDAO.create(classes);
}


@Override
public void update(Classes classes){
    classesDAO.update(classes);
}


@Override
public void delet(int id){
    classesDAO.delet(id);
}


}