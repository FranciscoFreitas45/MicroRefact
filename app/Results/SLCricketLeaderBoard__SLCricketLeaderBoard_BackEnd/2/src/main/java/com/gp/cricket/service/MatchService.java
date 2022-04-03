package com.gp.cricket.service;
 import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.ClubRanking;
import com.gp.cricket.entity.Match;
import com.gp.cricket.entity.MatchType;
import com.gp.cricket.entity.Player;
import com.gp.cricket.entity.SelectedPlayer;
import com.gp.cricket.entity.Tournament;
import com.gp.cricket.entity.TournamentClub;
import com.gp.cricket.entity.TournamentClubCaptain;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.MatchRepository;
import com.gp.cricket.repository.MatchTypeRepository;
import com.gp.cricket.repository.RefereeRepository;
import com.gp.cricket.repository.SelectedPlayerRepository;
import com.gp.cricket.repository.StadiumRepository;
import com.gp.cricket.repository.TournamentClubCaptainRepository;
import com.gp.cricket.repository.TournamentClubPlayerRepository;
import com.gp.cricket.repository.TournamentClubRepository;
import com.gp.cricket.repository.TournamentRepository;
import com.gp.cricket.Interface.StadiumRepository;
import com.gp.cricket.Interface.TournamentRepository;
import com.gp.cricket.Interface.TournamentClubRepository;
import com.gp.cricket.Interface.TournamentClubPlayerRepository;
import com.gp.cricket.Interface.SelectedPlayerRepository;
import com.gp.cricket.Interface.SelectedPlayerService;
import com.gp.cricket.Interface.TournamentClubRepository;
import com.gp.cricket.Interface.TournamentClubCaptainRepository;
import com.gp.cricket.Interface.TournamentClubRepository;
import com.gp.cricket.DTO.TournamentClubCaptain;
@Service
public class MatchService {

@Autowired
 private MatchTypeRepository mathcTypeRepository;

@Autowired
 private MatchRepository matchRepo;

@Autowired
 private StadiumRepository stadiumRepository;

@Autowired
 private RefereeRepository refereeRepository;

@Autowired
 private MatchTypeRepository matchTypeRepository;

@Autowired
 private TournamentRepository tournamentRepository;

@Autowired
 private TournamentClubRepository tournamnetClubRepository;

@Autowired
 private TournamentClubPlayerRepository tournamnetClubPlayerRepository;

@Autowired
 private SelectedPlayerRepository selectedPlayerRepository;

@Autowired
 private SelectedPlayerService selectedPlayerService;

@Autowired
 private ClubRepository clubRepository;

@Autowired
 private TournamentClubCaptainRepository tournamentClubCaptainRepository;

@Autowired
 private TournamentClubRepository tournamentClubRepository;

@Autowired
 private ClubRankingService clubRankingService;


public List<Match> getRefereeMatchesUpcommingTTwenty(Integer tournamentId,Integer refreeId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getRefereeMatchesUpcommingTTwenty(currentDate, tournamentId, refreeId);
}


public List<Match> getRefereeMatchesPlayedTTwenty(Integer tournamentId,Integer refreeId){
    LocalDate currentDate = LocalDate.now();
    System.out.println(tournamentId + "   " + refreeId);
    return matchRepo.getRefereeMatchesPlayedTTwenty(currentDate, tournamentId, refreeId);
}


public List<Match> getRefereeMatchesPlayedOneDay(Integer tournamentId,Integer refreeId){
    LocalDate currentDate = LocalDate.now();
    System.out.println(tournamentId + "   " + refreeId);
    return matchRepo.getRefereeMatchesPlayedOneDay(currentDate, tournamentId, refreeId);
}


public Match updateMatchFor34Days(Match match){
    match.setState(1);
    Match createdMatch = matchRepo.save(match);
    return createdMatch;
}


public List<Match> playedMatches(Integer tournamentId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getPlayedMatches(currentDate, tournamentId);
}


public List<Match> refereeMatchesPlayed(Integer tournamentId,Integer refreeId){
    LocalDate currentDate = LocalDate.now();
    System.out.println(tournamentId + "   " + refreeId);
    return matchRepo.getRefereeMatchesPlayed(currentDate, tournamentId, refreeId);
}


public List<Match> getRefereeMatchesUpcommingOneDay(Integer tournamentId,Integer refreeId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getRefereeMatchesUpcommingOneDay(currentDate, tournamentId, refreeId);
}


public List<Match> getLiveMatchForReferee(int refId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getLiveMatchForReferee((Integer) refId, currentDate);
}


public List<MatchType> getMathcTypes(){
    return mathcTypeRepository.findAll();
}


public List<Match> getRefereeMatchesPlayedUpdated34Days(Integer tournamentId,Integer refereeId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getRefereeMatchesPlayedUpdated34Days(currentDate, tournamentId, refereeId);
}


public List<Match> getRefereeMatchesPlayedUpdatedTTwenty(Integer tournamentId,Integer refereeId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getRefereeMatchesPlayedUpdatedTTwenty(currentDate, tournamentId, refereeId);
}


public List<Match> getRefereeMatchesPlayedUpdatedOneDay(Integer tournamentId,Integer refereeId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getRefereeMatchesPlayedUpdatedOneDay(currentDate, tournamentId, refereeId);
}


public List<Match> refereeMatchesUpcomming(Integer tournamentId,Integer refreeId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getRefereeMatchesUpcomming(currentDate, tournamentId, refreeId);
}


public List<Match> getRefereeMatchesPlayed34Days(Integer tournamentId,Integer refreeId){
    LocalDate currentDate = LocalDate.now();
    System.out.println(tournamentId + "   " + refreeId);
    return matchRepo.getRefereeMatchesPlayed34Days(currentDate, tournamentId, refreeId);
}


public Match createMatch(Match match){
    System.out.println("1111111111#####################");
    // get captain and vice captain
    getCaptainsForMatch(match);
    Match createdMatch = matchRepo.save(match);
    Integer tournamentClubIdforclub1 = tournamnetClubRepository.findIdByTournamentAndClub(match.getTournamentIdValue(), match.getClubOneId());
    Integer tournamentClubIdforclub12 = tournamnetClubRepository.findIdByTournamentAndClub(match.getTournamentIdValue(), match.getClubTwoId());
    List<Player> club1Players = tournamnetClubPlayerRepository.findPlayersForMatch(tournamentClubIdforclub1);
    List<Player> club2Players = tournamnetClubPlayerRepository.findPlayersForMatch(tournamentClubIdforclub12);
    club1Players.forEach((p) -> {
        SelectedPlayer record = new SelectedPlayer(null, createdMatch, p, 0);
        selectedPlayerService.saveSelectedPlayer(record);
    });
    club2Players.forEach((p) -> {
        SelectedPlayer record = new SelectedPlayer(null, createdMatch, p, 0);
        selectedPlayerService.saveSelectedPlayer(record);
    });
    return createdMatch;
}


public List<Match> findMatchesByTournamentIdForCalender(Integer tournamentId){
    return matchRepo.getMatchForpublic(tournamentId);
}


public List<Match> toPlayMatches(Integer tournamentId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getToPlayMatches(currentDate, tournamentId);
}


public List<Match> findMatchesByTournamentId(Integer tournamentId){
    // TODO Auto-generated method stub
    return matchRepo.findMatchesByTournamentId(tournamentId);
}


public List<Match> refereeMatchesPlayedUpdated(Integer tournamentId,Integer refereeId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getRefereeMatchesPlayedUpdated(currentDate, tournamentId, refereeId);
}


public List<Match> upComingMatchList(Integer clubId){
    if (clubId != null && clubRepository.existsById(clubId)) {
        return matchRepo.findUpcomingMatchesByClubId(clubId);
    }
    return null;
}


public void getCaptainsForMatch(Match match){
    Integer club1 = match.getClubOneId();
    Integer club2 = match.getClubTwoId();
    Tournament tournament = match.getTournamentId();
    if (club1 != null && club2 != null && tournament != null) {
        if (clubRepository.existsById(club1) && clubRepository.existsById(club2) && tournamentRepository.existsById(tournament.getTournamentId())) {
            // Club1
            TournamentClub tc1 = tournamentClubRepository.findByClubIdAndTournamentId(clubRepository.findClubByClubId(club1), tournament);
            TournamentClubCaptain c1 = tournamentClubCaptainRepository.findByTournamentClubId(tc1);
            if (c1 != null) {
                match.setCaptainClubOne(c1.getCaptainId());
                match.setClubOneViceCaptain(c1.getViceCaptainId());
            }
            // club2
            TournamentClub tc2 = tournamentClubRepository.findByClubIdAndTournamentId(clubRepository.findClubByClubId(club2), tournament);
            TournamentClubCaptain c2 = tournamentClubCaptainRepository.findByTournamentClubId(tc2);
            if (c2 != null) {
                match.setCaptainClubTwo(c2.getCaptainId());
                match.setClubTwoViceCaptain(c2.getViceCaptainId());
            }
        }
    }
}


public Match updateMatch(Match match){
    // Club ranking
    if (match.getState() == 0) {
        clubRankingService.clubRanking(match);
    }
    match.setState(1);
    Match createdMatch = matchRepo.save(match);
    return createdMatch;
}


public Match findMatchById(Integer matchId){
    return this.matchRepo.findMatchById(matchId);
}


public List<Match> getRefereeMatchesUpcomming34Days(Integer tournamentId,Integer refreeId){
    LocalDate currentDate = LocalDate.now();
    return matchRepo.getRefereeMatchesUpcomming34Days(currentDate, tournamentId, refreeId);
}


public List<Match> getPlayedMatchList(Integer clubId){
    System.out.println("----" + clubId);
    if (clubId != null && clubRepository.existsById(clubId)) {
        System.out.println("Hello");
        return matchRepo.findPlayedMatchesByClubId(clubId);
    }
    return null;
}


public List<Player> selectedPlayers(Integer matchId,Integer clubId){
    return selectedPlayerRepository.selectedPlayersForMatch(matchId, clubId);
}


}