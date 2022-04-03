package com.example.steam.utils;
 import com.example.steam.entity.Label;
import java.util.Comparator;
public class HotComparator implements Comparator{


@Override
public int compare(Object o1,Object o2){
    Label label1 = (Label) o1;
    Label label2 = (Label) o2;
    if (label1.getHotNum() > label2.getHotNum()) {
        System.out.println("大于");
        return -1;
    } else if (label1.getHotNum() < label2.getHotNum()) {
        return 1;
    }
    return 0;
}


}