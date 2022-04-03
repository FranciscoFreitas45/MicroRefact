package com.project.stockexchangeappbackend.repository;
 import com.project.stockexchangeappbackend.entity.Stock;
import com.project.stockexchangeappbackend.util.timemeasuring.DBQueryMeasureTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock>{


//@Override
//@DBQueryMeasureTime
//public <S extends Stock> List<S> saveAll(Iterable<Stock> var1)
//;

@Override
@DBQueryMeasureTime
public Optional<Stock> findById(Long id)
;

@DBQueryMeasureTime
public Optional<Stock> findByAbbreviationIgnoreCase(String abbreviation)
;

@Override
@DBQueryMeasureTime
 <S extends Stock> S save(S s);
 
@DBQueryMeasureTime
public Optional<Stock> findByNameIgnoreCase(String name)
;

@DBQueryMeasureTime
public Optional<Stock> findByAbbreviationIgnoreCaseAndIsDeletedFalse(String abbreviation)
;

@DBQueryMeasureTime
public Optional<Stock> findByIdAndIsDeletedFalse(Long id)
;

@Override
@DBQueryMeasureTime
public List<Stock> findAll()
;

}