package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.BatmanRecord;
public interface BatmanRecordRepository extends JpaRepository<BatmanRecord, Integer>{


@Query("FROM BatmanRecord b WHERE b.selectedPlayerId.playerId.playerId = :playerId AND " + "b.selectedPlayerId.matchId.matchTypeId.matchTypeId = :matchType " + "ORDER BY b.selectedPlayerId.matchId.finishDate DESC")
public List<BatmanRecord> findByPlayerIdANDMatchType(Integer playerId,Integer matchType)
;

@Query("FROM BatmanRecord b WHERE b.selectedPlayerId.playerId.playerId = :userId")
public List<BatmanRecord> findByUserId(Integer userId)
;

@Query("FROM BatmanRecord b WHERE b.selectedPlayerId.selectedPlayerId = :selectedPlayerId")
public BatmanRecord getbatmanRecordBtSelectedPlayerId(Integer selectedPlayerId)
;

}