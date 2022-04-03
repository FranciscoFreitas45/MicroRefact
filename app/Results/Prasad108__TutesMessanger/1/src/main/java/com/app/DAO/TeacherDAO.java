package com.app.DAO;
 import java.util.List;
import com.app.pojo.Institute;
import com.app.pojo.Login;
import com.app.pojo.Teacher;
public interface TeacherDAO {


public List<Teacher> getall()
;

public void changeUserName(String newUserName,Login login)
;

public Teacher edit(int id)
;

public Boolean checkPassword(String oldPassword,Integer id)
;

public Teacher find(int id)
;

public Institute GetInstitute(int id)
;

public void create(Teacher teacher)
;

public void update(Teacher teacher)
;

public void delet(Teacher teacher)
;

public Login getLoginIdByEmail(String email)
;

public Teacher findByLoginId(int id)
;

public void changePassword(String newPassword,Login login)
;

}