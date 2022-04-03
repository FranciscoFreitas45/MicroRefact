package com.easyshopping.template.directive;
 import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.entity.Member;
import com.easyshopping.service.MemberService;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("currentMemberDirective")
public class CurrentMemberDirective extends BaseDirective{

 private  String VARIABLE_NAME;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;


@SuppressWarnings("rawtypes")
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    Member currentMember = memberService.getCurrent();
    if (body != null) {
        setLocalVariable(VARIABLE_NAME, currentMember, env, body);
    } else {
        if (currentMember != null) {
            Writer out = env.getOut();
            out.write(currentMember.getUsername());
        }
    }
}


}