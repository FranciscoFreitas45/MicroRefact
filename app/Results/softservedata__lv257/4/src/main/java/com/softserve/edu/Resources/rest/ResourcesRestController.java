package com.softserve.edu.Resources.rest;
 import com.softserve.edu.Resources.dto.ExceptionJSONInfo;
import com.softserve.edu.Resources.dto.ResourceTypeBrief;
import com.softserve.edu.Resources.dto.TypeInfoDTO;
import com.softserve.edu.Resources.dto.ViewTypesDTO;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.exception.ResourceTypeInstantiationException;
import com.softserve.edu.Resources.exception.ResourceTypeNotFoundException;
import com.softserve.edu.Resources.service.ResourceCategoryService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.softserve.edu.Resources.Interface.ResourceCategoryService;
import com.softserve.edu.Resources.Interface.RequestService;
import com.softserve.edu.Resources.Interface.UserService;
@RestController
@RequestMapping(value = "/api", method = RequestMethod.GET)
public class ResourcesRestController {

@Autowired
 private ResourceTypeService resourceTypeService;

@Autowired
 private ResourceCategoryService resourceCategoryService;

@Autowired
 private RequestService requestService;

@Autowired
 private UserService userService;


@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
public ResourceTypeBrief getResourceType(long id){
    Optional<ResourceType> resourceType = resourceTypeService.get(id, true);
    if (!resourceType.isPresent())
        throw new ResourceTypeNotFoundException("Requested Resource Type not found.");
    return new ResourceTypeBrief(resourceType.get());
}


@RequestMapping(value = "/instantiateType/{id}", method = RequestMethod.PUT)
public void instantiateResourceType(Long id){
    resourceTypeService.instantiateType(id);
}


@RequestMapping(value = "/getTypes", method = RequestMethod.GET)
public List<ViewTypesDTO> getAllResourceTypes(){
    return resourceTypeService.getTypes().stream().map(ViewTypesDTO::new).sorted(Comparator.comparing(ViewTypesDTO::getTypeName)).collect(Collectors.toList());
}


@ExceptionHandler(ResourceTypeInstantiationException.class)
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public ExceptionJSONInfo handleResourceTypeInstantiationException(HttpServletRequest request,Exception ex){
    ExceptionJSONInfo response = new ExceptionJSONInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(ex.getMessage());
    return response;
}


@RequestMapping(value = "/resource/requestId/{requestId}", method = RequestMethod.POST)
public ResourceTypeBrief saveResourceType(ResourceTypeBrief resourceTypeBrief,long requestId){
    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    User resourceAdmin = userService.getUser(loggedInUser.getName());
    ResourceType resourceType = resourceTypeService.save(resourceTypeBrief, resourceAdmin);
    // save fake request for the sake of request history
    /* if (requestId == 0) {
            org.springframework.security.core.userdetails.User principal =
                    (org.springframework.security.core.userdetails.User)
                            SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User currentUser = userService.getUser(principal.getUsername());

            requestService.fillUpRequest(new ResourceRequest()
                                                 .setDescription(resourceType.getTypeName())
                                                 .setRegister(currentUser)
                                                 .setResourcesAdmin(currentUser)
                                                 .setUpdate(new Date())
                                                 .setStatus(ResourceRequest.Status.ACCEPTED),
                                         null);
        }*/
    return new ResourceTypeBrief(resourceType);
}


@RequestMapping(value = "/deleteType/{id}", method = RequestMethod.DELETE)
public void deleteResourceType(Long id){
    resourceTypeService.removeById(id);
}


@RequestMapping(value = "/typeInfo/{id}", method = RequestMethod.GET)
public TypeInfoDTO getResourceTypeInfo(Long id){
    Optional<ResourceType> resourceType = resourceTypeService.get(id, true);
    if (!resourceType.isPresent())
        throw new ResourceTypeNotFoundException("Requested Resource Type not found.");
    return new TypeInfoDTO(resourceType.get());
}


}