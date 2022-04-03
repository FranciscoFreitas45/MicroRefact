package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.TournamentRepository;
import com.gp.cricket.entity.Tournament;
@Service
public class TournamentTournamentClubService {

@Autowired
 private TournamentRepository tournamentrepository;


public void setTournamentId(Integer tournamentIdv2,Tournament tournamentId){
tournamentrepository.setTournamentId(tournamentIdv2,tournamentId);
}


public Tournament getTournamentId(Integer tournamentIdv2){
return tournamentrepository.getTournamentId(tournamentIdv2);
}


}