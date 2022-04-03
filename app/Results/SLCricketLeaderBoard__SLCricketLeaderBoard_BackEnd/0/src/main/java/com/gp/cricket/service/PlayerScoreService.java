package com.gp.cricket.service;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BallerRecord;
import com.gp.cricket.entity.BallerScore;
import com.gp.cricket.entity.BatmanRecord;
import com.gp.cricket.entity.BatmanScore;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.Player;
import com.gp.cricket.mapobject.PlayerMatchRecord;
import com.gp.cricket.mapobject.PlayerRate;
import com.gp.cricket.repository.BallerRecordRepository;
import com.gp.cricket.repository.BallerScoreRepository;
import com.gp.cricket.repository.BatmanRecordRepository;
import com.gp.cricket.repository.BatmanScoreRepository;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.PlayerRepository;
import com.gp.cricket.Interface.PlayerRepository;
import com.gp.cricket.Interface.ClubRepository;
import com.gp.cricket.Interface.BatmanRecordRepository;
import com.gp.cricket.Interface.BallerRecordRepository;
@Service
public class PlayerScoreService {

@Autowired
 private BatmanScoreRepository batmanScoreRepository;

@Autowired
 private BallerScoreRepository ballerScoreRepository;

@Autowired
 private PlayerRepository playerRepository;

@Autowired
 private ClubRepository clubRepository;

@Autowired
 private BatmanRecordRepository batmanRecordRepository;

@Autowired
 private BallerRecordRepository ballerRecordRepository;


public List<PlayerRate> getPlayerRateList(Integer clubId,Integer playerType,Integer order){
    List<PlayerRate> ratingList = new ArrayList<PlayerRate>();
    if (clubId != null && playerType != null && order != null) {
        Club club = clubRepository.findClubByClubId(clubId);
        List<Player> remainingList = new ArrayList<Player>();
        if (playerType == 1) {
            // Batmen
            // 1.Get ODI
            List<BatmanScore> batmenRatingListODI = batmanScoreRepository.findBatmenRating(club, 1, playerType);
            for (BatmanScore batmanScore : batmenRatingListODI) {
                PlayerRate ob = new PlayerRate();
                ob.setName(batmanScore.getPlayerId().getUserId().getNameWithInitial());
                ob.setRate1(batmanScore.getPoints());
                ob.setPlayerId(batmanScore.getPlayerId().getPlayerId());
                ratingList.add(ob);
            }
            // Get remaining players(They haven't ODI record yet)
            remainingList = playerRepository.findRemainingBatmenPlayers(club, playerType, 1);
            for (Player batmen : remainingList) {
                PlayerRate ob = new PlayerRate();
                ob.setName(batmen.getUserId().getNameWithInitial());
                ob.setRate1(0D);
                ob.setPlayerId(batmen.getPlayerId());
                ratingList.add(ob);
            }
            // 2.Get T20
            for (PlayerRate batmanScore : ratingList) {
                BatmanScore ob = batmanScoreRepository.findByMatchTypeANDPlayerId(batmanScore.getPlayerId(), 3);
                if (ob != null) {
                    batmanScore.setRate2(ob.getPoints());
                } else {
                    batmanScore.setRate2(0D);
                }
            }
            // 2.Get Test
            for (PlayerRate batmanScore : ratingList) {
                BatmanScore ob = batmanScoreRepository.findByMatchTypeANDPlayerId(batmanScore.getPlayerId(), 2);
                if (ob != null) {
                    batmanScore.setRate3(ob.getPoints());
                } else {
                    batmanScore.setRate3(0D);
                }
            }
        } else if (playerType == 2) {
            // Baller
            // 1.Get ODI
            List<BallerScore> ballerRatingListODI = ballerScoreRepository.findBallerRating(club, 1, playerType);
            for (BallerScore ballerScore : ballerRatingListODI) {
                PlayerRate ob = new PlayerRate();
                ob.setName(ballerScore.getPlayerId().getUserId().getNameWithInitial());
                ob.setRate1(ballerScore.getPoints());
                ob.setPlayerId(ballerScore.getPlayerId().getPlayerId());
                ratingList.add(ob);
            }
            // Get remaining players(They haven't ODI record yet)
            remainingList = playerRepository.findRemainingBallerPlayers(club, playerType, 1);
            for (Player baller : remainingList) {
                PlayerRate ob = new PlayerRate();
                ob.setName(baller.getUserId().getNameWithInitial());
                ob.setRate1(0D);
                ob.setPlayerId(baller.getPlayerId());
                ratingList.add(ob);
            }
            // 2.Get T20
            for (PlayerRate ballerScore : ratingList) {
                BallerScore ob = ballerScoreRepository.findByMatchTypeANDPlayerId(ballerScore.getPlayerId(), 3);
                if (ob != null) {
                    ballerScore.setRate2(ob.getPoints());
                } else {
                    ballerScore.setRate2(0D);
                }
            }
            // 2.Get Test
            for (PlayerRate ballerScore : ratingList) {
                BallerScore ob = ballerScoreRepository.findByMatchTypeANDPlayerId(ballerScore.getPlayerId(), 2);
                if (ob != null) {
                    ballerScore.setRate3(ob.getPoints());
                } else {
                    ballerScore.setRate3(0D);
                }
            }
        } else if (playerType == 3) {
            // AllRounder
            // 1.Get ODI
            List<BatmanScore> batmenRatingListODI = batmanScoreRepository.findBatmenRating(club, 1, playerType);
            for (BatmanScore allRounderScore : batmenRatingListODI) {
                PlayerRate ob = new PlayerRate();
                ob.setName(allRounderScore.getPlayerId().getUserId().getNameWithInitial());
                ob.setRate1(allRounderScore.getPoints());
                ob.setPlayerId(allRounderScore.getPlayerId().getPlayerId());
                ratingList.add(ob);
            }
            // Get remaining players(They haven't ODI record yet)
            remainingList = playerRepository.findRemainingBatmenPlayers(club, playerType, 1);
            for (Player allRounderScore : remainingList) {
                PlayerRate ob = new PlayerRate();
                ob.setName(allRounderScore.getUserId().getNameWithInitial());
                ob.setRate1(0D);
                ob.setPlayerId(allRounderScore.getPlayerId());
                ratingList.add(ob);
            }
            // get ODI Baller Record
            for (PlayerRate allRounderScore : ratingList) {
                BallerScore ob = ballerScoreRepository.findByMatchTypeANDPlayerId(allRounderScore.getPlayerId(), 1);
                if (ob != null) {
                    allRounderScore.setRate1(allRounderScore.getRate1() + ob.getPoints());
                }
            }
            // 3.Get T20
            for (PlayerRate allRounderScore : ratingList) {
                BatmanScore ob1 = batmanScoreRepository.findByMatchTypeANDPlayerId(allRounderScore.getPlayerId(), 3);
                BallerScore ob2 = ballerScoreRepository.findByMatchTypeANDPlayerId(allRounderScore.getPlayerId(), 3);
                if (ob1 != null && ob2 != null) {
                    allRounderScore.setRate2(ob1.getPoints() + ob2.getPoints());
                } else if (ob1 != null && ob2 == null) {
                    allRounderScore.setRate2(ob1.getPoints());
                } else if (ob1 == null && ob2 != null) {
                    allRounderScore.setRate2(ob2.getPoints());
                } else {
                    allRounderScore.setRate2(0D);
                }
            }
            // 2.Get Test
            for (PlayerRate allRounderScore : ratingList) {
                BatmanScore ob1 = batmanScoreRepository.findByMatchTypeANDPlayerId(allRounderScore.getPlayerId(), 2);
                BallerScore ob2 = ballerScoreRepository.findByMatchTypeANDPlayerId(allRounderScore.getPlayerId(), 2);
                if (ob1 != null && ob2 != null) {
                    allRounderScore.setRate3(ob1.getPoints() + ob2.getPoints());
                } else if (ob1 != null && ob2 == null) {
                    allRounderScore.setRate3(ob1.getPoints());
                } else if (ob1 == null && ob2 != null) {
                    allRounderScore.setRate3(ob2.getPoints());
                } else {
                    allRounderScore.setRate3(0D);
                }
            }
        }
    }
    return ratingList;
}


public List<PlayerMatchRecord> getPlayerMatchRecord(Integer playerId,Integer playerType,Integer matchType){
    matchType = changeConventionofMatchType(matchType);
    List<PlayerMatchRecord> playerMatchRecordList = new ArrayList<PlayerMatchRecord>();
    if (playerId != null && matchType != null && playerType != null) {
        if (playerType == 1) {
            // Batmen
            List<BatmanRecord> batmenRecordList = batmanRecordRepository.findByPlayerIdANDMatchType(playerId, matchType);
            int count = 0;
            while (count < 10) {
                PlayerMatchRecord ob = new PlayerMatchRecord();
                ob.setPoints(0D);
                ob.setDate("");
                if (batmenRecordList.size() >= (count + 1)) {
                    ob.setPoints(batmenRecordList.get(count).getBattingPoints());
                    ob.setDate(batmenRecordList.get(count).getSelectedPlayerId().getMatchId().getFinishDate().toString());
                }
                playerMatchRecordList.add(ob);
                count++;
            }
        } else if (playerType == 2) {
            // Baller
            List<BallerRecord> ballerRecordList = ballerRecordRepository.findByPlayerIdANDMatchType(playerId, matchType);
            int count = 0;
            while (count < 10) {
                PlayerMatchRecord ob = new PlayerMatchRecord();
                ob.setPoints(0D);
                ob.setDate("");
                if (ballerRecordList.size() >= (count + 1)) {
                    ob.setPoints(ballerRecordList.get(count).getBallingPoints());
                    ob.setDate(ballerRecordList.get(count).getSelectedPlayerId().getMatchId().getFinishDate().toString());
                }
                playerMatchRecordList.add(ob);
                count++;
            }
        } else if (playerType == 3) {
            // All rounder
            // GetBatting records and balling records
            List<BatmanRecord> batmenRecordList = batmanRecordRepository.findByPlayerIdANDMatchType(playerId, matchType);
            int count = 0;
            while (count < 10) {
                PlayerMatchRecord ob = new PlayerMatchRecord();
                ob.setPoints(0D);
                ob.setDate("");
                if (batmenRecordList.size() >= (count + 1)) {
                    ob.setPoints(batmenRecordList.get(count).getBattingPoints());
                    ob.setDate(batmenRecordList.get(count).getSelectedPlayerId().getMatchId().getFinishDate().toString());
                }
                playerMatchRecordList.add(ob);
                count++;
            }
            count = 0;
            for (BatmanRecord batmanRecord : batmenRecordList) {
                BallerRecord ob = ballerRecordRepository.findByPlayerIdANDMatchId(playerId, batmanRecord.getSelectedPlayerId().getMatchId());
                if (ob != null) {
                    PlayerMatchRecord b = playerMatchRecordList.get(count);
                    b.setPoints(b.getPoints() + ob.getBallingPoints());
                    playerMatchRecordList.set(count, b);
                }
            }
        }
    }
    return playerMatchRecordList;
}


public Integer changeConventionofMatchType(Integer matchType){
    // DB 1:ODI , 2:Test, 3:T20
    // Angular 1:ODI, 2:T20, 3:Test
    // Change Angular convention to DB
    if (matchType == 2) {
        return 3;
    }
    if (matchType == 3) {
        return 2;
    }
    return 1;
}


}