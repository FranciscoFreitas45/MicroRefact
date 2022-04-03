package edu.nju.careerbridge.Interface;
public interface UserBasicMessageRepository {

   public void deleteByPhone(String phone);
   public Object save(Object Object);
   public UserBasicMessage findByPhone(String phone);
}