package com.zammc.repository;
 import com.zammc.domain.coupon.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, Long>, JpaSpecificationExecutor<CouponEntity>{


}