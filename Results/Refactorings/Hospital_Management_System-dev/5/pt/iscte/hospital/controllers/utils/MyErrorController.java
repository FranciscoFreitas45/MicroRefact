import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pt.iscte.hospital.objects.utils.AlertMessageImage;
@Controller
public class MyErrorController implements ErrorController{

@Autowired
 private  Common common;


@GetMapping("/error")
public String handleError(ModelMap modelMap){
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("imageURL", AlertMessageImage.FAILURE.getImageURL());
    return "components/error";
}


@Override
public String getErrorPath(){
    return null;
}


}