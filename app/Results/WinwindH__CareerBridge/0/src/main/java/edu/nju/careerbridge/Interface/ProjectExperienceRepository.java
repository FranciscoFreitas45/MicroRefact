package edu.nju.careerbridge.Interface;
public interface ProjectExperienceRepository {

   public void deleteByPhone(String phone);
   public Object save(Object Object);
   public List<ProjectExperience> findByPhone(String phone);
}