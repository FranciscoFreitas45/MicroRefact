package controllers;
 import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Sponsorship;
import services.ConfigurationService;
import services.CreditCardService;
import services.ParadeService;
import services.SponsorService;
import services.SponsorshipService;
@Controller
@RequestMapping("/sponsorship/administrator/")
public class SponsorshipAdministratorController extends AbstractController{

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  SponsorshipService sponsorshipService;

@Autowired
 private  CreditCardService creditCardService;

@Autowired
 private  SponsorService sponsorService;

@Autowired
 private  ConfigurationService configurationService;

// Constructors -----------------------------------------------------------
public SponsorshipAdministratorController() {
    super();
}
@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView sponsorshipList(){
    ModelAndView result;
    Collection<Sponsorship> sponsorships = this.sponsorshipService.getSponsorshipsAsAdmin(null);
    Map<Integer, Boolean> isValid = new HashMap<>();
    for (Sponsorship s : sponsorships) if (this.creditCardService.validateNumberCreditCard(s.getCreditCard()) && this.creditCardService.validateDateCreditCard(s.getCreditCard()) && this.creditCardService.validateCvvCreditCard(s.getCreditCard()))
        isValid.put(s.getId(), true);
    else
        isValid.put(s.getId(), false);
    result = new ModelAndView("administrator/sponsorships");
    result.addObject("sponsorships", sponsorships);
    result.addObject("isValid", isValid);
    result.addObject("requestURI", "sponsorship/administrator/list.do");
    return result;
}


@RequestMapping(value = "/filter", method = { RequestMethod.POST, RequestMethod.GET }, params = "refresh")
public ModelAndView sponsorshipFilter(String fselect){
    ModelAndView result;
    if (fselect.equals("ALL") || (!fselect.equals("ACTIVATED") && !fselect.equals("DEACTIVATED")))
        result = new ModelAndView("redirect:list.do");
    else {
        Boolean isActivated;
        if (fselect.equals("ACTIVATED"))
            isActivated = true;
        else
            isActivated = false;
        Collection<Sponsorship> sponsorships = this.sponsorshipService.getSponsorshipsAsAdmin(isActivated);
        Map<Integer, Boolean> isValid = new HashMap<>();
        for (Sponsorship s : sponsorships) if (this.creditCardService.validateNumberCreditCard(s.getCreditCard()) && this.creditCardService.validateDateCreditCard(s.getCreditCard()) && this.creditCardService.validateCvvCreditCard(s.getCreditCard()))
            isValid.put(s.getId(), true);
        else
            isValid.put(s.getId(), false);
        result = new ModelAndView("administrator/sponsorships");
        result.addObject("sponsorships", sponsorships);
        result.addObject("isValid", isValid);
        result.addObject("requestURI", "sponsorship/administrator/filter.do");
    }
    return result;
}


@RequestMapping(value = "/checkAndDeactivate", method = RequestMethod.GET)
public ModelAndView sponsorshipCheck(){
    ModelAndView result;
    this.sponsorshipService.checkAndDeactivateSponsorships();
    result = new ModelAndView("redirect:list.do");
    return result;
}


}