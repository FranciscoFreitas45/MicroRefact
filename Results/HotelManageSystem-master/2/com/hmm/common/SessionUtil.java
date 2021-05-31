import java.util.List;
import javax.servlet.http.HttpSession;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
public class SessionUtil {

 public  String USER;

 public  String USERNAME;

 public  String GROUPS;

 public  String GROUPNAMES;

 public  String DEPTNAME;

 public  String LOGINTYPE;

 public  String IMAGE;


@SuppressWarnings("unchecked")
public List<Group> getGroupList(HttpSession session){
    Object groupList = session.getAttribute(GROUPS);
    return groupList == null ? null : (List<Group>) groupList;
}


public void setGroupList(HttpSession session,List<Group> groupList){
    session.setAttribute(GROUPS, groupList);
}


public String getGroupNames(HttpSession session){
    Object groupNames = session.getAttribute(GROUPNAMES);
    return groupNames == null ? null : (String) groupNames;
}


public User getUser(HttpSession session){
    Object user = session.getAttribute(USER);
    return user == null ? null : (User) user;
}


public String getDeptName(HttpSession session){
    String deptName = (String) session.getAttribute(DEPTNAME);
    return deptName == null ? null : deptName;
}


public void setIamge(HttpSession session,String Image){
    session.setAttribute(IMAGE, Image);
}


public void setDeptName(HttpSession session,String deptName){
    session.setAttribute(DEPTNAME, deptName);
}


public String getLogintyp(HttpSession session){
    String logintype = (String) session.getAttribute(LOGINTYPE);
    return logintype == null ? null : logintype;
}


public void setLogintype(HttpSession session,String logintype){
    session.setAttribute(LOGINTYPE, logintype);
}


public void setUserName(HttpSession session,String userName){
    session.setAttribute(USERNAME, userName);
}


public void setGroupNames(HttpSession session,String groupNames){
    session.setAttribute(GROUPNAMES, groupNames);
}


public String getUserName(HttpSession session){
    Object userName = session.getAttribute(USERNAME);
    return userName == null ? null : (String) userName;
}


public void setUser(HttpSession session,User user){
    session.setAttribute(USER, user);
    setUserName(session, user.getId());
}


public void removeAttribute(HttpSession session){
    session.removeAttribute(USER);
    session.removeAttribute(USERNAME);
    session.removeAttribute(GROUPS);
    session.removeAttribute(GROUPNAMES);
    session.removeAttribute(DEPTNAME);
    session.removeAttribute(LOGINTYPE);
}


public String getImage(HttpSession session){
    String image = (String) session.getAttribute(IMAGE);
    return image == null ? null : image;
}


}