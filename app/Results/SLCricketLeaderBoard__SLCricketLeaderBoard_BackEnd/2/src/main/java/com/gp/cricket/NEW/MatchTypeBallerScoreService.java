package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.MatchTypeRepository;
import com.gp.cricket.entity.MatchType;
@Service
public class MatchTypeBallerScoreService {

@Autowired
 private MatchTypeRepository matchtyperepository;


public void setMatchTypeId(Integer matchTypeIdv2,MatchType matchTypeId){
matchtyperepository.setMatchTypeId(matchTypeIdv2,matchTypeId);
}


public MatchType getMatchTypeId(Integer matchTypeIdv2){
return matchtyperepository.getMatchTypeId(matchTypeIdv2);
}


}