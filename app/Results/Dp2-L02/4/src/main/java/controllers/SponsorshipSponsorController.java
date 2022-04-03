package controllers;
 import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import domain.CreditCard;
import domain.Parade;
import domain.Sponsor;
import domain.Sponsorship;
import forms.FormObjectSponsorshipCreditCard;
import services.ActorService;
import services.ConfigurationService;
import services.CreditCardService;
import services.MemberService;
import services.ParadeService;
import services.SponsorService;
import services.SponsorshipService;
import Interface.ParadeService;
import Interface.MemberService;
import DTO.ParadeService;
@Controller
@RequestMapping("/sponsorship/sponsor/")
public class SponsorshipSponsorController extends AbstractController{

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

@Autowired
 private  MemberService memberService;

@Autowired
 private  ActorService actorService;

// Constructors -----------------------------------------------------------
public SponsorshipSponsorController() {
    super();
}
@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView sponsorshipList(){
    ModelAndView result;
    Collection<Sponsorship> sponsorships = this.sponsorshipService.getSponsorships(null);
    result = new ModelAndView("sponsor/sponsorships");
    result.addObject("sponsorships", sponsorships);
    result.addObject("requestURI", "sponsorship/sponsor/list.do");
    return result;
}


@RequestMapping(value = "/filter", method = { RequestMethod.POST, RequestMethod.GET }, params = "refresh")
public ModelAndView requestsFilter(String fselect){
    ModelAndView result;
    if (fselect.equals("ALL") || (!fselect.equals("ACTIVATED") && !fselect.equals("DEACTIVATED")))
        result = new ModelAndView("redirect:list.do");
    else {
        Boolean isActivated;
        if (fselect.equals("ACTIVATED"))
            isActivated = true;
        else
            isActivated = false;
        Collection<Sponsorship> sponsorships = this.sponsorshipService.getSponsorships(isActivated);
        result = new ModelAndView("sponsor/sponsorships");
        result.addObject("sponsorships", sponsorships);
        result.addObject("requestURI", "sponsorship/sponsor/filter.do");
    }
    return result;
}


@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
public ModelAndView saveSponsorship(FormObjectSponsorshipCreditCard formObject,BindingResult binding,int paradeId){
    ModelAndView result;
    Sponsorship sponsorship = new Sponsorship();
    CreditCard creditCard = new CreditCard();
    Parade parade;
    if (paradeId > 0)
        parade = this.paradeService.findOne(paradeId);
    else
        parade = null;
    creditCard = this.creditCardService.reconstruct(formObject, binding);
    sponsorship = this.sponsorshipService.reconstruct(formObject, binding, creditCard, parade);
    if (creditCard.getNumber() != null)
        if (!this.creditCardService.validateNumberCreditCard(creditCard))
            if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES"))
                binding.addError(new FieldError("formObject", "number", formObject.getNumber(), false, null, null, "El numero de la tarjeta es invalido"));
            else
                binding.addError(new FieldError("formObject", "number", formObject.getNumber(), false, null, null, "The card number is invalid"));
    if (creditCard.getExpirationMonth() != null && creditCard.getExpirationYear() != null)
        if (!this.creditCardService.validateDateCreditCard(creditCard))
            if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES"))
                binding.addError(new FieldError("formObject", "expirationMonth", creditCard.getExpirationMonth(), false, null, null, "La tarjeta no puede estar caducada"));
            else
                binding.addError(new FieldError("formObject", "expirationMonth", creditCard.getExpirationMonth(), false, null, null, "The credit card can not be expired"));
    List<String> cardType = this.configurationService.getConfiguration().getCardType();
    if (!cardType.contains(sponsorship.getCreditCard().getBrandName()))
        if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES"))
            binding.addError(new FieldError("formObject", "brandName", creditCard.getBrandName(), false, null, null, "Tarjeta no admitida"));
        else
            binding.addError(new FieldError("formObject", "brandName", creditCard.getBrandName(), false, null, null, "The credit card is not accepted"));
    if (binding.hasErrors())
        result = this.createEditModelAndView("sponsor/createSponsorship", formObject, paradeId);
    else
        try {
            this.sponsorshipService.addOrUpdateSponsorship(sponsorship);
            result = new ModelAndView("redirect:/sponsorship/sponsor/list.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView("sponsor/createSponsorship", formObject, paradeId, "sponsorship.commit.error");
        }
    return result;
}


@RequestMapping(value = { "/activate", "/deactivate" }, method = RequestMethod.GET)
public ModelAndView changeStatusSponsorship(int sponsorshipId){
    ModelAndView result;
    try {
        this.sponsorshipService.changeStatus(sponsorshipId);
        result = new ModelAndView("redirect:list.do");
    } catch (Throwable oops) {
        result = this.sponsorshipList();
        result.addObject("message", "sponsorship.changeStatus.error");
    }
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView createSponsorship(int paradeId){
    ModelAndView result;
    FormObjectSponsorshipCreditCard formObject = new FormObjectSponsorshipCreditCard();
    result = this.createEditModelAndView("sponsor/createSponsorship", formObject, paradeId);
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView editSponsorship(int sponsorshipId){
    ModelAndView result;
    Sponsorship sponsorship = this.sponsorshipService.findOne(sponsorshipId);
    FormObjectSponsorshipCreditCard formObject = new FormObjectSponsorshipCreditCard();
    formObject.setId(sponsorship.getId());
    formObject.setBanner(sponsorship.getBanner());
    formObject.setTargetURL(sponsorship.getTargetURL());
    result = this.createEditModelAndView("sponsor/editSponsorship", formObject, 0);
    return result;
}


public ModelAndView createEditModelAndView(String tiles,FormObjectSponsorshipCreditCard formObject,int paradeId,String message){
    ModelAndView result = this.createEditModelAndView(tiles, formObject, paradeId);
    result.addObject("message", message);
    return result;
}


@RequestMapping(value = "/export", method = RequestMethod.GET)
@ResponseBody
public String export(int id,HttpServletResponse response){
    this.sponsorService.loggedAsSponsor();
    Sponsor sponsor = new Sponsor();
    sponsor = this.sponsorService.findOne(id);
    // Defines un StringBuilder para construir tu string
    StringBuilder sb = new StringBuilder();
    // Cada append a�ade una linea, cada "line.separator" a�ade un salto de linea
    sb.append("Personal data:").append(System.getProperty("line.separator"));
    sb.append("Name: " + sponsor.getName()).append(System.getProperty("line.separator"));
    sb.append("Middle name: " + sponsor.getMiddleName()).append(System.getProperty("line.separator"));
    sb.append("Surname: " + sponsor.getSurname()).append(System.getProperty("line.separator"));
    sb.append("Address: " + sponsor.getAddress()).append(System.getProperty("line.separator"));
    sb.append("Email: " + sponsor.getEmail()).append(System.getProperty("line.separator"));
    sb.append("Photo: " + sponsor.getPhoto()).append(System.getProperty("line.separator"));
    sb.append("Phone: " + sponsor.getPhoneNumber()).append(System.getProperty("line.separator"));
    sb.append(System.getProperty("line.separator"));
    sb.append("SocialProfiles: ").append(System.getProperty("line.separator"));
    // Este metodo te muestra los socialProfiles de la misma manera que el resto del
    // documento
    sb.append(this.sponsorService.SocialProfilesToString()).append(System.getProperty("line.separator"));
    if (sponsor == null || this.sponsorService.loggedSponsor().getId() != id)
        return null;
    // Defines el nombre del archivo y la extension
    response.setContentType("text/txt");
    response.setHeader("Content-Disposition", "attachment;filename=exportDataSponsor.txt");
    // Con estos comandos permites su descarga cuando clickas
    ServletOutputStream outStream = response.getOutputStream();
    outStream.println(sb.toString());
    outStream.flush();
    outStream.close();
    // El return no llega nunca, es del metodo viejo
    return sb.toString();
}


}