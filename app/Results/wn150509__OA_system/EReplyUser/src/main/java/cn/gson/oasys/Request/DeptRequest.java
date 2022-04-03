package cn.gson.oasys.Request;
import cn.gson.oasys.DTO.Dept;
public interface DeptRequest {

   public void setDept(Dept dept,Long deptId);
   public Dept getDept(Long deptId);
}