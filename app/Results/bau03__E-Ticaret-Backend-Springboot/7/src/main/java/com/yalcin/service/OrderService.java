package com.yalcin.service;
 import com.yalcin.dto.request.OrderForm;
import com.yalcin.entity;
import com.yalcin.event.OnRegistrationSuccessEvent;
import com.yalcin.event.ProductSuccessEvent;
import com.yalcin.exception.ErrorWhileSendingEmailException;
import com.yalcin.repository.AdressRepository;
import com.yalcin.repository.OrderRepository;
import com.yalcin.repository.StoreRepository;
import com.yalcin.repository.TotalOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.lang.Integer.parseInt;
import com.yalcin.Interface.UserServiceImpl;
import com.yalcin.Interface.AdressRepository;
import com.yalcin.Interface.OrderRepository;
import com.yalcin.DTO.Order;
@Service
public class OrderService {

@Autowired
 private UserServiceImpl userServiceImpl;

@Autowired
 private StoreRepository storeRepository;

@Autowired
 private AdressRepository adressRepository;

@Autowired
 private OrderRepository orderRepository;

@Autowired
 private  ApplicationEventPublisher eventPublisher;

@Autowired
 private TotalOrderRepository totalOrderRepository;


public void orderSave(OrderForm orderForm){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    Adress adress = adressRepository.findAllById(parseInt(orderForm.getAdress()));
    TotalOrder totalOrder = new TotalOrder();
    List<Store> store = storeRepository.findAllByUser_IdAndEnabled(user.getId(), false);
    Set<Store> store3 = storeRepository.findAllByUserIdAndEnabled(user.getId(), false);
    System.out.println(store3);
    float totalPrice = 0;
    for (int i = store.size() - 1; i >= 0; i--) {
        Order order = new Order();
        totalPrice = totalPrice + store.get(i).getProduct().getPrice();
        order.setUser(user);
        order.setAdress(adress);
        order.setEnabled(false);
        order.setTimestamp(new Date());
        order.setProduct(store.get(i).getProduct());
        orderRepository.save(order);
        try {
            eventPublisher.publishEvent(new ProductSuccessEvent(user, store.get(i).getProduct()));
        } catch (Exception re) {
            throw new ErrorWhileSendingEmailException(re.getMessage());
        }
    }
    totalOrder.setUser(user);
    totalOrder.setTotalPrice(totalPrice);
    totalOrder.setStore(store3);
    totalOrder.setEnabled(false);
    totalOrder.setTimestamp(new Date());
    totalOrderRepository.save(totalOrder);
    List<Store> store2 = storeRepository.findAllByUser_IdAndEnabled(user.getId(), false);
    for (int i = store2.size() - 1; i >= 0; i--) {
        store2.get(i).setEnabled(true);
        storeRepository.save(store2.get(i));
    }
}


}