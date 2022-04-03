package com.gbcom.common.drools;
 import java.util.List;
public interface DroolsRuleEngine {


public void executeRuleEngine(List<Object> domain)
;

public void refreshEnginRule()
;

}