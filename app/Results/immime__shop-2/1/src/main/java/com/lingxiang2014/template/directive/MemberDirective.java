package com.lingxiang2014.template.directive;
 import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.BonudsService;
import com.lingxiang2014.service.MemberService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.lingxiang2014.Interface.BonudsService;
@Component("memberCurrentDirective")
public class MemberDirective extends BaseDirective{

 private  String VARIABLE_NAME;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "bonudsServiceImpl")
 private  BonudsService bonudsService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    Member member;
    boolean useCache = useCache(env, params);
    if (useCache) {
        member = memberService.getCurrent();
    } else {
        member = memberService.getCurrent();
    }
    member.setAllBonuds(bonudsService.count(member, null));
    setLocalVariable(VARIABLE_NAME, member, env, body);
}


}