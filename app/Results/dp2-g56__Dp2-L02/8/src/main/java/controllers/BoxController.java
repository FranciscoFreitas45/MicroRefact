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
import services.BoxService;
import domain.Box;
@Controller
@RequestMapping("/box/actor")
public class BoxController extends AbstractController{

@Autowired
 private  BoxService boxService;


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView edit(int boxId){
    ModelAndView result;
    Box box;
    box = this.boxService.findOne(boxId);
    Assert.notNull(box);
    result = this.createEditModelAndView(box);
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
public ModelAndView save(Box box,BindingResult binding){
    ModelAndView result;
    box = this.boxService.reconstruct(box, binding);
    System.out.println(box.getName());
    System.out.println(box.getFatherBox());
    System.out.println(box.getIsSystem());
    System.out.println(box.getMessages());
    if (binding.hasErrors())
        result = this.createEditModelAndView(box);
    else
        try {
            this.boxService.updateBox(box);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView(box, "message.commit.error");
        }
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView create(){
    ModelAndView result;
    Box box;
    box = this.boxService.create();
    result = this.createEditModelAndView(box);
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(){
    ModelAndView result;
    List<Box> boxes = new ArrayList<>();
    boxes = this.boxService.getActorBoxes();
    // boxes = this.boxService.findAll();
    result = new ModelAndView("box/actor/list");
    result.addObject("boxes", boxes);
    result.addObject("requestURI", "box/actor/list.do");
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
public ModelAndView delete(Box box,BindingResult binding){
    ModelAndView result;
    box = this.boxService.reconstruct(box, binding);
    try {
        this.boxService.deleteBox(box);
        result = new ModelAndView("redirect:list.do");
    } catch (Throwable oops) {
        result = this.createEditModelAndView(box, "box.commit.error");
    }
    return result;
}


public ModelAndView createEditModelAndView(Box box,String messageCode){
    ModelAndView result;
    List<Box> boxes = new ArrayList<Box>();
    boxes = this.boxService.getActorBoxes();
    result = new ModelAndView("box/actor/create");
    result.addObject("box", box);
    result.addObject("boxes", boxes);
    result.addObject("message", messageCode);
    return result;
}


}