package com.zammc.repository;
 import com.zammc.domain.reserve.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface ReserveRepository extends JpaRepository<ReserveEntity, Long>, JpaSpecificationExecutor<ReserveEntity>{


}