package com.dtdhehe.ptulife.util;
 import com.dtdhehe.ptulife.entity.PtuUser;
import com.dtdhehe.ptulife.enums.UserSexEnum;
import com.dtdhehe.ptulife.enums.UserStatusEnum;
import com.dtdhehe.ptulife.service.UserService;
import javax.servlet.http.HttpServletRequest;
public class CheckUserUtils {


public Integer checkStatus(String userStatus){
    if (userStatus == null || userStatus.equals("")) {
        return 0;
    }
    if (userStatus.equals(UserStatusEnum.Wor.getMessage())) {
        return 2;
    } else if (userStatus.equals(UserStatusEnum.Tea.getMessage())) {
        return 1;
    }
    return 0;
}


public Integer checkSex(String userSex){
    if (userSex.equals(UserSexEnum.WOMAN.getSex())) {
        return 1;
    }
    return 0;
}


public String checkUserStatus(Integer userStatus){
    if (userStatus == 1) {
        return UserStatusEnum.Tea.getMessage();
    } else if (userStatus == 2) {
        return UserStatusEnum.Wor.getMessage();
    } else {
        return UserStatusEnum.Stu.getMessage();
    }
}


}