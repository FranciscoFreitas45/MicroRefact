package gov.cdc.nccdphp.controllers;
 import gov.cdc.nccdphp.bootstrap.InitializerDataLoader;
import gov.cdc.nccdphp.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletConfigAware;
import javax.servlet.ServletException;
@Controller
public class IndexController {

@Autowired
 private  DivisionRepository divisionRepository;

@Autowired
 private  InitializerDataLoader initializerDataLoader;


@RequestMapping("/admin")
public String adminHome(Model model){
    model.addAttribute("divisions", divisionRepository.findAll());
    return "adminHome";
}


@RequestMapping("/")
public String index(Model model){
    // model.addAttribute("divisions", divisionRepository.findAll());
    initializerDataLoader.loadDivisions();
    return "index";
}


}