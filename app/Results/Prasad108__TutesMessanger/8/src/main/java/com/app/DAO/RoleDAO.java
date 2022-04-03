package com.app.DAO;
 import java.util.List;
import com.app.pojo.Role;
public interface RoleDAO {


public List<Role> getall()
;

public Role edit(int id)
;

public Role find(int id)
;

public void create(Role role)
;

public void update(Role role)
;

public Role findByName(String name)
;

public void delet(int id)
;

}