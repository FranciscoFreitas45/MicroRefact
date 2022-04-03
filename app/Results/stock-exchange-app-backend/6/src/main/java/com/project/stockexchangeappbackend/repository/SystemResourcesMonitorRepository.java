package com.project.stockexchangeappbackend.repository;
 import com.project.stockexchangeappbackend.entity.SystemResourcesMonitor;
import com.project.stockexchangeappbackend.util.timemeasuring.DBQueryMeasureTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SystemResourcesMonitorRepository extends JpaSpecificationExecutor<SystemResourcesMonitor>, JpaRepository<SystemResourcesMonitor, Long>{


@Override
@DBQueryMeasureTime
public List<SystemResourcesMonitor> findAll(Specification<SystemResourcesMonitor> specification,Sort sort)
;

public SystemResourcesMonitor findFirstByOrderByTimestampAsc()
;

}