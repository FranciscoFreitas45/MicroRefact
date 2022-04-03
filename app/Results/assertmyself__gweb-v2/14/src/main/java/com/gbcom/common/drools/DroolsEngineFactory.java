package com.gbcom.common.drools;
 public class DroolsEngineFactory {

 public  int ENGINE_TYPE_DB;

 public  int ENGINE_TYPE_FILE;


public DroolsRuleEngine dbEngine(){
    return DroolsRuleEngineDBImpl.getInstance();
}


public DroolsRuleEngine fileEngine(){
    return DroolsRuleEngineImpl.getInstance();
}


}