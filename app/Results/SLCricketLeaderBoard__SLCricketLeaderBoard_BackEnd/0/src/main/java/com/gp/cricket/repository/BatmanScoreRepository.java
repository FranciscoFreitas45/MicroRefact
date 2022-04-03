package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.BallerScore;
import com.gp.cricket.entity.BatmanScore;
import com.gp.cricket.entity.Club;
public interface BatmanScoreRepository extends JpaRepository<BatmanScore, Integer>{


@Query("FROM BatmanScore b WHERE b.playerId.clubId = :clubId " + "AND b.matchTypeId.matchTypeId = :matchId " + "AND b.playerId.specialType = :playerType " + "ORDER BY b.points DESC")
public List<BatmanScore> findBatmenRating(Club club,Integer matchId,Integer playerType)
;

@Query("FROM BatmanScore b WHERE b.matchTypeId.matchTypeId = :matchTypeId ORDER BY b.points DESC")
public List<BatmanScore> topBatmanPlayers(Integer matchTypeId)
;

@Query("FROM BatmanScore b WHERE b.playerId.playerId = :playerId AND b.matchTypeId.matchType =:matchType")
public BatmanScore getRecordByPlayerIdMatchType(Integer playerId,String matchType)
;

@Query("FROM BatmanScore b WHERE b.playerId.playerId = :playerId")
public BatmanScore getDetailsByPlayerId(Integer playerId)
;

@Query("FROM BatmanScore b WHERE b.playerId.playerId = :playerId AND b.matchTypeId.matchTypeId = :matchId ")
public BatmanScore findByMatchTypeANDPlayerId(Integer playerId,Integer matchId)
;

public void updateMatchCount(Integer id,Integer matchCount);

public void setStrikeRate(Integer id,Double strikeRate);

public void updateFacedBalls(Integer id,Integer facedBalls);

public void updateFour(Integer id,Integer four);

public void updateSix(Integer id,Integer six);

public void updateRuns(Integer id,Integer runs);

public void updatePoints(Integer id,Double points);

public void updateHalfCenturies(Integer id,Integer halfCenturies);

public void updateCenturies(Integer id,Integer centuries);

public void updateDoubleCenturies(Integer id,Integer doubleCenturies);

public void updateTripleCenturies(Integer id,Integer tripleCenturies);

public void updateFourbleCenturies(Integer id,Integer fourbleCenturies);

public void updateFivebleCenturies(Integer id,Integer fivebleCenturies);

public void updateNotOut(Integer id,Integer notOut);

public void setBatmanScoreId(Integer id,Integer batmanScoreId);

public void setPlayerId(Integer id,Player playerId);

public void setMatchTypeId(Integer id,MatchType matchTypeId);

public void setMatchCount(Integer id,Integer matchCount);

public void setFacedBalls(Integer id,Integer facedBalls);

public void setFour(Integer id,Integer four);

public void setSix(Integer id,Integer six);

public void setRuns(Integer id,Integer runs);

public void setPoints(Integer id,Double points);

public void setHalfCenturies(Integer id,Integer halfCenturies);

public void setCenturies(Integer id,Integer centuries);

public void setDoubleCenturies(Integer id,Integer doubleCenturies);

public void setTripleCenturies(Integer id,Integer tripleCenturies);

public void setFourbleCenturies(Integer id,Integer fourbleCenturies);

public void setFivebleCenturies(Integer id,Integer fivebleCenturies);

public void setNotOut(Integer id,Integer notOut);

}