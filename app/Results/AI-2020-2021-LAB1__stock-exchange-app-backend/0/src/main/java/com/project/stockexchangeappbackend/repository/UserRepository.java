package com.project.stockexchangeappbackend.repository;
 import com.project.stockexchangeappbackend.entity.Role;
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
public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, Long>{


@DBQueryMeasureTime
public Optional<User> findByEmailIgnoreCase(String email)
;

@Override
@DBQueryMeasureTime
public Optional<User> findById(Long id)
;

@Override
@DBQueryMeasureTime
public S save(S s)
;

@Override
@DBQueryMeasureTime
public Page<User> findAll(Specification<User> specification,Pageable pageable)
;

@DBQueryMeasureTime
public Optional<User> findByEmailIgnoreCaseAndIsActiveTrue(String email)
;

@DBQueryMeasureTime
public Long countByRole(Role role)
;

}