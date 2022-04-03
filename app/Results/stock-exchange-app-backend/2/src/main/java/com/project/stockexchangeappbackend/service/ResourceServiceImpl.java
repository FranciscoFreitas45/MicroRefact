package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.dto.MoveStockDTO;
import com.project.stockexchangeappbackend.dto.OwnerDTO;
import com.project.stockexchangeappbackend.dto.ResourceDTO;
import com.project.stockexchangeappbackend.dto.UserDTO;
import com.project.stockexchangeappbackend.exception.InvalidInputDataException;
import com.project.stockexchangeappbackend.Interface.OrderRepository;
import com.project.stockexchangeappbackend.Interface.ResourceRepository;
import com.project.stockexchangeappbackend.Interface.StockRepository;
import com.project.stockexchangeappbackend.Interface.UserRepository;
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
import javax.persistence.criteria.Join;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;
import com.project.stockexchangeappbackend.Interface.ResourceRepository;
import com.project.stockexchangeappbackend.Interface.OrderRepository;
import com.project.stockexchangeappbackend.Interface.UserRepository;
import com.project.stockexchangeappbackend.Interface.StockRepository;
import com.project.stockexchangeappbackend.DTO.Resource;
import com.project.stockexchangeappbackend.DTO.User;
import com.project.stockexchangeappbackend.DTO.Stock;
import com.project.stockexchangeappbackend.DTO.Role;
import com.project.stockexchangeappbackend.DTO.Order;
@Service
@Slf4j
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService{

 private  ResourceRepository resourceRepository;

 private  OrderRepository orderRepository;

 private  UserRepository userRepository;

 private  StockRepository stockRepository;

 private  ModelMapper modelMapper;


@Override
@LogicBusinessMeasureTime
@Transactional
public void moveStock(Long stockId,MoveStockDTO moveStock){
    Stock stock = stockRepository.findByIdAndIsDeletedFalse(stockId).orElseThrow(() -> new EntityNotFoundException("Stock not found"));
    Optional<User> source = userRepository.findById(moveStock.getUserSource().getId());
    Optional<User> destination = userRepository.findById(moveStock.getUserDestination().getId());
    Optional<Resource> sourceResource = source.isPresent() ? resourceRepository.findByUserAndStock(source.get(), stock) : Optional.empty();
    validateMoveStock(moveStock, stock, source, destination, sourceResource);
    int sourceNewAmount = sourceResource.get().getAmount() - moveStock.getAmount();
    if (sourceNewAmount > 0) {
        sourceResource.get().setAmount(sourceNewAmount);
        resourceRepository.save(sourceResource.get());
    } else {
        resourceRepository.delete(sourceResource.get());
    }
    Resource destinationResource = resourceRepository.findByUserAndStock(destination.get(), stock).orElseGet(() -> Resource.builder().user(destination.get()).stock(stock).amount(0).build());
    destinationResource.setAmount(destinationResource.getAmount() + moveStock.getAmount());
    resourceRepository.save(destinationResource);
    log.info("Stock " + stock.getAbbreviation() + " was successfully moved from " + source.get().getEmail() + " to " + destination.get().getEmail() + ".");
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<OwnerDTO> getStockOwners(Pageable pageable,Specification<Resource> specification,Long stockId){
    Stock stock = stockRepository.findByIdAndIsDeletedFalse(stockId).orElseThrow(() -> new EntityNotFoundException("Stock not found"));
    Specification<Resource> byStock = (root, criteriaQuery, criteriaBuilder) -> {
        Join<Resource, Stock> stockJoin = root.join("stock");
        return criteriaBuilder.equal(stockJoin.get("id"), stock.getId());
    };
    return resourceRepository.findAll(Specification.where(byStock).and(specification), pageable).map(resource -> OwnerDTO.builder().user(modelMapper.map(resource.getUser(), UserDTO.class)).amount(resource.getAmount()).build());
}


public void validateMoveStock(MoveStockDTO moveStock,Stock stock,Optional<User> source,Optional<User> destination,Optional<Resource> resource){
    Map<String, List<String>> errors = new HashMap<>();
    if (source.isEmpty()) {
        errors.put("userSource", List.of("User not found."));
    }
    if (destination.isEmpty()) {
        errors.put("destinationSource", List.of("User not found."));
    }
    if (source.isPresent() && destination.isPresent()) {
        if (source.get().getId().equals(destination.get().getId())) {
            errors.putIfAbsent("destinationSource", new ArrayList<>());
            errors.get("destinationSource").add("Source and destination users must be different users.");
        }
        if (destination.get().getRole().equals(Role.ADMIN)) {
            errors.putIfAbsent("destinationSource", new ArrayList<>());
            errors.get("destinationSource").add("Destination user cannot be admin.");
        }
        if (!stock.getTag().equals(source.get().getTag()) || !stock.getTag().equals(destination.get().getTag())) {
            errors.put("stock", List.of("Both users and stock must be tagged using the same tag."));
        }
        int sellingAmountOfStock = orderRepository.findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(stock, source.get(), OrderType.SELLING_ORDER, OffsetDateTime.now(ZoneId.systemDefault())).stream().mapToInt(Order::getRemainingAmount).sum();
        if (resource.isEmpty() || resource.get().getAmount() - sellingAmountOfStock < moveStock.getAmount()) {
            errors.put("amount", List.of("Source user doesn't possess enough free amount of stock."));
        }
    }
    if (!errors.isEmpty()) {
        throw new InvalidInputDataException("Data validation", errors);
    }
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<ResourceDTO> getUsersResources(Pageable pageable,Specification<Resource> specification,Long userId){
    User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    return findResources(pageable, specification, user.getEmail());
}


public Page<ResourceDTO> findResources(Pageable pageable,Specification<Resource> specification,String username){
    Specification<Resource> userIsPrincipal = (root, criteriaQuery, criteriaBuilder) -> {
        Join<Resource, User> owner = root.join("user");
        return criteriaBuilder.equal(owner.get("email"), username);
    };
    return resourceRepository.findAll(Specification.where(userIsPrincipal).and(specification), pageable).map(resource -> {
        ResourceDTO resourceDTO = modelMapper.map(resource, ResourceDTO.class);
        int sellingAmountOfStock = orderRepository.findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(resource.getStock(), resource.getUser(), OrderType.SELLING_ORDER, OffsetDateTime.now(ZoneId.systemDefault())).stream().mapToInt(Order::getRemainingAmount).sum();
        resourceDTO.setId(resource.getStock().getId());
        resourceDTO.setAmountAvailableForSale(resourceDTO.getAmount() - sellingAmountOfStock);
        return resourceDTO;
    });
}


@Override
@LogicBusinessMeasureTime
@Transactional(readOnly = true)
public Page<ResourceDTO> getOwnedResources(Pageable pageable,Specification<Resource> resourceSpecification){
    String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return findResources(pageable, resourceSpecification, principal);
}


}