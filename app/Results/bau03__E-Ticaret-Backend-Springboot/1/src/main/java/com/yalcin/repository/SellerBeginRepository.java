package com.yalcin.repository;
 import com.yalcin.entity.SellerBegin;
import com.yalcin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SellerBeginRepository extends JpaRepository<SellerBegin, Integer>{


}