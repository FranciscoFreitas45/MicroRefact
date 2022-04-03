package com.lingxiang2014.dao.impl;
 import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.dao.AdminDao;
import com.lingxiang2014.entity.Admin;
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long>implements AdminDao{


public boolean usernameExists(String username){
    if (username == null) {
        return false;
    }
    String jpql = "select count(*) from Admin admin where lower(admin.username) = lower(:username)";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
    return count > 0;
}


public Admin findByUsername(String username){
    if (username == null) {
        return null;
    }
    try {
        String jpql = "select admin from Admin admin where lower(admin.username) = lower(:username)";
        return entityManager.createQuery(jpql, Admin.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


}