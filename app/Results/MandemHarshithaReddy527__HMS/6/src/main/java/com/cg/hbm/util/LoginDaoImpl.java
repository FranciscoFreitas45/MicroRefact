package com.cg.hbm.util;
 import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import com.cg.hbm.entites.Admin;
import org.springframework.stereotype.Repository;
@Transactional
@Repository
public class LoginDaoImpl implements LoginDao{

@PersistenceContext
 private EntityManager em;


@Override
public String validateCredintals(Object obj){
    // TODO Auto-generated method stub
    if (obj instanceof Admin) {
        Admin a = (Admin) obj;
        TypedQuery<Admin> q = em.createQuery("select a from Admin a where a.admin_id=:aid and a.password=:pass", Admin.class);
        q.setParameter("aid", a.getAdmin_id());
        q.setParameter("pass", a.getPassword());
        List<Admin> adminList = q.getResultList();
        if (adminList.size() > 0) {
            return "login success";
        } else {
            return "Invalid";
        }
    }
    return null;
}


}