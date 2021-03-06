package org.jeecgframework.core.groovy;
 import java.util.HashMap;
import java.util.Map;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;
public class GroovyParse {


public Object formulaParse(String formula,Map<String,Object> map){
    ApplicationContext context = ApplicationContextUtil.getContext();
    GroovyScriptEngine groovyScriptEngine = context.getBean(GroovyScriptEngine.class);
    Object value = groovyScriptEngine.executeObject(formula, map);
    return value;
}


public void main(String[] args){
    // 表达式
    String formula = "println 'Hello World!';po = '9s00';return (a * b);";
    Map map = new HashMap();
    map.put("a", 900);
    map.put("b", 10);
    // 规则解析器
    GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine();
    Object value = groovyScriptEngine.executeObject(formula, map);
    System.out.println(value);
    System.out.println(groovyScriptEngine.binding.getVariable("po"));
}


}