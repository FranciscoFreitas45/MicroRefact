package com.easyshopping.controller.admin;
 import javax.annotation.Resource;
import com.easyshopping.entity.DeliveryCenter;
import com.easyshopping.entity.DeliveryTemplate;
import com.easyshopping.service.DeliveryCenterService;
import com.easyshopping.service.DeliveryTemplateService;
import com.easyshopping.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.easyshopping.Interface.OrderService;
@Controller("adminPrintController")
@RequestMapping("/admin/print")
public class PrintController extends BaseController{

@Resource(name = "orderServiceImpl")
 private  OrderService orderService;

@Resource(name = "deliveryTemplateServiceImpl")
 private  DeliveryTemplateService deliveryTemplateService;

@Resource(name = "deliveryCenterServiceImpl")
 private  DeliveryCenterService deliveryCenterService;


@RequestMapping(value = "/delivery", method = RequestMethod.GET)
public String delivery(Long orderId,Long deliveryTemplateId,Long deliveryCenterId,ModelMap model){
    DeliveryTemplate deliveryTemplate = deliveryTemplateService.find(deliveryTemplateId);
    DeliveryCenter deliveryCenter = deliveryCenterService.find(deliveryCenterId);
    if (deliveryTemplate == null) {
        deliveryTemplate = deliveryTemplateService.findDefault();
    }
    if (deliveryCenter == null) {
        deliveryCenter = deliveryCenterService.findDefault();
    }
    model.addAttribute("deliveryTemplates", deliveryTemplateService.findAll());
    model.addAttribute("deliveryCenters", deliveryCenterService.findAll());
    model.addAttribute("order", orderService.find(orderId));
    model.addAttribute("deliveryTemplate", deliveryTemplate);
    model.addAttribute("deliveryCenter", deliveryCenter);
    return "/admin/print/delivery";
}


@RequestMapping(value = "/product", method = RequestMethod.GET)
public String product(Long id,ModelMap model){
    model.addAttribute("order", orderService.find(id));
    return "/admin/print/product";
}


@RequestMapping(value = "/shipping", method = RequestMethod.GET)
public String shipping(Long id,ModelMap model){
    model.addAttribute("order", orderService.find(id));
    return "/admin/print/shipping";
}


@RequestMapping(value = "/order", method = RequestMethod.GET)
public String order(Long id,ModelMap model){
    model.addAttribute("order", orderService.find(id));
    return "/admin/print/order";
}


}