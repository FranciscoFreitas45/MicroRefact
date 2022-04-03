package com.gp.cricket.service;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.Player;
import com.gp.cricket.entity.TournamentClub;
import com.gp.cricket.entity.TournamentClubCaptain;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.PlayerRepository;
import com.gp.cricket.repository.TournamentClubCaptainRepository;
import com.gp.cricket.repository.TournamentClubRepository;
import com.gp.cricket.repository.TournamentRepository;
import com.gp.cricket.Interface.PlayerRepository;
import com.gp.cricket.Interface.TournamentRepository;
import com.gp.cricket.Interface.ClubRepository;
@Service
public class TournamentClubCaptainService {

@Autowired
 private  TournamentClubCaptainRepository tournamentClubCaptainRepository;

@Autowired
 private PlayerRepository playerRepository;

@Autowired
 private TournamentClubRepository tournamentClubRepository;

@Autowired
 private TournamentRepository tournamentRepository;

@Autowired
 private ClubRepository clubRepository;


public Boolean tournamentClubCaptainObjectValid(TournamentClubCaptain object){
    if (object != null) {
        if (object.getCaptainId() != null && object.getViceCaptainId() != null && object.getTournamentClubId() != null) {
            Integer p1 = object.getCaptainId();
            Integer p2 = object.getViceCaptainId();
            Integer tc = object.getTournamentClubId().getTournamentClubId();
            if (playerRepository.existsById(p1) && playerRepository.existsById(p2) && tournamentClubRepository.existsById(tc)) {
                return true;
            }
        }
    }
    return false;
}


public List<Player> getTournamentCaptains(Integer tournamentId,Integer clubId){
    if (tournamentId != null && clubId != null && tournamentRepository.existsById(tournamentId) && clubRepository.existsById(clubId)) {
        TournamentClub tournamentClub = tournamentClubRepository.findByClubIdAndTournamentId(clubRepository.findClubByClubId(clubId), tournamentRepository.findByTournamentId(tournamentId));
        TournamentClubCaptain tournamentClubCaptain = tournamentClubCaptainRepository.findByTournamentClubId(tournamentClub);
        if (tournamentClubCaptain != null) {
            List<Player> player = new ArrayList<Player>();
            player.add(playerRepository.findPlayerById(tournamentClubCaptain.getCaptainId()));
            player.add(playerRepository.findPlayerById(tournamentClubCaptain.getViceCaptainId()));
            return player;
        }
    }
    return null;
}


public Integer tournamentClubCaptainSave(TournamentClubCaptain tournamentClubCaptain){
    if (tournamentClubCaptainObjectValid(tournamentClubCaptain)) {
        tournamentClubCaptainRepository.save(tournamentClubCaptain);
        return 1;
    }
    return null;
}


public Integer tournamentCaptainsUpdate(TournamentClubCaptain tournamentClubCaptain){
    if (tournamentClubCaptainObjectValid(tournamentClubCaptain)) {
        TournamentClubCaptain object = tournamentClubCaptainRepository.findByTournamentClubId(tournamentClubCaptain.getTournamentClubId());
        if (object != null) {
            object.setCaptainId(tournamentClubCaptain.getCaptainId());
            object.setViceCaptainId(tournamentClubCaptain.getViceCaptainId());
            tournamentClubCaptainRepository.save(object);
            return 1;
        }
    }
    return null;
}


}