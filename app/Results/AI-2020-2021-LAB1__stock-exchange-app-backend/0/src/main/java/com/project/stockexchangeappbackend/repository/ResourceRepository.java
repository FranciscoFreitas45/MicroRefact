package com.project.stockexchangeappbackend.repository;
 import com.project.stockexchangeappbackend.entity.Resource;
import com.project.stockexchangeappbackend.entity.Stock;
import com.project.stockexchangeappbackend.entity.User;
import com.project.stockexchangeappbackend.util.timemeasuring.DBQueryMeasureTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface ResourceRepository extends JpaSpecificationExecutor<Resource>, JpaRepository<Resource, Long>{


@DBQueryMeasureTime
public void deleteByStock(Stock stock)
;

@DBQueryMeasureTime
public Long countByUser(User user)
;

@DBQueryMeasureTime
public Optional<Resource> findByUserAndStock(User user,Stock stock)
;

@Override
@DBQueryMeasureTime
public Optional<Resource> findById(Long id)
;

@Override
@DBQueryMeasureTime
public S save(S s)
;

@Override
@DBQueryMeasureTime
public void deleteById(Long id)
;

@Override
@DBQueryMeasureTime
public Page<Resource> findAll(Specification<Resource> var1,Pageable var2)
;

}