package com.project.stockexchangeappbackend.service;

import com.project.stockexchangeappbackend.dto.*;
import com.project.stockexchangeappbackend.entity.*;
import com.project.stockexchangeappbackend.repository.*;
import org.junit.jupiter.api.BeforeEach;
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

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;


import static com.project.stockexchangeappbackend.service.TagServiceImplTest.getTagsList;
import static com.project.stockexchangeappbackend.service.TagServiceImplTest.assertTag;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

   
    @BeforeEach
    void setup() {
        setStockList();
    }
    public static void assertStock(Stock output, Stock expected) {
        assertAll(() -> assertEquals(expected.getId(), output.getId()),
                () -> assertEquals(expected.getName(), output.getName()),
                () -> assertEquals(expected.getAbbreviation(), output.getAbbreviation()),
                () -> assertEquals(expected.getCurrentPrice(), output.getCurrentPrice()),
                () -> assertEquals(expected.getAmount(), output.getAmount()),
                () -> assertTag(expected.getTag(), output.getTag()));
    }

    public static List<Stock> stocks;

    public static List<Stock> getStocksList() {
        if (stocks == null) {
            setStockList();
        }
        return stocks;
    }

    private static void setStockList() {
        var tags = getTagsList();
        stocks = Arrays.asList(
                Stock.builder()
                        .id(1L).name("WiG20").abbreviation("W20").amount(10000).currentPrice(BigDecimal.ZERO)
                        .tag(tags.get(0)).isDeleted(Boolean.FALSE).resources(new ArrayList<>())
                        .build(),
                Stock.builder()
                        .id(2L).name("WiG30").abbreviation("W30").amount(10000).currentPrice(BigDecimal.ZERO)
                        .tag(tags.get(0)).isDeleted(Boolean.FALSE).resources(new ArrayList<>())
                        .build());
    }

    
}
