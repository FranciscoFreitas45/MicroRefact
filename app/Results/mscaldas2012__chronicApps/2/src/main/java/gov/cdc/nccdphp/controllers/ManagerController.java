package gov.cdc.nccdphp.controllers;
 import gov.cdc.nccdphp.domain.Manager;
import gov.cdc.nccdphp.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
@Controller
public class ManagerController {

@Autowired
 private  ManagerRepository managerRepo;


@RequestMapping(value = "/manager", method = RequestMethod.POST)
public String save(Manager manager,BindingResult bindingResults){
    if (bindingResults.hasErrors()) {
        return "projectform";
    }
    managerRepo.save(manager);
    return "redirect:/managers";
}


}