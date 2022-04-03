package com.gp.cricket.controller;
 import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.Match;
import com.gp.cricket.entity.MatchType;
import com.gp.cricket.entity.Player;
import com.gp.cricket.service.MatchService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class MatchController {

@Autowired
 private MatchService matchService;


@GetMapping("getRefereeMatchesPlayedUpdatedTTwenty/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesPlayedUpdatedTTwenty(Integer tournamentId,Integer refereeId){
    return matchService.getRefereeMatchesPlayedUpdatedTTwenty(tournamentId, refereeId);
}


@GetMapping("getRefereeMatchesPlayedUpdatedOneDay/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesPlayedUpdatedOneDay(Integer tournamentId,Integer refereeId){
    return matchService.getRefereeMatchesPlayedUpdatedOneDay(tournamentId, refereeId);
}


@GetMapping("/toPlayMatches/{tournamentId}")
public List<Match> getToPlayMatches(Integer tournamentId){
    return matchService.toPlayMatches(tournamentId);
}


@GetMapping("getRefereeMatchesUpcommingTTwenty/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesUpcommingTTwenty(Integer tournamentId,Integer refereeId){
    return matchService.getRefereeMatchesUpcommingTTwenty(tournamentId, refereeId);
}


@GetMapping("getRefereeMatchesPlayedTTwenty/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesPlayedTTwenty(Integer tournamentId,Integer refereeId){
    System.out.println("Played matches " + tournamentId + "" + refereeId);
    return matchService.getRefereeMatchesPlayedTTwenty(tournamentId, refereeId);
}


@GetMapping("/match/{matchId}")
public Match getMatcheById(Integer matchId){
    return matchService.findMatchById(matchId);
}


@GetMapping("/playedMatches/{tournamentId}")
public List<Match> getPlayedMatches(Integer tournamentId){
    return matchService.playedMatches(tournamentId);
}


@GetMapping("getRefereeMatchesPlayed34Days/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesPlayed34Days(Integer tournamentId,Integer refereeId){
    System.out.println("Played matches " + tournamentId + "" + refereeId);
    return matchService.getRefereeMatchesPlayed34Days(tournamentId, refereeId);
}


@GetMapping("getLiveMatchTodayForReferee/{refreeId}")
public ResponseEntity<List<Match>> getLivetodayMatchForReferee(String refreeId){
    int refId = Integer.parseInt(refreeId);
    List<Match> result = matchService.getLiveMatchForReferee(refId);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


@GetMapping("getRefereeMatchesPlayedOneDay/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesPlayedOneDay(Integer tournamentId,Integer refereeId){
    System.out.println("Played matches " + tournamentId + "" + refereeId);
    return matchService.getRefereeMatchesPlayedOneDay(tournamentId, refereeId);
}


@GetMapping("refereeMatchesUpcomming/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesUpcommig(Integer tournamentId,Integer refereeId){
    return matchService.refereeMatchesUpcomming(tournamentId, refereeId);
}


@GetMapping("refereeMatchesPlayed/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesPlayed(Integer tournamentId,Integer refereeId){
    System.out.println("Played matches " + tournamentId + "" + refereeId);
    return matchService.refereeMatchesPlayed(tournamentId, refereeId);
}


@PostMapping("/updateMatchFor34Days")
public Match updateMatchFor34Days(Match match){
    System.out.println(match);
    return matchService.updateMatchFor34Days(match);
// registerung managers
}


@GetMapping("match/upcoming/{clubId}")
public ResponseEntity<List<Match>> upComingMatchList(Integer clubId){
    List<Match> result = matchService.upComingMatchList(clubId);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


@GetMapping("/match/players/{matchId}/{clubId}")
public List<Player> getMatchePlayers(Integer matchId,Integer clubId){
    return matchService.selectedPlayers(matchId, clubId);
}


@GetMapping("/matches/{tournamentId}")
public List<Match> getMatchesByTournamentId(Integer tournamentId){
    return matchService.findMatchesByTournamentId(tournamentId);
}


@PostMapping("/createMatch")
public Match createMathc(Match match){
    System.out.println(match);
    return matchService.createMatch(match);
// registerung managers
}


@GetMapping("getRefereeMatchesUpcommingOneDay/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesUpcommingOneDay(Integer tournamentId,Integer refereeId){
    return matchService.getRefereeMatchesUpcommingOneDay(tournamentId, refereeId);
}


@GetMapping("refereeMatchesPlayedUpdated/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesPlayedUpdated(Integer tournamentId,Integer refereeId){
    return matchService.refereeMatchesPlayedUpdated(tournamentId, refereeId);
}


@PostMapping("/updateMatch")
public Match updateMatch(Match match){
    System.out.println(match);
    return matchService.updateMatch(match);
// registerung managers
}


@GetMapping("getRefereeMatchesUpcomming34Days/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesUpcomming34Days(Integer tournamentId,Integer refereeId){
    return matchService.getRefereeMatchesUpcomming34Days(tournamentId, refereeId);
}


@GetMapping("match/played/{clubId}")
public ResponseEntity<List<Match>> getPlayedMatchList(Integer clubId){
    List<Match> result = matchService.getPlayedMatchList(clubId);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


@GetMapping("getRefereeMatchesPlayedUpdated34Days/{tournamentId}/{refereeId}")
public List<Match> getRefereeMatchesPlayedUpdated34Days(Integer tournamentId,Integer refereeId){
    return matchService.getRefereeMatchesPlayedUpdated34Days(tournamentId, refereeId);
}


@GetMapping("/matchType")
public List<MatchType> getMatchTypes(){
    return matchService.getMathcTypes();
}


}