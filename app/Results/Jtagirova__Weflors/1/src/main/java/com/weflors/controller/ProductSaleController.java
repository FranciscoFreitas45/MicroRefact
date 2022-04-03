package com.weflors.controller;
 import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.weflors.entity;
import com.weflors.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation;
import com.weflors.service.SaleServiceImpl;
import com.weflors.Interface.ProcurementServiceImpl;
import com.weflors.Interface.ClientServiceImpl;
import com.weflors.Interface.ProductStatusServiceImpl;
@Controller
public class ProductSaleController {

 private  SaleServiceImpl saleServiceImpl;

 private  ProcurementServiceImpl procurementServiceImpl;

 private  ProductServiceImpl productService;

 private  ClientServiceImpl clientService;

 private  ProductStatusServiceImpl productStatusService;

@Autowired
public ProductSaleController(SaleServiceImpl saleServiceImpl, ProcurementServiceImpl procurementServiceImpl, ProductServiceImpl productService, ClientServiceImpl clientService, ProductStatusServiceImpl productStatusService) {
    this.saleServiceImpl = saleServiceImpl;
    this.procurementServiceImpl = procurementServiceImpl;
    this.productService = productService;
    this.clientService = clientService;
    this.productStatusService = productStatusService;
}
public LocalDate getCurrentDate(){
    LocalDate currentDate = LocalDate.now();
    return currentDate;
}


@RequestMapping(value = "/addSaleProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public String addSaleProduct(List<SaleEntity> saleEntityList){
    Map<Integer, Integer> productIdQuantityMap = new HashMap<>();
    for (SaleEntity saleEntity : saleEntityList) {
        if (productIdQuantityMap.containsKey(saleEntity.getProductId())) {
            productIdQuantityMap.put(saleEntity.getProductId(), (productIdQuantityMap.get(saleEntity.getProductId()) + saleEntity.getQuantity()));
        } else {
            productIdQuantityMap.put(saleEntity.getProductId(), saleEntity.getQuantity());
        }
    }
    String responseText = "Вы продали: " + "\n";
    for (Map.Entry<Integer, Integer> item : productIdQuantityMap.entrySet()) {
        if (item.getValue() > productStatusService.findProductStatusEntity(item.getKey()).getQuantityWarehouse()) {
            return "Вы хотите продать " + productService.findByProductId(item.getKey()).getProductName() + " в количестве " + item.getValue() + " На складе есть: " + productStatusService.findProductStatusEntity(item.getKey()).getQuantityWarehouse() + " единиц товара.";
        } else {
            productStatusService.updateQuantityShopSaleAndWarehouse(item.getKey(), item.getValue());
            responseText = responseText + productService.findByProductId(item.getKey()).getProductName() + " в количестве " + item.getValue() + "\n";
        }
    }
    for (SaleEntity saleEntity : saleEntityList) {
        if (saleEntity.getClientByClientId() != null)
            updateClientDiscount(saleEntity.getClientByClientId().getClientId(), saleEntity.getSalePrice());
    }
    saleServiceImpl.addAllToSales(saleEntityList);
    return responseText;
}


@RequestMapping(value = { "/productsale" }, method = RequestMethod.GET)
public String addProductPage(Model model){
    model.addAttribute("salesForThisDay", saleServiceImpl.findSalesForThisDay(getCurrentDate()));
    model.addAttribute("currentDate", getCurrentDate());
    model.addAttribute("products", saleServiceImpl.getAllProduct());
    model.addAttribute("allClientsEmail", clientService.findAllClients());
    model.addAttribute("saleForm", new SaleEntity());
    model.addAttribute("formName", "Продажа товара");
    return "productsale";
}


public void updateClientDiscount(Integer clientId,BigDecimal totalAmountPurchased){
    if (clientId == null || clientId == 0) {
        return;
    }
    ClientEntity clientToUpdate = clientService.getClientByClientID(clientId);
    // update purchased amount
    BigDecimal clientTotalAmountPurchased = clientToUpdate.getAmountPurchased().add(totalAmountPurchased);
    clientToUpdate.setAmountPurchased(clientTotalAmountPurchased);
    // update discount
    Integer maxDiscount = 30;
    Integer newDiscount = clientTotalAmountPurchased.divide(new BigDecimal("1000"), 0, RoundingMode.DOWN).intValueExact();
    Integer curDiscount = clientToUpdate.getDiscount();
    newDiscount = newDiscount <= maxDiscount ? newDiscount : maxDiscount;
    if (curDiscount < newDiscount)
        clientToUpdate.setDiscount(newDiscount);
}


@RequestMapping(value = "/loadclientdiscount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ClientEntity loadClientDiscount(Integer clientID){
    return clientService.getClientByClientID(clientID);
}


@RequestMapping(value = "/loadroductinfobyproductid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ProductEntity loadProductInfoByProductName(ProductEntity productEntity){
    return saleServiceImpl.getProductByProductId(productEntity.getProductId());
}


}