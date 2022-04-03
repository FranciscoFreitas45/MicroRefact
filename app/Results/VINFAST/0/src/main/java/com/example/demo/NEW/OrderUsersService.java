package com.example.demo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Dao.OrderDetailDAO;
import com.example.demo.entity.Order;
@Service
public class OrderUsersService {

@Autowired
 private OrderDetailDAO orderdetaildao;


}