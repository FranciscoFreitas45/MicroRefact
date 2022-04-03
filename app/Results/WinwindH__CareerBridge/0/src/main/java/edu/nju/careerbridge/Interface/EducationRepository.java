package edu.nju.careerbridge.Interface;
public interface EducationRepository {

   public void deleteByPhone(String phone);
   public Object save(Object Object);
   public Education findByPhone(String phone);
}