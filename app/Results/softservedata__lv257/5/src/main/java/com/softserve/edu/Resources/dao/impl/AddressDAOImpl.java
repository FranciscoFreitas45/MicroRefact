package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.util.CriteriaQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
@Repository
public class AddressDAOImpl extends GenericDAOImpl<Address, Long>implements AddressDAO{

@Autowired
 private  CriteriaQueryBuilder criteriaQueryBuilder;

public AddressDAOImpl() {
    super(Address.class);
}
@Override
public List<Address> findAddresses(SearchDTO searchDTO){
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<?> query = criteriaQueryBuilder.createCriteriaQuery(criteriaBuilder, searchDTO);
    List<Address> resultList = (List<Address>) em.createQuery(query).getResultList();
    System.out.println(resultList);
    return resultList;
}


@Override
public void deleteAddress(Address address){
    em.remove(em.contains(address) ? address : em.merge(address));
}


}