package com.easyshopping.controller.admin;
 import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.easyshopping.ExcelView;
import com.easyshopping.Message;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Coupon;
import com.easyshopping.entity.CouponCode;
import com.easyshopping.service.AdminService;
import com.easyshopping.service.CouponCodeService;
import com.easyshopping.service.CouponService;
import com.easyshopping.util.FreemarkerUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.easyshopping.Interface.AdminService;
@Controller("adminCouponController")
@RequestMapping("/admin/coupon")
public class CouponController extends BaseController{

@Resource(name = "couponServiceImpl")
 private  CouponService couponService;

@Resource(name = "couponCodeServiceImpl")
 private  CouponCodeService couponCodeService;

@Resource(name = "adminServiceImpl")
 private  AdminService adminService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(ModelMap model){
    return "/admin/coupon/add";
}


@RequestMapping(value = "/download", method = RequestMethod.POST)
public ModelAndView download(Long id,Integer count,ModelMap model){
    if (count == null || count <= 0) {
        count = 50;
    }
    Coupon coupon = couponService.find(id);
    List<CouponCode> data = couponCodeService.build(coupon, null, count);
    String filename = "coupon_code_" + new SimpleDateFormat("yyyyMM").format(new Date()) + ".xls";
    String[] contents = new String[4];
    contents[0] = message("admin.coupon.type") + ": " + coupon.getName();
    contents[1] = message("admin.coupon.count") + ": " + count;
    contents[2] = message("admin.coupon.operator") + ": " + adminService.getCurrentUsername();
    contents[3] = message("admin.coupon.date") + ": " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    return new ModelAndView(new ExcelView(filename, null, new String[] { "code" }, new String[] { message("admin.coupon.title") }, null, null, data, contents), model);
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("coupon", couponService.find(id));
    return "/admin/coupon/edit";
}


@RequestMapping(value = "/build", method = RequestMethod.GET)
public String build(Long id,ModelMap model){
    Coupon coupon = couponService.find(id);
    model.addAttribute("coupon", coupon);
    model.addAttribute("totalCount", couponCodeService.count(coupon, null, null, null, null));
    model.addAttribute("usedCount", couponCodeService.count(coupon, null, null, null, true));
    return "/admin/coupon/build";
}


@RequestMapping(value = "/check_price_expression", method = RequestMethod.GET)
@ResponseBody
public boolean checkPriceExpression(String priceExpression){
    if (StringUtils.isEmpty(priceExpression)) {
        return false;
    }
    try {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("quantity", 111);
        model.put("price", new BigDecimal(9.99));
        new BigDecimal(FreemarkerUtils.process("#{(" + priceExpression + ");M50}", model));
        return true;
    } catch (Exception e) {
        return false;
    }
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Coupon coupon,RedirectAttributes redirectAttributes){
    if (!isValid(coupon)) {
        return ERROR_VIEW;
    }
    if (coupon.getBeginDate() != null && coupon.getEndDate() != null && coupon.getBeginDate().after(coupon.getEndDate())) {
        return ERROR_VIEW;
    }
    if (coupon.getMinimumQuantity() != null && coupon.getMaximumQuantity() != null && coupon.getMinimumQuantity() > coupon.getMaximumQuantity()) {
        return ERROR_VIEW;
    }
    if (coupon.getMinimumPrice() != null && coupon.getMaximumPrice() != null && coupon.getMinimumPrice().compareTo(coupon.getMaximumPrice()) > 0) {
        return ERROR_VIEW;
    }
    if (StringUtils.isNotEmpty(coupon.getPriceExpression())) {
        try {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("quantity", 111);
            model.put("price", new BigDecimal(9.99));
            new BigDecimal(FreemarkerUtils.process("#{(" + coupon.getPriceExpression() + ");M50}", model));
        } catch (Exception e) {
            return ERROR_VIEW;
        }
    }
    if (coupon.getIsExchange() && coupon.getPoint() == null) {
        return ERROR_VIEW;
    }
    if (!coupon.getIsExchange()) {
        coupon.setPoint(null);
    }
    coupon.setCouponCodes(null);
    coupon.setPromotions(null);
    coupon.setOrders(null);
    couponService.save(coupon);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Coupon coupon,RedirectAttributes redirectAttributes){
    if (!isValid(coupon)) {
        return ERROR_VIEW;
    }
    if (coupon.getBeginDate() != null && coupon.getEndDate() != null && coupon.getBeginDate().after(coupon.getEndDate())) {
        return ERROR_VIEW;
    }
    if (coupon.getMinimumQuantity() != null && coupon.getMaximumQuantity() != null && coupon.getMinimumQuantity() > coupon.getMaximumQuantity()) {
        return ERROR_VIEW;
    }
    if (coupon.getMinimumPrice() != null && coupon.getMaximumPrice() != null && coupon.getMinimumPrice().compareTo(coupon.getMaximumPrice()) > 0) {
        return ERROR_VIEW;
    }
    if (StringUtils.isNotEmpty(coupon.getPriceExpression())) {
        try {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("quantity", 111);
            model.put("price", new BigDecimal(9.99));
            new BigDecimal(FreemarkerUtils.process("#{(" + coupon.getPriceExpression() + ");M50}", model));
        } catch (Exception e) {
            return ERROR_VIEW;
        }
    }
    if (coupon.getIsExchange() && coupon.getPoint() == null) {
        return ERROR_VIEW;
    }
    if (!coupon.getIsExchange()) {
        coupon.setPoint(null);
    }
    couponService.update(coupon, "couponCodes", "promotions", "orders");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Pageable pageable,ModelMap model){
    model.addAttribute("page", couponService.findPage(pageable));
    return "/admin/coupon/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long[] ids){
    couponService.delete(ids);
    return SUCCESS_MESSAGE;
}


}