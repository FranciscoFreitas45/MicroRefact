package com.gp.cricket.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.SelectedPlayer;
import com.gp.cricket.repository.SelectedPlayerRepository;
@Service
public class SelectedPlayerService {

@Autowired
 private  SelectedPlayerRepository selectedPlayerRepository;


public SelectedPlayer saveSelectedPlayer(SelectedPlayer player){
    return selectedPlayerRepository.save(player);
}


public List<SelectedPlayer> getSelectedPlayersNotUpdated(Integer matchId,Integer clubId){
    // TODO Auto-generated method stub
    return selectedPlayerRepository.selectedPlayersMatchIdClubIdNotUpdated(matchId, clubId);
}


public List<SelectedPlayer> getSelectedPlayers(Integer matchId,Integer clubId){
    return selectedPlayerRepository.selectedPlayersMatchIdClubId(matchId, clubId);
}


public Integer selectedPlayerStateUpdate(SelectedPlayer player){
    SelectedPlayer x = selectedPlayerRepository.save(player);
    if (x != null) {
        return 1;
    } else {
        return 0;
    }
}


public SelectedPlayer getSelectedPlayerById(Integer id){
    // TODO Auto-generated method stub
    return selectedPlayerRepository.findById(id).get();
}


public List<SelectedPlayer> getSelectedPlayersUpdated(Integer matchId,Integer clubId){
    return selectedPlayerRepository.selectedPlayersMatchIdClubIdUpdated(matchId, clubId);
}


}