package controllers;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BrotherhoodService;
import domain.Brotherhood;
@Controller
@RequestMapping("/picture/member")
public class PicturesBrotherhoodController {

@Autowired
 private  BrotherhoodService brotherhoodService;

public PicturesBrotherhoodController() {
    super();
}
@RequestMapping(value = "/listPerBrotherhood", method = RequestMethod.GET)
public ModelAndView listBro(int broId){
    ModelAndView result;
    List<String> pictures;
    Brotherhood b;
    b = this.brotherhoodService.findOne(broId);
    pictures = b.getPictures();
    result = new ModelAndView("picture/member/listPerBrotherhood");
    result.addObject("pictures", pictures);
    result.addObject("requestURI", "picture/member/listPerBrotherhood.do");
    return result;
}


}