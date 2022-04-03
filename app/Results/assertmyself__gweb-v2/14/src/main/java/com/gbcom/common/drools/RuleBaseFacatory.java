package com.gbcom.common.drools;
 import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
public class RuleBaseFacatory {

 private  RuleBase ruleBase;


public RuleBase getRuleBase(){
    return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();
}


}