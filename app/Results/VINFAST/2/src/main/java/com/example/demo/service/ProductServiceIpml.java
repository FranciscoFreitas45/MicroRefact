package com.example.demo.service;
 import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.Dao.ProductDAO;
import com.example.demo.entity.Product;
@Service
public class ProductServiceIpml implements ProductService{

@Autowired
 private ProductDAO dao;


@Override
public List<S> saveAll(Iterable<S> entities){
    return dao.saveAll(entities);
}


@Override
public Product save(Product entity){
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
public void deleteAllByIdInBatch(Iterable<Integer> ids){
    dao.deleteAllByIdInBatch(ids);
}


@Override
public void deleteAllById(Iterable<? extends Integer> ids){
    dao.deleteAllById(ids);
}


@Override
public Product findByIdproduct(Integer id){
    Product product = dao.findById(id).get();
    if (product.getId() == id) {
        return product;
    }
    return null;
}


@Override
public List<S> findAll(Example<S> example,Sort sort){
    return dao.findAll(example, sort);
}


@Override
public void delete(Product entity){
    dao.delete(entity);
}


@Override
public S saveAndFlush(S entity){
    return dao.saveAndFlush(entity);
}


@Override
public boolean existsById(Integer id){
    return dao.existsById(id);
}


@Override
public List<Product> findAllById(Iterable<Integer> ids){
    return dao.findAllById(ids);
}


@Override
public void flush(){
    dao.flush();
}


@Override
@SuppressWarnings("deprecation")
public void deleteInBatch(Iterable<Product> entities){
    dao.deleteInBatch(entities);
}


@Override
public Product getById(Integer id){
    return dao.getById(id);
}


@Override
public Optional<Product> findById(Integer id){
    return dao.findById(id);
}


@Override
public void deleteById(Integer id){
    dao.deleteById(id);
}


@Override
@SuppressWarnings("deprecation")
public Product getOne(Integer id){
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


@Override
public Page<Product> findByCategoryId(String cid,Pageable pageable){
    return dao.findByCategoryId(cid, pageable);
}


}