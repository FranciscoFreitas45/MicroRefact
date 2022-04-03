package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.entity.Privilege;
import java.util.List;
public interface PrivilegeDAO {


public Privilege addPrivilege(Privilege privilege)
;

public List<Privilege> getAllPrivileges()
;

public Privilege findByName(String name)
;

public void delete(Privilege privilege)
;

public void deleteAllPrivileges()
;

}