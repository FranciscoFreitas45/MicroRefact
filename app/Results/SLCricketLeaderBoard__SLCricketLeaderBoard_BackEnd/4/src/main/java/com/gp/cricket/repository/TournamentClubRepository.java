package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.Tournament;
import com.gp.cricket.entity.TournamentClub;
public interface TournamentClubRepository extends JpaRepository<TournamentClub, Integer>{


@Query("FROM TournamentClub t WHERE t.clubId = :clubId ORDER By t.tournamentId.startDate DESC")
public List<TournamentClub> findByclubId(Club findClubByClubId)
;

@Query("FROM TournamentClub t WHERE t.clubId = :clubId AND t.tournamentId = :tournamentId")
public TournamentClub findByClubIdAndTournamentId(Club clubId,Tournament tournamentId)
;

@Query("SELECT t.tournamentClubId FROM TournamentClub t WHERE t.tournamentId.tournamentId = :tournamentId AND t.clubId.clubId = :clubId  AND t.status=1  ORDER By t.tournamentId.startDate DESC")
public Integer findIdByTournamentAndClub(Integer tournamentId,Integer clubId)
;

@Query("SELECT t.clubId FROM TournamentClub t WHERE t.tournamentId.tournamentId = :tournamentId AND t.status=1 ORDER By t.tournamentId.startDate DESC")
public List<Club> findClubsByTournamentId(Integer tournamentId)
;

}