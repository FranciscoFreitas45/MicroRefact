import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sdrc.childinfo.util.Constants;
import org.sdrc.childinfo.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Controller
public class LoginController {

@Autowired
 private  StateManager stateManager;


@RequestMapping(value = { "logout" }, method = RequestMethod.POST)
public String logout(HttpServletRequest request,HttpServletResponse resp){
    /**
     * Update the Log out Info i.e update
     */
    HttpSession session = request.getSession(false);
    if (session != null) {
        // long txnId = ((UserDetailsModel) stateManager.getValue(Constants.USER_PRINCIPAL)).getLoginTxnId ();
        // loginService.updateLoginMeta(txnId, new Timestamp(new Date().getTime()));
        stateManager.setValue(Constants.USER_PRINCIPAL, null);
        request.getSession().setAttribute(Constants.USER_PRINCIPAL, null);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().getSession(true).removeAttribute(Constants.USER_PRINCIPAL);
        attr.getRequest().getSession(true).invalidate();
        request.logout();
        return "redirect:/";
    } else {
        request.getSession().invalidate();
        return "redirect:/";
    }
}


}