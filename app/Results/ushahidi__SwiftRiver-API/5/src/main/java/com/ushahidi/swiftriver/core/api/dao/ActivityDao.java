package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.model.Activity;
public interface ActivityDao extends GenericDao<Activity>{


public List<Activity> find(long accountId,Integer count,Long lastId,Boolean newer,Boolean followers)
;

}