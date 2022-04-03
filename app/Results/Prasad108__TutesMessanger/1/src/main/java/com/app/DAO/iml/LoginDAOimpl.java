package com.app.DAO.iml;
 import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.app.DAO.LoginDAO;
import com.app.pojo.Login;
@Repository("loginDAO")
public class LoginDAOimpl implements LoginDAO{

@Autowired
 private SessionFactory sessionFactory;


@Override
@Transactional
public boolean exist(Login login){
    Query query = currentSession().createQuery("from Login l where l.username = :username and password = :password");
    query.setParameter("username", login.getUsername());
    query.setParameter("password", login.getPassword());
    List result = query.list();
    return !result.isEmpty();
}


public Session currentSession(){
    return this.sessionFactory.getCurrentSession();
}


@Override
@Transactional
public List<Login> getall(){
    return currentSession().createCriteria(Login.class).list();
}


@Override
@Transactional
public Login findByUsername(String username){
    Query query = currentSession().createQuery("from Login l where l.username = :username");
    query.setParameter("username", username);
    return (Login) query.uniqueResult();
}


@Override
@Transactional
public Login edit(int id){
    return find(id);
}


@Override
@Transactional
public Login find(int id){
    return (Login) currentSession().get(Login.class, id);
}


@Override
@Transactional
public void create(Login login){
    currentSession().save(login);
}


@Override
@Transactional
public void update(Login login){
    currentSession().update(login);
}


@Override
@Transactional
public Login find_By_Uname_pwd(Login login){
    Query query = currentSession().createQuery("from Login l where l.username = :username and password = :password");
    query.setParameter("username", login.getUsername());
    query.setParameter("password", login.getPassword());
    return (Login) query.uniqueResult();
}


@Override
@Transactional
public void delet(Login login){
    currentSession().delete(login);
}


@Override
@Transactional
public boolean Isenabled(Login login){
    Query query = currentSession().createQuery("from Login l where l.id = :id and l.enableMaster=TRUE and l.enableInstitute=TRUE");
    query.setParameter("id", login.getId());
    List result = query.list();
    return !result.isEmpty();
}


}