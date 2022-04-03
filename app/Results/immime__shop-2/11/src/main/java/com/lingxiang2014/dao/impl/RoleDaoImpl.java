package com.lingxiang2014.dao.impl;
 import org.springframework.stereotype.Repository;
import com.lingxiang2014.dao.RoleDao;
import com.lingxiang2014.entity.Role;
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long>implements RoleDao{


}