package com.csquard.mregister.repository;
 import org.springframework.stereotype.Repository;
import com.csquard.mregister.model.SalesRegion;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface SalesRegionRepository extends JpaRepository<SalesRegion, Long>{


}