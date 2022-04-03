package com.project.stockexchangeappbackend.repository;
 import com.project.stockexchangeappbackend.entity.Tag;
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
public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag>{


@DBQueryMeasureTime
public Optional<Tag> findByNameIgnoreCase(String name)
;

@Override
@DBQueryMeasureTime
public S save(S s)
;

@Override
@DBQueryMeasureTime
public Page<Tag> findAll(Specification<Tag> specification,Pageable pageable)
;

@Override
@DBQueryMeasureTime
public void delete(Tag tag)
;

}