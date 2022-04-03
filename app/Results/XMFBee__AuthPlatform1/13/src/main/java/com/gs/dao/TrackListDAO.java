package com.gs.dao;
 import com.gs.bean.TrackList;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TrackListDAO extends BaseDAO<String, TrackList>{


public int countName(TrackList trackList,User user)
;

public List<TrackList> queryByPagerName(Pager pager,TrackList trackList)
;

}