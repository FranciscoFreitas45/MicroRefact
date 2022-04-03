package com.softserve.edu.Resources.service.impl;
 import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dao.impl.DocumentDAOImpl;
import com.softserve.edu.Resources.dao.impl.ResourceRequestDAOImpl;
import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.dto.RequestDTO;
import com.softserve.edu.Resources.entity.Document;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.util.AcceptRequestMail;
import com.softserve.edu.Resources.util.ResponceMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.softserve.edu.Resources.Interface.UserDAO;
@Service
@Transactional
public class RequestService {

 private  Logger logger;

@Autowired
 private ResourceRequestDAOImpl resourceRequestDAO;

@Autowired
 private DocumentDAOImpl documentDAO;

@Autowired
 private UserDAO userDAO;

@Autowired
 private VelocityMailService velocityMailService;

public RequestService() {
}
public ResourceRequest getRequestById(long id){
    Optional<ResourceRequest> request = resourceRequestDAO.findById(id);
    return request.orElse(new ResourceRequest());
}


public ResourceRequest assignResourceAdmin(long requestId,String resourceAdminEmail) throws Exception{
    ResourceRequest request = new ResourceRequest();
    Optional<ResourceRequest> requestOptional = resourceRequestDAO.findById(requestId);
    User resourceAdmin = userDAO.findByEmail(resourceAdminEmail);
    if (requestOptional.isPresent()) {
        if (requestOptional.get().getResourcesAdmin() == null) {
            request = requestOptional.get();
            request.setUpdate(new Date());
            request.setResourcesAdmin(resourceAdmin);
            return resourceRequestDAO.makePersistent(request);
        } else {
            throw new Exception("ResourseRequest instance" + requestOptional.get() + " has already assigned.");
        }
    } else {
        logger.warn("ResourseRequest instance with id:" + requestId + " is undefined.");
    }
    return request;
}


public List<ResourceRequest> getResourcesRequests(){
    return resourceRequestDAO.findAll();
}


public void acceptRequest(long id){
    Optional<ResourceRequest> requestOptional = resourceRequestDAO.findById(id);
    ResourceRequest request;
    if (requestOptional.isPresent()) {
        request = requestOptional.get();
        request.setUpdate(new Date());
        request.setStatus(ResourceRequest.Status.ACCEPTED);
        resourceRequestDAO.makePersistent(request);
        if (request.getResourcesAdmin().getUsername() != request.getRegister().getUsername()) {
            AcceptRequestMail mail = new AcceptRequestMail(request);
            velocityMailService.sendCreateResourceTypeNotification(mail);
        }
    } else {
        logger.warn("ResourseRequest instance with id:" + id + " is undefined.");
    }
}


public List<ResourceRequest> filterByStatus(List<ResourceRequest> requests,ResourceRequest.Status status){
    return requests.stream().filter(request -> request.getStatus().equals(status)).collect(Collectors.toList());
}


public void response(Message message){
    Optional<ResourceRequest> requestOptional = resourceRequestDAO.findById(message.getId_request());
    ResourceRequest request;
    if (requestOptional.isPresent()) {
        request = requestOptional.get();
        request.setUpdate(new Date());
        request.setStatus(message.getRequestStatus());
        resourceRequestDAO.makePersistent(request);
        ResponceMail mail = new ResponceMail(message, request);
        velocityMailService.sendResponceMail(mail);
    } else {
        logger.warn("ResourseRequest instance with id:" + message.getId_request() + " is undefined.");
    }
}


public List<ResourceRequest> getNewResourcesRequest(){
    return filterByStatus(getResourcesRequests(), ResourceRequest.Status.NEW);
}


public RequestDTO responceAfterRefinement(long requestId){
    RequestDTO requestDTO = new RequestDTO();
    Optional<ResourceRequest> requestOptional = resourceRequestDAO.findById(requestId);
    if (requestOptional.isPresent()) {
        ResourceRequest request = requestOptional.get();
        request.setUpdate(new Date());
        request.setStatus(ResourceRequest.Status.NEW);
        resourceRequestDAO.makePersistent(request);
        requestDTO.setResourceName(request.getResourceName());
        requestDTO.setAssignerName(request.getResourcesAdmin().getUsername());
        requestDTO.setUpdate(request.getUpdate());
        requestDTO.setId(request.getId());
        return requestDTO;
    } else {
        logger.warn("ResourseRequest instance with id:" + requestId + " is undefined.");
    }
    return requestDTO;
}


public List<ResourceRequest> getRequestsForRegistrar(){
    org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userDAO.findByEmail(userSpring.getUsername());
    List<ResourceRequest> requests = getResourcesRequests().stream().filter(request -> request.getRegister().getId() == user.getId()).collect(Collectors.toList());
    return requests;
}


public void fillUpRequest(ResourceRequest resourceRequest,Document document){
    org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    resourceRequest.setStatus(ResourceRequest.Status.NEW);
    resourceRequest.setDocument(document);
    resourceRequest.setUpdate(new Date());
    User user = userDAO.findByEmail(userSpring.getUsername());
    resourceRequest.setRegister(user);
    resourceRequestDAO.makePersistent(resourceRequest);
}


public List<ResourceRequest> getHistoryResourcesRequest(){
    List<ResourceRequest> requests = getResourcesRequests();
    List<ResourceRequest> history = filterByStatus(requests, ResourceRequest.Status.ACCEPTED);
    history.addAll(filterByStatus(requests, ResourceRequest.Status.DECLINED));
    return history;
}


}