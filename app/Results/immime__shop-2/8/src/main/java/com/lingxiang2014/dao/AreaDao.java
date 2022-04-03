package com.lingxiang2014.dao;
 import java.util.List;
import com.lingxiang2014.entity.Area;
public interface AreaDao extends BaseDao<Area, Long>{


public List<Area> findRoots(Integer count)
;

}