package com.example.demo.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.demo.entity.Product;
public interface ProductService {


public List<S> saveAll(Iterable<S> entities)
;

public void deleteAll(Iterable<? extends Product> entities)
;

public void deleteAllInBatch(Iterable<Product> entities)
;

public long count(Example<S> example)
;

public Optional<S> findOne(Example<S> example)
;

public Product save(Product entity)
;

public void deleteAllByIdInBatch(Iterable<Integer> ids)
;

public void deleteAllById(Iterable<? extends Integer> ids)
;

public Product findByIdproduct(Integer id)
;

public Page<Product> findAll(Pageable pageable)
;

public void delete(Product entity)
;

public S saveAndFlush(S entity)
;

public boolean existsById(Integer id)
;

public void deleteInBatch(Iterable<Product> entities)
;

public void flush()
;

public List<Product> findAllById(Iterable<Integer> ids)
;

public Product getById(Integer id)
;

public Optional<Product> findById(Integer id)
;

public Product getOne(Integer id)
;

public void deleteById(Integer id)
;

public boolean exists(Example<S> example)
;

public List<S> saveAllAndFlush(Iterable<S> entities)
;

public Page<Product> findByCategoryId(String cid,Pageable pageable)
;

}