public class SampleClass {

 private int value;

 private String name;


public void setName(String name){
    this.name = name;
}


public int getValue(){
    return value;
}


public String getName(){
    return name;
}


public void setValue(int value){
    this.value = value;
}


@Override
public String toString(){
    return "SampleClass [value=" + value + ", name=" + name + "]";
}


}