package org.opengeoportal.security;
 import org.opengeoportal.config.ogp.OgpConfigRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
@Controller
public class NopLoginController {

@Autowired
@Qualifier("formLoginService")
 private LoginService loginService;

@Autowired
 private OgpConfigRetriever ogpConfigRetriever;

 final  Logger logger;


@RequestMapping(value = "weblogin", method = RequestMethod.GET)
@ResponseBody
public ModelAndView getStatus(){
    logger.debug("Login status checked");
    String sendingPage = ogpConfigRetriever.getPropertyWithDefault("ogp.domain", "");
    String noopUser = ogpConfigRetriever.getPropertyWithDefault("login.nop.user", "");
    String noopPass = ogpConfigRetriever.getPropertyWithDefault("login.nop.password", "");
    // create the model to return
    ModelAndView mav = new ModelAndView("iframeLogin");
    // The appropriate authentication manager must be configured.  The default one should work.
    // Make sure this username and password combo matches what's in your Spring Security context
    LoginStatus status = loginService.login(noopUser, noopPass);
    ObjectWriter ow = new ObjectMapper().writer();
    String json = ow.writeValueAsString(status);
    // test
    // json = json.replace("false", "true");
    // send the authentication status as a json string
    mav.addObject("authStatus", json);
    // the sendingPage must be set to the domain of the OGP instance running for postMessage to work properly
    mav.addObject("sendingPage", sendingPage);
    return mav;
}


}