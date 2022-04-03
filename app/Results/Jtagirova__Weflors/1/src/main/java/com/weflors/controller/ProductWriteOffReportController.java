package com.weflors.controller;
 import com.weflors.entity.ProductEntity;
import com.weflors.entity.SaleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.weflors.service.ProductServiceImpl;
import com.weflors.service.SaleServiceImpl;
import java.math.BigDecimal;
import java.util.List;
@Controller
@RequestMapping("/productwriteoffreport")
public class ProductWriteOffReportController {

 private  SaleServiceImpl saleServiceImpl;

 private  ProductServiceImpl productServiceImpl;

@Autowired
public ProductWriteOffReportController(SaleServiceImpl saleServiceImpl, ProductServiceImpl productServiceImpl) {
    this.saleServiceImpl = saleServiceImpl;
    this.productServiceImpl = productServiceImpl;
}
@GetMapping
public String addProductListPage(Model model){
    model.addAttribute("formName", "Отчет о списанном товаре");
    model.addAttribute("productWriteOffList", saleServiceImpl.findAllSalesBySalePrice(BigDecimal.ZERO));
    return "productwriteoffreport";
}


}