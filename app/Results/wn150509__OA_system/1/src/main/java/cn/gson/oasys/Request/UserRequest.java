package cn.gson.oasys.Request;
import cn.gson.oasys.DTO.User;
public interface UserRequest {

   public User getUserId(Long userIdv2);
   public void setUserId(User userId,Long userIdv2);
}