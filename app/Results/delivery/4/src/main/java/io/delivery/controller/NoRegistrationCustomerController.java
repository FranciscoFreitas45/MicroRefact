package io.delivery.controller;
 import io.delivery.entity.NoRegistrationCustomer;
import io.delivery.service.NoRegistrationCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation;
import java.util.List;
@Controller
@RequestMapping("/noregcustomer")
public class NoRegistrationCustomerController {

 final  NoRegistrationCustomerService noRegistrationCustomerService;


@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@ResponseBody
public NoRegistrationCustomer addNoRegistrationCustomer(NoRegistrationCustomer noRegistrationCustomer){
    noRegistrationCustomerService.create(noRegistrationCustomer);
    return noRegistrationCustomer;
}


@RequestMapping(value = "/all", method = RequestMethod.GET)
@ResponseBody
public List<NoRegistrationCustomer> getNoRegistrationCustomerList(){
    return noRegistrationCustomerService.getNoRegistrationCustomerList();
}


@RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
@ResponseBody
public NoRegistrationCustomer getNoRegistrationCustomerById(String id){
    return noRegistrationCustomerService.findById(Long.parseLong(id));
}


@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;UTF-8")
@ResponseBody
public NoRegistrationCustomer updareNoRegistrationCustomer(NoRegistrationCustomer noRegistrationCustomer){
    noRegistrationCustomerService.update(noRegistrationCustomer);
    return noRegistrationCustomer;
}


@RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
@ResponseBody
public List<NoRegistrationCustomer> getNoRegistrationCustomerByName(String name){
    return noRegistrationCustomerService.findByName(name);
}


@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
@ResponseBody
public NoRegistrationCustomer deleteNoRegistrationCustomer(String inputId){
    return noRegistrationCustomerService.deleteNoRegistrationCustomer(Long.parseLong(inputId));
}


}