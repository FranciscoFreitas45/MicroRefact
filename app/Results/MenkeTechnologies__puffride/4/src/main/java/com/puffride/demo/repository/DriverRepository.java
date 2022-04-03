package com.puffride.demo.repository;
 import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import com.puffride.demo.entity.Driver;
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{


}