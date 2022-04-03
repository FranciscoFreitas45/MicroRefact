package com.easyshopping.template.directive;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.Review;
import com.easyshopping.entity.Review.Type;
import com.easyshopping.service.MemberService;
import com.easyshopping.service.ProductService;
import com.easyshopping.service.ReviewService;
import com.easyshopping.util.FreemarkerUtils;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.easyshopping.Interface.ReviewService;
import com.easyshopping.Interface.MemberService;
@Component("reviewListDirective")
public class ReviewListDirective extends BaseDirective{

 private  String MEMBER_ID_PARAMETER_NAME;

 private  String PRODUCT_ID_PARAMETER_NAME;

 private  String TYPE_PARAMETER_NAME;

 private  String VARIABLE_NAME;

@Resource(name = "reviewServiceImpl")
 private  ReviewService reviewService;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;

@Resource(name = "productServiceImpl")
 private  ProductService productService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    Long memberId = FreemarkerUtils.getParameter(MEMBER_ID_PARAMETER_NAME, Long.class, params);
    Long productId = FreemarkerUtils.getParameter(PRODUCT_ID_PARAMETER_NAME, Long.class, params);
    Type type = FreemarkerUtils.getParameter(TYPE_PARAMETER_NAME, Type.class, params);
    Member member = memberService.find(memberId);
    Product product = productService.find(productId);
    List<Review> reviews;
    if ((memberId != null && member == null) || (productId != null && product == null)) {
        reviews = new ArrayList<Review>();
    } else {
        boolean useCache = useCache(env, params);
        String cacheRegion = getCacheRegion(env, params);
        Integer count = getCount(params);
        List<Filter> filters = getFilters(params, Review.class);
        List<Order> orders = getOrders(params);
        if (useCache) {
            reviews = reviewService.findList(member, product, type, true, count, filters, orders, cacheRegion);
        } else {
            reviews = reviewService.findList(member, product, type, true, count, filters, orders);
        }
    }
    setLocalVariable(VARIABLE_NAME, reviews, env, body);
}


}