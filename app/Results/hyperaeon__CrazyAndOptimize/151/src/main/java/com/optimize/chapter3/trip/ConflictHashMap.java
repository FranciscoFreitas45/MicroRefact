package com.optimize.chapter3.trip;
 import java.util.HashMap;
import java.util.Map;
public class ConflictHashMap {


public void main(String[] args){
    Map<BadHash, Integer> map = new HashMap<BadHash, Integer>();
    BadHash b1 = new BadHash(1.0);
    BadHash b2 = new BadHash(2.0);
    map.put(b1, 1);
    map.put(b1, 2);
    map.put(b2, 2);
    map.put(b2, 1);
    System.out.println(map);
}


}