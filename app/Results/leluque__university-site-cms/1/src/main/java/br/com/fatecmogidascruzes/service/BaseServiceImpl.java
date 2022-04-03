package br.com.fatecmogidascruzes.service;
 import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fatecmogidascruzes.data.DAOImpl;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.data.SearchCriteria.Order;
import br.com.fatecmogidascruzes.domain.Entity;
public class BaseServiceImpl {

 private  DAOImpl<O,K> dao;

 private  JpaRepository<O,K> daoJPA;

@SuppressWarnings("unchecked")
public BaseServiceImpl(Object dao) {
    if (!(dao instanceof DAOImpl) || !(dao instanceof JpaRepository)) {
        throw new IllegalArgumentException("The specified DAO is not an instance of the required interfaces.");
    }
    this.dao = (DAOImpl<O, K>) dao;
    this.daoJPA = (JpaRepository<O, K>) dao;
}
public Long countAll(){
    return this.daoJPA.count();
}


public Page<O> getAll(SearchCriteria searchCriteria){
    return this.daoJPA.findAll(this.prepareCriteria(searchCriteria));
}


public void removeLogicallyByKey(K key){
    this.getByKey(key).ifPresent(object -> this.removeLogically(object));
}


public Optional<O> getByHash(UUID hash){
    return this.dao.findByHash(hash);
}


public Optional<O> getByKey(K key){
    return this.daoJPA.findById(key);
}


public void save(Collection<O> objects){
    this.daoJPA.saveAll(objects);
}


public void update(O object){
    this.daoJPA.saveAndFlush(object);
}


public void removeLogicallyByHash(UUID hash){
    this.getByHash(hash).ifPresent(object -> this.removeLogically(object));
}


public Optional<O> getEnabledByHash(UUID hash){
    return this.dao.findByHashAndEnabledTrue(hash);
}


public void removeByKey(K key){
    this.getByKey(key).ifPresent(object -> this.daoJPA.delete(object));
}


public void remove(O object){
    this.daoJPA.delete(object);
}


public Sort prepareSortObject(SearchCriteria searchCriteria){
    Sort.Direction ORDER = Order.ASCENDING == searchCriteria.getOrder() ? Sort.Direction.ASC : Sort.Direction.DESC;
    return new Sort(ORDER, searchCriteria.getOrderBy());
}


public Long countEnabled(){
    return this.dao.countByEnabledTrue();
}


public Page<O> getEnabled(SearchCriteria searchCriteria){
    return this.dao.findByEnabledTrue(this.prepareCriteria(searchCriteria));
}


public PageRequest prepareCriteria(SearchCriteria searchCriteria){
    if (!searchCriteria.hasPagination())
        return null;
    return PageRequest.of(searchCriteria.getInitialRegister(), searchCriteria.getNumberOfRegisters(), prepareSortObject(searchCriteria));
}


public void removeByHash(UUID hash){
    this.getByHash(hash).ifPresent(object -> this.daoJPA.delete(object));
}


public void removeLogically(O object){
    object.setEnabled(false);
    update(object);
}


}