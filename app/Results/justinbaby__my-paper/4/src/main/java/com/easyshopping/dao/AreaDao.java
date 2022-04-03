package com.easyshopping.dao;
 import java.util.List;
import com.easyshopping.entity.Area;
public interface AreaDao extends BaseDao<Area, Long>{


public List<Area> findRoots(Integer count)
;

}