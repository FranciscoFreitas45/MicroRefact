package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Player;
import com.gp.cricket.entity.SelectedPlayer;
public interface SelectedPlayerRepository extends JpaRepository<SelectedPlayer, Integer>{


@Query("SELECT s FROM SelectedPlayer s WHERE s.matchId.matchId =:matchId AND s.playerId.clubId.clubId =:clubId AND s.state=0")
public List<SelectedPlayer> selectedPlayersMatchIdClubIdNotUpdated(Integer matchId,Integer clubId)
;

@Query("SELECT s FROM SelectedPlayer s WHERE s.matchId.matchId =:matchId AND s.playerId.clubId.clubId =:clubId")
public List<SelectedPlayer> selectedPlayersMatchIdClubId(Integer matchId,Integer clubId)
;

@Query("SELECT s FROM SelectedPlayer s WHERE s.matchId.matchId =:matchId AND s.playerId.clubId.clubId =:clubId AND s.state=1")
public List<SelectedPlayer> selectedPlayersMatchIdClubIdUpdated(Integer matchId,Integer clubId)
;

@Query("SELECT s.playerId FROM SelectedPlayer s WHERE s.matchId.matchId =:matchId AND s.playerId.clubId.clubId =:clubId")
public List<Player> selectedPlayersForMatch(Integer matchId,Integer clubId)
;

public SelectedPlayer getSelectedPlayerId(Integer selectedPlayerIdv2);

public void setSelectedPlayerId(Integer selectedPlayerIdv2,SelectedPlayer selectedPlayerId);

public void setSelectedPlayerId(Integer selectedPlayerIdv2,SelectedPlayer selectedPlayerId);

public SelectedPlayer getSelectedPlayerId(Integer selectedPlayerIdv2);

public void setSelectedPlayerId(Integer selectedPlayerIdv2,SelectedPlayer selectedPlayerId);

public SelectedPlayer getSelectedPlayerId(Integer selectedPlayerIdv2);

}