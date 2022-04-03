package com.example.demo.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.OrderDetail;
@RestController
@CrossOrigin
public class OrderDetailProductController {

@Autowired
 private OrderDetailProductService orderdetailproductservice;


}