package edu.nju.careerbridge.Interface;
public interface HonorRepository {

   public void deleteByPhone(String phone);
   public Object save(Object Object);
   public List<Honor> findByPhone(String phone);
}