package com.csquard.mregister.repository;
 import java.util;
import org.springframework.data.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.csquard.mregister.model.Agent;
import com.csquard.mregister.model.Tdr;
@Repository
public interface AgentRepository extends JpaRepository<Agent, Long>{


public long countBySalesAreaId(Long sales_area_id)
;

public Page<Agent> findByCreatedBy(Long agent_no,Pageable pageable)
;

public List<Agent> findBySalesRegionId(Long salesRegionId)
;

public long countByCreatedBy(Long id)
;

public long countBySalesRegionId(Long salesRegionId)
;

public Long countByTdrId(Tdr tdr_id)
;

@Query(nativeQuery = true, value = "SELECT `agent_no`, `created_at`, `updated_at`, `created_by`, `updated_by`, `address`, `device_type`, `id_attachment`, `id_no`, `imei_no`, `location`, `mobile`," + " `signature`, `signed_contact`, `signed_name`, `town`, `sales_area_id`, `sales_region_id`, `tdr_id` FROM `mregister_test`.`agents`")
public List<Agent> findAllAgents()
;

public List<Agent> findBySalesAreaId(Long salesAreaId)
;

public List<Agent> findByTdrId(long tdrId)
;

}