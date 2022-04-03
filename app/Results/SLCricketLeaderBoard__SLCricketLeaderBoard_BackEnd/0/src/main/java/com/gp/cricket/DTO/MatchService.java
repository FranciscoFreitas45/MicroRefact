package com.gp.cricket.DTO;
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
public class MatchService {

 private MatchTypeRepository mathcTypeRepository;

 private MatchRepository matchRepo;

 private StadiumRepository stadiumRepository;

 private RefereeRepository refereeRepository;

 private MatchTypeRepository matchTypeRepository;

 private TournamentRepository tournamentRepository;

 private TournamentClubRepository tournamnetClubRepository;

 private TournamentClubPlayerRepository tournamnetClubPlayerRepository;

 private SelectedPlayerRepository selectedPlayerRepository;

 private SelectedPlayerService selectedPlayerService;

 private ClubRepository clubRepository;

 private TournamentClubCaptainRepository tournamentClubCaptainRepository;

 private TournamentClubRepository tournamentClubRepository;

 private ClubRankingService clubRankingService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


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


public List<Match> getRefereeMatchesPlayed34Days(Integer tournamentId,Integer refreeId){
    LocalDate currentDate = LocalDate.now();
    System.out.println(tournamentId + "   " + refreeId);
    return matchRepo.getRefereeMatchesPlayed34Days(currentDate, tournamentId, refreeId);
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


public List<Match> findMatchesByTournamentIdForCalender(Integer tournamentId){
    return matchRepo.getMatchForpublic(tournamentId);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findMatchesByTournamentIdForCalender"))

.queryParam("tournamentId",tournamentId)
;
List<Match> aux = restTemplate.getForObject(builder.toUriString(),List<Match>.class);
return aux;
}


}