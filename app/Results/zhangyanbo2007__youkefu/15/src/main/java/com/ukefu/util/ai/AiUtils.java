package com.ukefu.util.ai;
 import java.io.IOException;
import java.util.List;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.SceneItemRepository;
import com.ukefu.webim.web.model.AiConfig;
import com.ukefu.webim.web.model.SceneItem;
public class AiUtils {

 private  AiDicTrie aiDicTrie;


public AiDicTrie init(String orgi){
    aiDicTrie.clean();
    SceneItemRepository sceneItemRes = UKDataContext.getContext().getBean(SceneItemRepository.class);
    List<SceneItem> sceneItemList = sceneItemRes.findByOrgiAndItemtype(orgi, UKDataContext.AiItemType.USERINPUT.toString());
    for (SceneItem item : sceneItemList) {
        aiDicTrie.insert(item.getContent(), item.getSceneid());
    }
    return aiDicTrie;
}


public AiConfig initAiConfig(String aiid,String orgi){
    return UKTools.initAiConfig(aiid, orgi);
}


public AiDicTrie getAiDicTrie(){
    return aiDicTrie;
}


}