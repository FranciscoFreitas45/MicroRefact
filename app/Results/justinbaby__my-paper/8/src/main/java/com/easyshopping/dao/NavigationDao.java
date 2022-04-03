package com.easyshopping.dao;
 import java.util.List;
import com.easyshopping.entity.Navigation;
import com.easyshopping.entity.Navigation.Position;
public interface NavigationDao extends BaseDao<Navigation, Long>{


public List<Navigation> findList(Position position)
;

}