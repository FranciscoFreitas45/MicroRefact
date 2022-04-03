package com.vino.scaffold.shiro.repository;
 import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.vino.scaffold.shiro.entity.User;
public class UserRepositoryImpl {

@PersistenceContext
 private  EntityManager em;


public Page<User> getUsersByCondition(User user,Pageable pageable){
    System.out.println(user);
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<User> root = cq.from(User.class);
    cq.select(root);
    // ʹ��like��ʱ����Բ����ж��Ƿ��иò�����������Ϊlike %% �͵���û�������������
    Predicate condition1 = cb.like(root.get("username").as(String.class), cb.parameter(String.class, "username"));
    Predicate condition2 = cb.like(root.get("userAlias").as(String.class), cb.parameter(String.class, "userAlias"));
    // Predicate condition3=cb.equal(root.get("createTime").as(Date.class),cb.parameter(String.class, "createTime"));
    cq.where(condition1, condition2);
    cb.and(condition1, condition2);
    TypedQuery<User> query = em.createQuery(cq);
    query.setParameter("username", "%" + user.getUsername() + "%");
    query.setParameter("userAlias", "%" + user.getUserAlias() + "%");
    query.setFirstResult(pageable.getOffset());
    query.setMaxResults(pageable.getPageSize());
    List<User> users = query.getResultList();
    Page<User> page = new PageImpl<User>(users, pageable, 10);
    return page;
}


public EntityManager getEm(){
    return em;
}


public void setEm(EntityManager em){
    this.em = em;
}


}