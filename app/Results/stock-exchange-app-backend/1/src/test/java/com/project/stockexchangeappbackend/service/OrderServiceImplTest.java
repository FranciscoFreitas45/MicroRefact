package com.project.stockexchangeappbackend.service;

import com.project.stockexchangeappbackend.DTO.*;
import com.project.stockexchangeappbackend.entity.*;
import com.project.stockexchangeappbackend.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

   

    public static ArchivedOrder createSellingArchivedOrder(Long id, Integer amount, BigDecimal price,
                                                           OffsetDateTime dateExpiration, User user, Stock stock) {
        return ArchivedOrder.builder()
                .id(id).amount(amount).remainingAmount(0)
                .dateCreation(OffsetDateTime.now()).dateExpiration(dateExpiration)
                .orderType(OrderType.SELLING_ORDER).priceType(PriceType.EQUAL).price(price)
                .stock(stock).user(user)
                .build();
    }

    public static ArchivedOrder createBuyingArchivedOrder(Long id, Integer amount, BigDecimal price,
                                                          OffsetDateTime dateExpiration, User user, Stock stock) {
        return ArchivedOrder.builder()
                .id(id).amount(amount).remainingAmount(0)
                .dateCreation(OffsetDateTime.now()).dateExpiration(dateExpiration)
                .orderType(OrderType.BUYING_ORDER).priceType(PriceType.EQUAL).price(price)
                .stock(stock).user(user)
                .build();
    }
}
