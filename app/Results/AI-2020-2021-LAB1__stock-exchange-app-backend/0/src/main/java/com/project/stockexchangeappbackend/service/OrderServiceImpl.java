package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.dto.CreateOrderDTO;
import com.project.stockexchangeappbackend.entity;
import com.project.stockexchangeappbackend.exception.InvalidInputDataException;
import com.project.stockexchangeappbackend.repository;
import com.project.stockexchangeappbackend.util.timemeasuring.LogicBusinessMeasureTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Join;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util;
import java.util.stream.Collectors;
import com.project.stockexchangeappbackend.Interface.StockRepository;
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

 private  OrderRepository orderRepository;

 private  ArchivedOrderRepository archivedOrderRepository;

 private  StockRepository stockRepository;

 private  UserRepository userRepository;

 private  ResourceRepository resourceRepository;

 private  ModelMapper modelMapper;

 private  AllOrdersRepository allOrdersRepository;


@Override
@LogicBusinessMeasureTime
@Transactional
public void createOrder(CreateOrderDTO orderDTO){
    Stock stock = stockRepository.findByIdAndIsDeletedFalse(orderDTO.getStock().getId()).orElseThrow(() -> new InvalidInputDataException("Validation error", Map.of("stock", "Stock company not found.")));
    String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findByEmailIgnoreCase(username).orElseThrow(() -> new AccessDeniedException("Access Denied"));
    Order order = orderRepository.save(validateOrder(orderDTO, stock, user));
    log.info(orderDTO.getOrderType().toString() + " with id " + order.getId() + " of user " + user.getEmail() + " was successfully created.");
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<AllOrders> getOwnedOrders(Pageable pageable,Specification<AllOrders> specification){
    String principal = SecurityContextHolder.getContext().getAuthentication().getName();
    Specification<AllOrders> userIsPrincipal = (root, criteriaQuery, criteriaBuilder) -> {
        Join<Order, User> owner = root.join("user");
        return criteriaBuilder.equal(owner.get("email"), principal);
    };
    Page<AllOrders> orders = allOrdersRepository.findAll(Specification.where(userIsPrincipal).and(specification), pageable);
    orders.forEach(order -> order.setUser(null));
    return orders;
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public List<Order> getActiveBuyingOrders(){
    return orderRepository.findByOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(OrderType.BUYING_ORDER, OffsetDateTime.now(ZoneId.systemDefault()));
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Optional<Order> refreshObjectById(Long id){
    return orderRepository.findById(id);
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<AllOrders> findAllOrders(Pageable pageable,Specification<AllOrders> specification){
    Page<AllOrders> orders = allOrdersRepository.findAll(specification, pageable);
    if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
        orders.forEach(order -> order.setUser(null));
    }
    return orders;
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<AllOrders> getOrdersByUser(Pageable pageable,Specification<AllOrders> specification,Long id){
    User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User does not exist"));
    Specification<AllOrders> orderByUser = (root, criteriaQuery, criteriaBuilder) -> {
        Join<Order, User> owner = root.join("user");
        return criteriaBuilder.equal(owner.get("email"), user.getEmail());
    };
    return allOrdersRepository.findAll(Specification.where(specification).and(orderByUser), pageable);
}


@Override
@LogicBusinessMeasureTime
@Transactional
public void moveInactiveOrders(){
    List<Order> orders = orderRepository.findByDateExpirationIsBeforeOrRemainingAmountOrDateClosingIsNotNull(OffsetDateTime.now(ZoneId.systemDefault()), 0);
    orderRepository.deleteAll(orders);
    List<ArchivedOrder> archivedOrders = orders.stream().map(order -> {
        order.setDateClosing(OffsetDateTime.now(ZoneId.systemDefault()));
        return modelMapper.map(order, ArchivedOrder.class);
    }).collect(Collectors.toList());
    archivedOrderRepository.saveAll(archivedOrders).forEach(order -> log.info(order.getOrderType().toString() + " with id " + order.getId() + " was successfully archived."));
}


@Override
@LogicBusinessMeasureTime
@Transactional
public void deactivateOrder(Long id){
    Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
    String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.findByEmailIgnoreCase(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
    if (!order.getUser().getEmail().equals(username) && !user.getRole().equals(Role.ADMIN)) {
        throw new AccessDeniedException("Access Denied");
    }
    orderRepository.delete(order);
    ArchivedOrder archivedOrder = archivedOrderRepository.findById(id).orElseGet(() -> modelMapper.map(order, ArchivedOrder.class));
    archivedOrder.setDateClosing(OffsetDateTime.now(ZoneId.systemDefault()));
    archivedOrderRepository.save(archivedOrder);
    log.info(archivedOrder.getOrderType().toString() + " with id " + order.getId() + " was successfully deactivated.");
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public List<Order> getActiveSellingOrdersByStockAndPriceLessThanEqual(Stock stock,BigDecimal maximalPrice){
    return orderRepository.findByStockAndOrderTypeAndPriceIsLessThanEqualAndDateExpirationIsAfterAndDateClosingIsNullOrderByPrice(stock, OrderType.SELLING_ORDER, maximalPrice, OffsetDateTime.now(ZoneId.systemDefault()));
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public AllOrders findOrderById(Long id){
    AllOrders order = allOrdersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order Not Found"));
    if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
        order.setUser(null);
    }
    return order;
}


public Order validateOrder(CreateOrderDTO orderDTO,Stock stock,User user){
    Map<String, List<String>> errors = new HashMap<>();
    if (orderDTO.getOrderType() == OrderType.BUYING_ORDER) {
        if (orderDTO.getPriceType() == PriceType.GREATER_OR_EQUAL) {
            errors.putIfAbsent("priceType", new ArrayList<>());
            errors.get("priceType").add("The buying order price's type cannot be GREATER_OR_EQUAL.");
        }
        if (orderDTO.getAmount() > stock.getAmount()) {
            errors.putIfAbsent("amount", new ArrayList<>());
            errors.get("amount").add("The given stock company does not have enough amount of action.");
        }
    } else {
        if (orderDTO.getPriceType() == PriceType.LESS_OR_EQUAL) {
            errors.putIfAbsent("priceType", new ArrayList<>());
            errors.get("priceType").add("The selling order price's type cannot be LESS_OR_EQUAL.");
        }
        Optional<Resource> resource = resourceRepository.findByUserAndStock(user, stock);
        int sellingAmountOfStock = orderRepository.findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(stock, user, OrderType.SELLING_ORDER, OffsetDateTime.now(ZoneId.systemDefault())).stream().mapToInt(Order::getRemainingAmount).sum();
        if (resource.isEmpty() || resource.get().getAmount() < orderDTO.getAmount() || sellingAmountOfStock + orderDTO.getAmount() > resource.get().getAmount()) {
            errors.putIfAbsent("amount", new ArrayList<>());
            errors.get("amount").add("The logged in user does not have enough available amount of stocks for sale.");
        }
    }
    if (!stock.getTag().getName().equals(user.getTag().getName())) {
        errors.putIfAbsent("stock", new ArrayList<>());
        errors.get("stock").add("The logged in user in given stock are tagged using others tags.");
    }
    if (!errors.isEmpty()) {
        throw new InvalidInputDataException("Data validation", errors);
    }
    Order order = modelMapper.map(orderDTO, Order.class);
    order.setStock(stock);
    order.setUser(user);
    order.setRemainingAmount(orderDTO.getAmount());
    order.setDateCreation(OffsetDateTime.now(ZoneId.systemDefault()));
    order.setDateClosing(null);
    return order;
}


}