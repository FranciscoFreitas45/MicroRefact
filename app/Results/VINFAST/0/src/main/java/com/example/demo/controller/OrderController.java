package com.example.demo.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.Dao.OrderDAO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Report;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ParamService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SessionService;
import com.example.demo.service.ShoppingCartService;
@Controller
@RequestMapping("admin/order")
public class OrderController {

@Autowired
 private ParamService paramService;

@Autowired
 private OrderDAO dao;

@Autowired
 private OrderService orderService;

@Autowired
 private OrderDetailService orderdetailSevice;

@Autowired
 private ShoppingCartService cart;

@Autowired
 private ProductService product;

@Autowired
 private SessionService session;

@Autowired
 private OrderDAO orderdao;


@RequestMapping("/show/{id}")
public String showindex(Model model,long id){
    List<OrderDetail> detail = orderdetailSevice.findByOrderId(id);
    model.addAttribute("detail", detail);
    model.addAttribute("view", "/views/user/order-view.jsp");
    model.addAttribute("view", "/views/admin/order/order-detail.jsp");
    return "layout";
}


@RequestMapping("/index/{st}")
public String indexST(Model model,int st){
    Order item = new Order();
    model.addAttribute("item", item);
    List<Order> items = dao.findByStatus(st);
    model.addAttribute("order", items);
    model.addAttribute("view", "/views/admin/order/order.jsp");
    return "layout";
}


@RequestMapping("/report")
public String report(Model model){
    List<Report> list = orderdetailSevice.revenueCategory();
    model.addAttribute("list", list);
    model.addAttribute("view", "/views/admin/order/report.jsp");
    return "layout";
}


@RequestMapping("/index")
public String index(Model model){
    Order item = new Order();
    model.addAttribute("item", item);
    List<Order> items = dao.findAll();
    model.addAttribute("order", items);
    model.addAttribute("view", "/views/admin/order/order.jsp");
    return "layout";
}


@RequestMapping("/update/{id}")
public String update(Model model,long id){
    @SuppressWarnings("deprecation")
    Order order = orderService.getOne(id);
    order.setStatus(2);
    orderService.save(order);
    return "redirect:/admin/order/index";
}


}