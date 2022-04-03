package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Rule;
public interface RuleRequest {

   public List<Rule> getRules(Long id);
   public void setRules(List<Rule> rules,Long id);
}