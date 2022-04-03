package com.yalcin.repository;
 import com.yalcin.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AttemptRepository extends JpaRepository<Attempt, String>{


public Boolean existsByIp(String username)
;

}