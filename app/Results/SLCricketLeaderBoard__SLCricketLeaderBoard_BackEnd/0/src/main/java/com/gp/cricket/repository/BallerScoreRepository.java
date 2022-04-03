package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.BallerRecord;
import com.gp.cricket.entity.BallerScore;
import com.gp.cricket.entity.BatmanScore;
import com.gp.cricket.entity.Club;
public interface BallerScoreRepository extends JpaRepository<BallerScore, Integer>{


@Query("FROM BallerScore b WHERE b.matchTypeId.matchTypeId = :matchTypeId ORDER BY b.points DESC")
public List<BallerScore> topBallerPlayers(Integer matchTypeId)
;

@Query("FROM BallerScore b WHERE b.playerId.playerId = :playerId AND b.matchTypeId.matchType =:matchType")
public BallerScore getRecordByPlayerIdMatchType(Integer playerId,String matchType)
;

@Query("FROM BallerScore b WHERE b.playerId.clubId = :clubId " + "AND b.matchTypeId.matchTypeId = :matchTypeId " + "AND b.playerId.specialType = :playerType " + "ORDER BY b.points DESC")
public List<BallerScore> findBallerRating(Club clubId,Integer matchTypeId,Integer playerType)
;

@Query("FROM BallerScore b WHERE b.playerId.playerId= :playerId")
public BallerScore getDetailsByPlayerId(Integer playerId)
;

@Query("FROM BallerScore b WHERE b.playerId.playerId = :playerId AND b.matchTypeId.matchTypeId = :matchId ")
public BallerScore findByMatchTypeANDPlayerId(Integer playerId,Integer matchId)
;

public void updateMatchCount(Integer id,Integer mathcCount);

public void setAverageSpeed(Integer id,Double averageSpeed);

public void updateOvers(Integer id,Double overs);

public void updateWickets(Integer id,Integer wickets);

public void updateWideBalls(Integer id,Integer wideBalls);

public void updateNoBalls(Integer id,Integer noBalls);

public void updateNumberOfRunsAgainst(Integer id,Integer numberOfRunsAgainst);

public void updateHatricks(Integer id,Integer hatricks);

public void updatePoints(Integer id,Double points);

public void setBallerScoredId(Integer id,Integer ballerScoredId);

public void setPlayerId(Integer id,Player playerId);

public void setMatchTypeId(Integer id,MatchType matchTypeId);

public void setMatchCount(Integer id,Integer mathcCount);

public void setOvers(Integer id,Double overs);

public void setWickets(Integer id,Integer wickets);

public void setWideBalls(Integer id,Integer wideBalls);

public void setNoBalls(Integer id,Integer noBalls);

public void setNumberOfRunsAgainst(Integer id,Integer numberOfRunsAgainst);

public void setHatricks(Integer id,Integer hatricks);

public void setPoints(Integer id,Double points);

}