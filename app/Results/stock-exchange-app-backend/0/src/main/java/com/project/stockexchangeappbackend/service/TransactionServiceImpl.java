package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.entity.*;
import com.project.stockexchangeappbackend.repository.*;
import com.project.stockexchangeappbackend.util.timemeasuring.LogicBusinessMeasureTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

 private  TransactionRepository transactionRepository;

 private  ArchivedOrderRepository archivedOrderRepository;

 private  OrderRepository orderRepository;

 private  AllOrdersRepository allOrdersRepository;

 private  ResourceRepository resourceRepository;

 private  UserRepository userRepository;

 private  ModelMapper modelMapper;


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<Transaction> getOwnedTransactions(Pageable pageable,Specification<Transaction> specification,boolean isSeller,boolean isBuyer){
    String principal = SecurityContextHolder.getContext().getAuthentication().getName();
    Specification<Transaction> userIsBuyer = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("buyingOrder").join("user").get("email"), principal);
    Specification<Transaction> userIsSeller = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("sellingOrder").join("user").get("email"), principal);
    Page<Transaction> transactions = getTransactions(pageable, specification, isSeller, isBuyer, userIsBuyer, userIsSeller);
    transactions.forEach(transaction -> {
        transaction.getBuyingOrder().setUser(null);
        transaction.getSellingOrder().setUser(null);
    });
    return transactions;
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Transaction findTransactionById(Long id){
    Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transaction Not Found"));
    if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
        transaction.getBuyingOrder().setUser(null);
        transaction.getSellingOrder().setUser(null);
    }
    return transaction;
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<Transaction> getUserTransactions(Pageable pageable,Specification<Transaction> specification,Long userId,boolean isSeller,boolean isBuyer){
    User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    Specification<Transaction> withBuyingOrder = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("buyingOrder").join("user").get("id"), user.getId());
    Specification<Transaction> withSellingOrder = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("sellingOrder").join("user").get("id"), user.getId());
    return getTransactions(pageable, specification, isSeller, isBuyer, withBuyingOrder, withSellingOrder);
}


public Page<Transaction> getTransactions(Pageable pageable,Specification<Transaction> specification,boolean isSeller,boolean isBuyer,Specification<Transaction> userIsBuyer,Specification<Transaction> userIsSeller){
    Specification<Transaction> spec1 = Specification.where(userIsBuyer).and(specification);
    Specification<Transaction> spec2 = Specification.where(userIsSeller).and(specification);
    if (isBuyer && isSeller) {
        return transactionRepository.findAll(Specification.where(spec1).or(spec2), pageable);
    } else if (isBuyer) {
        return transactionRepository.findAll(Specification.where(spec1), pageable);
    } else if (isSeller) {
        return transactionRepository.findAll(Specification.where(spec2), pageable);
    } else {
        return Page.empty();
    }
}


@Override
@Transactional
public void makeTransaction(Order buyingOrder,Order sellingOrder,int amount,BigDecimal pricePerUnit){
    ArchivedOrder archivedBuyingOrder = archivedOrderRepository.findById(buyingOrder.getId()).orElseGet(() -> modelMapper.map(buyingOrder, ArchivedOrder.class));
    ArchivedOrder archivedSellingOrder = archivedOrderRepository.findById(sellingOrder.getId()).orElseGet(() -> modelMapper.map(sellingOrder, ArchivedOrder.class));
    archivedBuyingOrder.setRemainingAmount(buyingOrder.getRemainingAmount());
    archivedBuyingOrder.setDateClosing(buyingOrder.getDateClosing());
    archivedSellingOrder.setRemainingAmount(sellingOrder.getRemainingAmount());
    archivedSellingOrder.setDateClosing(sellingOrder.getDateClosing());
    transactionRepository.save(Transaction.builder().date(Optional.ofNullable(buyingOrder.getDateClosing()).orElseGet(sellingOrder::getDateClosing)).amount(amount).unitPrice(pricePerUnit).buyingOrder(archivedBuyingOrder).sellingOrder(archivedSellingOrder).build());
    updateOrder(buyingOrder);
    updateOrder(sellingOrder);
    exchangeMoneyAndStocks(buyingOrder, sellingOrder, amount, pricePerUnit);
    log.info("Transaction with stock " + archivedBuyingOrder.getStock().getAbbreviation() + " between " + archivedBuyingOrder.getUser().getEmail() + " and " + archivedSellingOrder.getUser().getEmail() + " was successfully created.");
}


