package com.ukefu.util.ai;
 import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.web.model.AiUser;
import com.ukefu.webim.web.model.Scene;
import DTO.AiUser;
public class AiDicTrie {

 private  AiDic trie;

 private  AtomicInteger words;

 private  AtomicInteger sceneitems;

 private  BiMap<String,Integer> dicMap;

 private  BiMap<String,Integer> itemMap;


public int getWordInx(String word){
    if (dicMap.get(word) == null) {
        dicMap.put(word, words.incrementAndGet());
    }
    return dicMap.get(word);
}


public String search(String content,AiUser aiUser){
    String sceneitemid = null;
    AiDic.Node node = trie.search(content);
    if (node != null) {
        if (node.id != null) {
            // 准确匹配 ， 直接找到对话场景下的内容
            // id = node.id[0] ;	//有多条问答，最常见的是 按地区 区分问答
            for (int temp : node.id) {
                // "scene."+scene.getId()
                String sceneid = itemMap.inverse().get(temp);
                Scene scene = (Scene) CacheHelper.getSystemCacheBean().getCacheObject("scene." + sceneid, UKDataContext.SYSTEM_ORGI);
                if (scene != null) {
                    boolean result = OnlineUserUtils.filterSceneType(scene.getCate(), scene.getOrgi(), aiUser != null ? aiUser.getIpdata() : null);
                    if (result) {
                        sceneitemid = sceneid;
                        break;
                    }
                }
            }
        } else {
        // ID为空，未准确命中， 需要 决策 是否从下级对话中 返回结果
        }
    }
    return sceneitemid;
}


public BiMap<String,Integer> getDicMap(){
    return dicMap;
}


public void insert(String content,String id){
    if (itemMap.get(id) == null) {
        sceneitems.incrementAndGet();
        itemMap.put(id, sceneitems.intValue());
    }
    trie.insert(content, itemMap.get(id));
}


public void clean(){
    words = new AtomicInteger();
    sceneitems = new AtomicInteger();
    dicMap.clear();
    itemMap.clear();
    trie = new AiDic(this);
}


}