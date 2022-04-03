package com.gs.service.impl;
 import com.gs.bean.TrackList;
import com.gs.bean.User;
import com.gs.dao.TrackListDAO;
import com.gs.service.TrackListService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.common.bean.Pager;
@Service
public class TrackListServiceImpl implements TrackListService{

@Resource
 private  TrackListDAO trackListDAO;


public List<TrackList> queryByPagerDisable(Pager pager){
    return trackListDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<TrackList> list){
    return trackListDAO.batchInsert(list);
}


public int batchUpdate(List<TrackList> list){
    return trackListDAO.batchUpdate(list);
}


public TrackList query(TrackList trackList){
    return trackListDAO.query(trackList);
}


public int count(User user){
    return trackListDAO.count(user);
}


public int insert(TrackList trackList){
    return trackListDAO.insert(trackList);
}


public int update(TrackList trackList){
    return trackListDAO.update(trackList);
}


public int active(String id){
    return trackListDAO.active(id);
}


@Override
public List<TrackList> queryAll(String status){
    return trackListDAO.queryAll(status);
}


public List<TrackList> blurredQuery(Pager pager,TrackList trackList){
    return null;
}


@Override
public List<TrackList> queryByPagerName(Pager pager,TrackList trackList){
    return trackListDAO.queryByPagerName(pager, trackList);
}


public int delete(TrackList trackList){
    return trackListDAO.delete(trackList);
}


public int batchDelete(List<TrackList> list){
    return trackListDAO.batchDelete(list);
}


public List<TrackList> queryByStatus(String status){
    return trackListDAO.queryAll(status);
}


public int inactive(String id){
    return trackListDAO.inactive(id);
}


public int countByBlurred(TrackList trackList){
    return 0;
}


@Override
public int countName(TrackList trackList,User user){
    return trackListDAO.countName(trackList, user);
}


public int deleteById(String id){
    return trackListDAO.deleteById(id);
}


public int countByDisable(User user){
    return trackListDAO.countByDisable(user);
}


public TrackList queryById(String id){
    return trackListDAO.queryById(id);
}


public List<TrackList> queryByPager(Pager pager){
    return trackListDAO.queryByPager(pager);
}


}