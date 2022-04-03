package controllers;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Area;
import domain.Chapter;
import domain.Parade;
import domain.ParadeStatus;
import services.ChapterService;
import services.ParadeService;
@Controller
@RequestMapping("/parade/chapter")
public class ParadesChapterController extends AbstractController{

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  ChapterService chapterService;

// Constructor
public ParadesChapterController() {
    super();
}
@RequestMapping(value = "/filter", method = { RequestMethod.POST, RequestMethod.GET }, params = "refresh")
public ModelAndView paradeFilter(String fselect){
    ModelAndView result;
    Chapter loggedChapter = this.chapterService.loggedChapter();
    Boolean hasArea = !(loggedChapter.getArea() == null);
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
    List<Parade> parades = this.paradeService.filterParadesChapter(loggedChapter, fselect);
    result = new ModelAndView("parade/chapter/list");
    result.addObject("parades", parades);
    result.addObject("requestURI", "parade/chapter/filter.do");
    result.addObject("hasArea", hasArea);
    result.addObject("paradeStatus", paradeStatus);
    result.addObject("statusName", statusName);
    return result;
}


@RequestMapping(value = "/selectArea", method = RequestMethod.POST, params = "edit")
public ModelAndView selectArea(Chapter chapter,BindingResult binding){
    ModelAndView result;
    Chapter cha;
    cha = this.chapterService.reconstructArea(chapter, binding);
    if (binding.hasErrors())
        result = this.createEditModelAndViewB(cha);
    else
        try {
            this.chapterService.updateChapterArea(cha);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndViewB(cha, "area.commit.error");
        }
    return result;
}


@RequestMapping(value = "/changeStatus", method = RequestMethod.POST, params = "save")
public ModelAndView changeStatus(Parade parade,BindingResult binding){
    ModelAndView result;
    boolean errorString = false;
    List<ParadeStatus> paradeStatus = Arrays.asList(ParadeStatus.values());
    List<String> statusName = new ArrayList<>();
    // Security
    this.chapterService.loggedAsChapter();
    Assert.isTrue(this.chapterService.paradeSecurity(parade.getId()));
    if (parade.getParadeStatus().equals(ParadeStatus.REJECTED) && (parade.getRejectedReason().trim().equals("") || parade.getRejectedReason() == null)) {
        String locale = LocaleContextHolder.getLocale().getLanguage();
        if (locale == "es") {
            statusName.add("PRESENTADO");
            statusName.add("ACEPTADO");
            statusName.add("RECHAZADO");
        } else if (locale == "en") {
            statusName.add("SUBMITTED");
            statusName.add("ACCEPTED");
            statusName.add("REJECTED");
        }
        errorString = true;
    }
    if (binding.hasErrors() || errorString) {
        result = new ModelAndView("parade/chapter/changeStatus");
        result.addObject("parade", parade);
        result.addObject("message", "parade.status.error");
        result.addObject("paradeStatus", paradeStatus);
        result.addObject("statusName", statusName);
    } else {
        // Reconstruct
        parade = this.paradeService.reconstrucParadeStatus(parade);
        try {
            this.chapterService.changeParadeStatus(parade);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = new ModelAndView("parade/chapter/changeStatus");
            result.addObject("parade", parade);
        }
    }
    return result;
}


public ModelAndView createEditModelAndViewB(Chapter chapter,String messageCode){
    ModelAndView result;
    List<Area> areas = this.chapterService.listFreeAreas();
    result = new ModelAndView("parade/chapter/selectArea");
    result.addObject("chapter", chapter);
    result.addObject("areas", areas);
    result.addObject("message", messageCode);
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(){
    ModelAndView result;
    List<Parade> parades;
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
    this.chapterService.loggedAsChapter();
    Chapter logguedChapter = this.chapterService.loggedChapter();
    parades = this.paradeService.getParadesByArea(logguedChapter.getArea());
    result = new ModelAndView("parade/chapter/list");
    result.addObject("paradeStatus", paradeStatus);
    result.addObject("statusName", statusName);
    result.addObject("parades", parades);
    result.addObject("hasArea", this.paradeService.hasArea(logguedChapter));
    result.addObject("requestURI", "parade/chapter/list.do");
    return result;
}


}