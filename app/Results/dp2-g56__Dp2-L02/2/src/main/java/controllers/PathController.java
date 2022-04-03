package controllers;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Segment;
import services.ParadeService;
import services.SegmentService;
@Controller
@RequestMapping("/path/")
public class PathController {

@Autowired
 private  SegmentService segmentService;

@Autowired
 private  ParadeService paradeService;

// Constructors -----------------------------------------------------------
public PathController() {
    super();
}
@RequestMapping(value = { "/brotherhood/create", "/brotherhood/delete" }, method = RequestMethod.GET)
public ModelAndView pathCreateOrDelete(Integer paradeId){
    ModelAndView result;
    Integer pathId = this.paradeService.findOne(paradeId).getId();
    this.paradeService.putOrDeletePath(pathId);
    result = new ModelAndView("redirect:/parade/brotherhood/list.do");
    return result;
}


@RequestMapping(value = "/brotherhood/list", method = RequestMethod.GET)
public ModelAndView paradesList(Integer paradeId){
    ModelAndView result;
    List<Segment> segments = this.segmentService.getSegmentByParade(paradeId);
    result = new ModelAndView("path/brotherhood/list");
    result.addObject("segments", segments);
    return result;
}


}