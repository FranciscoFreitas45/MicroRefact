package com.gp.cricket.service;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BallerRecord;
import com.gp.cricket.entity.BatmanRecord;
import com.gp.cricket.entity.Player;
import com.gp.cricket.mapobject.PlayerMatchBallerRecordExport;
import com.gp.cricket.mapobject.PlayerMatchBatmenRecordExport;
import com.gp.cricket.mapobject.PlayersData;
import com.gp.cricket.repository.BallerRecordRepository;
import com.gp.cricket.repository.BatmanRecordRepository;
import com.gp.cricket.repository.PlayerRepository;
import com.gp.cricket.Interface.PlayerRepository;
import com.gp.cricket.Interface.BatmanRecordRepository;
import com.gp.cricket.Interface.BallerRecordRepository;
import com.gp.cricket.DTO.Player;
@Service
public class DataExportService {

@Autowired
 private PlayerRepository playerRepository;

@Autowired
 private BatmanRecordRepository batmanRecordRepository;

@Autowired
 private BallerRecordRepository ballerRecordRepository;


public List<PlayersData> getPlayers(){
    List<PlayersData> playerDataList = new ArrayList<PlayersData>();
    List<Player> playerData = playerRepository.findAll();
    for (Player player : playerData) {
        PlayersData ob = new PlayersData();
        ob.setPlayerId(player.getPlayerId());
        ob.setClub(player.getClubId().getClubName());
        ob.setPlayerName(player.getUserId().getNameWithInitial());
        playerDataList.add(ob);
    }
    return playerDataList;
}


public List<PlayerMatchBatmenRecordExport> getBatmenRecord(Integer playerId){
    System.out.println("------" + playerId);
    List<PlayerMatchBatmenRecordExport> dataList = new ArrayList<PlayerMatchBatmenRecordExport>();
    List<BatmanRecord> batmenRecordList = new ArrayList<BatmanRecord>();
    batmenRecordList = batmanRecordRepository.findByUserId(playerId);
    for (BatmanRecord batmen : batmenRecordList) {
        PlayerMatchBatmenRecordExport ob = new PlayerMatchBatmenRecordExport();
        ob.setBattingPoint(batmen.getBattingPoints().toString());
        ob.setDate(batmen.getSelectedPlayerId().getMatchId().getStartDate().toString());
        ob.setDoubleSentuary(batmen.getDoubleSentury().toString());
        ob.setFaceBall(batmen.getFacedBalls().toString());
        ob.setHalfSentuary(batmen.getHalfentury().toString());
        ob.setMatchtype(batmen.getSelectedPlayerId().getMatchId().getMatchTypeId().getMatchType());
        ob.setNotOut(batmen.getNotOut().toString());
        ob.setRuns(batmen.getBattingRuns().toString());
        ob.setSentuary(batmen.getSentury().toString());
        ob.setSix(batmen.getSixes().toString());
        ob.setStrikeRate(batmen.getStrikeRate().toString());
        ob.setTournamentName(batmen.getSelectedPlayerId().getMatchId().getTournamentId().getTournamentName());
        ob.setTripleSentuary(batmen.getTripleSentury().toString());
        dataList.add(ob);
    }
    return dataList;
}


public List<PlayerMatchBallerRecordExport> getBallerRecord(Integer playerId){
    System.out.println("------" + playerId);
    List<PlayerMatchBallerRecordExport> dataList = new ArrayList<PlayerMatchBallerRecordExport>();
    List<BallerRecord> ballerRecordList = new ArrayList<BallerRecord>();
    ballerRecordList = ballerRecordRepository.findByUserId(playerId);
    for (BallerRecord baller : ballerRecordList) {
        PlayerMatchBallerRecordExport ob = new PlayerMatchBallerRecordExport();
        ob.setAvgSpeed(baller.getAvgSpeed().toString());
        ob.setBallingPoint(baller.getBallingPoints().toString());
        ob.setHatTrick(baller.getHatTriks().toString());
        ob.setNumOfNo(baller.getNumberOfNos().toString());
        ob.setNumOfWides(baller.getNumberOfWides().toString());
        ob.setNumRunAgainst(baller.getNumberOfRunsAgainst().toString());
        ob.setOvers(baller.getOvers().toString());
        ob.setWicket(baller.getWickets().toString());
        ob.setTournamentName(baller.getSelectedPlayerId().getMatchId().getTournamentId().getTournamentName());
        ob.setDate(baller.getSelectedPlayerId().getMatchId().getStartDate().toString());
        ob.setMatchtype(baller.getSelectedPlayerId().getMatchId().getMatchTypeId().getMatchType());
        dataList.add(ob);
    }
    return dataList;
}


}