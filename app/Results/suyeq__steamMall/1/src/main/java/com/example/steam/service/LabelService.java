package com.example.steam.service;
 import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.LabelDao;
import com.example.steam.entity.GameLabel;
import com.example.steam.entity.Label;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.utils.HotComparator;
import com.example.steam.utils.ResultMsg;
import com.example.steam.vo.GameDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import com.example.steam.Interface.RedisService;
import com.example.steam.DTO.GameDetail;
import com.example.steam.DTO.ResultMsg;
@Service
public class LabelService {

@Autowired
 private LabelDao labelDao;

@Autowired
 private RedisService redisService;

@Autowired
 private ApplicationContext applicationContext;

 private Logger log;


public int addLabel(Label label){
    return labelDao.addLabel(label);
}


public int deleteLabelById(long labelId){
    return labelDao.deleteLabelByLabelId(labelId);
}


@Transactional(rollbackFor = Exception.class)
public List<String> findLabelNamesByGameId(long gameId){
    List<GameLabel> gameLabelList = labelDao.findLabelsByGameId(gameId);
    List<String> labelList = new LinkedList<>();
    for (int i = 0; i < gameLabelList.size(); i++) {
        GameLabel gameLabel = gameLabelList.get(i);
        labelList.add(labelDao.findLabelNameById(gameLabel.getLabelId()));
    }
    return labelList;
}


public Label findLabelByLabelName(String labelName,String dataSource){
    DynamicDataSourceHolder.putDataSource(dataSource);
    return labelDao.findLabelByLableName(labelName);
}


public List<Label> findLabelInGame(long gameId){
    List<GameLabel> gameLabelList = labelDao.findLabelsByGameId(gameId);
    List<Label> labelList = new LinkedList<>();
    for (GameLabel gameLabel : gameLabelList) {
        Label label = new Label(gameLabel.getLabelId(), labelDao.findLabelNameById(gameLabel.getLabelId()), gameLabel.getHotNum());
        labelList.add(label);
    }
    return labelList;
}


public List<Label> findAllLabel(){
    return labelDao.findAllLabel();
}


public int deleteGameLabelByGameId(long gameId){
    List<GameLabel> gameLabelList = labelDao.findLabelsByGameId(gameId);
    if (gameLabelList != null) {
        return labelDao.deleteGameLabelByGameId(gameId);
    }
    return -1;
}


public ResultMsg addLabelInGame(long gameId,String labelName){
    Label label1 = labelDao.findLabelByLableName(labelName);
    GameDetail gameDetail = redisService.get(GameKey.GAME_ID, gameId + "", GameDetail.class);
    List<String> labelList = gameDetail.getLabel();
    labelList.add(labelName);
    gameDetail.setLabel(labelList);
    redisService.set(GameKey.GAME_ID, gameId + "", gameDetail);
    if (label1 != null) {
        Label label2 = labelDao.findLabelByLabelIdAndGameId(gameId, label1.getId());
        if (label2 != null) {
            return ResultMsg.LABEL_PRESENCE;
        } else {
            GameLabel gameLabel = new GameLabel(0L, gameId, label1.getId(), 0);
            labelDao.addLabelInGame(gameLabel);
        }
    } else {
        label1 = new Label(0L, labelName, 0);
        labelDao.addLabel(label1);
        GameLabel gameLabel = new GameLabel(0L, gameId, label1.getId(), 0);
        labelDao.addLabelInGame(gameLabel);
    }
    return ResultMsg.SUCCESS(label1.getId());
}


@Transactional(rollbackFor = Exception.class)
public ResultMsg updateHotNumByGameId(long gameId,long labelId){
    int result = labelDao.labelHotNumIncr(gameId, labelId);
    GameDetail gameDetail = redisService.get(GameKey.GAME_ID, gameId + "", GameDetail.class);
    List<Label> labelList = ((LabelService) applicationContext.getBean("labelService")).findLabelInGame(gameId);
    PriorityQueue<Label> priorityQueue = new PriorityQueue<>(new HotComparator());
    for (Label label : labelList) {
        priorityQueue.add(label);
    }
    List<String> labelNameList = gameDetail.getLabel();
    labelNameList.clear();
    Iterator<Label> iterator = priorityQueue.iterator();
    while (iterator.hasNext()) {
        Label label = iterator.next();
        labelNameList.add(label.getName());
    }
    gameDetail.setLabel(labelNameList);
    redisService.set(GameKey.GAME_ID, gameId + "", gameDetail);
    return ResultMsg.SUCCESS(result);
}


@Transactional(rollbackFor = Exception.class)
public List<Label> findLabelsByGameId(long gameId){
    List<GameLabel> gameLabelList = labelDao.findLabelsByGameId(gameId);
    List<Label> labelList = new LinkedList<>();
    for (int i = 0; i < gameLabelList.size(); i++) {
        GameLabel gameLabel = gameLabelList.get(i);
        labelList.add(labelDao.findLabelByLabelId(gameLabel.getLabelId()));
    }
    return labelList;
}


}