package com.zammc.service.user.impl;
 import com.zammc.domain.user.AdminUserEntity;
import com.zammc.repository.AdminUserRepository;
import com.zammc.response.Message;
import com.zammc.response.MessageStatus;
import com.zammc.response.MessageTitle;
import com.zammc.service.user.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Service
public class AdminUserServiceImpl implements AdminUserService{

@Autowired
 private  AdminUserRepository adminUserRepository;


public Message userLogin(AdminUserEntity adminUserEntity,HttpServletRequest request){
    if ("".equals(adminUserEntity.getUserName()) || null == adminUserEntity.getUserName()) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "用户名不能为空");
    }
    if ("".equals(adminUserEntity.getPassWord()) || null == adminUserEntity.getPassWord()) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "密码不能为空");
    }
    int userCount = adminUserRepository.queryUserCount(adminUserEntity);
    if (userCount == 0) {
        return new Message(MessageStatus.FAIL, MessageTitle.失败, "用户不存在");
    }
    if (userCount != 1) {
        return new Message(MessageStatus.FAIL, MessageTitle.系统异常, "系统异常");
    } else {
        AdminUserEntity userInfo = adminUserRepository.findByUserNameAndPassWord(adminUserEntity.getUserName(), adminUserEntity.getPassWord());
        HttpSession session = request.getSession();
        session.setAttribute("userName", userInfo.getUserName());
        session.setAttribute("userId", userInfo.getUserId());
        session.setAttribute("realName", userInfo.getRealName());
    }
    return new Message(MessageStatus.SUCCESS, MessageTitle.成功, "");
}


}