package org.jeecgframework.core.groovy.impl;
 import org.jeecgframework.core.groovy.IScript;
import org.springframework.stereotype.Component;
@Component
public class FormulaImpl implements IScript{


public Double add(Double a,Double b){
    return a * b;
}


}