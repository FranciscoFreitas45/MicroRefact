package com.example.demo.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.demo.entity.Order;
public interface OrderService {


public List<S> saveAll(Iterable<S> entities)
;

public void deleteAll(Iterable<? extends Order> entities)
;

public void deleteAllInBatch(Iterable<Order> entities)
;

public long count(Example<S> example)
;

public Optional<S> findOne(Example<S> example)
;

public Order save(Order entity)
;

public void deleteAllByIdInBatch(Iterable<Long> ids)
;

public void deleteAllById(Iterable<? extends Long> ids)
;

public Page<Order> findAll(Pageable pageable)
;

public void delete(Order entity)
;

public S saveAndFlush(S entity)
;

public boolean existsById(Long id)
;

public void deleteInBatch(Iterable<Order> entities)
;

public void flush()
;

public List<Order> findAllById(Iterable<Long> ids)
;

public List<Order> findByUsername(String username)
;

public Order getById(Long id)
;

public Optional<Order> findById(Long id)
;

public Order getOne(Long id)
;

public void deleteById(Long id)
;

public boolean exists(Example<S> example)
;

public List<S> saveAllAndFlush(Iterable<S> entities)
;

}