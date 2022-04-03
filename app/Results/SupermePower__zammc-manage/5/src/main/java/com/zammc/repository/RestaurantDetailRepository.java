package com.zammc.repository;
 import com.zammc.domain.restaurant.RestaurantPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface RestaurantDetailRepository extends JpaSpecificationExecutor<RestaurantPropertyEntity>, JpaRepository<RestaurantPropertyEntity, Long>{


}