package com.easyshopping.template.directive;
 import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Article;
import com.easyshopping.entity.Attribute;
import com.easyshopping.entity.Brand;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.Product.OrderType;
import com.easyshopping.entity.ProductCategory;
import com.easyshopping.entity.Promotion;
import com.easyshopping.entity.Tag;
import com.easyshopping.service.AttributeService;
import com.easyshopping.service.BrandService;
import com.easyshopping.service.ProductCategoryService;
import com.easyshopping.service.ProductService;
import com.easyshopping.service.PromotionService;
import com.easyshopping.service.TagService;
import com.easyshopping.util.FreemarkerUtils;
import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.easyshopping.Interface.PromotionService;
import com.easyshopping.Interface.TagService;
@Component("productListDirective")
public class ProductListDirective extends BaseDirective{

 private  String PRODUCT_CATEGORY_ID_PARAMETER_NAME;

 private  String BRAND_ID_PARAMETER_NAME;

 private  String PROMOTION_ID_PARAMETER_NAME;

 private  String TAG_IDS_PARAMETER_NAME;

 private  String ATTRIBUTE_VALUE_PARAMETER_NAME;

 private  String START_PRICE_PARAMETER_NAME;

 private  String END_PRICE_PARAMETER_NAME;

 private  String ORDER_TYPE_PARAMETER_NAME;

 private  String VARIABLE_NAME;

@Resource(name = "productServiceImpl")
 private  ProductService productService;

@Resource(name = "productCategoryServiceImpl")
 private  ProductCategoryService productCategoryService;

@Resource(name = "brandServiceImpl")
 private  BrandService brandService;

@Resource(name = "promotionServiceImpl")
 private  PromotionService promotionService;

@Resource(name = "attributeServiceImpl")
 private  AttributeService attributeService;

@Resource(name = "tagServiceImpl")
 private  TagService tagService;


@SuppressWarnings({ "unchecked", "rawtypes" })
public void execute(Environment env,Map params,TemplateModel[] loopVars,TemplateDirectiveBody body){
    Long productCategoryId = FreemarkerUtils.getParameter(PRODUCT_CATEGORY_ID_PARAMETER_NAME, Long.class, params);
    Long brandId = FreemarkerUtils.getParameter(BRAND_ID_PARAMETER_NAME, Long.class, params);
    Long promotionId = FreemarkerUtils.getParameter(PROMOTION_ID_PARAMETER_NAME, Long.class, params);
    Long[] tagIds = FreemarkerUtils.getParameter(TAG_IDS_PARAMETER_NAME, Long[].class, params);
    Map<Long, String> attributeValue = FreemarkerUtils.getParameter(ATTRIBUTE_VALUE_PARAMETER_NAME, Map.class, params);
    BigDecimal startPrice = FreemarkerUtils.getParameter(START_PRICE_PARAMETER_NAME, BigDecimal.class, params);
    BigDecimal endPrice = FreemarkerUtils.getParameter(END_PRICE_PARAMETER_NAME, BigDecimal.class, params);
    OrderType orderType = FreemarkerUtils.getParameter(ORDER_TYPE_PARAMETER_NAME, OrderType.class, params);
    ProductCategory productCategory = productCategoryService.find(productCategoryId);
    Brand brand = brandService.find(brandId);
    Promotion promotion = promotionService.find(promotionId);
    List<Tag> tags = tagService.findList(tagIds);
    Map<Attribute, String> attributeValueMap = new HashMap<Attribute, String>();
    if (attributeValue != null) {
        for (Entry<Long, String> entry : attributeValue.entrySet()) {
            Attribute attribute = attributeService.find(entry.getKey());
            if (attribute != null) {
                attributeValueMap.put(attribute, entry.getValue());
            }
        }
    }
    List<Product> products;
    if ((productCategoryId != null && productCategory == null) || (brandId != null && brand == null) || (promotionId != null && promotion == null) || (tagIds != null && tags.isEmpty()) || (attributeValue != null && attributeValueMap.isEmpty())) {
        products = new ArrayList<Product>();
    } else {
        boolean useCache = useCache(env, params);
        String cacheRegion = getCacheRegion(env, params);
        Integer count = getCount(params);
        List<Filter> filters = getFilters(params, Article.class);
        List<Order> orders = getOrders(params);
        if (useCache) {
            products = productService.findList(productCategory, brand, promotion, tags, attributeValueMap, startPrice, endPrice, true, true, null, false, null, null, orderType, count, filters, orders, cacheRegion);
        } else {
            products = productService.findList(productCategory, brand, promotion, tags, attributeValueMap, startPrice, endPrice, true, true, null, false, null, null, orderType, count, filters, orders);
        }
    }
    setLocalVariable(VARIABLE_NAME, products, env, body);
}


}