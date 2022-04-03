package com.tech.services.interfaces;
 import com.tech.models.entities.Friend;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
public interface IFriendService {


@Transactional
public List<Friend> getFriendsByYear(Long userid)
;

@Transactional
public void addFriend(Friend friend)
;

@Transactional
public List<Friend> getFriendsByUser(Long userid)
;

@Transactional
public List<Friend> getFriendsByMonth(Long userid)
;

@Transactional
public boolean checkFriendIfExists(Long userid,Long friendid)
;

@Transactional
public void deleteFriend(Friend friend)
;

@Transactional
public List<Friend> getAllFriends()
;

}