package com.app.Interface;
public interface BranchService {

   public List<Branch> getallOfParticularInstitute(Institute id);
   public void create(Branch branch);
   public void update(Branch branch);
   public void delet(int id);
   public Branch find(int id);
}