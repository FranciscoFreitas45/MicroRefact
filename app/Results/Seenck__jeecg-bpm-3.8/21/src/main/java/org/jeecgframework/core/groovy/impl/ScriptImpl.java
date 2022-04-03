package org.jeecgframework.core.groovy.impl;
 import org.jeecgframework.core.groovy.IScript;
import org.springframework.stereotype.Component;
@Component
public class ScriptImpl implements IScript{


public String getCurrentUserId(){
    return "1";
}


}