package com.zammc.service.customer;
 import com.zammc.domain.user.UserInfoEntity;
import com.zammc.page.PageBean;
public interface CustomerService {


public void deleteCustomer(UserInfoEntity userInfo)
;

public void queryCustomerPage(UserInfoEntity userInfoEntity,PageBean pageBean)
;

}