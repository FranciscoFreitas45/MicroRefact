package com.easyshopping.dao.impl;
 import com.easyshopping.dao.ReturnsDao;
import com.easyshopping.entity.Returns;
import org.springframework.stereotype.Repository;
@Repository("returnsDaoImpl")
public class ReturnsDaoImpl extends BaseDaoImpl<Returns, Long>implements ReturnsDao{


}