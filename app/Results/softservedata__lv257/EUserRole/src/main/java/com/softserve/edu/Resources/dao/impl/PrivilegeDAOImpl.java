package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.PrivilegeDAO;
import com.softserve.edu.Resources.entity.Privilege;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
public class PrivilegeDAOImpl implements PrivilegeDAO{

@PersistenceContext
 private  EntityManager entityManager;


@Override
public Privilege addPrivilege(Privilege privilege){
    entityManager.persist(privilege);
    return privilege;
}


@Override
@SuppressWarnings("unchecked")
public List<Privilege> getAllPrivileges(){
    List<?> list = entityManager.createQuery("SELECT p FROM Privilege p").getResultList();
    return (List<Privilege>) list;
}


@Override
public Privilege findByName(String name){
    Query query = entityManager.createQuery("select i from Privilege i where i.name = :name").setParameter("name", name);
    Privilege privilege = (Privilege) query.getSingleResult();
    return privilege;
}


@Override
public void delete(Privilege privilege){
}


@Override
public void deleteAllPrivileges(){
    entityManager.createNativeQuery("TRUNCATE TABLE roles_privileges;").executeUpdate();
    entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0;").executeUpdate();
    entityManager.createNativeQuery("TRUNCATE table privilege;").executeUpdate();
    entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1;").executeUpdate();
}


public void setName(Long id,String name)

public void setDescription(Long id,String description)

public void setPrivilegeType(Long id,PrivilegeType privilegeType)

}