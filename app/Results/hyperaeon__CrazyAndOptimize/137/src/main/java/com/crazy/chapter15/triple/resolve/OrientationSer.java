package com.crazy.chapter15.triple.resolve;
 import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.crazy.chapter15.triple.Constants;
public class OrientationSer {


public void main(String[] args){
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.currentPath + "orient.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.currentPath + "orient.txt"))) {
        oos.writeObject(Orientation.HORIZONTAL);
        Orientation ori = (Orientation) ois.readObject();
        System.out.println(ori == Orientation.HORIZONTAL);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}