package controllers;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import domain.Brotherhood;
import services.BrotherhoodService;
import services.FloatService;
@Controller
@RequestMapping("/float/brotherhood")
public class BrotherhoodController extends AbstractController{

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  FloatService floatService;


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView edit(int floatId){
    ModelAndView result;
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    List<domain.Float> floatFinalMode = new ArrayList<domain.Float>();
    floatFinalMode = this.floatService.floatsInParadeInFinalMode();
    domain.Float floatt = this.floatService.findOne(floatId);
    if (floatFinalMode.contains(floatt))
        return this.list();
    Assert.notNull(floatt);
    Assert.isTrue(loggedBrotherhood.getFloats().contains(floatt));
    result = this.createEditModelAndView(floatt);
    return result;
}


@RequestMapping(value = "/picture/list", method = RequestMethod.GET)
public ModelAndView listBro(int floatId,boolean parade){
    ModelAndView result;
    List<String> pictures = this.floatService.getPicturesOfFloat(floatId, parade);
    Boolean isInFinal = false;
    List<domain.Float> floatFinalMode = new ArrayList<domain.Float>();
    floatFinalMode = this.floatService.floatsInParadeInFinalMode();
    domain.Float floatt = this.floatService.findOne(floatId);
    if (floatFinalMode.contains(floatt))
        isInFinal = true;
    result = new ModelAndView("picture/brotherhood/picturesFloat");
    result.addObject("pictures", pictures);
    result.addObject("requestURI", "float/brotherhood/picture/list.do");
    result.addObject("parade", parade);
    result.addObject("floatId", floatId);
    result.addObject("isInFinal", isInFinal);
    return result;
}


@RequestMapping(value = "/picture/create", method = RequestMethod.GET)
public ModelAndView createPictures(int floatId,boolean parade){
    ModelAndView result;
    result = new ModelAndView("picture/brotherhood/createPicture");
    result.addObject("floatId", floatId);
    result.addObject("parade", parade);
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView create(){
    Brotherhood bro = new Brotherhood();
    bro = this.brotherhoodService.loggedBrotherhood();
    Assert.isTrue(bro.getArea() != null);
    ModelAndView result;
    this.brotherhoodService.loggedAsBrotherhood();
    domain.Float floatt = new domain.Float();
    floatt = this.floatService.create();
    result = this.createEditModelAndView(floatt);
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
public ModelAndView saveFloat(domain.Float floatt,BindingResult binding){
    ModelAndView result;
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    List<domain.Float> floatFinalMode = new ArrayList<domain.Float>();
    floatFinalMode = this.floatService.floatsInParadeInFinalMode();
    Assert.isTrue(!floatFinalMode.contains(floatt));
    Assert.notNull(loggedBrotherhood.getArea());
    domain.Float f;
    f = this.floatService.reconstruct(floatt, binding);
    if (binding.hasErrors())
        result = this.createEditModelAndView(floatt);
    else
        try {
            this.floatService.save(f);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView(floatt, "brotherhood.commit.error");
        }
    return result;
}


@RequestMapping(value = "/picture/save", method = RequestMethod.POST, params = "save")
public ModelAndView savePicture(String picture,int floatId,boolean parade){
    ModelAndView result;
    domain.Float floatt = new domain.Float();
    floatt = this.floatService.findOne(floatId);
    try {
        if (picture.trim().isEmpty() || !this.floatService.isUrl(picture)) {
            result = new ModelAndView("picture/brotherhood/createPicture");
            result.addObject("floatId", floatId);
            result.addObject("parade", parade);
        } else {
            this.floatService.addPicture(picture, floatt);
            result = new ModelAndView("redirect:list.do?floatId=" + floatId + "&parade=" + parade);
        }
    } catch (Throwable oops) {
        result = new ModelAndView("picture/brotherhood/createPicture");
        result.addObject("floatId", floatId);
        result.addObject("parade", parade);
    }
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(){
    ModelAndView result;
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Boolean hasArea = !(loggedBrotherhood.getArea() == null);
    List<domain.Float> allFloats = new ArrayList<domain.Float>();
    List<domain.Float> floatFinalMode = new ArrayList<domain.Float>();
    floatFinalMode = this.floatService.floatsInParadeInFinalMode();
    allFloats = this.floatService.showBrotherhoodFloats();
    result = new ModelAndView("float/brotherhood/list");
    result.addObject("allFloats", allFloats);
    result.addObject("floatFinalMode", floatFinalMode);
    result.addObject("requestURI", "float/brotherhood/list.do");
    result.addObject("hasArea", hasArea);
    return result;
}


public ModelAndView createEditModelAndView(domain.Float floatt,String messageCode){
    ModelAndView result;
    result = new ModelAndView("float/brotherhood/create");
    result.addObject("floatt", floatt);
    result.addObject("message", messageCode);
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
public ModelAndView delete(domain.Float floatt,BindingResult binding){
    this.brotherhoodService.loggedAsBrotherhood();
    ModelAndView result;
    Brotherhood brother = new Brotherhood();
    brother = this.brotherhoodService.loggedBrotherhood();
    List<domain.Float> floatts = new ArrayList<domain.Float>();
    floatts = this.brotherhoodService.getFloatsByBrotherhood(brother);
    if (!(floatts.contains(floatt)))
        return new ModelAndView("redirect:list.do");
    try {
        this.floatService.remove(floatt);
        result = new ModelAndView("redirect:list.do");
    } catch (final Throwable oops) {
        result = this.createEditModelAndView(floatt, "message.commit.error");
    }
    return result;
}


@RequestMapping(value = "/export", method = RequestMethod.GET)
@ResponseBody
public String export(int id,HttpServletResponse response){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood brotherhood = new Brotherhood();
    brotherhood = this.brotherhoodService.findOne(id);
    // Defines un StringBuilder para construir tu string
    StringBuilder sb = new StringBuilder();
    // Cada append a�ade una linea, cada "line.separator" a�ade un salto de
    // linea
    sb.append("Personal data:").append(System.getProperty("line.separator"));
    sb.append("Name: " + brotherhood.getName()).append(System.getProperty("line.separator"));
    sb.append("Middle name: " + brotherhood.getMiddleName()).append(System.getProperty("line.separator"));
    sb.append("Surname: " + brotherhood.getSurname()).append(System.getProperty("line.separator"));
    sb.append("Address: " + brotherhood.getAddress()).append(System.getProperty("line.separator"));
    sb.append("Email: " + brotherhood.getEmail()).append(System.getProperty("line.separator"));
    sb.append("Photo: " + brotherhood.getPhoto()).append(System.getProperty("line.separator"));
    sb.append("Phone: " + brotherhood.getPhoneNumber()).append(System.getProperty("line.separator"));
    sb.append(System.getProperty("line.separator"));
    sb.append("SocialProfiles: ").append(System.getProperty("line.separator"));
    // Este metodo te muestra los socialProfiles de la misma manera que el resto del
    // documento
    sb.append(this.brotherhoodService.SocialProfilesToString()).append(System.getProperty("line.separator"));
    sb.append(this.brotherhoodService.HistoryToString()).append(System.getProperty("line.separator"));
    if (brotherhood == null || this.brotherhoodService.loggedBrotherhood().getId() != id)
        return null;
    // Defines el nombre del archivo y la extension
    response.setContentType("text/txt");
    response.setHeader("Content-Disposition", "attachment;filename=exportDataBrotherhood.txt");
    // Con estos comandos permites su descarga cuando clickas
    ServletOutputStream outStream = response.getOutputStream();
    outStream.println(sb.toString());
    outStream.flush();
    outStream.close();
    // El return no llega nunca, es del metodo viejo
    return sb.toString();
}


}