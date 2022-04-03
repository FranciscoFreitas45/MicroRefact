package com.app.Interface;
public interface ClassesService {

   public List<Classes> getallOfParticularBranch(Branch branch);
   public void create(Classes classes);
   public void update(Classes classes);
   public void delet(int id);
   public Classes find(int id);
}