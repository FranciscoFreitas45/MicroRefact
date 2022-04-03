package com.ushahidi.swiftriver.core.api.dao.impl;
 import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.RuleDao;
import com.ushahidi.swiftriver.core.model.Rule;
@Repository
public class JpaRuleDao extends AbstractJpaDao<Rule>implements RuleDao{


public List<Rule> getRules(Long id)

public void setRules(Long id,List<Rule> rules)

public void setDateAdded(long id,Date dateAdded)

}