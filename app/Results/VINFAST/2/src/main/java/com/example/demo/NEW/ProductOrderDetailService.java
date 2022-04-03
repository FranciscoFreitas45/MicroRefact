package com.example.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Dao.ProductDAO;
import com.example.demo.entity.Product;
@Service
public class ProductOrderDetailService {

@Autowired
 private ProductDAO productdao;


}