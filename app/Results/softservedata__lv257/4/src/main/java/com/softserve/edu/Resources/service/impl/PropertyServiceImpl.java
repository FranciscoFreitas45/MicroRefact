package com.softserve.edu.Resources.service.impl;
 import com.softserve.edu.Resources.dao.ResourcePropertyDAO;
import com.softserve.edu.Resources.dto.ResourcePropertyDescription;
import com.softserve.edu.Resources.dto.ValueTypeDTO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ValueType;
import com.softserve.edu.Resources.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util;
import java.util.stream.Collectors.toList;
@Service
@Transactional
public class PropertyServiceImpl implements PropertyService{

 final  ResourcePropertyDAO propertyDAO;

@Autowired
public PropertyServiceImpl(ResourcePropertyDAO propertyDAO) {
    this.propertyDAO = propertyDAO;
}
@Override
public ResourceProperty add(ResourceProperty property){
    return propertyDAO.makePersistent(property);
}


@Override
public Long propertiesCount(){
    return propertyDAO.getCount();
}


@Override
public List<ResourcePropertyDescription> getPropertyDescriptions(){
    return propertyDAO.findAll().stream().map(ResourcePropertyDescription::new).sorted().collect(toList());
}


@Override
public Optional<ResourceProperty> getProperty(String description){
    return propertyDAO.findByDescription(description);
}


@Override
public Set<ResourceProperty> getProperties(String propertyTitle){
    return new HashSet<>(propertyDAO.findByTitle(propertyTitle));
}


@Override
public ResourceProperty update(ResourceProperty property){
    return propertyDAO.makePersistent(property);
// return propertyDAO.update(property);
}


@Override
public List<ValueTypeDTO> getValueTypes(){
    return Arrays.stream(ValueType.values()).map(ValueTypeDTO::new).collect(toList());
}


@Override
public Set<Long> getPropertyIDs(){
    return propertyDAO.findAllIds();
}


@Override
public Optional<ResourceProperty> getPropertyById(Long propID){
    return propertyDAO.findById(propID);
}


}