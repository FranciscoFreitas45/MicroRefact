package cn.gson.oasys.Interface;
public interface RolepowerlistDao {

   public List<Rolemenu> findname(Long id,Long roleid,Boolean bo,Boolean le,String name);
   public List<Rolemenu> findbyparentxianall(Long id,Long roleid,Boolean bo,Boolean le);
}