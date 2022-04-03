package com.example.demo.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.Dao.OrderDAO;
import com.example.demo.entity.Order;
@Service
public class OrderServiceIpml implements OrderService{

@Autowired
 private OrderDAO dao;


@Override
public List<S> saveAll(Iterable<S> entities){
    return dao.saveAll(entities);
}


@Override
public Order save(Order entity){
    return dao.save(entity);
}


@Override
public Optional<S> findOne(Example<S> example){
    return dao.findOne(example);
}


@Override
public long count(){
    return dao.count();
}


@Override
public void deleteAllInBatch(){
    dao.deleteAllInBatch();
}


@Override
public void deleteAll(){
    dao.deleteAll();
}


@Override
public void deleteAllByIdInBatch(Iterable<Long> ids){
    dao.deleteAllByIdInBatch(ids);
}


@Override
public void deleteAllById(Iterable<? extends Long> ids){
    dao.deleteAllById(ids);
}


@Override
public List<S> findAll(Example<S> example,Sort sort){
    return dao.findAll(example, sort);
}


@Override
public void delete(Order entity){
    dao.delete(entity);
}


@Override
public S saveAndFlush(S entity){
    return dao.saveAndFlush(entity);
}


@Override
public boolean existsById(Long id){
    return dao.existsById(id);
}


@Override
public List<Order> findAllById(Iterable<Long> ids){
    return dao.findAllById(ids);
}


@Override
public void flush(){
    dao.flush();
}


@Override
public void deleteInBatch(Iterable<Order> entities){
    dao.deleteInBatch(entities);
}


@Override
public List<Order> findByUsername(String username){
    return dao.findByUsername(username);
}


@Override
public Order getById(Long id){
    return dao.getById(id);
}


@Override
public Optional<Order> findById(Long id){
    return dao.findById(id);
}


@Override
public void deleteById(Long id){
    dao.deleteById(id);
}


@Override
public Order getOne(Long id){
    return dao.getOne(id);
}


@Override
public boolean exists(Example<S> example){
    return dao.exists(example);
}


@Override
public List<S> saveAllAndFlush(Iterable<S> entities){
    return dao.saveAllAndFlush(entities);
}


}