package cn.gson.oasys.Request;
import cn.gson.oasys.DTO.Attends;
public interface AttendsRequest {

   public Set<Attends> getaSet(Long userId);
   public void setaSet(Set<Attends> aSet,Long userId);
}