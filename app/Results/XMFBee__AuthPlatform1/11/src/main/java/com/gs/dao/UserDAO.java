package com.gs.dao;
 import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;
@Repository
public interface UserDAO extends BaseDAO<String, User>{


public int countAll(User user)
;

public List<User> queryByPagerDisable(Pager pager)
;

public int countNO(User user)
;

public int queryPhoneByOne(String phone)
;

public void updatePwdByEmail(String pwd,String email)
;

public int queryIsIdentityByOne(String userIdentity,String userId)
;

public User queryByPhone(String userPhone)
;

public int countAllCar(User user)
;

public void updatePwdByPhone(String pwd,String phone)
;

public Set<String> queryRoles(String email)
;

public int countCarByNo(User user)
;

public int countCar()
;

public List<User> queryByRoleName(String roleName)
;

public List<User> queryByCompanyId(String companyId)
;

public int updateSelfManage(User user)
;

public int queryPhoneIsNull(String phone)
;

public List<User> queryCarByOk(Pager pager)
;

public int queryIsPhoneByOne(String phone,String userId)
;

public int queryIsEmailByOne(String userEmail,String userId)
;

public void updatePwd(User user)
;

public List<User> querySystemAdminByPager(Pager pager,String status)
;

public Set<String> queryPermissions(String email)
;

public List<User> queryCarByRoleName(Pager pager)
;

public User queryInfoById(String id)
;

public int countCarByOk(User user)
;

public List<User> queryCarByPagerLike(Pager pager)
;

public int countStatus(User user,String status)
;

public User queryByEmail(String email)
;

public int countSystemAdminStatus(User user,String status)
;

public List<User> queryAllByPager(Pager pager)
;

public int updIcon(String userId,String userIcon)
;

public List<User> queryByPagerLike(Pager pager)
;

public List<User> queryCarByNo(Pager pager)
;

public List<User> queryByPagerAll(Pager pager)
;

public List<User> queryEmail(String ids)
;

public int countOK(User user)
;

public User queryUser(String email)
;

public int queryEmailIsNull(String email)
;

public User queryById(User user)
;

}