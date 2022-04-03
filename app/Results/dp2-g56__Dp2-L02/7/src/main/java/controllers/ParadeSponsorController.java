package controllers;
 import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import domain.Parade;
import services.ParadeService;
import services.SponsorService;
import Interface.ParadeService;
import Interface.SponsorService;
import DTO.ParadeService;
@Controller
@RequestMapping("/parade/sponsor/")
public class ParadeSponsorController extends AbstractController{

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  SponsorService sponsorService;

// Constructors -----------------------------------------------------------
public ParadeSponsorController() {
    super();
}
@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView paradesList(){
    ModelAndView result;
    Collection<Parade> parades = this.paradeService.listAcceptedParadeIfSponsor();
    result = new ModelAndView("sponsor/parades");
    result.addObject("parades", parades);
    return result;
}


}