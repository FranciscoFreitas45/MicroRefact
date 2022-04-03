package com.softserve.edu.Resources.rest;
 import com.softserve.edu.Resources.dto.ResourcePropertyDescription;
import com.softserve.edu.Resources.dto.ValueTypeDTO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping(value = "/api/resources", method = RequestMethod.GET)
public class PropertiesRestController {

@Autowired
 private  PropertyService propertyService;


@RequestMapping("/properties")
public List<ResourcePropertyDescription> getAvailableProperties(){
    return propertyService.getPropertyDescriptions();
}


@RequestMapping("/properties/valueTypes")
public List<ValueTypeDTO> getValueTypes(){
    return propertyService.getValueTypes();
}


@RequestMapping(value = "/property", method = RequestMethod.POST)
public ResourcePropertyDescription addResourceProperty(ResourceProperty newResourceProperty){
    newResourceProperty = propertyService.add(newResourceProperty);
    return new ResourcePropertyDescription(newResourceProperty);
}


}