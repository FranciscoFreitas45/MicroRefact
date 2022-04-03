package com.zammc.repository;
 import com.zammc.domain.recharge.RechargePackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface RechargePackageRepository extends JpaSpecificationExecutor<RechargePackageEntity>, JpaRepository<RechargePackageEntity, Long>{


}