package controllers;
 import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.BrotherhoodService;
import services.ChapterService;
import services.ConfigurationService;
import services.MemberService;
import services.SponsorService;
import domain.Brotherhood;
import domain.Chapter;
import domain.Configuration;
import domain.Member;
import domain.Sponsor;
import forms.FormObjectBrotherhood;
import forms.FormObjectChapter;
import forms.FormObjectMember;
import forms.FormObjectSponsor;
import Interface.ChapterService;
import Interface.SponsorService;
import DTO.ChapterService;
import DTO.SponsorService;
import DTO.Configuration;
@Controller
@RequestMapping("/anonymous")
public class AnonymousController extends AbstractController{

@Autowired
 private  MemberService memberService;

@Autowired
 private  ChapterService chapterService;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  ConfigurationService configurationService;

@Autowired
 private  SponsorService sponsorService;

public AnonymousController() {
    super();
}
@RequestMapping(value = "/createMember", method = RequestMethod.GET)
public ModelAndView createMember(){
    ModelAndView result;
    FormObjectMember formObjectMember = new FormObjectMember();
    formObjectMember.setTermsAndConditions(false);
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    result = this.createEditModelAndView(formObjectMember);
    result.addObject("locale", locale);
    return result;
}


@RequestMapping(value = "/createChapter", method = RequestMethod.GET)
public ModelAndView createChapter(){
    ModelAndView result;
    FormObjectChapter formObjectChapter = new FormObjectChapter();
    formObjectChapter.setTermsAndConditions(false);
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    result = this.createEditModelAndView(formObjectChapter);
    result.addObject("areas", this.chapterService.listFreeAreas());
    result.addObject("locale", locale);
    return result;
}


@RequestMapping(value = "/termsAndConditionsES", method = RequestMethod.GET)
public ModelAndView listES(){
    ModelAndView result;
    result = new ModelAndView("termsAndConditionsES");
    return result;
}


@RequestMapping(value = "/createSponsor", method = RequestMethod.POST, params = "save")
public ModelAndView save(FormObjectSponsor formObjectSponsor,BindingResult binding){
    ModelAndView result;
    Sponsor sponsor = new Sponsor();
    sponsor = this.sponsorService.createSponsor();
    Configuration configuration = this.configurationService.getConfiguration();
    String prefix = configuration.getSpainTelephoneCode();
    // Confirmacion contrase�a
    if (!formObjectSponsor.getPassword().equals(formObjectSponsor.getConfirmPassword()))
        if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES")) {
            binding.addError(new FieldError("formObjectSponsor", "password", formObjectSponsor.getPassword(), false, null, null, "Las contrase�as no coinciden"));
            return this.createEditModelAndView(sponsor);
        } else {
            binding.addError(new FieldError("formObjectSponsor", "password", formObjectSponsor.getPassword(), false, null, null, "Passwords don't match"));
            return this.createEditModelAndView(sponsor);
        }
    // Confirmacion terminos y condiciones
    if (!formObjectSponsor.getTermsAndConditions())
        if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES")) {
            binding.addError(new FieldError("formObjectSponsor", "termsAndConditions", formObjectSponsor.getTermsAndConditions(), false, null, null, "Debe aceptar los terminos y condiciones"));
            return this.createEditModelAndView(sponsor);
        } else {
            binding.addError(new FieldError("formObjectSponsor", "termsAndConditions", formObjectSponsor.getTermsAndConditions(), false, null, null, "You must accept the terms and conditions"));
            return this.createEditModelAndView(sponsor);
        }
    // Reconstruccion
    sponsor = this.sponsorService.reconstruct(formObjectSponsor, binding);
    if (binding.hasErrors())
        result = this.createEditModelAndView(sponsor);
    else
        try {
            if (sponsor.getEmail().matches("[\\w.%-]+\\<[\\w.%-]+\\@+\\>|[\\w.%-]+")) {
                if (LocaleContextHolder.getLocale().getLanguage().toUpperCase().contains("ES")) {
                    binding.addError(new FieldError("member", "email", sponsor.getEmail(), false, null, null, "No sigue el patron ejemplo@dominio.asd o alias <ejemplo@dominio.asd>"));
                    return this.createEditModelAndView(sponsor);
                } else {
                    binding.addError(new FieldError("member", "email", sponsor.getEmail(), false, null, null, "Dont follow the pattern example@domain.asd or alias <example@domain.asd>"));
                    return this.createEditModelAndView(sponsor);
                }
            } else if (sponsor.getPhoneNumber().matches("(\\+[0-9]{1,3})(\\([0-9]{1,3}\\))([0-9]{4,})$") || sponsor.getPhoneNumber().matches("(\\+[0-9]{1,3})([0-9]{4,})$"))
                this.sponsorService.saveCreate(sponsor);
            else if (sponsor.getPhoneNumber().matches("([0-9]{4,})$")) {
                sponsor.setPhoneNumber(prefix + sponsor.getPhoneNumber());
                this.sponsorService.saveCreate(sponsor);
            } else
                this.sponsorService.saveCreate(sponsor);
            result = new ModelAndView("redirect:/security/login.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView(sponsor, "brotherhood.commit.error");
        }
    return result;
}


@RequestMapping(value = "/createSponsor", method = RequestMethod.GET)
public ModelAndView createSponsor(){
    ModelAndView result;
    FormObjectSponsor formObjectSponsor = new FormObjectSponsor();
    formObjectSponsor.setTermsAndConditions(false);
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    result = this.createEditModelAndView(formObjectSponsor);
    result.addObject("locale", locale);
    return result;
}


public ModelAndView createEditModelAndView(Sponsor sponsor,String messageCode){
    ModelAndView result;
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    result = new ModelAndView("anonymous/createSponsor");
    result.addObject("sponsor", sponsor);
    result.addObject("message", messageCode);
    result.addObject("locale", locale);
    return result;
}


@RequestMapping(value = "/createBrotherhood", method = RequestMethod.GET)
public ModelAndView createBrotherhood(){
    ModelAndView result;
    FormObjectBrotherhood formObjectBrotherhood = new FormObjectBrotherhood();
    formObjectBrotherhood.setTermsAndConditions(false);
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    result = this.createEditModelAndView(formObjectBrotherhood);
    result.addObject("locale", locale);
    return result;
}


@RequestMapping(value = "/termsAndConditionsEN", method = RequestMethod.GET)
public ModelAndView listEN(){
    ModelAndView result;
    result = new ModelAndView("termsAndConditionsEN");
    return result;
}


}