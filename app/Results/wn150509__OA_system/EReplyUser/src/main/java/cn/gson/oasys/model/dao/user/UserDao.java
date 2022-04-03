package cn.gson.oasys.model.dao.user;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import cn.gson.oasys.model.entity.user.Dept;
import cn.gson.oasys.model.entity.user.User;
public interface UserDao extends JpaRepository<User, Long>{


@Query("select u from User u where  (u.userName like %?1% or u.realName like %?1%) and u.fatherId=?2 ")
public Page<User> findbyFatherId(String name,Long parentid,Pageable pa)
;

public Page<User> findByPinyinLike(String pinyin,Pageable pa)
;

@Query("from User u where u.dept.deptName like %?1% or u.userName like %?1% or u.realName like %?1% or u.userTel like %?1% or u.role.roleName like %?1%")
public Page<User> findnamelike(String name,Pageable pa)
;

public User findByUserName(String title)
;

@Query("from User u where u.userName like %:name% or u.realName like %:name%")
public Page<User> findbyUserNameLike(String name,Pageable pa)
;

public Page<User> findByIsLock(Integer isLock,Pageable pa)
;

@Query("from User u where (u.userName = ?1 or u.userTel = ?1) and u.password =?2")
public User findOneUser(String userName,String password)
;

public Page<User> findByrealNameLike(String title,Pageable pa)
;

public List<User> findByUserId(Long id)
;

@Query("from User u where (u.userName like ?1 or u.dept.deptName like ?1 or u.userTel like ?1 or u.position.name like ?1) and u.pinyin like ?2")
public Page<User> findSelectUsers(String baseKey,String pinyinm,Pageable pa)
;

@Query("from User u where u.userName like ?1 or u.dept.deptName like ?1 or u.userTel like ?1 or u.position.name like ?1 or u.pinyin like ?2")
public Page<User> findUsers(String baseKey,String baseKey2,Pageable pa)
;

public List<User> findByDept(Dept dept)
;

@Query("select u from User u where u.userName=:name")
public User findid(String name)
;

@Query("select u from User u where u.role.roleId=?1")
public List<User> findrole(Long lid)
;

public Page<User> findByFatherId(Long parentid,Pageable pa)
;

@Query("select tu.pkId from Taskuser tu where tu.taskId.taskId=:taskid and tu.userId.userId=:userid")
public Long findpkId(Long taskid,Long userid)
;

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getShareuser(Long userId);

public User getUser(Long userId);

public void setShareuser(Long userId,User shareuser);

public void setUser(Long userId,User user);

public User getShareuser(Long userId);

public User getUser(Long userId);

public void setShareuser(Long userId,User shareuser);

public void setUser(Long userId,User user);

public void setMyuser(Long userId,User myuser);

public User getMyuser(Long userId);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getUserId(Long userIdv2);

public void setUserId(Long userIdv2,User userId);

public User getUserId(Long userIdv2);

public void setUserId(Long userIdv2,User userId);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getReciverId(Long userId);

public void setReciverId(Long userId,User reciverId);

public User getMailUserId(Long userId);

public void setMailUserId(Long userId,User mailUserId);

public void setMailUserid(Long userId,User mailUserid);

public User getMailUserid(Long userId);

public User getUserId(Long userIdv2);

public void setUserId(Long userIdv2,User userId);

public User getUsersId(Long userId);

public void setUsersId(Long userId,User usersId);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getUserId(Long userIdv2);

public void setUserId(Long userIdv2,User userId);

public User getUserId(Long userIdv2);

public void setUserId(Long userIdv2,User userId);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public User getUser(Long userId);

public void setUser(Long userId,User user);

public void setIsLock(Long id,Integer isLock);

public void setUserTel(Long id,String userTel);

public void setRealName(Long id,String realName);

public void setEamil(Long id,String eamil);

public void setAddress(Long id,String address);

public void setUserEdu(Long id,String userEdu);

public void setSchool(Long id,String school);

public void setIdCard(Long id,String idCard);

public void setBank(Long id,String bank);

public void setThemeSkin(Long id,String themeSkin);

public void setSalary(Long id,String salary);

public void setFatherId(Long id,Long fatherId);

public void setPassword(Long id,String password);

public void setDept(Long id,Dept dept);

public void setRole(Long id,Role role);

public void setPosition(Long id,Position position);

public void setPinyin(Long id,String pinyin);

public void setSex(Long id,String sex);

public void setBirth(Long id,Date birth);

public void setUserSign(Long id,String userSign);

public void setImgPath(Long id,String imgPath);

public void setaSet(Long id,Set<Attends> aSet);

}