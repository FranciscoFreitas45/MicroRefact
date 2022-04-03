package org.jeecgframework.web.cgform.enhance;
 import java.sql.Timestamp;
public class CurrentDateFillRule implements IFillRuleHandler{


@Override
public Object execute(String paramJson){
    return new Timestamp(System.currentTimeMillis());
}


}