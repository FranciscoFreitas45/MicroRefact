package es.us.isa.ideas.utilities;
 import java.io.Serializable;
public class AppAnnotations implements Serializable{

 private  long serialVersionUID;

 public  String INFO;

 public  String WARNING;

 public  String ERROR;

 public  String FATAL;

 protected  String row;

 protected  String column;

 protected  String text;

 protected  String type;

public AppAnnotations() {
    super();
}
public void setColumn(String column){
    this.column = column;
}


public String getType(){
    return type;
}


public String getText(){
    return text;
}


public String getColumn(){
    return column;
}


public void setRow(String row){
    this.row = row;
}


public String getRow(){
    return row;
}


public void setType(String type){
    this.type = type;
}


public void setText(String text){
    this.text = text;
}


}