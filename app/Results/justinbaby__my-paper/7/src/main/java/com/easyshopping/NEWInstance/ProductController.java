package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductController {

 private Product product;

 private Product product;


@PutMapping
("/setAllocatedStock")
public void setAllocatedStock(@RequestParam(name = "allocatedStock") Integer allocatedStock){
product.setAllocatedStock(allocatedStock);
}


@PutMapping
("/setStock")
public void setStock(@RequestParam(name = "stock") Integer stock){
product.setStock(stock);
}


@PutMapping
("/setWeekSales")
public void setWeekSales(@RequestParam(name = "weekSales") Long weekSales){
product.setWeekSales(weekSales);
}


@PutMapping
("/setMonthSales")
public void setMonthSales(@RequestParam(name = "monthSales") Long monthSales){
product.setMonthSales(monthSales);
}


@PutMapping
("/setSales")
public void setSales(@RequestParam(name = "sales") Long sales){
product.setSales(sales);
}


@PutMapping
("/setWeekSalesDate")
public void setWeekSalesDate(@RequestParam(name = "weekSalesDate") Date weekSalesDate){
product.setWeekSalesDate(weekSalesDate);
}


@PutMapping
("/setMonthSalesDate")
public void setMonthSalesDate(@RequestParam(name = "monthSalesDate") Date monthSalesDate){
product.setMonthSalesDate(monthSalesDate);
}


@PutMapping
("/setScoreCount")
public void setScoreCount(@RequestParam(name = "scoreCount") Long scoreCount){
product.setScoreCount(scoreCount);
}


}