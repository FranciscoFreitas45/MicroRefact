package org.live.live.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.live.vo.LiveRoomVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.HashMap;
import java.util.Map;
public class LiveRoomRepositoryImpl extends BaseRepositoryImpl{

 private  String FIND_LIVEROOMS_XHQL;


public Page<LiveRoomVo> findLiveRooms(PageRequest page,String searchStr){
    Map<String, Object> filter = new HashMap<>();
    filter.put("account", searchStr);
    filter.put("realName", searchStr);
    filter.put("nickname", searchStr);
    filter.put("roomNum", searchStr);
    filter.put("roomName", searchStr);
    String hql = this.xsqlConvertHql(FIND_LIVEROOMS_XHQL, filter);
    hql = hql.replace("where ()", "").replace("( or", "(");
    return this.find(page, hql, null);
}


}