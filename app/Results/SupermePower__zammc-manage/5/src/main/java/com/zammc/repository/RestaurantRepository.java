package com.zammc.repository;
 import com.zammc.domain.restaurant.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long>, JpaSpecificationExecutor<RestaurantEntity>{


}