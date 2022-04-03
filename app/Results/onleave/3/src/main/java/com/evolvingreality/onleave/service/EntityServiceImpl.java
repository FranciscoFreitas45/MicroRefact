package com.evolvingreality.onleave.service;
 import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evolvingreality.onleave.model.AbstractAuditingEntity;
public class EntityServiceImpl implements EntityService<T>{

 protected  Logger log;

 protected  JpaRepository<T,Long> jpaRepository;

public EntityServiceImpl(final JpaRepository<T, Long> jpaRepository) {
    this.jpaRepository = jpaRepository;
}
@Override
public Page<T> getPage(Pageable pageable){
    return jpaRepository.findAll(pageable);
}


@Override
public Optional<T> get(Long id){
    return Optional.of(jpaRepository.findOne(id));
}


@Override
public void save(T entity){
    jpaRepository.save(entity);
}


@Override
public Slice<T> getSlice(Pageable pageable){
    return jpaRepository.findAll(pageable);
}


@Override
public void delete(Long id){
    jpaRepository.delete(id);
}


}