package com.gp.cricket.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.BatmanRecord;
import com.gp.cricket.entity.FieldingRecord;
public interface FieldingRecordRepository extends JpaRepository<FieldingRecord, Integer>{


@Query("FROM FieldingRecord b WHERE b.selectedPlayerId.selectedPlayerId = :selectedPlayerId")
public FieldingRecord getFieldingRecordBtSelectedPlayerId(Integer selectedPlayerId)
;

}