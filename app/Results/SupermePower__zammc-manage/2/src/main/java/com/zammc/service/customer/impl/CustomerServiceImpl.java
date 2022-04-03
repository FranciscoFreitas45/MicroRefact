package com.zammc.service.customer.impl;
 import com.zammc.domain.user.UserInfoEntity;
import com.zammc.page.PageBean;
import com.zammc.repository.CustomerRepository;
import com.zammc.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{

@Autowired
 private  CustomerRepository customerRepository;


public void deleteCustomer(UserInfoEntity userInfo){
    UserInfoEntity one = customerRepository.findOne(userInfo.getUserId());
    if (null != one && one.getDataStatus() == 0) {
        one.setDataStatus((byte) 1);
        customerRepository.saveAndFlush(one);
    }
}


public void queryCustomerPage(UserInfoEntity userInfoEntity,PageBean pageBean){
    int page = pageBean.getPageNum() - 1;
    int size = pageBean.getPageSize();
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.DESC, "userId"));
    Sort sort = new Sort(orders);
    Pageable pageable = new PageRequest(page, size, sort);
    Page<UserInfoEntity> customerInfoPage = customerRepository.findAll(CustomerSpecification.where(userInfoEntity), pageable);
    pageBean.setPageSize(customerInfoPage.getSize());
    pageBean.setPageNum(customerInfoPage.getNumber() + 1);
    pageBean.setTotalPage(customerInfoPage.getTotalPages());
    pageBean.setTotalRecorder(customerInfoPage.getTotalElements());
    pageBean.setContent(customerInfoPage.getContent());
}


}