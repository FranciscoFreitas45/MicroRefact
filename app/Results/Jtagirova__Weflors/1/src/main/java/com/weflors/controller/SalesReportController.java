package com.weflors.controller;
 import com.weflors.entity.ProcurementEntity;
import com.weflors.entity.ProductEntity;
import com.weflors.entity.SaleEntity;
import com.weflors.service.ProcurementServiceImpl;
import com.weflors.service.ProductServiceImpl;
import com.weflors.service.SaleServiceImpl;
import com.weflors.util.SalesReportHelperBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util;
import com.weflors.Interface.ProcurementServiceImpl;
@Controller
public class SalesReportController {

@Autowired
 private  ProcurementServiceImpl procurementServiceImpl;

@Autowired
 private  SaleServiceImpl saleServiceImpl;

@Autowired
 private  ProductServiceImpl productServiceImpl;


@RequestMapping(value = "/updateSalesReportView", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public List<ProductEntity> updateSalesReport(SalesReportHelperBean salesReportHelperBean){
    // Date date = getCorrectDateFormat(salesReportHelperBean.getReportStartDatePeriod());
    List<ProductEntity> products = new ArrayList<ProductEntity>();
    List<Integer> productIDs = salesReportHelperBean.getProductIDs();
    if (productIDs != null && productIDs.size() == 0) {
        return productServiceImpl.findListOfProducts();
    }
    if (productIDs != null && productIDs.size() > 0 && salesReportHelperBean.getReportStartDatePeriod() == null) {
        for (int productID : productIDs) {
            ProductEntity productEntity = productServiceImpl.findByProductId(productID);
            List<SaleEntity> salesEntities = saleServiceImpl.findAllSalesByProductID(productID);
            productEntity.setSalesByProductId(salesEntities);
            products.add(productEntity);
        }
        return products;
    // return productServiceImpl.findAllProductsByProductId(productIDs);
    }
    for (int productID : productIDs) {
        ProductEntity productEntity = productServiceImpl.findByProductId(productID);
        List<ProcurementEntity> procurementEntities = procurementServiceImpl.findAllProcurementsByProductDate(productID, salesReportHelperBean.getReportStartDatePeriod(), salesReportHelperBean.getReportEndDatePeriod());
        List<SaleEntity> salesEntities = saleServiceImpl.findAllSalesByProductDatePeriod(productID, salesReportHelperBean.getReportStartDatePeriod(), salesReportHelperBean.getReportEndDatePeriod());
        productEntity.setProcurementsByProductId(procurementEntities);
        productEntity.setSalesByProductId(salesEntities);
        products.add(productEntity);
    }
    return products;
}


@RequestMapping(value = { "/salesreport" }, method = RequestMethod.GET)
public String addProductPage(Model model){
    model.addAttribute("allproducts", saleServiceImpl.getAllProduct());
    model.addAttribute("formName", "Отчет о продажах");
    return "salesreport";
}


@RequestMapping(value = "/getAllSalesByProductID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public List<SaleEntity> getAllSalesByProductID(Integer productID){
    return saleServiceImpl.findAllSalesByProductID(productID);
}


@RequestMapping(method = RequestMethod.GET)
public String salesReportPage(Model model){
    return "salesreport";
}


@RequestMapping(value = "/getAllProcurementsByProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public List<ProcurementEntity> getAllProcurements(Integer productId){
    return procurementServiceImpl.loadAllProcurements();
}


}