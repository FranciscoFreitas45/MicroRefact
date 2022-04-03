package com.gp.cricket.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BatmanScore;
import com.gp.cricket.entity.FieldingScore;
import com.gp.cricket.repository.FieldingScoreRepository;
@Service
public class FieldingScoreService {

@Autowired
 private FieldingScoreRepository fieldingScoreRepository;


public FieldingScore saveFieldingRecord(FieldingScore record){
    return fieldingScoreRepository.save(record);
}


public FieldingScore getRecordByPlayerId(Integer playerId){
    return fieldingScoreRepository.getDetailsByPlayerId(playerId);
}


public FieldingScore getRecordByPlayerIdMatchType(Integer playerId,String matchTypeString){
    return fieldingScoreRepository.getRecordByPlayerIdMatchType(playerId, matchTypeString);
}


}