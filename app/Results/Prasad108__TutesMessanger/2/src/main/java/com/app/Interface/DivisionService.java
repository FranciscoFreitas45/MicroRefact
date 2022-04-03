package com.app.Interface;
public interface DivisionService {

   public List<Division> getallOfParticularClass(Classes classes);
   public void create(Division div);
   public void update(Division classes);
   public void delet(int id);
   public Division find(int id);
}