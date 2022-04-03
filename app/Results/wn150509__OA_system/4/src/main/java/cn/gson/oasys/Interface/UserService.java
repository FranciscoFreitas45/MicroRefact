package cn.gson.oasys.Interface;
public interface UserService {

   public Page<User> findmyemployuser(int page,String baseKey,long parentid);
}