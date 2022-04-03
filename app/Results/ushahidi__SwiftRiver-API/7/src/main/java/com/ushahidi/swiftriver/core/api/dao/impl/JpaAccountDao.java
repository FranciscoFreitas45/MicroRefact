package com.ushahidi.swiftriver.core.api.dao.impl;
 import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.AccountDao;
import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.AccountFollower;
@Repository
public class JpaAccountDao extends AbstractJpaDao<Account>implements AccountDao{

 private  Logger logger;


public Account findByAccountPath(String accountPath){
    String query = "SELECT a FROM Account a WHERE a.accountPath = :account_path";
    Account account = null;
    try {
        account = (Account) em.createQuery(query).setParameter("account_path", accountPath).getSingleResult();
    } catch (NoResultException e) {
    // Do nothing;
    }
    return account;
}


public Account findByUsernameOrEmail(String username){
    String qlString = "SELECT a FROM Account a JOIN a.owner o " + "WHERE o.username = :username OR o.email = :email";
    Account account = null;
    try {
        TypedQuery<Account> query = em.createQuery(qlString, Account.class);
        query.setParameter("username", username);
        query.setParameter("email", username);
        account = query.getSingleResult();
    } catch (NoResultException e) {
        logger.info("Account associated with {} not found", username);
    }
    return account;
}


public List<Account> search(String searchTerm,int count,int page){
    // Calculate the offset
    int offset = count * (page - 1);
    return getSearchResultList("%" + searchTerm + "%", count, offset);
}


public void decreaseRiverQuota(Account account,int decrement){
    int updatedQuota = account.getRiverQuotaRemaining() - decrement;
    account.setRiverQuotaRemaining(updatedQuota);
    update(account);
}


public Account getFollower(Account account,Long accountId){
    String qlString = "FROM AccountFollower " + "WHERE account = :account " + "AND follower.id = :follower";
    TypedQuery<AccountFollower> query = this.em.createQuery(qlString, AccountFollower.class);
    query.setParameter("account", account);
    query.setParameter("follower", accountId);
    List<AccountFollower> followers = query.getResultList();
    return followers.isEmpty() ? null : followers.get(0).getFollower();
}


public void addFollower(Account account,Account follower){
    AccountFollower accountFollower = new AccountFollower();
    accountFollower.setAccount(account);
    accountFollower.setFollower(follower);
    accountFollower.setDateAdded(new Date());
    this.em.persist(accountFollower);
}


public void increaseRiverQuota(Account account,int increment){
    int updatedQuota = account.getRiverQuotaRemaining() + increment;
    account.setRiverQuotaRemaining(updatedQuota);
    update(account);
}


public boolean deleteFollower(Account account,Account follower){
    String sql = "DELETE FROM AccountFollower " + "WHERE account = :account " + "AND follower = :follower";
    Query query = em.createQuery(sql);
    query.setParameter("account", account);
    query.setParameter("follower", follower);
    return query.executeUpdate() == 1;
}


@Override
public Account update(Account t){
    t.setDateModified(new Date());
    return super.update(t);
}


public Account findByEmail(String email){
    String query = "FROM Account WHERE owner.email = :email";
    Account account = null;
    try {
        account = em.createQuery(query, Account.class).setParameter("email", email).getSingleResult();
    } catch (NoResultException e) {
        logger.debug("No account has been registered to {}", email);
    }
    return account;
}


public List<Account> getSearchResultList(String searchTerm,int count,int offset){
    String qlString = "SELECT a FROM Account a WHERE a.accountPath like :q " + "OR a.owner.name like :q OR a.owner.email like :q";
    List<Account> accounts = null;
    try {
        TypedQuery<Account> query = em.createQuery(qlString, Account.class);
        query.setParameter("q", searchTerm);
        query.setMaxResults(count);
        query.setFirstResult(offset);
        accounts = query.getResultList();
    } catch (NoResultException e) {
    // Do nothing;
    }
    return accounts;
}


public Account getAccount(long idQGFJ)

public void setAccount(long idQGFJ,Account account)

public Account getAccount(long id159A)

public void setAccount(long id159A,Account account)

public Account getAccount(long idG79G)

public void setAccount(long idG79G,Account account)

public Account getAccount(long idWINU)

public void setAccount(long idWINU,Account account)

public Account getAccount(long idXN57)

public void setAccount(long idXN57,Account account)

public Account getAccount(long idLW0Z)

public void setAccount(long idLW0Z,Account account)

public Account getAccount(long idVO82)

public void setAccount(long idVO82,Account account)

public Account getAccount(long idXXQN)

public void setAccount(long idXXQN,Account account)

public Account getAccount(long id0O8K)

public void setAccount(long id0O8K,Account account)

public Account getActionTo(long idTMNI)

public void setActionTo(long idTMNI,Account actionTo)

public Account getAccount(long idTMNI)

public void setAccount(long idTMNI,Account account)

public Account getActionOnObj(long idD2H4)

public void setActionOnObj(long idD2H4,Account actionOnObj)

}