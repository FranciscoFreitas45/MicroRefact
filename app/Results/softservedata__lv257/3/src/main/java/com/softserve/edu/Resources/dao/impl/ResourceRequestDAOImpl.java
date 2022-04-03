package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Repository
public class ResourceRequestDAOImpl extends GenericDAOImpl<ResourceRequest, Long>{

@PersistenceContext
 protected  EntityManager em;

public ResourceRequestDAOImpl() {
    super(ResourceRequest.class);
}
@Override
public Optional<ResourceRequest> findById(Long aLong){
    return super.findById(aLong);
}


@Override
public ResourceRequest makePersistent(ResourceRequest instance){
    return super.makePersistent(instance);
}


@Override
public List<ResourceRequest> findAll(){
    TypedQuery<ResourceRequest> query = em.createQuery("SELECT c FROM ResourceRequest c ORDER BY updated DESC ", ResourceRequest.class);
    List<ResourceRequest> results = query.getResultList();
    return results;
}


public ResourceType setRequest(long idW6RM,ResourceRequest request)

public ResourceRequest getRequest(long idW6RM)

public ResourceRequest setResourceType(long id,ResourceType resourceType)

public ResourceRequest setStatus(long id,Status status)

}