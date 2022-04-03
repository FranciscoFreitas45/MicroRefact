package org.sdrc.devinfo.repository.springdatajpa;
 import org.sdrc.devinfo.domain.UtSubgroupTypeEn;
import org.sdrc.devinfo.repository.UtSubgroupTypeEnRepository;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DevinfoUtSubgroupTypeEnRepository extends UtSubgroupTypeEnRepository, JpaRepository<UtSubgroupTypeEn, Integer>{


}