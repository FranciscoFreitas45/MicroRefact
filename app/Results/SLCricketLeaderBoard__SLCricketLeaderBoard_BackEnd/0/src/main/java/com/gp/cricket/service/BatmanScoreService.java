package com.gp.cricket.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BatmanScore;
import com.gp.cricket.repository.BatmanScoreRepository;
@Service
public class BatmanScoreService {

@Autowired
 private BatmanScoreRepository batmanScoreRepository;


public BatmanScore getRecordByPlayerId(Integer playerId){
    return batmanScoreRepository.getDetailsByPlayerId(playerId);
}


public BatmanScore saveRecord(BatmanScore batmanScore){
    return batmanScoreRepository.save(batmanScore);
}


}