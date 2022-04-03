package com.example.steam.utils;
 import com.example.steam.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util;
@Component
public class SensitiveWordUtil {

@Autowired
 private SensitiveWordService sensitiveWordService;

@Autowired
 private StaticField staticField;

 private  Map<String,String> keyWordMap;

 private  String replaceString;

 private  Map<Character,String> ignoreWord;

// {
// try {
// init();
// for (int i=0;i< StaticField.IgnoreWords.length;i++){
// ignoreWord.put(StaticField.IgnoreWords[i],"1");
// }
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
public SensitiveWordUtil() {
}public SensitiveWordUtil(String replaceString) throws IOException {
    this.replaceString = replaceString;
// init();
}
public void init(){
    Set<String> set = readKeyWordFromFile();
    addKeyWordtoHashMap(set);
    ignoreWord = new HashMap<>();
    for (int i = 0; i < staticField.getIGNORE_WORD().length; i++) {
        ignoreWord.put(staticField.getIGNORE_WORD()[i], "1");
    }
}


public Set<String> readKeyWordFromFile(){
    Set<String> set = new HashSet<>();
    // //不指定字符集会乱码
    // InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(new File("F://SensitiveWord.txt")),"GBK");
    // BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
    // String line=bufferedReader.readLine();
    // while (line!=null){
    // set.add(line);
    // line=bufferedReader.readLine();
    // }
    // 读取敏感词汇
    List<String> list = sensitiveWordService.getSensitiveWord();
    set.addAll(list);
    return set;
}


public void addKeyWordtoHashMap(Set<String> keyWordSet){
    keyWordMap = new HashMap<String, String>();
    String key = null;
    Map nowMap = null;
    Map<String, String> newWorMap = null;
    Iterator<String> iterator = keyWordSet.iterator();
    while (iterator.hasNext()) {
        key = iterator.next();
        nowMap = keyWordMap;
        for (int i = 0; i < key.length(); i++) {
            char keyChar = key.charAt(i);
            Object wordMap = nowMap.get(keyChar);
            if (wordMap != null) {
                nowMap = (Map) wordMap;
            } else {
                newWorMap = new HashMap<String, String>();
                // End为结束标志，1表示为该分支已完成，0表示该分支还未完成
                newWorMap.put("End", "0");
                nowMap.put(keyChar, newWorMap);
                nowMap = newWorMap;
            }
            if (i == key.length() - 1) {
                nowMap.put("End", "1");
            }
        }
    }
// System.out.println("语法树");
// System.out.println(keyWordMap);
}


public String replaceSensitiveWord(String text){
    String result = text;
    Set<String> set = getSensitiveWord(text);
    Iterator<String> iterator = set.iterator();
    String word = null;
    String replaceString = null;
    while (iterator.hasNext()) {
        word = iterator.next();
        result = result.replaceAll(word, replaceString);
    }
    return result;
}


public Set<String> getSensitiveWord(String text){
    Set<String> sensitiveWordList = new HashSet<String>();
    for (int i = 0; i < text.length(); i++) {
        int length = checkSensitiveWord(i, text);
        if (length > 0) {
            // 将敏感词给截取下来，存入集合中
            sensitiveWordList.add(text.substring(i, i + length));
            // text.substring(i,i+length);
            i = i + length - 1;
        }
    }
    return sensitiveWordList;
}


public int checkSensitiveWord(int beginIndex,String text){
    Map newWordMap = keyWordMap;
    // 记录扫描敏感词而产生的偏移量
    int indexCount = 0;
    // 用来记录是否检测到敏感词
    boolean isContainSensitiveWord = false;
    char temp = 0;
    for (int i = beginIndex; i < text.length(); i++) {
        temp = text.charAt(i);
        // 忽略无效字符
        if (ignoreWord.get(temp) != null) {
            indexCount++;
            continue;
        }
        newWordMap = (Map) newWordMap.get(temp);
        if (newWordMap != null) {
            indexCount++;
            if ("1".equals(newWordMap.get("End"))) {
                isContainSensitiveWord = true;
            }
        } else {
            break;
        }
    }
    // 敏感词长度不能小于2
    if (indexCount < 2 || !isContainSensitiveWord) {
        indexCount = 0;
    }
    // System.out.println(indexCount+"jjjj");
    return indexCount;
}


}