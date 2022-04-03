package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.OwnerDAO;
import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.util.CriteriaQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class OwnerDAOImpl extends GenericDAOImpl<Owner, Long>implements OwnerDAO{

@Autowired
 private  CriteriaQueryBuilder criteriaQueryBuilder;

public OwnerDAOImpl() {
    super(Owner.class);
}
@Override
public List<Owner> getAllCompanies(){
    Query query = em.createQuery("SELECT c FROM Company c");
    return (List<Owner>) query.getResultList();
}


@Override
public List<Owner> getAllPersons(){
    Query query = em.createQuery("SELECT p FROM Person p");
    return (List<Owner>) query.getResultList();
}


@Override
public List<Owner> findOwners(SearchDTO searchDTO){
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<?> query = criteriaQueryBuilder.createCriteriaQuery(criteriaBuilder, searchDTO);
    List<Owner> resultList = (List<Owner>) em.createQuery(query).getResultList();
    System.out.println(resultList);
    return resultList;
}


@Override
public void deleteOwnerById(Long id){
    Owner ownerToDelete = findById(id).get();
    em.remove(em.contains(ownerToDelete) ? ownerToDelete : em.merge(ownerToDelete));
}


}