public void updateOrder(Order order){
    Order ord = orderRepository.findById(order.getId()).orElseThrow(() -> new EntityNotFoundException("Order not found"));
    if (order.getRemainingAmount() == 0) {
        orderRepository.delete(ord);
    } else {
        orderRepository.save(order);
    }
}


public void exchangeMoneyAndStocks(Order buyingOrder,Order sellingOrder,int amount,BigDecimal pricePerUnit){
    Resource sellerResource = resourceRepository.findByUserAndStock(sellingOrder.getUser(), sellingOrder.getStock()).orElseThrow(() -> new EntityNotFoundException("Data in database not consistent."));
    Resource buyerResource = resourceRepository.findByUserAndStock(buyingOrder.getUser(), buyingOrder.getStock()).orElseGet(() -> Resource.builder().amount(0).stock(buyingOrder.getStock()).user(buyingOrder.getUser()).build());
    sellerResource.setAmount(sellerResource.getAmount() - amount);
    sellerResource.getUser().setMoney(sellerResource.getUser().getMoney().add(pricePerUnit.multiply(BigDecimal.valueOf(amount))));
    buyerResource.setAmount(buyerResource.getAmount() + amount);
    buyerResource.getUser().setMoney(buyerResource.getUser().getMoney().subtract(pricePerUnit.multiply(BigDecimal.valueOf(amount))));
    if (sellerResource.getAmount() == 0) {
        resourceRepository.delete(sellerResource);
        User seller = sellerResource.getUser();
        seller.getUserStocks().remove(sellerResource);
        userRepository.save(seller);
    } else {
        resourceRepository.save(sellerResource);
    }
    resourceRepository.save(buyerResource);
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<Transaction> findAllTransactions(Pageable pageable,Specification<Transaction> specification){
    Page<Transaction> transactions = transactionRepository.findAll(specification, pageable);
    if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
        transactions.forEach(transaction -> {
            transaction.getBuyingOrder().setUser(null);
            transaction.getSellingOrder().setUser(null);
        });
    }
    return transactions;
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<Transaction> getTransactionsByOrder(Pageable pageable,Specification<Transaction> specification,Long orderId){
    AllOrders order = allOrdersRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
    Specification<Transaction> withBuyingOrder = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("buyingOrder").get("id"), order.getId());
    Specification<Transaction> withSellingOrder = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("sellingOrder").get("id"), order.getId());
    Specification<Transaction> spec1 = Specification.where(withBuyingOrder).and(specification);
    Specification<Transaction> spec2 = Specification.where(withSellingOrder).and(specification);
    Page<Transaction> transactions = transactionRepository.findAll(Specification.where(spec1).or(spec2), pageable);
    if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
        transactions.forEach(transaction -> {
            transaction.getBuyingOrder().setUser(null);
            transaction.getSellingOrder().setUser(null);
        });
    }
    return transactions;
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public List<Transaction> getTransactionsByStockIdForPricing(Long stockId,Integer amount){
    List<Transaction> transactions = transactionRepository.getTransactionsByStockId(stockId);
    int sumOfAmount = amount;
    for (int i = 0; i < transactions.size(); i++) {
        if (sumOfAmount <= 0) {
            transactions.remove(i);
            i--;
        } else {
            sumOfAmount -= transactions.get(i).getAmount();
        }
    }
    return transactions;
}


}