package controllers;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import security.Authority;
import services.ActorService;
import services.BrotherhoodService;
import services.ChapterService;
import services.MemberService;
import services.SponsorService;
import domain.Actor;
import Interface.SponsorService;
import Interface.ChapterService;
import DTO.ChapterService;
import DTO.SponsorService;
@Controller
@RequestMapping(value = "/authenticated")
public class DeleteUserController extends AbstractController{

@Autowired
 private  ActorService actorService;

@Autowired
 private  MemberService memberService;

@Autowired
 private  SponsorService sponsorService;

@Autowired
 private  ChapterService chapterService;

@Autowired
 private  BrotherhoodService brotherhoodService;

// Constructors -----------------------------------------------------------
public DeleteUserController() {
    super();
}
@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
public ModelAndView deleteUser(){
    ModelAndView result;
    Actor actor = this.actorService.loggedActor();
    List<Authority> authorities = (List<Authority>) actor.getUserAccount().getAuthorities();
    try {
        if (authorities.get(0).toString().equals("MEMBER"))
            this.memberService.deleteLoggedMember();
        else if (authorities.get(0).toString().equals("SPONSOR"))
            this.sponsorService.deleteSponsor();
        else if (authorities.get(0).toString().equals("CHAPTER"))
            this.chapterService.deleteAccountChapter();
        else if (authorities.get(0).toString().equals("BROTHERHOOD"))
            this.brotherhoodService.deleteBrotherhood();
        result = new ModelAndView("redirect:/j_spring_security_logout");
    } catch (Throwable oops) {
        result = new ModelAndView("redirect:/");
    }
    return result;
}


}