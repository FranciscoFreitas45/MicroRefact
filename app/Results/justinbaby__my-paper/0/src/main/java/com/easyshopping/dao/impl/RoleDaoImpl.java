package com.easyshopping.dao.impl;
 import com.easyshopping.dao.RoleDao;
import com.easyshopping.entity.Role;
import org.springframework.stereotype.Repository;
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long>implements RoleDao{


}