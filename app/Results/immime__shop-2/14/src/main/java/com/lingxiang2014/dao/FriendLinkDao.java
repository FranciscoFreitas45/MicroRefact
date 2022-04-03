package com.lingxiang2014.dao;
 import java.util.List;
import com.lingxiang2014.entity.FriendLink;
import com.lingxiang2014.entity.FriendLink.Type;
public interface FriendLinkDao extends BaseDao<FriendLink, Long>{


public List<FriendLink> findList(Type type)
;

}