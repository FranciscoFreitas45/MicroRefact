package com.example.steam.service;
 import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SensitiveKey;
import com.example.steam.utils.ResultMsg;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import com.example.steam.Interface.RedisService;
@Service
public class SensitiveWordService {

@Autowired
 private RedisService redisService;


public void addSensitiveWord(String word){
    List<String> sensitiveList = redisService.getList(SensitiveKey.WORD_LIST, SensitiveKey.SENSITIVE_KEY, String.class);
    if (sensitiveList == null) {
        sensitiveList = new LinkedList<>();
    }
    sensitiveList.add(word);
    redisService.set(SensitiveKey.WORD_LIST, SensitiveKey.SENSITIVE_KEY, sensitiveList);
}


public String sensitiveToVo(List<String> sensitiviList){
    String sensitiveVo = "";
    for (int i = 0; i < sensitiviList.size(); i++) {
        if (i < sensitiviList.size() - 1) {
            sensitiveVo += sensitiviList.get(i) + "|";
        } else {
            sensitiveVo += sensitiviList.get(i);
        }
    }
    return sensitiveVo;
}


public ResultMsg handleSaveWord(String[] words){
    List<String> wordList = getSensitiveWord();
    wordList.clear();
    for (int i = 0; i < words.length; i++) {
        wordList.add(words[i]);
    }
    redisService.set(SensitiveKey.WORD_LIST, SensitiveKey.SENSITIVE_KEY, wordList);
    return ResultMsg.SUCCESS;
}


public void deleteSensitiveWord(String word){
    List<String> sensitiveList = redisService.getList(SensitiveKey.WORD_LIST, SensitiveKey.SENSITIVE_KEY, String.class);
    sensitiveList.remove(word);
    redisService.set(SensitiveKey.WORD_LIST, SensitiveKey.SENSITIVE_KEY, sensitiveList);
}


public List<String> getSensitiveWord(){
    List<String> sensitiveList = redisService.getList(SensitiveKey.WORD_LIST, SensitiveKey.SENSITIVE_KEY, String.class);
    return sensitiveList;
}


public String sensitiveVo(){
    List<String> sensitiveList = redisService.getList(SensitiveKey.WORD_LIST, SensitiveKey.SENSITIVE_KEY, String.class);
    return sensitiveToVo(sensitiveList);
}


}