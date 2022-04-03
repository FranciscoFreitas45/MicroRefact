package com.easyshopping.template.directive;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Brand;
import com.easyshopping.entity.Consultation;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
import com.easyshopping.service.ConsultationService;
import com.easyshopping.service.MemberService;
import com.easyshopping.service.ProductService;
import com.easyshopping.util.FreemarkerUtils;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.easyshopping.Interface.ConsultationService;
import com.easyshopping.Interface.MemberService;
@Component("consultationListDirective")
public class ConsultationListDirective extends BaseDirective{

 private  String MEMBER_ID_PARAMETER_NAME;

 private  String PRODUCT_ID_PARAMETER_NAME;

 private  String VARIABLE_NAME;

@Resource(name = "consultationServiceImpl")
 private  ConsultationService consultationService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "productServiceImpl")
 private  ProductService productService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    Long memberId = FreemarkerUtils.getParameter(MEMBER_ID_PARAMETER_NAME, Long.class, params);
    Long productId = FreemarkerUtils.getParameter(PRODUCT_ID_PARAMETER_NAME, Long.class, params);
    Member member = memberService.find(memberId);
    Product product = productService.find(productId);
    List<Consultation> consultations;
    boolean useCache = useCache(env, params);
    String cacheRegion = getCacheRegion(env, params);
    Integer count = getCount(params);
    List<Filter> filters = getFilters(params, Brand.class);
    List<Order> orders = getOrders(params);
    if ((memberId != null && member == null) || (productId != null && product == null)) {
        consultations = new ArrayList<Consultation>();
    } else {
        if (useCache) {
            consultations = consultationService.findList(member, product, true, count, filters, orders, cacheRegion);
        } else {
            consultations = consultationService.findList(member, product, true, count, filters, orders);
        }
    }
    setLocalVariable(VARIABLE_NAME, consultations, env, body);
}


}