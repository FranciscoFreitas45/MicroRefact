package run.halo.app.controller.admin.api;
 import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.annotation.DisableOnCondition;
import run.halo.app.mail.MailService;
import run.halo.app.model.params.MailParam;
import run.halo.app.model.support.BaseResponse;
@RestController
@RequestMapping("/api/admin/mails")
public class MailController {

 private  MailService mailService;

public MailController(MailService mailService) {
    this.mailService = mailService;
}
@PostMapping("test/connection")
@ApiOperation("Test connection with email server")
@DisableOnCondition
public BaseResponse<String> testConnection(){
    mailService.testConnection();
    return BaseResponse.ok("您和邮箱服务器的连接通畅");
}


@PostMapping("test")
@ApiOperation("Tests the SMTP service")
@DisableOnCondition
public BaseResponse<String> testMail(MailParam mailParam){
    mailService.sendTextMail(mailParam.getTo(), mailParam.getSubject(), mailParam.getContent());
    return BaseResponse.ok("已发送，请查收。若确认没有收到邮件，请检查服务器日志");
}


}