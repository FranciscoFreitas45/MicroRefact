package controllers;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Segment;
import services.ParadeService;
import services.PathService;
import services.SegmentService;
@Controller
@RequestMapping("/segment/brotherhood")
public class SegmentController {

@Autowired
 private  SegmentService segmentService;

@Autowired
 private  ParadeService paradeService;

@Autowired
 private  PathService pathService;

// Constructors -----------------------------------------------------------
public SegmentController() {
    super();
}
@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView edit(Integer segmentId,Integer paradeId){
    ModelAndView result;
    Segment segment = new Segment();
    Boolean isOrigin = false;
    if (segmentId != 0)
        segment = this.segmentService.findOne(segmentId);
    if (this.segmentService.isOrigin(segment, paradeId))
        isOrigin = true;
    result = this.createEditModelAndView(segment);
    result.addObject("isOrigin", isOrigin);
    result.addObject("segmentId", segmentId);
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
public ModelAndView save(Segment segmentForm,BindingResult binding,Integer paradeId){
    ModelAndView result;
    Segment segment = this.segmentService.recontruct(segmentForm, paradeId, binding);
    if (binding.hasErrors()) {
        result = this.createEditModelAndView(segmentForm);
        result.addObject("segmentId", segmentForm.getId());
    } else
        try {
            this.segmentService.editSegment(segment, paradeId);
            result = new ModelAndView("redirect:/path/brotherhood/list.do?" + paradeId);
        } catch (Throwable oops) {
            result = this.createEditModelAndView(segmentForm, "segment.commit.error");
        }
    result.addObject("paradeId", paradeId);
    result.addObject("isOrigin", this.segmentService.isOrigin(segment, paradeId));
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
public ModelAndView delete(Segment segmentForm,BindingResult binding,Integer paradeId){
    ModelAndView result;
    if (binding.hasErrors()) {
        result = this.createEditModelAndView(segmentForm);
        result.addObject("segmentId", segmentForm.getId());
    } else
        try {
            this.segmentService.deleteSegment(segmentForm, paradeId);
            result = new ModelAndView("redirect:/path/brotherhood/list.do?paradeId=" + paradeId);
        } catch (Throwable oops) {
            result = this.createEditModelAndView(segmentForm, "segment.commit.error");
        }
    result.addObject("paradeId", paradeId);
    return result;
}


public ModelAndView createEditModelAndView(Segment segment,String messageCode){
    ModelAndView result;
    result = new ModelAndView("segment/brotherhood/create");
    result.addObject("segment", segment);
    result.addObject("message", messageCode);
    return result;
}


}