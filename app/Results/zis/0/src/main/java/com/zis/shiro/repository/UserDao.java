package com.zis.shiro.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.shiro.bean.User;
import com.zis.shiro.dto.UpdateUserInfo;
public interface UserDao extends JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Integer>{

 final String ADMIN = "admin";
	final String YES = "yes";
	final String NO = "no";
	final String NORMAL = "normal";
	final String DELETE = "delete";


@Query(value = "SELECT new com.zis.shiro.dto.UpdateUserInfo(ut, rt, cp) " + "FROM User ut, Role rt, Company cp " + "WHERE rt.id= ut.roleId AND cp.companyName LIKE %:companyName% AND ut.realName = :realName " + "AND cp.companyId = ut.companyId " + " AND cp.status = '" + NORMAL + "' AND ut.userName <> '" + ADMIN + "'" + " AND ut.isDelete = '" + NO + "' AND rt.status = '" + NORMAL + "' ORDER BY ut.updateTime DESC")
public Page<UpdateUserInfo> findUpdateUserInfoByCompanyNameLikeAndRealName(String companyName,String realName,Pageable page)
;

@Query(value = "FROM User WHERE id =:id AND userName <> '" + ADMIN + "' AND isDelete = '" + NO + "'")
public User findUserByUserId(Integer id)
;

@Query(value = "SELECT new com.zis.shiro.dto.UpdateUserInfo(ut, rt, cp) " + "FROM User ut, Role rt,Company cp " + "WHERE rt.id= ut.roleId AND ut.userName = :userName AND cp.companyId = ut.companyId " + "AND cp.status = '" + NORMAL + "' AND ut.userName <> '" + ADMIN + "'" + " AND ut.isDelete = '" + NO + "' AND rt.status = '" + NORMAL + "'")
public Page<UpdateUserInfo> findUpdateUserInfoByUserName(String userName,Pageable page)
;

@Query(value = "SELECT new com.zis.shiro.dto.UpdateUserInfo(ut, rt, cp) " + "FROM User ut, Role rt, Company cp " + "WHERE rt.id= ut.roleId AND cp.companyId = ut.companyId " + "AND cp.status = '" + NORMAL + "' AND ut.userName <> '" + ADMIN + "' AND ut.isDelete = '" + NO + "' AND rt.status = '" + NORMAL + "' ORDER BY ut.updateTime DESC")
public Page<UpdateUserInfo> findUserAllOrderByUserUpdateTimeDesc(Pageable page)
;

@Query(value = "SELECT new com.zis.shiro.dto.UpdateUserInfo(ut, rt, cp) " + "FROM User ut, Role rt, Company cp " + "WHERE rt.id= ut.roleId AND cp.companyId = :companyId AND cp.companyId = ut.companyId " + " AND cp.status = '" + NORMAL + "' AND ut.userName <> '" + ADMIN + "'" + " AND ut.isDelete = '" + NO + "' AND rt.status = '" + NORMAL + "' ORDER BY ut.updateTime DESC")
public List<UpdateUserInfo> findAllUserByCompanyId(Integer companyId)
;

@Query(value = "FROM User WHERE userName = :userName AND isDelete = '" + NO + "'")
public List<User> findByUserName(String userName)
;

public List<User> findByRoleId(Integer roleId)
;

@Query(value = "FROM User WHERE companyId = :companyId AND id = :userId AND isDelete = '" + NO + "'")
public User findAllUserByCompanyIdAndUserId(Integer companyId,Integer userId)
;

@Query(value = "SELECT new com.zis.shiro.dto.UpdateUserInfo(ut, rt, cp) " + "FROM User ut, Role rt, Company cp " + "WHERE rt.id= ut.roleId AND ut.realName = :realName AND cp.companyId = ut.companyId " + " AND cp.status = '" + NORMAL + "' AND ut.userName <> '" + ADMIN + "'" + " AND ut.isDelete = '" + NO + "' AND rt.status = '" + NORMAL + "' ORDER BY ut.updateTime DESC")
public Page<UpdateUserInfo> findUpdateUserInfoByRealName(String realName,Pageable page)
;

@Query(value = "SELECT new com.zis.shiro.dto.UpdateUserInfo(ut, rt, cp) " + "FROM User ut, Role rt, Company cp " + "WHERE rt.id= ut.roleId AND cp.companyName LIKE %:companyName% AND cp.companyId = ut.companyId " + " AND cp.status = '" + NORMAL + "' AND ut.userName <> '" + ADMIN + "'" + " AND ut.isDelete = '" + NO + "' AND rt.status = '" + NORMAL + "' ORDER BY ut.updateTime DESC")
public Page<UpdateUserInfo> findUpdateUserInfoByCompanyNameLike(String companyName,Pageable page)
;

}