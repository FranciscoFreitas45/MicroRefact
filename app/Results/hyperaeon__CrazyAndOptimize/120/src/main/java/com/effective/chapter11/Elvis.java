package com.effective.chapter11;
 import java.io.Serializable;
import java.util.Arrays;
public class Elvis implements Serializable{

 public  Elvis INSTANCE;

 private  String[] favoriteSongs;

private Elvis() {
}
public Object readResolve(){
    return INSTANCE;
}


public void printFavorites(){
    System.out.println(Arrays.toString(favoriteSongs));
}


}