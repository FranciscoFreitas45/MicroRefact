package com.gs.service;
 import com.gs.bean.TrackList;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface TrackListService extends BaseService<String, TrackList>{


public int countName(TrackList trackList,User user)
;

public List<TrackList> queryByPagerName(Pager pager,TrackList trackList)
;

}