package com.yalcin.repository;
 import com.yalcin.entity.Purse;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PurseRepository extends JpaRepository<Purse, Integer>{


public Purse findAllByUser_Id(Integer userId)
;

}