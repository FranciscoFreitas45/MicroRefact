package com.project.stockexchangeappbackend.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.stockexchangeappbackend.entity.Stock;
@RestController
@CrossOrigin
public class StockArchivedOrderController {

@Autowired
 private StockArchivedOrderService stockarchivedorderservice;


}