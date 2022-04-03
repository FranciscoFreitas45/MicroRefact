package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AuthenticationFailureHandlerController {

 private AuthenticationFailureHandler authenticationfailurehandler;


@PutMapping
("/onFailure")
public void onFailure(@RequestParam(name = "request") HttpServletRequest request,@RequestParam(name = "response") HttpServletResponse response,@RequestParam(name = "exception") AbstractHaloException exception){
authenticationfailurehandler.onFailure(request,response,exception);
}


}