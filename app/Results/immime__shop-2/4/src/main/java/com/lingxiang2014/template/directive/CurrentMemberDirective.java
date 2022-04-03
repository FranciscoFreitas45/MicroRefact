package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.MemberService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.DTO.Member;
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