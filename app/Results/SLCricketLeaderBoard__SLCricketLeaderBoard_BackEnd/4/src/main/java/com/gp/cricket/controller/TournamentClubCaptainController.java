package com.gp.cricket.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.Player;
import com.gp.cricket.entity.TournamentClubCaptain;
import com.gp.cricket.service.TournamentClubCaptainService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class TournamentClubCaptainController {

@Autowired
 private TournamentClubCaptainService tournamentClubCaptainService;


@PostMapping("tournamentclubcaptain")
public ResponseEntity<Integer> tournamentCaptainsSave(TournamentClubCaptain tournamentClubCaptain){
    Integer result = tournamentClubCaptainService.tournamentClubCaptainSave(tournamentClubCaptain);
    if (result != null) {
        return ResponseEntity.ok(1);
    }
    return ResponseEntity.badRequest().build();
}


@GetMapping("tournamentclubcaptain/{tournamentId}/{clubId}")
public ResponseEntity<List<Player>> getTournamentCaptains(Integer tournamentId,Integer clubId){
    List<Player> result = tournamentClubCaptainService.getTournamentCaptains(tournamentId, clubId);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


@PutMapping("tournamentclubcaptain")
public ResponseEntity<Integer> tournamentCaptainsUpdate(TournamentClubCaptain tournamentClubCaptain){
    System.out.println("-----------------");
    System.out.println(tournamentClubCaptain);
    Integer result = tournamentClubCaptainService.tournamentCaptainsUpdate(tournamentClubCaptain);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


}