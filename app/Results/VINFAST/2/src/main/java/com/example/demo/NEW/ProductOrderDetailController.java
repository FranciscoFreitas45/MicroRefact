package com.example.demo.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Product;
@RestController
@CrossOrigin
public class ProductOrderDetailController {

@Autowired
 private ProductOrderDetailService productorderdetailservice;


}