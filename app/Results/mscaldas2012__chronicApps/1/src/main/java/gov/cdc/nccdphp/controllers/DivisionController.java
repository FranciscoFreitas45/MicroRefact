package gov.cdc.nccdphp.controllers;
 import gov.cdc.nccdphp.domain.Division;
import gov.cdc.nccdphp.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
@Controller
public class DivisionController {

@Autowired
 private  DivisionRepository divisionRepository;


@RequestMapping("/division/edit/{id}")
public String edit(Integer id,Model model){
    model.addAttribute("division", divisionRepository.findOne(id));
    return "divisionform";
}


@RequestMapping("/division/new")
public String newDivision(Model model){
    model.addAttribute("division", new Division());
    return "divisionform";
}


@RequestMapping("/divisions")
public String list(Model model){
    model.addAttribute("divisions", divisionRepository.findAll());
    return "divisions";
}


@RequestMapping(value = "/division", method = RequestMethod.POST)
public String saveDivision(Division div){
    divisionRepository.save(div);
    return "redirect:/division/" + div.getId();
}


@RequestMapping("/division/delete/{id}")
public String delete(Integer id){
    divisionRepository.delete(id);
    return "redirect:/divisions";
}


@RequestMapping("/division/{id}")
public String showDivision(Integer id,Model model){
    model.addAttribute("division", divisionRepository.findOne(id));
    return "divisionshow";
}


}