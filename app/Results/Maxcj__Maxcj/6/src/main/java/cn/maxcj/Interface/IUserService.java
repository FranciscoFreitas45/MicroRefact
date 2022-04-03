package cn.maxcj.Interface;
public interface IUserService {

   public User getByAccount(String account);
   public Integer isSheLian(Integer userId);
}