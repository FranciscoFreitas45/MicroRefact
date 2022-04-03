package edu.nju.careerbridge.Interface;
public interface SkillRepository {

   public void deleteByPhone(String phone);
   public Object save(Object Object);
   public List<Skill> findByPhone(String phone);
}