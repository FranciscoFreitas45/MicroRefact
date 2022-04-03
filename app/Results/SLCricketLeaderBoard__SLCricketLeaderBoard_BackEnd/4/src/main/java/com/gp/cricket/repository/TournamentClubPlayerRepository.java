package com.gp.cricket.repository;
 import java.util.List;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Player;
import com.gp.cricket.entity.TournamentClub;
import com.gp.cricket.entity.TournamentClubPlayer;
public interface TournamentClubPlayerRepository extends JpaRepository<TournamentClubPlayer, Integer>{


@Query("SELECT t.playerId FROM TournamentClubPlayer t WHERE t.tournamentClubId.tournamentClubId = :tournamentClubId")
public List<Player> findPlayersForMatch(Integer tournamentClubId)
;

@Transactional
@Modifying
@Query("DELETE FROM TournamentClubPlayer t WHERE t.tournamentClubId = :tournamentClubId")
public void deleteByTournamentClubId(TournamentClub tournamentClubId)
;

@Query("SELECT t.playerId FROM TournamentClubPlayer t WHERE t.tournamentClubId = :tournamentClubId")
public List<Player> findByTournamentClubId(TournamentClub tournamentClubId)
;

@Query("FROM TournamentClubPlayer t WHERE " + " t.playerId = :playerId AND " + " (  (t.tournamentClubId.tournamentId.startDate < current_date() AND t.tournamentClubId.tournamentId.endDate >= current_date() ) OR" + " t.tournamentClubId.tournamentId.startDate >= current_date() ) ")
@QueryHints(@QueryHint(name = "MAX_ROW", value = "1"))
public List<TournamentClubPlayer> findPlayerTournamentStatus(Player playerId)
;

}