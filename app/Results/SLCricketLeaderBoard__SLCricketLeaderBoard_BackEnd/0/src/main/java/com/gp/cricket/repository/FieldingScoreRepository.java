package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.BatmanScore;
import com.gp.cricket.entity.FieldingScore;
public interface FieldingScoreRepository extends JpaRepository<FieldingScore, Integer>{


@Query("FROM FieldingScore b WHERE b.matchTypeId.matchTypeId = :matchTypeId ORDER BY b.points DESC")
public List<FieldingScore> topFieldingPlayers(Integer matchTypeId)
;

@Query("FROM FieldingScore b WHERE b.playerId.playerId = :playerId AND b.matchTypeId.matchType =:matchType")
public FieldingScore getRecordByPlayerIdMatchType(Integer playerId,String matchType)
;

@Query("FROM FieldingScore b WHERE b.playerId.playerId =:playerId")
public FieldingScore getDetailsByPlayerId(Integer playerId)
;

public void updateFieldingPoints(Integer id,Double fieldingPoints2);

public void setFieldingId(Integer id,Integer fieldingId);

public void setPlayerId(Integer id,Player playerId);

public void setMatchTypeId(Integer id,MatchType matchTypeId);

public void setNumberOfCatch(Integer id,Integer numberOfCatch);

public void setFieldingPoints(Integer id,Double fieldingPoints);

}