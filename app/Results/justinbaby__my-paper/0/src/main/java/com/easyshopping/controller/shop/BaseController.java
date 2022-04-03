package com.easyshopping.controller.shop;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import com.easyshopping.DateEditor;
import com.easyshopping.HtmlCleanEditor;
import com.easyshopping.Message;
import com.easyshopping.Setting;
import com.easyshopping.template.directive.FlashMessageDirective;
import com.easyshopping.util.SettingUtils;
import com.easyshopping.util.SpringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.easyshopping.Interface.Message;
import com.easyshopping.Interface.Message;
public class BaseController {

 protected  String ERROR_VIEW;

 protected  Message ERROR_MESSAGE;

 protected  Message SUCCESS_MESSAGE;

 private  String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME;

@Resource(name = "validator")
 private  Validator validator;


public boolean isValid(Class<?> type,String property,Object value,Class<?> groups){
    Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
    if (constraintViolations.isEmpty()) {
        return true;
    } else {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
        return false;
    }
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(String.class, new HtmlCleanEditor(true, true));
    binder.registerCustomEditor(Date.class, new DateEditor(true));
}


public String currency(BigDecimal amount,boolean showSign,boolean showUnit){
    Setting setting = SettingUtils.get();
    String price = setting.setScale(amount).toString();
    if (showSign) {
        price = setting.getCurrencySign() + price;
    }
    if (showUnit) {
        price += setting.getCurrencyUnit();
    }
    return price;
}


public void addFlashMessage(RedirectAttributes redirectAttributes,Message message){
    if (redirectAttributes != null && message != null) {
        redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
    }
}


public String message(String code,Object args){
    return SpringUtils.getMessage(code, args);
}


}