package cn.maxcj.Interface;
public interface IMenuService {

   public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds);
}