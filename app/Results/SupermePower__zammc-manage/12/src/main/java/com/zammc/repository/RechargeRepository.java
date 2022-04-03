package com.zammc.repository;
 import com.zammc.domain.recharge.RechargeOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface RechargeRepository extends JpaSpecificationExecutor<RechargeOrderEntity>, JpaRepository<RechargeOrderEntity, Long>{


}