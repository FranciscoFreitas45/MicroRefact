package com.evolvingreality.onleave.service;
 import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import com.evolvingreality.onleave.model.AbstractAuditingEntity;
public interface EntityService {


public Page<T> getPage(Pageable pageable)
;

public Optional<T> get(Long id)
;

public void save(T entity)
;

public Slice<T> getSlice(Pageable pageable)
;

public void delete(Long id)
;

}