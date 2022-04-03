package cn.com.cnc.fcc.web.rest.vm;
 import ch.qos.logback.classic.Logger;
public class LoggerVM {

 private  String name;

 private  String level;

public LoggerVM(Logger logger) {
    this.name = logger.getName();
    this.level = logger.getEffectiveLevel().toString();
}public LoggerVM() {
// Empty public constructor used by Jackson.
}
public void setName(String name){
    this.name = name;
}


public String getLevel(){
    return level;
}


public String getName(){
    return name;
}


@Override
public String toString(){
    return "LoggerVM{" + "name='" + name + '\'' + ", level='" + level + '\'' + '}';
}


public void setLevel(String level){
    this.level = level;
}


}