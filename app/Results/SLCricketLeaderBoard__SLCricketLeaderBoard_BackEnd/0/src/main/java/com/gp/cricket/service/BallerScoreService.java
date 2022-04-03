package com.gp.cricket.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BallerScore;
import com.gp.cricket.entity.BatmanScore;
import com.gp.cricket.repository.BallerScoreRepository;
@Service
public class BallerScoreService {

@Autowired
 private BallerScoreRepository ballerScoreRepository;


public BallerScore getRecordByPlayerId(Integer playerId){
    return ballerScoreRepository.getDetailsByPlayerId(playerId);
}


public BallerScore saveRecord(BallerScore ballerScoreRecord){
    return ballerScoreRepository.save(ballerScoreRecord);
}


public BallerScore getRecordByPlayerIdMatchType(Integer playerId,String matchTypeString){
    return ballerScoreRepository.getRecordByPlayerIdMatchType(playerId, matchTypeString);
}


}