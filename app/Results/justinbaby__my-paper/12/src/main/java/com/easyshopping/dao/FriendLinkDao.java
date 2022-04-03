package com.easyshopping.dao;
 import java.util.List;
import com.easyshopping.entity.FriendLink;
import com.easyshopping.entity.FriendLink.Type;
public interface FriendLinkDao extends BaseDao<FriendLink, Long>{


public List<FriendLink> findList(Type type)
;

}