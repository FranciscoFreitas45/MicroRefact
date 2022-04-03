package cn.gson.oasys.Request;
import cn.gson.oasys.DTO.User;
public interface UserRequest {

   public void setMailUserid(User mailUserid,Long userId);
   public User getMailUserid(Long userId);
}