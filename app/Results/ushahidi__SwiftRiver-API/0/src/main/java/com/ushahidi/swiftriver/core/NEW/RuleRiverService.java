package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaRuleDao;
import com.ushahidi.swiftriver.core.model.Rule;
@Service
public class RuleRiverService {

@Autowired
 private JpaRuleDao jparuledao;


public List<Rule> getRules(Long id){
return jparuledao.getRules(id);
}


public void setRules(Long id,List<Rule> rules){
jparuledao.setRules(id,rules);
}


}