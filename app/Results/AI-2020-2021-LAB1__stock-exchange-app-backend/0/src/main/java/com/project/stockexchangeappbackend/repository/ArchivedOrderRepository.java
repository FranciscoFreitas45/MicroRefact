package com.project.stockexchangeappbackend.repository;
 import com.project.stockexchangeappbackend.entity.ArchivedOrder;
import com.project.stockexchangeappbackend.util.timemeasuring.DBQueryMeasureTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface ArchivedOrderRepository extends JpaRepository<ArchivedOrder, Long>{


@Override
@DBQueryMeasureTime
public List<S> saveAll(Iterable<S> var1)
;

@Override
@DBQueryMeasureTime
public Optional<ArchivedOrder> findById(Long id)
;

@DBQueryMeasureTime
public S save(S s)
;

}