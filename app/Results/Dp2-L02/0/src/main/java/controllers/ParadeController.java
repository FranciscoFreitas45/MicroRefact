package controllers;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Brotherhood;
import domain.Float;
import domain.Parade;
import domain.ParadeStatus;
import domain.Request;
import forms.FormObjectParadeFloat;
import forms.FormObjectParadeFloatCheckbox;
import services.BrotherhoodService;
import services.FloatService;
import services.ParadeService;
@Controller
@RequestMapping("/parade/brotherhood")
public class ParadeController extends AbstractController{

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  FloatService floatService;

public ParadeController() {
    super();
}
@RequestMapping(value = "/filter", method = { RequestMethod.POST, RequestMethod.GET }, params = "refresh")
public ModelAndView paradeFilter(String fselect){
    ModelAndView result;
    Brotherhood loggedBro = this.brotherhoodService.loggedBrotherhood();
    List<Parade> parades = this.paradeService.filterParadesBrotherhood(loggedBro, fselect);
    Boolean hasArea = !(loggedBro.getArea() == null);
    List<ParadeStatus> paradeStatus = Arrays.asList(ParadeStatus.values());
    String locale = LocaleContextHolder.getLocale().getLanguage();
    List<String> statusName = new ArrayList<>();
    if (locale == "es") {
        statusName.add("PRESENTADO");
        statusName.add("ACEPTADO");
        statusName.add("RECHAZADO");
    } else if (locale == "en") {
        statusName.add("SUBMITTED");
        statusName.add("ACCEPTED");
        statusName.add("REJECTED");
    }
    result = new ModelAndView("parade/brotherhood/list");
    result.addObject("parades", parades);
    result.addObject("requestURI", "parade/brotherhood/filter.do");
    result.addObject("hasArea", hasArea);
    result.addObject("paradeStatus", paradeStatus);
    result.addObject("statusName", statusName);
    result.addObject("locale", locale);
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView createParade(){
    ModelAndView result;
    FormObjectParadeFloat formObjectParadeFloat = new FormObjectParadeFloat();
    result = this.createEditModelAndView1(formObjectParadeFloat);
    return result;
}


@RequestMapping(value = "/editCheckbox", method = RequestMethod.GET)
public ModelAndView edit(int paradeId){
    ModelAndView result;
    Parade parade;
    parade = this.paradeService.findOne(paradeId);
    Brotherhood brother = new Brotherhood();
    brother = this.brotherhoodService.loggedBrotherhood();
    if (!parade.getIsDraftMode())
        return this.list();
    if (!(brother.getParades().contains(parade)))
        return this.list();
    FormObjectParadeFloatCheckbox formObjectParadeFloatCheckbox = this.paradeService.prepareFormObjectParadeFloatCheckbox(paradeId);
    result = this.createEditModelAndView(formObjectParadeFloatCheckbox);
    return result;
}


@RequestMapping(value = "/createCheckbox", method = RequestMethod.GET)
public ModelAndView createParadeCheckbox(){
    ModelAndView result;
    FormObjectParadeFloatCheckbox formObjectParadeFloatCheckbox = new FormObjectParadeFloatCheckbox();
    // NUEVO
    List<Integer> floats = new ArrayList<>();
    formObjectParadeFloatCheckbox.setFloats(floats);
    // FIN NUEVO
    result = this.createEditModelAndView(formObjectParadeFloatCheckbox);
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
public ModelAndView save(FormObjectParadeFloat formObjectParadeFloat,BindingResult binding){
    ModelAndView result;
    Parade parade = new Parade();
    parade = this.paradeService.create();
    domain.Float coach = new domain.Float();
    coach = this.floatService.create();
    parade = this.paradeService.reconstruct(formObjectParadeFloat, binding);
    coach = this.floatService.reconstructForm(formObjectParadeFloat, binding);
    if (binding.hasErrors())
        result = this.createEditModelAndView1(parade);
    else
        try {
            this.paradeService.saveFloatAndAssignToParade(coach, parade);
            // this.paradeService.save(parade);
            result = new ModelAndView("redirect:/parade/brotherhood/list.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView1(parade, "brotherhood.commit.error");
        }
    return result;
}


@RequestMapping(value = "/request/list", method = RequestMethod.GET)
public ModelAndView listRequest(int paradeId){
    ModelAndView result;
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Boolean hasArea = !(loggedBrotherhood.getArea() == null);
    List<Request> requests = new ArrayList<Request>();
    Parade parade = this.paradeService.findOne(paradeId);
    requests = parade.getRequests();
    result = new ModelAndView("parade/brotherhood/requests");
    result.addObject("requests", requests);
    result.addObject("requestURI", "parade/brotherhood/request/list.do");
    result.addObject("hasArea", hasArea);
    result.addObject("paradeId", paradeId);
    return result;
}


@RequestMapping(value = "/copy", method = RequestMethod.GET)
public ModelAndView copy(int paradeId){
    ModelAndView result;
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    try {
        Parade paradeToCopy = this.paradeService.findOne(paradeId);
        Assert.notNull(paradeToCopy);
        Assert.isTrue(loggedBrotherhood.getParades().contains(paradeToCopy));
        this.paradeService.copy(paradeToCopy);
    } catch (Throwable oops) {
    }
    result = new ModelAndView("redirect:/parade/brotherhood/list.do");
    return result;
}


public ModelAndView createEditModelAndView1(Parade parade,String messageCode){
    ModelAndView result;
    result = new ModelAndView("parade/brotherhood/create");
    result.addObject("parade", parade);
    result.addObject("message", messageCode);
    return result;
}


@RequestMapping(value = "/float/list", method = RequestMethod.GET)
public ModelAndView list(int paradeId){
    ModelAndView result;
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Boolean hasArea = !(loggedBrotherhood.getArea() == null);
    List<Float> allFloats = new ArrayList<Float>();
    Parade parade = this.paradeService.findOne(paradeId);
    allFloats = parade.getFloats();
    result = new ModelAndView("float/brotherhood/list");
    result.addObject("allFloats", allFloats);
    result.addObject("requestURI", "float/brotherhood/list.do");
    result.addObject("hasArea", hasArea);
    result.addObject("paradeId", paradeId);
    result.addObject("restriction", true);
    return result;
}


public ModelAndView createEditModelAndView(Parade parade,String messageCode){
    ModelAndView result;
    Map<Integer, String> map = new HashMap<>();
    map = this.floatService.getMapAvailableFloats();
    result = new ModelAndView("parade/brotherhood/createCheckbox");
    result.addObject("parade", parade);
    result.addObject("message", messageCode);
    result.addObject("map", map);
    result.addObject("paradeId", parade.getId());
    return result;
}


@RequestMapping(value = "/editCheckbox", method = RequestMethod.POST, params = "delete")
public ModelAndView delete(FormObjectParadeFloatCheckbox formObjectParadeFloatCheckbox,BindingResult binding){
    ModelAndView result;
    try {
        this.paradeService.deleteParadeWithId(formObjectParadeFloatCheckbox.getId());
        result = new ModelAndView("redirect:/parade/brotherhood/list.do");
    } catch (Throwable oops) {
        result = this.createEditModelAndView(formObjectParadeFloatCheckbox, "parade.commit.error");
    }
    return result;
}


}