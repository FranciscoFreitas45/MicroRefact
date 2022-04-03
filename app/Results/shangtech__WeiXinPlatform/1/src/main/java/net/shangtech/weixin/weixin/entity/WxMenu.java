package net.shangtech.weixin.weixin.entity;
 import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "wx_menu")
public class WxMenu extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "sys_user_id")
 private  Integer sysUserId;

@Column(name = "parent_id")
 private  Integer parentId;

@Column(name = "menu_name")
 private  String menuName;

@Column(name = "menu_type")
 private  Integer menuType;

@Column(name = "menu_url")
 private  String menuUrl;

@Column(name = "menu_key")
 private  String menuKey;

@Column(name = "sort")
 private  Integer sort;

@Column(name = "reply_type")
 private  Integer replyType;

@Column(name = "reply_content")
 private  String replyContent;

@Transient
 private  List<WxMenu> children;

@Transient
 private  boolean hasChildren;


public void setReplyContent(String replyContent){
    this.replyContent = replyContent;
}


public String getReplyContent(){
    return replyContent;
}


public void setReplyType(Integer replyType){
    this.replyType = replyType;
}


public String getMenuUrl(){
    return menuUrl;
}


public Integer getSysUserId(){
    return sysUserId;
}


public void setMenuUrl(String menuUrl){
    this.menuUrl = menuUrl;
}


public void setHasChildren(boolean hasChildren){
    this.hasChildren = hasChildren;
}


public void setMenuType(Integer menuType){
    this.menuType = menuType;
}


public void setSort(Integer sort){
    this.sort = sort;
}


public Integer getSort(){
    return sort;
}


public List<WxMenu> getChildren(){
    return children;
}


public Integer getMenuType(){
    return menuType;
}


public boolean getHasChildren(){
    return hasChildren;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public String getMenuKey(){
    return menuKey;
}


public void setParentId(Integer parentId){
    this.parentId = parentId;
}


public String getMenuName(){
    return menuName;
}


public Integer getReplyType(){
    return replyType;
}


public void setMenuKey(String menuKey){
    this.menuKey = menuKey;
}


public void setChildren(List<WxMenu> children){
    this.children = children;
}


public Integer getParentId(){
    if (parentId == null)
        parentId = 0;
    return parentId;
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


}