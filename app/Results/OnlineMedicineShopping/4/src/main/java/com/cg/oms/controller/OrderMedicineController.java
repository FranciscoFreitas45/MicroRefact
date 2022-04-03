package com.cg.oms.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.oms.exception.OrderNotFoundException;
import com.cg.oms.service.OrderMedicineServiceImpl;
import com.cg.oms.vo.OrderMedicineVo;
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
@RestController
public class OrderMedicineController {

@Autowired
 private  OrderMedicineServiceImpl orderMedicineServiceImpl;


@PostMapping("/Ordermedicine/addnew")
public ResponseEntity<OrderMedicineVo> addNewOrderMedicine(OrderMedicineVo orderMedicineVo){
    orderMedicineServiceImpl.saveOrderMedicine(orderMedicineVo);
    return ResponseEntity.ok(orderMedicineVo);
}


@GetMapping("/OrderMedicine/{id}")
public ResponseEntity<OrderMedicineVo> getOrderMedicineId(Long orderId){
    OrderMedicineVo orderMedicineVo = orderMedicineServiceImpl.getOrderMedicineById(orderId);
    return ResponseEntity.ok().body(orderMedicineVo);
}


}