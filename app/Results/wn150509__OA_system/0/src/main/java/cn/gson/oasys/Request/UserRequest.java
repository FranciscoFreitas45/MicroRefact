package cn.gson.oasys.Request;
import cn.gson.oasys.DTO.User;
public interface UserRequest {

   public void setMyuser(User myuser,Long userId);
   public User getMyuser(Long userId);
}