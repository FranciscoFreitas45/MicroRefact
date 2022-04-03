package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.Player;
public interface PlayerRepository extends JpaRepository<Player, Integer>{


@Query("FROM Player p WHERE p.playerId = :playerId")
public Player findPlayerById(Integer playerId)
;

@Query("SELECT COUNT(*) FROM Player p WHERE p.clubId = :club AND p.userId.status = 1")
public Integer getNumofPlayerinClub(Club club)
;

@Query("FROM Player p WHERE p.clubId = :clubId  " + "AND p.specialType = :playerType " + "AND p.userId.status = 1 " + "AND p.playerId NOT IN (SELECT b.playerId.playerId FROM BatmanScore b WHERE b.playerId.clubId = :clubId " + "AND b.playerId.specialType = :playerType " + "AND b.matchTypeId.matchTypeId = :matchTypeId) ")
public List<Player> findRemainingBatmenPlayers(Club clubId,Integer playerType,Integer matchTypeId)
;

@Query("FROM Player p WHERE p.clubId = :clubId  " + "AND p.specialType = :playerType " + "AND p.userId.status = 1 " + "AND p.playerId NOT IN (SELECT b.playerId.playerId FROM BallerScore b WHERE b.playerId.clubId = :clubId " + "AND b.playerId.specialType = :playerType " + "AND b.matchTypeId.matchTypeId = :matchTypeId) ")
public List<Player> findRemainingBallerPlayers(Club club,Integer playerType,Integer matchTypeId)
;

@Query("SELECT p.userId.nic FROM Player p WHERE p.clubId = :clubId AND p.userId.status = 1")
public List<String> findPlayerByClubId(Club clubId)
;

public void setPlayerId(Integer playerIdv2,Player playerId);

public Player getPlayerId(Integer playerIdv2);

public void setPlayerId(Integer playerIdv2,Player playerId);

public Player getPlayerId(Integer playerIdv2);

public Player player(Integer playerIdv2);

public void setPlayerId(Integer playerIdv2,Player playerId);

public Player getPlayerId(Integer playerIdv2);

public void setPlayerId(Integer playerIdv2,Player playerId);

public Player getPlayerId(Integer playerIdv2);

}