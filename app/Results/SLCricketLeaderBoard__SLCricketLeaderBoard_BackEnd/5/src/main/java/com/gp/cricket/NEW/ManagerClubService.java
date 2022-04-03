package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.ManagerRepository;
import com.gp.cricket.entity.Manager;
@Service
public class ManagerClubService {

@Autowired
 private ManagerRepository managerrepository;


public Manager getManagerId(Integer managerIdv2){
return managerrepository.getManagerId(managerIdv2);
}


public void setManagerId(Integer managerIdv2,Manager managerId){
managerrepository.setManagerId(managerIdv2,managerId);
}


}