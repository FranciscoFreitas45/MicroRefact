package com.gp.cricket.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BallerRecord;
import com.gp.cricket.entity.BallerScore;
import com.gp.cricket.repository.BallerRecordRepository;
import com.gp.cricket.Interface.BallerScoreService;
import com.gp.cricket.DTO.BallerScore;
@Service
public class BallerRecordService {

@Autowired
 private BallerRecordRepository ballerRecordRepo;

@Autowired
 private BallerScoreService ballerScoreService;


public BallerRecord saveRecord(BallerRecord record){
    Integer playerId = record.getSelectedPlayerId().getPlayerId().getPlayerId();
    String matchTypeString = record.getSelectedPlayerId().getMatchId().getMatchTypeId().getMatchType();
    BallerScore ballerScore;
    ballerScore = ballerScoreService.getRecordByPlayerIdMatchType(playerId, matchTypeString);
    if (ballerScore == null) {
        ballerScore = new BallerScore();
        ballerScore.setBallerScoredId(null);
        ballerScore.setPlayerId(record.getSelectedPlayerId().getPlayerId());
        ballerScore.setMatchTypeId(record.getSelectedPlayerId().getMatchId().getMatchTypeId());
        ballerScore.setAverageSpeed(record.getAvgSpeed());
        ballerScore.setMatchCount(1);
        ballerScore.setOvers(record.getOvers());
        ballerScore.setWickets(record.getWickets());
        ballerScore.setWideBalls(record.getNumberOfWides());
        ballerScore.setNoBalls(record.getNumberOfNos());
        ballerScore.setNumberOfRunsAgainst(record.getNumberOfRunsAgainst());
        ballerScore.setHatricks(record.getHatTriks());
        ballerScore.setPoints(record.getBallingPoints());
    } else {
        int prevMatchCount = ballerScore.getMatchCount();
        System.out.println("mathc count prev" + prevMatchCount);
        ballerScore.updateMatchCount(1);
        int afterMathcCount = ballerScore.getMatchCount();
        System.out.println("mathc count after" + afterMathcCount);
        Double averageSpeed = ((ballerScore.getAverageSpeed() * prevMatchCount) + record.getAvgSpeed()) / afterMathcCount;
        ballerScore.setAverageSpeed(averageSpeed);
        ballerScore.updateOvers(record.getOvers());
        ballerScore.updateWickets(record.getWickets());
        ballerScore.updateWideBalls(record.getNumberOfWides());
        ballerScore.updateNoBalls(record.getNumberOfNos());
        ballerScore.updateNumberOfRunsAgainst(record.getNumberOfRunsAgainst());
        ballerScore.updateHatricks(record.getHatTriks());
        ballerScore.updatePoints(record.getBallingPoints());
        System.out.println("this is the not null");
    }
    BallerScore updatedResult = ballerScoreService.saveRecord(ballerScore);
    if (updatedResult != null) {
        return ballerRecordRepo.save(record);
    } else {
        return null;
    }
}


public BallerRecord getballerRecordBtSelectedPlayerId(Integer selectedPlayerId){
    return ballerRecordRepo.getballerRecordBtSelectedPlayerId(selectedPlayerId);
}


}