package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.PrivilegeType;
import java.util.List;
public interface PrivilegeService {


public Privilege getPrivilegeById(Long id)
;

public void deleteAllPrivilege()
;

public Privilege addPrivilege(String name,String description,PrivilegeType privilegeType)
;

public List<Privilege> getAllPrivileges()
;

public void deletePrivilege()
;

}