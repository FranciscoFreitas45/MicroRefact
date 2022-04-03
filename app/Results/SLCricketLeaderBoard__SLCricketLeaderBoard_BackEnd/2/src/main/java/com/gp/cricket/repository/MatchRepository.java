package com.gp.cricket.repository;
 import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.Match;
import com.gp.cricket.entity.MatchType;
public interface MatchRepository extends JpaRepository<Match, Integer>{


@Query("FROM Match m WHERE m.finishDate <:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.matchTypeId.matchTypeId = 3  AND m.state =1 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesPlayedUpdatedTTwenty(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE m.finishDate <:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.matchTypeId.matchTypeId = 1  AND m.state =1 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesPlayedUpdatedOneDay(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE m.finishDate >:currentDate AND m.tournamentId.tournamentId = :tournamentId ORDER BY m.tournementRound ASC")
public List<Match> getToPlayMatches(LocalDate currentDate,Integer tournamentId)
;

@Query("FROM Match m WHERE m.finishDate >:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.matchTypeId.matchTypeId = 3 AND m.state = 0 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesUpcommingTTwenty(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE m.finishDate <:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.matchTypeId.matchTypeId = 3  AND m.state = 0 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesPlayedTTwenty(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("SELECT COUNT(*) FROM Match m WHERE (m.clubOneId = :clubId OR m.clubTwoId = :clubId) AND  m.matchTypeId = :matchTypeId")
public Integer getNumOfMatchPlayed(Integer clubId,MatchType matchType)
;

@Query("FROM Match m WHERE m.finishDate <:currentDate AND m.tournamentId.tournamentId = :tournamentId ORDER BY m.tournementRound ASC")
public List<Match> getPlayedMatches(LocalDate currentDate,Integer tournamentId)
;

@Query("FROM Match m WHERE m.finishDate <:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.matchTypeId.matchTypeId = 2  AND m.state = 0 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesPlayed34Days(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE m.finishDate <:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.matchTypeId.matchTypeId = 1 AND m.state = 0 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesPlayedOneDay(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE m.tournamentId.tournamentId = :tournamentId ORDER BY m.tournementRound ASC")
public List<Match> findMatchesByTournamentId(Integer tournamentId)
;

@Query("FROM Match m WHERE m.finishDate <:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.state = 0 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesPlayed(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE m.tournamentId.tournamentId = :tournamentId")
public List<Match> findBytournamentId(Integer tournamentId)
;

@Query("FROM Match m WHERE m.tournamentId.tournamentId = :tournamentId  ORDER BY m.startDate ASC")
public List<Match> getMatchForpublic(Integer tournamentId)
;

@Query("FROM Match m WHERE m.finishDate >:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.matchTypeId.matchTypeId = 1 AND m.state = 0 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesUpcommingOneDay(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE (m.clubOneId = :clubId OR m.clubTwoId = :clubId) AND m.finishDate < current_date() ORDER BY m.finishDate DESC ")
public List<Match> findPlayedMatchesByClubId(Integer clubId)
;

@Query("FROM Match m WHERE m.finishDate <:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.state =1 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesPlayedUpdated(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE (m.startDate <= :currentDate AND m.finishDate >= :currentDate) AND m.refereeId.userId.userId =:refId ORDER BY m.startDate ASC ")
public List<Match> getLiveMatchForReferee(Integer refId,LocalDate currentDate)
;

@Query("FROM Match m WHERE m.matchId = :matchId")
public Match findMatchById(Integer matchId)
;

@Query("FROM Match m WHERE m.finishDate >:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.state = 0 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesUpcomming(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE m.finishDate >:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.matchTypeId.matchTypeId = 2 AND m.state = 0 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesUpcomming34Days(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

@Query("FROM Match m WHERE (m.clubOneId = :clubId OR m.clubTwoId = :clubId) AND m.startDate >= current_date() ORDER BY m.startDate ASC ")
public List<Match> findUpcomingMatchesByClubId(Integer clubId)
;

@Query("FROM Match m WHERE m.finishDate <:currentDate AND m.tournamentId.tournamentId = :tournamentId AND m.refereeId.userId.userId =:refereeId AND m.matchTypeId.matchTypeId = 2  AND m.state =1 ORDER BY m.tournementRound ASC")
public List<Match> getRefereeMatchesPlayedUpdated34Days(LocalDate currentDate,Integer tournamentId,Integer refereeId)
;

public Match getMatchId(Integer matchIdv2);

public void setMatchId(Integer matchIdv2,Match matchId);

}