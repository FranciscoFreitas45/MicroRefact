package com.cg.oms.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.oms.converter.OrderMedicineConverter;
import com.cg.oms.exception.OrderNotFoundException;
import com.cg.oms.model.OrderMedicine;
import com.cg.oms.repository.OrderMedicineRepository;
import com.cg.oms.vo.OrderMedicineVo;
@Service
public class OrderMedicineServiceImpl implements OrderMedicineService{

 public  String EXCEPTION_MESSAGE;

@Autowired
 private  OrderMedicineRepository orderMedicineRepository;

@Autowired
 private  OrderMedicineConverter orderMedicineConverter;


@Override
public List<OrderMedicineVo> getAllOrderMedicine(){
    List<OrderMedicine> orderMedicine = orderMedicineRepository.findAll();
    return orderMedicineConverter.modelToVo(orderMedicine);
}


@Override
public OrderMedicineVo getOrderMedicineById(Long id){
    OrderMedicine orderMedicine = orderMedicineRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(EXCEPTION_MESSAGE + id));
    return orderMedicineConverter.modelToVo(orderMedicine);
}


@Override
public String saveOrderMedicine(OrderMedicineVo vo){
    OrderMedicine orderMedicine = orderMedicineConverter.voToModel(vo);
    orderMedicine = orderMedicineRepository.save(orderMedicine);
    OrderMedicineVo omvo = orderMedicineConverter.modelToVo(orderMedicine);
    return "Cart Added SuccessFully!!! " + omvo.toString();
}


}