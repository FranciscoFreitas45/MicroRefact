package cn.gson.oasys.Request;
import cn.gson.oasys.DTO.User;
public interface UserRequest {

   public User getUser(Long userId);
   public void setUser(User user,Long userId);
}