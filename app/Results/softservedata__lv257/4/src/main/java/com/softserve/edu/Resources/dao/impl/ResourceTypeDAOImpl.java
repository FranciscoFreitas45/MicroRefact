package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.entity.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository("resourceTypeDAO")
public class ResourceTypeDAOImpl extends GenericDAOImpl<ResourceType, Long>implements ResourceTypeDAO{

 static  Logger LOGGER;

public ResourceTypeDAOImpl() {
    super(ResourceType.class, LOGGER);
}
@Override
public List<ResourceType> getInstances(){
    String queryInstance = "select rp from ResourceType rp where rp.instantiated = true";
    return em.createQuery(queryInstance, ResourceType.class).getResultList();
}


@Override
public void createTable(ResourceType resourceType){
    String uniqueStatement = resourceType.getProperties().stream().map(cp -> cp.isUnique() ? "CONSTRAINT UC_" + cp.getProperty().getColumnName() + " UNIQUE (" + cp.getProperty().getColumnName() + ")" : "").filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
    StringBuilder createTableStatement = new StringBuilder().append("CREATE TABLE ").append(resourceType.getTableName()).append(" (id BIGINT(20) NOT NULL,").append(resourceType.getProperties().stream().map(property -> property.getProperty().getColumnName() + " " + property.getProperty().getValueType().getSqlTypeName()).collect(Collectors.joining(",\n"))).append(",").append(!uniqueStatement.isEmpty() ? uniqueStatement + "," : "").append("CONSTRAINT PK_").append(resourceType.getTypeName()).append(" PRIMARY KEY (id));");
    em.createNativeQuery(createTableStatement.toString()).executeUpdate();
}


@Override
public List<ResourceType> findByProperty(ResourceProperty property){
    return null;
}


@Override
public Optional<ResourceType> findByTypeName(String typeName){
    final String queryByTypeName = "select i from ResourceType i where i.typeName = :name";
    return querySingleResult(queryByTypeName, "name", typeName);
}


@Override
public Optional<ResourceType> findById(Long id,boolean doFetch){
    if (!doFetch)
        return super.findById(id);
    // fetch with loadgraph hint
    Map<String, Object> properties = new HashMap<>();
    properties.put("javax.persistence.loadgraph", em.getEntityGraph("TypesProperties"));
    return Optional.ofNullable(em.find(ResourceType.class, id, properties));
}


@Override
public void makeTransient(ResourceType resourceType){
    ResourceRequest request = resourceType.getRequest();
    if (request != null) {
        request.setResourceType(null).setStatus(ResourceRequest.Status.DECLINED);
    }
    super.makeTransient(resourceType);
}


@Override
public Optional<ResourceType> findByName(String name){
    final String queryByName = "select i from ResourceType i where i.name = :name";
    return querySingleResult(queryByName, "name", name);
}


@Override
public ResourceType findWithPropertiesByID(Long resourceTypeID){
    ResourceType resourceType = null;
    try {
        resourceType = (ResourceType) em.createQuery("SELECT r FROM ResourceType r LEFT JOIN FETCH r.properties WHERE r.id =:id").setParameter("id", resourceTypeID).getSingleResult();
    } catch (NoResultException e) {
        String warn = "No resourceType found with Id:" + resourceTypeID + "";
        LOGGER.warn(warn, e);
    }
    return resourceType;
}


@Override
public List<String> getInstanceNames(){
    String queryInstanceNames = "select rp.typeName from ResourceType rp where rp.instantiated = true";
    return em.createQuery(queryInstanceNames, String.class).getResultList();
}


public ResourceType getResourceType(Long idO93K)

public ResourceRequest setResourceType(Long idO93K,ResourceType resourceType)

public ResourceType getResourceType(Long id2JVR)

public void setResourceType(Long id2JVR,ResourceType resourceType)

public ResourceCategory setResourceTypes(Long id,Set<ResourceType> resourceTypes)

public Set<ResourceType> getResourceTypes(Long id)

public boolean equals(Long id,Object o)

public int compareTo(Long id,ResourceProperty o)

}