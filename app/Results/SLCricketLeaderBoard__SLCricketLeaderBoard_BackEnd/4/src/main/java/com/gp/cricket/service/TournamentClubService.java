package com.gp.cricket.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.TournamentClub;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.TournamentClubRepository;
import com.gp.cricket.repository.TournamentRepository;
import com.gp.cricket.entity.Tournament;
import com.gp.cricket.Interface.TournamentRepository;
import com.gp.cricket.Interface.ClubRepository;
@Service
public class TournamentClubService {

@Autowired
 private TournamentClubRepository tournamentClubRepository;

@Autowired
 private TournamentRepository tournamentRepository;

@Autowired
 private ClubRepository clubRepository;


public Boolean tournementClubObjectValidated(TournamentClub object){
    if (object.getClubId() != null && object.getTournamentId() != null && object.getStatus() != null) {
        return true;
    }
    return false;
}


public List<TournamentClub> getClubRegisteredTournaments(Integer clubId){
    if (clubId != null && clubRepository.existsById(clubId)) {
        return tournamentClubRepository.findByclubId(clubRepository.findClubByClubId(clubId));
    }
    return null;
}


public TournamentClub tournementClubRegister(Club club,Tournament tournament){
    if (club != null && tournament != null) {
        if (tournamentClubRepository.findByClubIdAndTournamentId(club, tournament) == null) {
            TournamentClub tournamentClub = new TournamentClub(0, (byte) 1, club, tournament);
            return tournamentClubRepository.save(tournamentClub);
        } else {
            // Already registered to the tournament
            return null;
        }
    }
    return null;
}


public List<Club> getClubsRegisteredTournament(Integer tournamentId){
    return this.tournamentClubRepository.findClubsByTournamentId(tournamentId);
// if(clubId!=null && clubRepository.existsById(clubId)) {
// return tournamentClubRepository.findByclubId(clubRepository.findClubByClubId(clubId));
// }
// return null;
}


}