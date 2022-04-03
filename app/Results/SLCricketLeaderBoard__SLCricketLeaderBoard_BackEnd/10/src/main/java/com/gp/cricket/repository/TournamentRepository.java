package com.gp.cricket.repository;
 import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.Tournament;
public interface TournamentRepository extends JpaRepository<Tournament, Integer>{


@Query("FROM Tournament t WHERE t.endDate < :date ORDER By t.startDate ASC ")
public List<Tournament> findPastTournament(Date date)
;

@Query("FROM Tournament t WHERE t.endDate <:currenDate ORDER By t.startDate ASC")
public List<Tournament> getTournamentHistory(Date currenDate)
;

@Query("FROM Tournament t WHERE t.startDate >:currenDate ORDER By t.startDate ASC")
public List<Tournament> getTournamentByDateOrder(Date currenDate)
;

@Query("FROM Tournament t WHERE t.startDate < :date AND t.endDate > :date ORDER By t.startDate ASC ")
public List<Tournament> findOnGoingTournament(Date date)
;

@Query("FROM Tournament t ORDER BY t.startDate ASC")
public List<Tournament> findAllTournaments()
;

@Query("FROM Tournament t WHERE t.registartionCloseDate >:currenDate ORDER By t.startDate DESC")
public List<Tournament> pendingTournaments(Date currenDate)
;

@Query("FROM Tournament t WHERE t.registartionCloseDate <:currenDate ORDER By t.startDate DESC")
public List<Tournament> closedTournaments(Date currenDate)
;

@Query("FROM Tournament t WHERE t.startDate > :date ORDER By t.startDate ASC ")
public List<Tournament> findUpcomingTournament(Date date)
;

@Query("FROM Tournament t WHERE t.tournamentId = :tournamentId")
public Tournament findByTournamentId(Integer tournamentId)
;

@Query(value = "SELECT * FROM tournament  WHERE current_date() <= registration_closing_date AND " + "tournament_id NOT IN (SELECT tournament_id FROM tournament_club  " + "WHERE club_id = :clubId)", nativeQuery = true)
public List<Tournament> findUpcomingTournamentForClubByClubId(Club clubId)
;

public Tournament getTournamentId(Integer tournamentIdv2);

public void setTournamentId(Integer tournamentIdv2,Tournament tournamentId);

public void setTournamentId(Integer tournamentIdv2,Tournament tournamentId);

public Tournament getTournamentId(Integer tournamentIdv2);

}