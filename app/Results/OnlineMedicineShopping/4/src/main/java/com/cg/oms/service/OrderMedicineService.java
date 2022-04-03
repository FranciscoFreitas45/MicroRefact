package com.cg.oms.service;
 import java.util.List;
import com.cg.oms.exception.OrderNotFoundException;
import com.cg.oms.vo.OrderMedicineVo;
public interface OrderMedicineService {


public List<OrderMedicineVo> getAllOrderMedicine()
;

public OrderMedicineVo getOrderMedicineById(Long id)
;

public String saveOrderMedicine(OrderMedicineVo vo)
;

}