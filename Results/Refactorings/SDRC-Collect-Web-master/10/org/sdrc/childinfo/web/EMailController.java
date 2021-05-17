import java.util.ArrayList;
import java.util.List;
import org.sdrc.childinfo.model.ContactUs;
import org.sdrc.childinfo.model.Error;
import org.sdrc.childinfo.model.Mail;
import org.sdrc.childinfo.repository.UserRepository;
import org.sdrc.childinfo.service.UserService;
import org.sdrc.childinfo.util.Constants;
import org.sdrc.childinfo.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class EMailController {

@Autowired
 private  UserService userService;

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  StateManager stateManager;

@Autowired
 private  ResourceBundleMessageSource applicationMessageSource;

@Autowired
 private  ResourceBundleMessageSource workspaceMessageSource;


@RequestMapping("/mail")
public ModelAndView showMailWindow(){
    ModelAndView modelAndView = new ModelAndView("mailwindow");
    Mail mail = new Mail();
    mail.setFromUserName(new StringBuffer("Kamal"));
    mail.setToUserName("Admin");
    mail.setToEmailId("kamallochan095@gmail.com");
    mail.setCc("");
    mail.setSubject(new StringBuffer("subject"));
    mail.setMsg(new StringBuffer("message"));
    modelAndView.addObject("mailModel", mail);
    return modelAndView;
}


public void contactUs(ContactUs cu,RedirectAttributes redirectAttributes){
    try {
        String msg = cu.getMsg();
        StringBuffer msgbuff = new StringBuffer(msg);
        // String mailIdOfSender = "\n My Mail Id : ".concat(cu.getMail());
        String mailIdOfSender = workspaceMessageSource.getMessage(Constants.CONTACT_US_MAIL_SENDER_MAILID, null, null).concat(cu.getMail());
        msgbuff.append(mailIdOfSender);
        Mail mail = new Mail();
        mail.setFromUserName(new StringBuffer(cu.getName()));
        // mail.setToUserName("Admin");
        mail.setToUserName(workspaceMessageSource.getMessage(Constants.CONTACT_US_MAIL_TOUSERNAME, null, null));
        // mail.setToEmailId("shanti.swarup.biswal@gmail.com");
        mail.setToEmailId(workspaceMessageSource.getMessage(Constants.CONTACT_US_MAIL_TOMAILID, null, null));
        mail.setCc("");
        // mail.setSubject(new StringBuffer("eSamiksha contact notification"));
        mail.setSubject(new StringBuffer(workspaceMessageSource.getMessage(Constants.CONTACT_US_MAIL_SUBJECT, null, null)));
        mail.setMsg(msgbuff);
        userService.sendMail(mail);
        List<String> sucessMsg = new ArrayList<String>();
        // sucessMsg.add("Thank you for contact us. We will catch you soon ");
        sucessMsg.add(workspaceMessageSource.getMessage(Constants.CONTACT_US_MAIL_SEND_SUCCESS, null, null));
        redirectAttributes.addFlashAttribute("formError", sucessMsg);
        redirectAttributes.addFlashAttribute("className", "alert alert-success");
    // return "redirect:/contactus";
    } catch (Exception e) {
        e.printStackTrace();
        List<String> err = new ArrayList<String>();
        // err.add("Some Eception Occur, try after some time ");
        err.add(workspaceMessageSource.getMessage(Constants.CONTACT_US_MAIL_SEND_FAIL, null, null));
        redirectAttributes.addFlashAttribute("formError", err);
        redirectAttributes.addFlashAttribute("className", "alert alert-danger");
    // return "redirect:/contactus";
    }
}


@RequestMapping(method = RequestMethod.POST, value = "/submitMailtoAdmin", headers = { "Content-type=application/json" })
@ResponseBody
public Error submitMailtoAdmin(Mail mail){
    Error error = new Error();
    try {
        userService.sendAdaptionMail(mail);
    } catch (Exception e) {
        return error;
    }
    error.setErrorMessage("Mail sent successfully");
    return error;
}


@RequestMapping(method = RequestMethod.POST, value = "/submitMail", headers = { "Content-type=application/json" })
@ResponseBody
public Error submitMail(Mail mail){
    Error error = new Error();
    try {
        userService.sendMail(mail);
    } catch (Exception e) {
        error.setErrorMessage("Mail sent failed!");
        return error;
    }
    error.setErrorMessage("Mail sent successfully");
    return error;
}


}