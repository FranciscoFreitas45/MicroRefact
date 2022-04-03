package controllers;
 import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Finder;
import domain.Member;
import domain.Parade;
import domain.Request;
import domain.Status;
import services.ConfigurationService;
import services.MemberService;
import services.MessageService;
import services.RequestService;
@Controller
@RequestMapping("/request/member/")
public class RequestMemberController extends AbstractController{

@Autowired
 private  RequestService requestService;

@Autowired
 private  MemberService memberService;

@Autowired
 private  ConfigurationService configurationService;

@Autowired
 private  MessageService messageService;

// Constructors -----------------------------------------------------------
public RequestMemberController() {
    super();
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
        Member loggedMember = this.memberService.securityAndMember();
        Collection<Request> requests = this.requestService.getRequestsByMemberAndStatus(loggedMember, status);
        result = new ModelAndView("member/requests");
        result.addObject("requests", requests);
        result.addObject("requestURI", "request/member/filter.do");
    }
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView requestsList(){
    ModelAndView result;
    Member loggedMember = this.memberService.securityAndMember();
    Collection<Request> requests = this.requestService.getRequestsByMember(loggedMember);
    result = new ModelAndView("member/requests");
    result.addObject("requests", requests);
    result.addObject("requestURI", "request/member/list.do");
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView requestCreate(int paradeId){
    ModelAndView result;
    Member member = this.memberService.securityAndMember();
    Boolean flag;
    Hibernate.initialize(member.getFinder());
    Finder finder = member.getFinder();
    // Current Date
    Date currentDate = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDate);
    Integer currentDay = calendar.get(Calendar.DATE);
    Integer currentMonth = calendar.get(Calendar.MONTH);
    Integer currentYear = calendar.get(Calendar.YEAR);
    Integer currentHour = calendar.get(Calendar.HOUR);
    // LastEdit Finder
    Date lasEdit = finder.getLastEdit();
    calendar.setTime(lasEdit);
    Integer lastEditDay = calendar.get(Calendar.DATE);
    Integer lastEditMonth = calendar.get(Calendar.MONTH);
    Integer lastEditYear = calendar.get(Calendar.YEAR);
    Integer lastEditHour = calendar.get(Calendar.HOUR);
    Integer time = this.configurationService.getConfiguration().getTimeFinder();
    List<Parade> parades = new ArrayList<>();
    Hibernate.initialize(finder.getParades());
    List<Parade> finderParades = finder.getParades();
    if (currentDay.equals(lastEditDay) && currentMonth.equals(lastEditMonth) && currentYear.equals(lastEditYear) && lastEditHour < (currentHour + time)) {
        Integer numFinderResult = this.configurationService.getConfiguration().getFinderResult();
        if (finderParades.size() > numFinderResult)
            for (int i = 0; i < numFinderResult; i++) parades.add(finderParades.get(i));
        else
            parades = finderParades;
    }
    if (this.requestService.canRequest(member, paradeId))
        try {
            this.requestService.createRequestAsMember(member, paradeId);
            result = new ModelAndView("member/finderResult");
            flag = true;
            result.addObject("flag", flag);
            result.addObject("parades", parades);
            result.addObject("member", member);
        } catch (Throwable oops) {
            result = new ModelAndView("member/finderResult");
            flag = false;
            result.addObject("flag", flag);
            result.addObject("parades", parades);
            result.addObject("member", member);
        }
    else {
        result = new ModelAndView("member/finderResult");
        flag = false;
        result.addObject("flag", flag);
        result.addObject("parades", parades);
        result.addObject("member", member);
    }
    return result;
}


@RequestMapping(value = "/delete", method = RequestMethod.GET)
public ModelAndView requestDelete(int requestId){
    ModelAndView result;
    Member loggedMember = this.memberService.securityAndMember();
    Boolean flag;
    try {
        this.requestService.deleteRequestAsMember(loggedMember, requestId);
        Collection<Request> requests = this.requestService.getRequestsByMember(loggedMember);
        result = new ModelAndView("member/requests");
        flag = true;
        result.addObject("requests", requests);
        result.addObject("flag", flag);
    } catch (Throwable oops) {
        Collection<Request> requests = this.requestService.getRequestsByMember(loggedMember);
        result = new ModelAndView("member/requests");
        flag = false;
        result.addObject("requests", requests);
        result.addObject("flag", flag);
    }
    return result;
}


}