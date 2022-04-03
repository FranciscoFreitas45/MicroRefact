package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.model.Account;
public interface AccountDao extends GenericDao<Account>{


public Account findByAccountPath(String accountPath)
;

public Account findByUsernameOrEmail(String username)
;

public List<Account> search(String searchTerm,int count,int page)
;

public void decreaseRiverQuota(Account account,int decrement)
;

public Account getFollower(Account account,Long accountId)
;

public void addFollower(Account account,Account follower)
;

public void increaseRiverQuota(Account account,int increment)
;

public boolean deleteFollower(Account account,Account follower)
;

public Account findByEmail(String email)
;

}