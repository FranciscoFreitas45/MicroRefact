package com.cg.oms.converter;
 import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.cg.oms.model.OrderMedicine;
import com.cg.oms.vo.OrderMedicineVo;
@Component
public class OrderMedicineConverter {


public List<OrderMedicine> voToModel(List<OrderMedicineVo> orderMedicineVo){
    return orderMedicineVo.stream().map(x -> voToModel(x)).collect(Collectors.toList());
}


public List<OrderMedicineVo> modelToVo(List<OrderMedicine> orderMedicine){
    return orderMedicine.stream().map(x -> modelToVo(x)).collect(Collectors.toList());
}


}