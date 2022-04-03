package controllers;
 import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Brotherhood;
import domain.Parade;
import domain.Request;
import domain.Status;
import services.BrotherhoodService;
import services.ParadeService;
import services.RequestService;
@Controller
@RequestMapping("/request/brotherhood/")
public class RequestBrotherhoodController extends AbstractController{

@Autowired
 private  RequestService requestService;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  ParadeService paradeService;

// Constructors -----------------------------------------------------------
public RequestBrotherhoodController() {
    super();
}
@RequestMapping(value = "/save", method = RequestMethod.POST, params = "edit")
public ModelAndView requestSave(Request request,BindingResult binding){
    ModelAndView result;
    Request r;
    Brotherhood brotherhood = this.brotherhoodService.securityAndBrotherhood();
    Request requestFounded = this.requestService.findOne(request.getId());
    Collection<Request> requests = this.requestService.getRequestApprovedByBrotherhoodAndParade(brotherhood, requestFounded.getParade());
    Parade parade = requestFounded.getParade();
    r = this.requestService.reconstructRequest(request, binding);
    Boolean approved;
    if (requestFounded.getStatus().equals(Status.APPROVED))
        approved = true;
    else
        approved = false;
    if (binding.hasErrors()) {
        result = new ModelAndView("brotherhood/editRequest");
        result.addObject("request", request);
        result.addObject("requests", requests);
        result.addObject("parade", parade);
        result.addObject("approved", approved);
    } else
        try {
            this.requestService.saveRequestWithPreviousChecking(r);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = new ModelAndView("brotherhood/editRequest");
            result.addObject("request", request);
            result.addObject("requests", requests);
            result.addObject("parade", parade);
            result.addObject("approved", approved);
            result.addObject("message", "request.commit.error");
        }
    return result;
}


@RequestMapping(value = "/filter", method = { RequestMethod.POST, RequestMethod.GET }, params = "refresh")
public ModelAndView requestsFilter(String fselect){
    ModelAndView result;
    if (fselect.equals("ALL"))
        result = new ModelAndView("redirect:list.do");
    else {
        Status status = Status.APPROVED;
        if (fselect.equals("PENDING"))
            status = Status.PENDING;
        else if (fselect.equals("REJECTED"))
            status = Status.REJECTED;
        Brotherhood loggedBrotherhood = this.brotherhoodService.securityAndBrotherhood();
        Collection<Request> requests = this.requestService.getRequestsByBrotherhoodAndStatus(loggedBrotherhood, status);
        result = new ModelAndView("brotherhood/requests");
        result.addObject("requests", requests);
        result.addObject("requestURI", "request/brotherhood/filter.do");
    }
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView requestsList(){
    ModelAndView result;
    Brotherhood loggedBrotherhood = this.brotherhoodService.securityAndBrotherhood();
    Collection<Request> requests = this.requestService.getRequestsByBrotherhood(loggedBrotherhood);
    result = new ModelAndView("brotherhood/requests");
    result.addObject("requests", requests);
    result.addObject("requestURI", "request/brotherhood/list.do");
    return result;
}


@RequestMapping(value = "/filterParade", method = RequestMethod.POST, params = "refresh2")
public ModelAndView requestsFilterParade(String fselect,int paradeId){
    ModelAndView result;
    Parade parade = new Parade();
    parade = this.paradeService.findOne(paradeId);
    Assert.isTrue(this.brotherhoodService.loggedBrotherhood().getParades().contains(parade));
    if (fselect.equals("ALL")) {
        result = new ModelAndView("parade/brotherhood/requests");
        List<Request> requests = this.paradeService.findOne(paradeId).getRequests();
        result.addObject("requests", requests);
        result.addObject("paradeId", paradeId);
    } else {
        Status status = Status.APPROVED;
        if (fselect.equals("PENDING"))
            status = Status.PENDING;
        else if (fselect.equals("REJECTED"))
            status = Status.REJECTED;
        List<Request> requests = this.requestService.getRequestsByParadeAndStatus(parade, status);
        result = new ModelAndView("parade/brotherhood/requests");
        result.addObject("requests", requests);
        result.addObject("paradeId", paradeId);
        result.addObject("requestURI", "request/brotherhood/filterParade.do");
    }
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView editRequest(int requestId){
    ModelAndView result;
    Brotherhood brotherhood = this.brotherhoodService.securityAndBrotherhood();
    Request request = this.requestService.findOne(requestId);
    Parade parade = request.getParade();
    if (!brotherhood.getParades().contains(parade))
        return this.requestsList();
    if (request.getId() != 0) {
        Collection<Request> requests = this.requestService.getRequestApprovedByBrotherhoodAndParade(brotherhood, request.getParade());
        List<Integer> freePosition = this.requestService.getFreePosition(request);
        Boolean approved;
        if (request.getStatus().equals(Status.APPROVED))
            approved = true;
        else
            approved = false;
        result = new ModelAndView("brotherhood/editRequest");
        result.addObject("request", request);
        result.addObject("requests", requests);
        result.addObject("parade", parade);
        result.addObject("freePosition", freePosition);
        result.addObject("approved", approved);
    } else
        result = new ModelAndView("redirect:list.do");
    return result;
}


}