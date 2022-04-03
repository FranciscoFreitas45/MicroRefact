package com.project.stockexchangeappbackend.repository;

import com.project.stockexchangeappbackend.DTO.Role;
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
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @DBQueryMeasureTime
    Optional<User> findByEmailIgnoreCase(String email);

    @DBQueryMeasureTime
    Optional<User> findByEmailIgnoreCaseAndIsActiveTrue(String email);

    @Override
    @DBQueryMeasureTime
    <S extends User> S save(S s);

    @Override
    @DBQueryMeasureTime
    Optional<User> findById(Long id);

    @Override
    @DBQueryMeasureTime
    Page<User> findAll(@Nullable Specification<User> specification, Pageable pageable);

    @DBQueryMeasureTime
    Long countByRole(Role role);

}
