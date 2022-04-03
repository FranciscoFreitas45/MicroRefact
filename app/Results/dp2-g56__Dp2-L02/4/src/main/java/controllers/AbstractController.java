package controllers;
 import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class AbstractController {


@ExceptionHandler(Throwable.class)
public ModelAndView panic(Throwable oops){
    ModelAndView result;
    result = new ModelAndView("misc/panic");
    result.addObject("name", ClassUtils.getShortName(oops.getClass()));
    result.addObject("exception", oops.getMessage());
    result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));
    return result;
}


}