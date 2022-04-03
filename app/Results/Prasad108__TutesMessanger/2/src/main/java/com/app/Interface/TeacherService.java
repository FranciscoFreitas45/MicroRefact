package com.app.Interface;
public interface TeacherService {

   public Institute GetInstitute(int id);
   public Teacher find(int id);
   public void update(Teacher teacher);
   public void changeUserName(String newUserName,Login login);
   public void changePassword(String newPassword,Login login);
}