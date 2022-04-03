package com.app.DAO;
 import java.util.List;
import com.app.pojo.Login;
public interface LoginDAO {


public boolean exist(Login login)
;

public List<Login> getall()
;

public Login findByUsername(String username)
;

public Login edit(int id)
;

public Login find(int id)
;

public void create(Login login)
;

public void update(Login login)
;

public Login find_By_Uname_pwd(Login l)
;

public void delet(Login login)
;

public boolean Isenabled(Login login)
;

}