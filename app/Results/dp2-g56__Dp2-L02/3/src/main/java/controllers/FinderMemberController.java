package controllers;
 import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import domain.Finder;
import domain.Member;
import domain.Parade;
import domain.Sponsorship;
import security.LoginService;
import security.UserAccount;
import services.ConfigurationService;
import services.FinderService;
import services.MemberService;
import services.SponsorshipService;
import Interface.SponsorshipService;
import DTO.SponsorshipService;
@Controller
@RequestMapping("/finder/member/")
public class FinderMemberController extends AbstractController{

@Autowired
 private  FinderService finderService;

@Autowired
 private  MemberService memberService;

@Autowired
 private  ConfigurationService configurationService;

@Autowired
 private  SponsorshipService sponsorshipService;

// Constructors -----------------------------------------------------------
public FinderMemberController() {
    super();
}
@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView edit(){
    ModelAndView result;
    UserAccount userAccount = LoginService.getPrincipal();
    Member logguedMember = this.memberService.getMemberByUsername(userAccount.getUsername());
    Finder finder = logguedMember.getFinder();
    Assert.notNull(finder);
    result = this.createEditModelAndView(finder);
    return result;
}


@RequestMapping(value = "/clean", method = RequestMethod.POST, params = "save")
public ModelAndView save(){
    ModelAndView result;
    UserAccount userAccount = LoginService.getPrincipal();
    Member logguedMember = this.memberService.getMemberByUsername(userAccount.getUsername());
    Finder finder = logguedMember.getFinder();
    Assert.notNull(finder);
    List<Parade> parades = this.finderService.getAllPublishedParades();
    Date date = new Date();
    finder.setArea("");
    finder.setKeyWord("");
    finder.setLastEdit(date);
    finder.setMaxDate(null);
    finder.setMinDate(null);
    finder.setParades(parades);
    this.finderService.save(finder);
    result = new ModelAndView("redirect:list.do");
    return result;
}


public ModelAndView createEditModelAndView(Finder finder,String messageCode){
    ModelAndView result;
    result = new ModelAndView("member/finder");
    result.addObject("finder", finder);
    result.addObject("message", messageCode);
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView paradesList(){
    ModelAndView result;
    UserAccount userAccount = LoginService.getPrincipal();
    Member member = this.memberService.getMemberByUsername(userAccount.getUsername());
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
    List<Parade> finderParades = finder.getParades();
    if (currentDay.equals(lastEditDay) && currentMonth.equals(lastEditMonth) && currentYear.equals(lastEditYear) && lastEditHour < (currentHour + time)) {
        Integer numFinderResult = this.configurationService.getConfiguration().getFinderResult();
        if (finderParades.size() > numFinderResult)
            for (int i = 0; i < numFinderResult; i++) parades.add(finderParades.get(i));
        else
            parades = finderParades;
    }
    result = new ModelAndView("member/finderResult");
    Map<Integer, Sponsorship> randomSpo = new HashMap<Integer, Sponsorship>();
    for (Parade p : parades) {
        Sponsorship spo = this.sponsorshipService.getRandomSponsorship(p.getId());
        randomSpo.put(p.getId(), spo);
        if (spo.getId() > 0)
            this.sponsorshipService.updateSpentMoneyOfSponsorship(p.getId(), spo.getId());
    }
    result.addObject("randomSpo", randomSpo);
    result.addObject("parades", parades);
    result.addObject("member", member);
    return result;
}


}