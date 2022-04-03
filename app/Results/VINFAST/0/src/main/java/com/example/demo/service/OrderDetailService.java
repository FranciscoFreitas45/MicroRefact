package com.example.demo.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Report;
public interface OrderDetailService {


public List<S> saveAll(Iterable<S> entities)
;

public List<OrderDetail> findByOrderId(long id)
;

public void deleteAll(Iterable<? extends OrderDetail> entities)
;

public void deleteAllInBatch(Iterable<OrderDetail> entities)
;

public long count(Example<S> example)
;

public Optional<S> findOne(Example<S> example)
;

public OrderDetail save(OrderDetail entity)
;

public void deleteAllByIdInBatch(Iterable<Long> ids)
;

public void deleteAllById(Iterable<? extends Long> ids)
;

public Page<OrderDetail> findAll(Pageable pageable)
;

public void delete(OrderDetail entity)
;

public S saveAndFlush(S entity)
;

public boolean existsById(Long id)
;

public void deleteInBatch(Iterable<OrderDetail> entities)
;

public void flush()
;

public List<OrderDetail> findAllById(Iterable<Long> ids)
;

public OrderDetail getById(Long id)
;

public Optional<OrderDetail> findById(Long id)
;

public OrderDetail getOne(Long id)
;

public void deleteById(Long id)
;

public boolean exists(Example<S> example)
;

public List<Report> revenueCategory()
;

public List<S> saveAllAndFlush(Iterable<S> entities)
;

}