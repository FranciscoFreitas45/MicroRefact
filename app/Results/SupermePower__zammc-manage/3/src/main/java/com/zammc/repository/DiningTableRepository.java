package com.zammc.repository;
 import com.zammc.domain.table.DiningTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DiningTableRepository extends JpaRepository<DiningTableEntity, Long>, JpaSpecificationExecutor<DiningTableEntity>{


@Query(value = "select * from dining_table where data_status = '0' limit 8", nativeQuery = true)
public List<DiningTableEntity> indexDiningTableData()
;

@Query(value = "select count(tables) from DiningTableEntity tables where tables.tableStatus = '1' and tables.dataStatus = '0'")
public Long queryFreeTableCount()
;

}