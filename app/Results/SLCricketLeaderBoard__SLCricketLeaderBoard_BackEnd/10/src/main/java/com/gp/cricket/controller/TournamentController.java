package com.gp.cricket.controller;
 import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.Tournament;
import com.gp.cricket.service.TournamentService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class TournamentController {

@Autowired
 private TournamentService tournamentService;


@PostMapping("registerTournament")
public String registerTournament(Tournament tournament){
    try {
        Tournament x = tournamentService.registerTournament(tournament);
        if (x != null) {
            return "Creation successfull";
        } else {
            return "Creation Faild";
        }
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println(e);
        return "Creation Faild";
    }
}


@GetMapping("/tournaments/registrationOpened")
public List<Tournament> getRegistartionOpenedTournaments(){
    return this.tournamentService.getRegistrationOpenedTournaments();
}


@GetMapping("/tournaments")
public List<Tournament> getAlltournament(){
    return this.tournamentService.getAllTournaments();
}


@GetMapping("/tournaments/registrationClosed")
public List<Tournament> getRegistartionClosedTournaments(){
    return this.tournamentService.getRegistrationClosedTournaments();
}


@GetMapping("/tournament/upcoming/{clubId}")
public ResponseEntity<List<Tournament>> getUpcomingTournamentForClub(Integer clubId){
    List<Tournament> result = tournamentService.getUpcomingTournamentForClub(clubId);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


@GetMapping("/tournament/{tournamentId}")
public ResponseEntity<Tournament> getTournament(Integer tournamentId){
    Optional<Tournament> object = tournamentService.getTournamentById(tournamentId);
    if (object != null) {
        if (object.isPresent()) {
            return ResponseEntity.ok(object.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    return ResponseEntity.badRequest().build();
}


@GetMapping("tournament/type/{type}")
public ResponseEntity<List<Tournament>> getTournamentsByType(Integer type){
    return ResponseEntity.ok(tournamentService.getTournamentsByType(type));
}


}