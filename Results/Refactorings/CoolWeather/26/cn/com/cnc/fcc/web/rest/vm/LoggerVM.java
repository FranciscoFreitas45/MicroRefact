import ch.qos.logback.classic.Logger;
public class LoggerVM {

 private  String name;

 private  String level;


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