package controllers;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import domain.Chapter;
import domain.Proclaim;
import services.ChapterService;
import services.ProclaimService;
@Controller
@RequestMapping("/chapter")
public class ChapterController {

@Autowired
 private ChapterService chapterService;

@Autowired
 private ProclaimService proclaimService;


@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView create(){
    ModelAndView result;
    this.chapterService.loggedAsChapter();
    Proclaim proclaim = new Proclaim();
    proclaim = this.proclaimService.createProclaim();
    result = this.createEditModelAndView(proclaim);
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
public ModelAndView saveFloat(Proclaim proclaim,BindingResult binding){
    ModelAndView result;
    this.chapterService.loggedAsChapter();
    proclaim = this.proclaimService.reconstruct(proclaim, binding);
    if (binding.hasErrors())
        result = this.createEditModelAndView(proclaim);
    else
        try {
            this.proclaimService.saveProclaim(proclaim);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView(proclaim, "brotherhood.commit.error");
        }
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(){
    ModelAndView result;
    this.chapterService.loggedAsChapter();
    List<Proclaim> proclaims = new ArrayList<Proclaim>();
    proclaims = this.proclaimService.showProclaims();
    result = new ModelAndView("chapter/list");
    result.addObject("proclaims", proclaims);
    result.addObject("requestURI", "chapter/list.do");
    return result;
}


public ModelAndView createEditModelAndView(Proclaim proclaim,String messageCode){
    ModelAndView result;
    result = new ModelAndView("chapter/create");
    result.addObject("proclaim", proclaim);
    result.addObject("message", messageCode);
    return result;
}


@RequestMapping(value = "/export", method = RequestMethod.GET)
@ResponseBody
public String export(int id,HttpServletResponse response){
    this.chapterService.loggedAsChapter();
    Chapter chapter = new Chapter();
    chapter = this.chapterService.findOne(id);
    // Defines un StringBuilder para construir tu string
    StringBuilder sb = new StringBuilder();
    // Cada append a�ade una linea, cada "line.separator" a�ade un salto de linea
    sb.append("Personal data:").append(System.getProperty("line.separator"));
    sb.append("Name: " + chapter.getName()).append(System.getProperty("line.separator"));
    sb.append("Middle name: " + chapter.getMiddleName()).append(System.getProperty("line.separator"));
    sb.append("Surname: " + chapter.getSurname()).append(System.getProperty("line.separator"));
    sb.append("Address: " + chapter.getAddress()).append(System.getProperty("line.separator"));
    sb.append("Email: " + chapter.getEmail()).append(System.getProperty("line.separator"));
    sb.append("Photo: " + chapter.getPhoto()).append(System.getProperty("line.separator"));
    sb.append("Phone: " + chapter.getPhoneNumber()).append(System.getProperty("line.separator"));
    sb.append(System.getProperty("line.separator"));
    sb.append("SocialProfiles: ").append(System.getProperty("line.separator"));
    // Este metodo te muestra los socialProfiles de la misma manera que el resto del
    // documento
    sb.append(this.chapterService.SocialProfilesToString()).append(System.getProperty("line.separator"));
    if (chapter == null || this.chapterService.loggedChapter().getId() != id)
        return null;
    // Defines el nombre del archivo y la extension
    response.setContentType("text/txt");
    response.setHeader("Content-Disposition", "attachment;filename=exportDataChapter.txt");
    // Con estos comandos permites su descarga cuando clickas
    ServletOutputStream outStream = response.getOutputStream();
    outStream.println(sb.toString());
    outStream.flush();
    outStream.close();
    // El return no llega nunca, es del metodo viejo
    return sb.toString();
}


}