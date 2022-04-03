package org.live.dictionary.cache;
 import org.live.dictionary.entity.DictType;
import org.live.dictionary.entity.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CacheDict {

 public  Map<String,DictType> allTypes;

 public  Map<String,List<Dictionary>> dictList;

 public  Map<String,Map<String,String>> dictMap;

 public  Map<String,String> allDictMap;


}