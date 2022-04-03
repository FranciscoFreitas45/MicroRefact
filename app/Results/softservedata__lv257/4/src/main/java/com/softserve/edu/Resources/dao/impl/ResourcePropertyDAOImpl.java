package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.ResourcePropertyDAO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.lang.invoke.MethodHandles;
import java.util;
@Repository("propertyDAO")
public class ResourcePropertyDAOImpl extends GenericDAOImpl<ResourceProperty, Long>implements ResourcePropertyDAO{

 static  Logger LOGGER;

protected ResourcePropertyDAOImpl() {
    super(ResourceProperty.class);
}
@Override
public Set<Long> findAllIds(){
    return new HashSet<>(em.createQuery("select rp.id from ResourceProperty rp", Long.class).getResultList());
}


@Override
public Optional<ResourceProperty> findByDescription(String description){
    final String findByDescription = "select rp from ResourceProperty rp " + "where lower(concat(rp.title, ', ', rp.units)) like lower(:description)";
    return querySingleResult(findByDescription, "description", description);
}


@Override
public List<ResourceProperty> findByTitle(String title){
    final String findByTitle = "select distinct rp from ResourceProperty rp where rp.title like :title";
    return queryResultList(findByTitle, "title", title);
}


@Override
public Optional<ResourceProperty> findByTitleAndUnits(String title,String units){
    final String findByTitleAndUnits = "select rp from ResourceProperty rp " + "where rp.title = :title and rp.units = :units";
    Map<String, Object> params = new HashMap<>();
    params.put("title", title);
    params.put("units", units);
    return querySingleResult(findByTitleAndUnits, params);
}


@Override
public List<ResourceProperty> findByUnits(String units){
    final String findByTitle = "select distinct rp from ResourceProperty rp where rp.units like :units";
    return queryResultList(findByTitle, "units", units);
}


}