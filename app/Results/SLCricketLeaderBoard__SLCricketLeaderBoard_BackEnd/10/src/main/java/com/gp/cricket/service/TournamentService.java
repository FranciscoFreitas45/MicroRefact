package com.gp.cricket.service;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.Tournament;
import com.gp.cricket.entity.TournamentClub;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.TournamentRepository;
import com.gp.cricket.Interface.ClubRepository;
@Service
public class TournamentService {

@Autowired
 private TournamentRepository tournamentRepo;

@Autowired
 private ClubRepository clubRepository;


public Tournament registerTournament(Tournament tournament){
    return this.tournamentRepo.save(tournament);
}


public List<Tournament> getAllTournaments(){
    return tournamentRepo.findAll();
}


public List<Tournament> getRegistrationOpenedTournaments(){
    Date currentDate = new Date();
    return tournamentRepo.pendingTournaments(currentDate);
}


public Optional<Tournament> getTournamentById(Integer id){
    return this.tournamentRepo.findById(id);
}


public List<Tournament> getUpcomingTournamentForClub(Integer clubId){
    if (clubId != null && clubRepository.existsById(clubId)) {
        return tournamentRepo.findUpcomingTournamentForClubByClubId(clubRepository.findClubByClubId(clubId));
    }
    return null;
}


public List<Tournament> getRegistrationClosedTournaments(){
    Date currentDate = new Date();
    return tournamentRepo.closedTournaments(currentDate);
}


public List<Tournament> getTournamentsByDateOrder(){
    Date currentDate = new Date();
    // return tournamentRepo.findAll();
    return tournamentRepo.getTournamentByDateOrder(currentDate);
}


public List<Tournament> getTournamentsByType(Integer type){
    List<Tournament> tournamentData = new ArrayList<Tournament>();
    if (type == 1) {
        tournamentData = tournamentRepo.findPastTournament(new Date());
    } else if (type == 2) {
        tournamentData = tournamentRepo.findUpcomingTournament(new Date());
    } else if (type == 3) {
        tournamentData = tournamentRepo.findOnGoingTournament(new Date());
    }
    return tournamentData;
}


public List<Tournament> getTournamentsHistory(){
    Date currentDate = new Date();
    // return tournamentRepo.findAll();
    return tournamentRepo.getTournamentHistory(currentDate);
}


}