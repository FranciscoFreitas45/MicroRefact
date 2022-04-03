package com.project.stockexchangeappbackend.repository;

import com.project.stockexchangeappbackend.entity.Resource;
import com.project.stockexchangeappbackend.DTO.Stock;
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
public interface ResourceRepository extends JpaRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {

    @Override
    @DBQueryMeasureTime
    <S extends Resource> S save(S s);

    @Override
    @DBQueryMeasureTime
    Optional<Resource> findById(Long id);

    @Override
    @DBQueryMeasureTime
    Page<Resource> findAll(@Nullable Specification<Resource> var1, Pageable var2);

    @DBQueryMeasureTime
    Optional<Resource> findByUserAndStock(User user, Stock stock);

    @DBQueryMeasureTime
    void deleteByStock(Stock stock);

    @Override
    @DBQueryMeasureTime
    void deleteById(Long id);

    @DBQueryMeasureTime
    Long countByUser(User user);

}
