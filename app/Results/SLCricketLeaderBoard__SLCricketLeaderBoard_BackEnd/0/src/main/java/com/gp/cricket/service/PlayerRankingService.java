package com.gp.cricket.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.BallerScore;
import com.gp.cricket.entity.BatmanScore;
import com.gp.cricket.entity.FieldingScore;
import com.gp.cricket.repository.BallerScoreRepository;
import com.gp.cricket.repository.BatmanScoreRepository;
import com.gp.cricket.repository.FieldingScoreRepository;
@Service
public class PlayerRankingService {

@Autowired
 private BallerScoreRepository ballerScoreRepository;

@Autowired
 private BatmanScoreRepository batmanScoreRepository;

@Autowired
 private FieldingScoreRepository fieldingScoreRepository;


public List<BallerScore> topBallerPlayers(Integer matchTypeId){
    return ballerScoreRepository.topBallerPlayers(matchTypeId);
}


public List<FieldingScore> topFieldingPlayers(Integer matchTypeId){
    return fieldingScoreRepository.topFieldingPlayers(matchTypeId);
}


public List<BatmanScore> topBatmanPlayers(Integer matchTypeId){
    return batmanScoreRepository.topBatmanPlayers(matchTypeId);
}


}