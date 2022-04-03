package com.cg.oms.converter;
 import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.cg.oms.model.Order;
import com.cg.oms.vo.OrderVo;
@Component
public class OrderConverter {


public List<Order> voToModel(List<OrderVo> orderVo){
    return orderVo.stream().map(x -> voToModel(x)).collect(Collectors.toList());
}


public List<OrderVo> modelToVo(List<Order> order){
    return order.stream().map(x -> modelToVo(x)).collect(Collectors.toList());
}


}