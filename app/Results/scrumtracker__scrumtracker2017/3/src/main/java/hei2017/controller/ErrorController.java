package hei2017.controller;
 import hei2017.service.ProjectService;
import hei2017.service.SprintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hei2017.Interface.SprintService;
@Controller
public class ErrorController {

@Inject
 private ProjectService projectService;

@Inject
 private SprintService sprintService;


public int getErrorCode(HttpServletRequest httpRequest){
    return (Integer) httpRequest.getAttribute("javax.servlet.error.message");
// https://tomcat.apache.org/tomcat-7.0-doc/servletapi/constant-values.html
}


@RequestMapping(value = "errors", method = RequestMethod.GET)
public String renderErrorPage(Model model,HttpServletRequest httpRequest){
    String errorMsg = "";
    int httpErrorCode = getErrorCode(httpRequest);
    switch(httpErrorCode) {
        case 400:
            {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
        case 401:
            {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
        case 404:
            {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
        case 500:
            {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
    }
    model.addAttribute("httpErrorCode", httpErrorCode);
    model.addAttribute("errorMsg", errorMsg);
    model.addAttribute("isErrorPage", true);
    model.addAttribute("projects", projectService.findAll());
    model.addAttribute("sprints", sprintService.findAll());
    return "erreur";
}


@RequestMapping({ "/*", "*", "/api/*" })
public String goError(Model model,HttpServletRequest request,HttpServletResponse response){
    model.addAttribute("isErrorPage", true);
    model.addAttribute("projects", projectService.findAll());
    model.addAttribute("sprints", sprintService.findAll());
    return "erreur";
}


}