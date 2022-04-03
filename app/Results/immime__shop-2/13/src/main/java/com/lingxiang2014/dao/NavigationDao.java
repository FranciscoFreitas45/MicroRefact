package com.lingxiang2014.dao;
 import java.util.List;
import com.lingxiang2014.entity.Navigation;
import com.lingxiang2014.entity.Navigation.Position;
public interface NavigationDao extends BaseDao<Navigation, Long>{


public List<Navigation> findList(Position position)
;

}