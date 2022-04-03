package com.gp.cricket.service;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BallerRecord;
import com.gp.cricket.entity.BatmanRecord;
import com.gp.cricket.entity.Player;
import com.gp.cricket.entity.TournamentClub;
import com.gp.cricket.entity.TournamentClubPlayer;
import com.gp.cricket.entity.User;
import com.gp.cricket.mapobject.PlayerMatchData;
import com.gp.cricket.repository.BallerRecordRepository;
import com.gp.cricket.repository.BatmanRecordRepository;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.PlayerRepository;
import com.gp.cricket.repository.TournamentClubPlayerRepository;
import com.gp.cricket.repository.UserRepository;
import com.gp.cricket.Interface.TournamentClubPlayerRepository;
import com.gp.cricket.Interface.ClubRepository;
import com.gp.cricket.Interface.TournamentClubPlayerRepository;
import com.gp.cricket.Interface.BatmanRecordRepository;
import com.gp.cricket.Interface.UserService;
import com.gp.cricket.Interface.BallerRecordRepository;
@Service
public class PlayerService {

@Autowired
 private PlayerRepository playerRepository;

@Autowired
 private ClubRepository clubRepository;

@Autowired
 private TournamentClubPlayerRepository tournamentClubPlayerRepository;

@Autowired
 private BatmanRecordRepository batmanRecordRepository;

@Autowired
 private UserService userService;

@Autowired
 private BallerRecordRepository ballerRecordRepository;


public List<Player> getClubPlayerList(Integer clubId,Byte status){
    if (clubId != null && clubId > 0 && status >= 0 && status <= 1) {
        return playerRepository.findPlayerByClubId(clubRepository.findClubByClubId(clubId), status);
    }
    return null;
}


public Player getPlayer(Integer playerId){
    if (playerId != null && playerId > 0) {
        return playerRepository.findPlayerById(playerId);
    }
    return null;
}


public Integer playerAccountDeactivate(Integer playerId){
    if (playerId != null && playerRepository.existsById(playerId)) {
        Player player = playerRepository.findPlayerById(playerId);
        List<TournamentClubPlayer> tournamentClubPlayer = tournamentClubPlayerRepository.findPlayerTournamentStatus(player);
        if (!(tournamentClubPlayer != null && tournamentClubPlayer.size() > 0)) {
            // Player currently not play any match.Also not play tournament in the future
            // if user account deactivate then return 1
            return userService.userAccountDeactivate(player.getUserId().getUserId());
        }
        // Player already play tournament or play tournament in the future
        return 0;
    }
    return null;
}


public Integer playerUpdate(Player player){
    if (validPlayerObject(player)) {
        Integer userUpdateResult = userService.updateUser(player.getUserId());
        if (userUpdateResult == 1) {
            playerRepository.save(player);
        }
        return userUpdateResult;
    }
    return null;
}


public List<PlayerMatchData> getPlayerMatchData(Integer playerType,Integer userId){
    List<PlayerMatchData> dataList = new ArrayList<PlayerMatchData>();
    if (playerType == 1) {
        // Battment
        List<BatmanRecord> data = new ArrayList<BatmanRecord>();
        data = batmanRecordRepository.findByUserId(userId);
        for (BatmanRecord batmen : data) {
            PlayerMatchData ob = new PlayerMatchData();
            ob.setTournament(batmen.getSelectedPlayerId().getMatchId().getTournamentId().getTournamentName());
            ob.setMatch(batmen.getSelectedPlayerId().getMatchId().getMatchTypeId().getMatchType() + "(" + batmen.getSelectedPlayerId().getMatchId().getStartDate().toString() + "");
            ob.setResult1(batmen.getBattingRuns().toString());
            ob.setResult2(batmen.getFacedBalls().toString());
            dataList.add(ob);
        }
    } else if (playerType == 2) {
        // Baller
        List<BallerRecord> data = new ArrayList<BallerRecord>();
        data = ballerRecordRepository.findByUserId(userId);
        for (BallerRecord baller : data) {
            PlayerMatchData ob = new PlayerMatchData();
            ob.setMatch(baller.getSelectedPlayerId().getMatchId().getMatchTypeId().getMatchType() + "(" + baller.getSelectedPlayerId().getMatchId().getStartDate().toString() + ")");
            ob.setTournament(baller.getSelectedPlayerId().getMatchId().getTournamentId().getTournamentName());
            ob.setResult1(baller.getOvers().toString());
            ob.setResult2(baller.getWickets().toString());
            dataList.add(ob);
        }
    }
    return dataList;
}


public Boolean validPlayerObject(Player player){
    if (player.getUserId() != null && player.getSpecialType() != null && player.getBatmanTypeId() != null && player.getBallerTypeId() != null && player.getClubId() != null) {
        return true;
    }
    return false;
}


public Integer playerSignup(Player player){
    if (validPlayerObject(player)) {
        User user = userService.signupUser(player.getUserId());
        if (user != null) {
            player.setUserId(user);
            playerRepository.save(player);
            return 1;
        }
        return 0;
    }
    return null;
}


}