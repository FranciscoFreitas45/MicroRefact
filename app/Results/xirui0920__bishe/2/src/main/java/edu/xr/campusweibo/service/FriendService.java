package edu.xr.campusweibo.service;
 import edu.xr.campusweibo.domain.Friend;
import edu.xr.campusweibo.repository.FriendRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FriendService {

 private  Logger logger;

@Autowired
 private  FriendRepository friendRepository;


public void addFriend(Friend friend){
    friendRepository.save(friend);
}


public List<Friend> getAllFans(Long id){
    List list = friendRepository.findAllByFuid(id);
    if (list == null) {
        logger.info("未查到好友=============");
        return null;
    }
    return list;
}


public List<Friend> getAllFrid(Long id){
    List list = friendRepository.findAllByUid(id);
    if (list == null) {
        logger.info("未查到好友=============");
        return null;
    }
    return list;
}


public boolean isExist(Friend friend){
    Friend flag = friendRepository.findByUidAndFuid(friend.getUid(), friend.getFuid());
    if (flag == null) {
        return true;
    }
    return false;
}


public int getFridNum(Long id){
    int num = 0;
    List list = friendRepository.findAllByUid(id);
    if (list != null) {
        num = list.size();
    } else {
        logger.info("未找到好友============");
    }
    return num;
}


}