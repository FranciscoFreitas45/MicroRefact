package controllers;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AreaService;
import services.BrotherhoodService;
import domain.Area;
import domain.Brotherhood;
import Interface.BrotherhoodService;
import DTO.BrotherhoodService;
@Controller
@RequestMapping("/area")
public class AreaController extends AbstractController{

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  AreaService areaService;

// Constructors -----------------------------------------------------------
public AreaController() {
    super();
}
@RequestMapping(value = "/brotherhood/showPictures", method = RequestMethod.GET)
public ModelAndView showPictures(int areaId){
    ModelAndView result;
    Area area = this.areaService.findOne(areaId);
    result = new ModelAndView("area/brotherhood/showPictures");
    result.addObject("area", area);
    result.addObject("pictures", area.getPictures());
    result.addObject("requestURI", "area/brotherhood/showPictures.do");
    return result;
}


@RequestMapping(value = "/administrator/edit", method = RequestMethod.POST, params = "edit")
public ModelAndView editArea(Area area,BindingResult binding,String newPictures){
    ModelAndView result;
    Area a;
    Boolean delete = this.areaService.brotherhoodOfAnArea(area.getId()).isEmpty();
    a = this.areaService.reconstructArea(area, binding, newPictures);
    if (binding.hasErrors()) {
        result = this.createEditModelAndView(area);
        result.addObject("delete", delete);
    } else
        try {
            this.areaService.updateArea(a);
            result = new ModelAndView("redirect:showAreas.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView(area, "area.commit.error");
            result.addObject("delete", delete);
        }
    return result;
}


@RequestMapping(value = "/brotherhood/selectArea", method = RequestMethod.POST, params = "edit")
public ModelAndView selectArea(Brotherhood brotherhood,BindingResult binding){
    ModelAndView result;
    Brotherhood bro;
    bro = this.brotherhoodService.reconstructArea(brotherhood, binding);
    if (binding.hasErrors()) {
        result = this.createEditModelAndViewB(bro);
    } else {
        try {
            this.brotherhoodService.updateBrotherhood(bro);
            result = new ModelAndView("redirect:showArea.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndViewB(bro, "area.commit.error");
        }
    }
    return result;
}


public ModelAndView createEditModelAndViewB(Brotherhood brotherhood,String messageCode){
    ModelAndView result;
    result = new ModelAndView("area/brotherhood/selectArea");
    result.addObject("brotherhood", brotherhood);
    result.addObject("message", messageCode);
    return result;
}


@RequestMapping(value = "/administrator/showPictures", method = RequestMethod.GET)
public ModelAndView showPicturesAdmin(int areaId){
    ModelAndView result;
    Area area = this.areaService.findOne(areaId);
    result = new ModelAndView("area/administrator/showPictures");
    result.addObject("area", area);
    result.addObject("pictures", area.getPictures());
    result.addObject("requestURI", "area/administrator/showPictures.do");
    return result;
}


@RequestMapping(value = "/administrator/showAreas", method = RequestMethod.GET)
public ModelAndView showAreas(){
    ModelAndView result;
    List<Area> areas = this.areaService.findAll();
    Boolean hasArea = true;
    result = new ModelAndView("area/administrator/showAreas");
    result.addObject("areas", areas);
    result.addObject("hasArea", hasArea);
    result.addObject("requestURI", "area/administrator/showAreas.do");
    return result;
}


@RequestMapping(value = "/administrator/edit", method = RequestMethod.POST, params = "delete")
public ModelAndView deleteArea(Area area){
    ModelAndView result;
    Area a;
    a = this.areaService.findOne(area.getId());
    try {
        this.areaService.deleteArea(a);
        result = new ModelAndView("redirect:showAreas.do");
    } catch (Throwable oops) {
        result = this.createEditModelAndView(area, "area.commit.error");
    }
    return result;
}


public ModelAndView createEditModelAndView(Area area,String messageCode){
    ModelAndView result;
    result = new ModelAndView("area/administrator/edit");
    result.addObject("area", area);
    result.addObject("message", messageCode);
    return result;
}


@RequestMapping(value = "/brotherhood/showArea", method = RequestMethod.GET)
public ModelAndView showArea(){
    ModelAndView result;
    Brotherhood brotherhood = this.brotherhoodService.loggedBrotherhood();
    Area a = brotherhood.getArea();
    List<Area> areas = new ArrayList<Area>();
    areas.add(a);
    Boolean hasArea = false;
    try {
        Assert.notNull(brotherhood.getArea());
        hasArea = true;
    } catch (Throwable oops) {
    }
    result = new ModelAndView("area/brotherhood/showArea");
    result.addObject("areas", areas);
    result.addObject("hasArea", hasArea);
    result.addObject("requestURI", "area/brotherhood/showArea.do");
    return result;
}


@RequestMapping(value = "/administrator/edit", method = RequestMethod.POST, params = "save")
public ModelAndView addArea(Area area,BindingResult binding,String newPictures){
    ModelAndView result;
    Area a;
    a = this.areaService.reconstructArea(area, binding, newPictures);
    if (binding.hasErrors()) {
        result = this.createEditModelAndView(area);
    } else {
        try {
            this.areaService.addArea(a);
            result = new ModelAndView("redirect:showAreas.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView(area, "area.commit.error");
        }
    }
    return result;
}


}