package cn.gson.oasys.Interface;
public interface PositionDao {

   public Object findAll(Object Object);
   public Object findOne(Object Object);
   public List<Position> findByDeptidAndNameNotLike(Long deptid,String name);
}