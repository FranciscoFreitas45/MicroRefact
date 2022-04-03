package com.gs.service.impl;
 import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.UserDAO;
import com.gs.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService{

@Resource
 private  UserDAO userDAO;


public List<User> queryByPagerDisable(Pager pager){
    return userDAO.queryByPagerDisable(pager);
}


public int countNO(User user){
    return userDAO.countNO(user);
}


@Override
public int queryPhoneByOne(String userPhone){
    return userDAO.queryPhoneByOne(userPhone);
}


@Override
public void updatePwdByEmail(String pwd,String email){
    userDAO.updatePwdByEmail(pwd, email);
}


public int inactive(String id){
    return userDAO.inactive(id);
}


public int countByBlurred(User user,User user1){
    return countByBlurred(user, user1);
}


@Override
public Set<String> queryRoles(String email){
    return userDAO.queryRoles(email);
}


public int countCar(){
    return userDAO.countCar();
}


@Override
public List<User> queryByCompanyId(String companyId){
    return userDAO.queryByCompanyId(companyId);
}


public int queryIsPhoneByOne(String phone,String userId){
    return userDAO.queryIsPhoneByOne(phone, userId);
}


public int queryIsEmailByOne(String userEmail,String userId){
    return userDAO.queryIsEmailByOne(userEmail, userId);
}


public User query(User user){
    return userDAO.query(user);
}


public int count(User user){
    return userDAO.count(user);
}


public List<User> querySystemAdminByPager(Pager pager,String status){
    return userDAO.querySystemAdminByPager(pager, status);
}


public int active(String id){
    return userDAO.active(id);
}


public User queryInfoById(String id){
    return userDAO.queryInfoById(id);
}


public int countStatus(User user,String status){
    return userDAO.countStatus(user, status);
}


public int deleteById(String id){
    return userDAO.deleteById(id);
}


@Override
public User queryByEmail(String email){
    return userDAO.queryByEmail(email);
}


public int countSystemAdminStatus(User user,String status){
    return userDAO.countSystemAdminStatus(user, status);
}


@Override
public int updIcon(String userId,String userIcon){
    return userDAO.updIcon(userId, userIcon);
}


public List<User> queryByPagerLike(Pager pager){
    return userDAO.queryByPagerLike(pager);
}


public List<User> queryAllByPager(Pager pager){
    return userDAO.queryAllByPager(pager);
}


@Override
public List<User> queryByPagerAll(Pager pager){
    return userDAO.queryByPagerAll(pager);
}


public List<User> queryEmail(String ids){
    return userDAO.queryEmail(ids);
}


public int countOK(User user){
    return userDAO.countOK(user);
}


public int countAll(User user){
    return userDAO.countAll(user);
}


public int batchInsert(List<User> list){
    return userDAO.batchInsert(list);
}


public int batchUpdate(List<User> list){
    return userDAO.batchUpdate(list);
}


public int insert(User user){
    return userDAO.insert(user);
}


public int update(User user){
    return userDAO.update(user);
}


public int queryIsIdentityByOne(String userIdentity,String userId){
    return userDAO.queryIsIdentityByOne(userIdentity, userId);
}


@Override
public List<User> queryAll(String status){
    return userDAO.queryAll(status);
}


public int delete(User user){
    return userDAO.delete(user);
}


public int batchDelete(List<User> list){
    return userDAO.batchDelete(list);
}


@Override
public User queryByPhone(String userPhone){
    return userDAO.queryByPhone(userPhone);
}


public int countAllCar(User user){
    return userDAO.countAllCar(user);
}


@Override
public void updatePwdByPhone(String pwd,String phone){
    userDAO.updatePwdByPhone(pwd, phone);
}


public int countCarByNo(User user){
    return userDAO.countNO(user);
}


@Override
public List<User> queryByRoleName(String roleName){
    return userDAO.queryByRoleName(roleName);
}


public int updateSelfManage(User user){
    return userDAO.updateSelfManage(user);
}


@Override
public int queryPhoneIsNull(String rtphone){
    return userDAO.queryPhoneIsNull(rtphone);
}


public List<User> queryCarByOk(Pager pager){
    return userDAO.queryCarByOk(pager);
}


public void updatePwd(User user){
    userDAO.updatePwd(user);
}


@Override
public Set<String> queryPermissions(String email){
    return userDAO.queryPermissions(email);
}


public List<User> blurredQuery(Pager pager,User user){
    return null;
}


public List<User> queryCarByRoleName(Pager pager){
    return userDAO.queryCarByRoleName(pager);
}


public List<User> queryByStatus(String status){
    return userDAO.queryAll(status);
}


public int countCarByOk(User user){
    return userDAO.countCarByOk(user);
}


public List<User> queryCarByPagerLike(Pager pager){
    return userDAO.queryCarByPagerLike(pager);
}


public int countByDisable(User user){
    return userDAO.countByDisable(user);
}


public List<User> queryCarByNo(Pager pager){
    return userDAO.queryCarByNo(pager);
}


@Override
public User queryUser(String email){
    return userDAO.queryUser(email);
}


public User queryById(User user){
    return userDAO.queryById(user);
}


public List<User> queryByPager(Pager pager){
    return userDAO.queryByPager(pager);
}


@Override
public int queryEmailIsNull(String eamil){
    return userDAO.queryEmailIsNull(eamil);
}


}