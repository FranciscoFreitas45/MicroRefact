import java.util.List;
import cn.com.cnc.fcc.domain.RbacRight;
public class RbacRightDTO {

 private  Integer menuId;

 private  Integer rightId;

 private  Long id;

 private  Integer pMenuId;

 private  String menuName;

 private  String maxRightCode;

 private  String updateTime;

 private  String rightName;

 private  RbacRight rbacRight;

 private  List<String> menuList;


public void setpMenuId(Integer pMenuId){
    this.pMenuId = pMenuId;
}


public Integer getpMenuId(){
    return pMenuId;
}


public void setRbacRight(RbacRight rbacRight){
    this.rbacRight = rbacRight;
}


public Integer getMenuId(){
    return menuId;
}


public Long getId(){
    return id;
}


public List<String> getMenuList(){
    return menuList;
}


public void setMenuList(List<String> menuList){
    this.menuList = menuList;
}


public RbacRight getRbacRight(){
    return rbacRight;
}


public void setMaxRightCode(String maxRightCode){
    this.maxRightCode = maxRightCode;
}


public String getUpdateTime(){
    return updateTime;
}


public String getRightName(){
    return rightName;
}


public void setRightName(String rightName){
    this.rightName = rightName;
}


public void setMenuId(Integer menuId){
    this.menuId = menuId;
}


public String getMaxRightCode(){
    return maxRightCode;
}


public void setUpdateTime(String updateTime){
    this.updateTime = updateTime;
}


public void setId(Long id){
    this.id = id;
}


public String getMenuName(){
    return menuName;
}


public void setRightId(Integer rightId){
    this.rightId = rightId;
}


public Integer getRightId(){
    return rightId;
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


}