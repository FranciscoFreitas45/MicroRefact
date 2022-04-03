package controllers;
 import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.PositionService;
import domain.Position;
@Controller
@RequestMapping("/position/administrator")
public class PositionAdministratorController extends AbstractController{

@Autowired
 private  PositionService positionService;

// Constructors -----------------------------------------------------------
public PositionAdministratorController() {
    super();
}
@RequestMapping(value = "/save", method = RequestMethod.POST, params = "delete")
public ModelAndView deletePosition(Position position){
    ModelAndView result;
    Boolean canBeDeleted = this.positionService.canBeDeleted(position);
    try {
        this.positionService.deletePosition(position);
        result = new ModelAndView("redirect:list.do");
    } catch (Throwable oops) {
        result = new ModelAndView("administrator/editPosition");
        result.addObject("position", position);
        result.addObject("canBeDeleted", canBeDeleted);
        result.addObject("message", "position.commit.error");
    }
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView createPositions(){
    ModelAndView result;
    Position position = this.positionService.createPosition();
    Boolean canBeDeleted = false;
    result = new ModelAndView("administrator/createPosition");
    result.addObject("position", position);
    result.addObject("canBeDeleted", canBeDeleted);
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView listPositions(){
    ModelAndView result;
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    Collection<Position> positions = this.positionService.getPositions();
    result = new ModelAndView("administrator/positions");
    result.addObject("positions", positions);
    result.addObject("locale", locale);
    result.addObject("requestURI", "position/administrator/list");
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView editPositions(int positionId){
    ModelAndView result;
    Position position = this.positionService.findOne(positionId);
    Boolean canBeDeleted = this.positionService.canBeDeleted(position);
    result = new ModelAndView("administrator/editPosition");
    result.addObject("position", position);
    result.addObject("canBeDeleted", canBeDeleted);
    return result;
}


@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
public ModelAndView savePosition(Position position,BindingResult binding){
    ModelAndView result;
    Boolean canBeDeleted = this.positionService.canBeDeleted(position);
    Boolean creating = position.getId() == 0;
    if (binding.hasErrors()) {
        if (creating)
            result = new ModelAndView("administrator/createPosition");
        else
            result = new ModelAndView("administrator/editPosition");
        result.addObject("position", position);
        result.addObject("canBeDeleted", canBeDeleted);
    } else
        try {
            this.positionService.savePosition(position);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            if (creating)
                result = new ModelAndView("administrator/createPosition");
            else
                result = new ModelAndView("administrator/editPosition");
            result.addObject("position", position);
            result.addObject("canBeDeleted", canBeDeleted);
            result.addObject("message", "position.commit.error");
        }
    return result;
}


}