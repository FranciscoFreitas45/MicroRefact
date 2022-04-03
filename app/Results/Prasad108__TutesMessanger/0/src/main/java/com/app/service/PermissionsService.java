package com.app.service;
 import java.util.List;
import com.app.pojo.Permissions;
import com.app.pojo.Teacher;
public interface PermissionsService {


public List<Permissions> getall()
;

public Permissions edit(int id)
;

public Permissions GetPermissionOfTeacher(Teacher t)
;

public Permissions find(int id)
;

public void create(Permissions permissions)
;

public void update(Permissions teacher)
;

public void delet(int id)
;

}