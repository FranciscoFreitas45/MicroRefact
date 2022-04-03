package com.gp.cricket.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.TournamentClub;
import com.gp.cricket.entity.TournamentClubCaptain;
public interface TournamentClubCaptainRepository extends JpaRepository<TournamentClubCaptain, Integer>{


@Query("FROM TournamentClubCaptain t WHERE t.tournamentClubId = :tournamentClub")
public TournamentClubCaptain findByTournamentClubId(TournamentClub tournamentClub)
;

}