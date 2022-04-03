package com.gp.cricket.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.TournamentClub;
import com.gp.cricket.service.TournamentClubService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class TournamentClubController {

@Autowired
 private TournamentClubService tournamentClubService;


@GetMapping("tournamentclub/registered/{clubId}")
public ResponseEntity<List<TournamentClub>> getClubRegisteredTournaments(Integer clubId){
    List<TournamentClub> result = tournamentClubService.getClubRegisteredTournaments(clubId);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


@GetMapping("tournamentclub/registeredClubs/{tournamentId}")
public ResponseEntity<List<Club>> getClubsRegisteredTournament(Integer tournamentId){
    List<Club> result = tournamentClubService.getClubsRegisteredTournament(tournamentId);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


}