package com.puffride.demo.repository;
 import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import com.puffride.demo.entity.Rider;
@Repository
public interface RiderRepository extends JpaRepository<Rider, Long>{


}