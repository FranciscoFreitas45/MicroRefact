package edu.nju.careerbridge.util;
 import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class DFA {

 final  Map<String,Set<Integer>> Dstates;

 final  Map<String,Map<Character,Set<Integer>>> Dtran;

 final  Map<String,Map<Character,String>> UsableDtran;

public DFA() {
}
public void trans(){
    for (Map.Entry<String, Map<Character, Set<Integer>>> entry : Dtran.entrySet()) {
        Map<Character, Set<Integer>> map1 = entry.getValue();
        Map<Character, String> newMap = new HashMap<Character, String>();
        for (Map.Entry<Character, Set<Integer>> en : map1.entrySet()) {
            Set<Integer> set = en.getValue();
            String name = "";
            for (Map.Entry<String, Set<Integer>> s : Dstates.entrySet()) {
                if (s.getValue().equals(set)) {
                    name = s.getKey();
                    newMap.put(en.getKey(), name);
                    break;
                }
            }
        }
        UsableDtran.put(entry.getKey(), newMap);
    }
}


}