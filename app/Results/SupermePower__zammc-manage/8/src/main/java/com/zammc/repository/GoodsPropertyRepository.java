package com.zammc.repository;
 import com.zammc.domain.goods.GoodsPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface GoodsPropertyRepository extends JpaRepository<GoodsPropertyEntity, Long>, JpaSpecificationExecutor<GoodsPropertyEntity>{


}