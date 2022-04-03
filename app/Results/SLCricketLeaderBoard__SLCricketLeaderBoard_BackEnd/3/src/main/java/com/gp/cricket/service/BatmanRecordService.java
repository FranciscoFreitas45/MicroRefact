package com.gp.cricket.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BallerScore;
import com.gp.cricket.entity.BatmanRecord;
import com.gp.cricket.entity.BatmanScore;
import com.gp.cricket.repository.BatmanRecordRepository;
import com.gp.cricket.repository.BatmanScoreRepository;
import com.gp.cricket.Interface.BatmanScoreRepository;
import com.gp.cricket.DTO.BatmanScore;
@Service
public class BatmanRecordService {

@Autowired
 private BatmanRecordRepository batmanRecordService;

@Autowired
 private BatmanScoreRepository batmanScoreRepository;


public BatmanRecord saveRecord(BatmanRecord record){
    Integer playerId = record.getSelectedPlayerId().getPlayerId().getPlayerId();
    String matchTypeString = record.getSelectedPlayerId().getMatchId().getMatchTypeId().getMatchType();
    BatmanScore batmanScore = batmanScoreRepository.getRecordByPlayerIdMatchType(playerId, matchTypeString);
    if (batmanScore == null) {
        batmanScore = new BatmanScore();
        batmanScore.setBatmanScoreId(null);
        batmanScore.setPlayerId(record.getSelectedPlayerId().getPlayerId());
        batmanScore.setMatchTypeId(record.getSelectedPlayerId().getMatchId().getMatchTypeId());
        batmanScore.setMatchCount(1);
        batmanScore.setStrikeRate(record.getStrikeRate());
        batmanScore.setFacedBalls(record.getFacedBalls());
        batmanScore.setFour(record.getFours());
        batmanScore.setSix(record.getSixes());
        batmanScore.setRuns(record.getBattingRuns());
        batmanScore.setPoints(record.getBattingPoints());
        batmanScore.setHalfCenturies(0);
        batmanScore.setCenturies(0);
        batmanScore.setDoubleCenturies(0);
        batmanScore.setTripleCenturies(0);
        batmanScore.setFourbleCenturies(0);
        batmanScore.setFivebleCenturies(0);
        batmanScore.setNotOut(0);
        if (record.getHalfentury() == 1) {
            batmanScore.setHalfCenturies(1);
        }
        if (record.getSentury() == 1) {
            batmanScore.setCenturies(1);
        }
        if (record.getDoubleSentury() == 1) {
            batmanScore.setDoubleCenturies(1);
        }
        if (record.getTripleSentury() == 1) {
            batmanScore.setTripleCenturies(1);
        }
        if (record.getFoubleSentury() == 1) {
            batmanScore.setFourbleCenturies(1);
        }
        if (record.getFivebleSentury() == 1) {
            batmanScore.setFivebleCenturies(1);
        }
        if (record.getNotOut() == 1) {
            batmanScore.setNotOut(1);
        }
    } else {
        int prevMatchCount = batmanScore.getMatchCount();
        batmanScore.updateMatchCount(1);
        int afterMatchCounter = batmanScore.getMatchCount();
        Double strikeRate = ((batmanScore.getStrikeRate() * prevMatchCount) + record.getStrikeRate()) / afterMatchCounter;
        batmanScore.setStrikeRate(strikeRate);
        batmanScore.updateFacedBalls(record.getFacedBalls());
        batmanScore.updateFour(record.getFours());
        batmanScore.updateSix(record.getSixes());
        batmanScore.updateRuns(record.getBattingRuns());
        batmanScore.updatePoints(record.getBattingPoints());
        if (record.getHalfentury() == 1) {
            batmanScore.updateHalfCenturies(1);
        }
        if (record.getSentury() == 1) {
            batmanScore.updateCenturies(1);
        }
        if (record.getDoubleSentury() == 1) {
            batmanScore.updateDoubleCenturies(1);
        }
        if (record.getTripleSentury() == 1) {
            batmanScore.updateTripleCenturies(1);
        }
        if (record.getFoubleSentury() == 1) {
            batmanScore.updateFourbleCenturies(1);
        }
        if (record.getFivebleSentury() == 1) {
            batmanScore.updateFivebleCenturies(1);
        }
        if (record.getNotOut() == 1) {
            batmanScore.updateNotOut(1);
        }
    }
    BatmanScore updatedResult = batmanScoreRepository.save(batmanScore);
    if (updatedResult != null) {
        return batmanRecordService.save(record);
    } else {
        return null;
    }
}


public BatmanRecord getbatmanRecordBtSelectedPlayerId(Integer selectedPlayerId){
    return batmanRecordService.getbatmanRecordBtSelectedPlayerId(selectedPlayerId);
}


}