package com.example.steam.utils;
 import com.example.steam.vo.SpecialGame;
import java.util.Comparator;
public class TimeComparator implements Comparator<SpecialGame>{


@Override
public int compare(SpecialGame o1,SpecialGame o2){
    if (o1.getId() < o2.getId()) {
        return -1;
    } else if (o1.getId() > o2.getId()) {
        return 1;
    }
    return 0;
}


}