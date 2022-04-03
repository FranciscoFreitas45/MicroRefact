package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.entity.ResourceProperty;
import java.util.List;
import java.util.Optional;
import java.util.Set;
public interface ResourcePropertyDAO extends GenericDAO<ResourceProperty, Long>{


public Set<Long> findAllIds()
;

public Optional<ResourceProperty> findByDescription(String description)
;

public List<ResourceProperty> findByTitle(String title)
;

public Optional<ResourceProperty> findByTitleAndUnits(String title,String units)
;

public List<ResourceProperty> findByUnits(String title)
;

}