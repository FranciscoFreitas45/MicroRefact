package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.MatchRepository;
import com.gp.cricket.entity.Match;
@Service
public class MatchSelectedPlayerService {

@Autowired
 private MatchRepository matchrepository;


public Match getMatchId(Integer matchIdv2){
return matchrepository.getMatchId(matchIdv2);
}


public void setMatchId(Integer matchIdv2,Match matchId){
matchrepository.setMatchId(matchIdv2,matchId);
}


}