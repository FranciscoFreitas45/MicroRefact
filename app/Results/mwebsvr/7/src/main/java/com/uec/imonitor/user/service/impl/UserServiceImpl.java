package com.uec.imonitor.user.service.impl;
 import java.util.List;
import java.util.UUID;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.BusinessException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.user.bean.UserEntity;
import com.uec.imonitor.user.dao.IUserJPARepository;
import com.uec.imonitor.user.service.IUserService;
@Service("userService")
@Transactional(value = "transactionManager")
public class UserServiceImpl implements IUserService{

@Autowired
 private  IUserJPARepository userRepository;


@Override
public boolean updatePassword(Integer innerid,String oldPassword,String newPassword){
    UserEntity user = this.findById(innerid);
    if (null != user) {
        if (user.getPassword().equals(CommonUtil.encryptPassword(oldPassword, user.getSalt()))) {
            user.setSalt(UUID.randomUUID().toString());
            user.setPassword(CommonUtil.encryptPassword(newPassword, user.getSalt()));
            userRepository.save(user);
        } else {
            throw new BusinessException("004001003");
        }
    } else {
        throw new BusinessException("004001002");
    }
    return true;
}


@Override
public UserEntity findByUserName(String userName){
    if (StringUtils.isBlank(userName)) {
        throw new RequestParamException(new String[] { "userName" });
    }
    UserEntity entity = userRepository.findByUserName(userName);
    return entity;
}


@Override
public UserEntity addUser(UserEntity user){
    if (null == user) {
        throw new RequestParamException(new String[] { "user" });
    }
    UserEntity entity = userRepository.save(user);
    return entity;
}


@Override
public List<UserEntity> listUsers(){
    List<UserEntity> list = userRepository.findAll();
    return list;
}


@Override
public UserEntity findById(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    UserEntity entity = userRepository.findOne(id);
    return entity;
}


@Override
public void deleteUser(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    userRepository.delete(id);
}


@Override
public UserEntity updateUser(UserEntity user){
    if (null == user) {
        throw new RequestParamException(new String[] { "user" });
    }
    UserEntity oldUser = this.findById(user.getInnerid());
    oldUser.setName(user.getName());
    oldUser.setEmail(user.getEmail());
    oldUser.setMobilePhone(user.getMobilePhone());
    UserEntity entity = userRepository.save(oldUser);
    return entity;
}


}