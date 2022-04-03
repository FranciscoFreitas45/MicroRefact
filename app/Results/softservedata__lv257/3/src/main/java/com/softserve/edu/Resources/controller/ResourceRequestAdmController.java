package com.softserve.edu.Resources.controller;
 import com.softserve.edu.Resources.dto.ExceptionJSONInfo;
import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.dto.RequestDTO;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.exception.ResourceTypeInstantiationException;
import com.softserve.edu.Resources.service.ResourceTypeService;
import com.softserve.edu.Resources.service.impl.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import com.softserve.edu.Resources.Interface.ResourceTypeService;
import com.softserve.edu.Resources.DTO.ExceptionJSONInfo;
@Controller
@Transactional
@RequestMapping("/resources")
public class ResourceRequestAdmController {

@Autowired
 private RequestService requestService;

@Autowired
 private ResourceTypeService resourceTypeService;


@RequestMapping(value = "/acceptRequest/{requestId}/{typeId}", method = RequestMethod.PUT)
@ResponseBody
public String acceptRequest(long requestId,long typeId){
    resourceTypeService.instantiateType(typeId);
    requestService.acceptRequest(requestId);
    return "success";
}


@RequestMapping(value = { "/history" }, method = RequestMethod.GET)
public String requestsHistory(Model model){
    List<RequestDTO> requestsDTO = requestService.getHistoryResourcesRequest().stream().map(request -> new RequestDTO(request)).collect(Collectors.toList());
    model.addAttribute("resourceRequest", requestsDTO);
    return "resourceRequestHistory";
}


@ExceptionHandler(ResourceTypeInstantiationException.class)
@ResponseStatus(value = HttpStatus.FORBIDDEN)
@ResponseBody
public ExceptionJSONInfo handleResourceTypeInstantiationException(HttpServletRequest request,Exception ex){
    ExceptionJSONInfo response = new ExceptionJSONInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(ex.getMessage());
    return response;
}


@RequestMapping(value = { "/sendResponce" }, method = RequestMethod.POST)
@ResponseBody
public String sendResponse(Message message){
    requestService.response(message);
    return "success";
}


@RequestMapping(value = { "/requests" }, method = RequestMethod.GET)
public String requests(Model model){
    org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    System.out.println(userSpring.getUsername());
    model.addAttribute("resourceAdmin", userSpring.getUsername());
    model.addAttribute("title", "Requests for Resource Type");
    List<RequestDTO> requestsDTO = requestService.getNewResourcesRequest().stream().map(request -> new RequestDTO(request)).collect(Collectors.toList());
    model.addAttribute("resourceRequest", requestsDTO);
    return "resourceRequest";
}


@RequestMapping(value = { "/assignRequest" }, method = RequestMethod.POST)
@ResponseBody
public ResponseEntity<?> assign(String id){
    org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    try {
        ResourceRequest request = requestService.assignResourceAdmin(Long.parseLong(id), userSpring.getUsername());
        RequestDTO requestDTO = new RequestDTO(request);
        return new ResponseEntity<>(requestDTO, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


}