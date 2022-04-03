package com.kingen.shiro.credentials;
 import java.util.concurrent.atomic.AtomicInteger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import com.kingen.util.Global;
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{

 private  Cache<String,AtomicInteger> passwordRetryCache;

public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
    passwordRetryCache = cacheManager.getCache("passwordRetryCache");
}public RetryLimitHashedCredentialsMatcher() {
    super();
// TODO Auto-generated constructor stub
}public RetryLimitHashedCredentialsMatcher(String hashAlgorithmName) {
    super(hashAlgorithmName);
// TODO Auto-generated constructor stub
}
@Override
public boolean doCredentialsMatch(AuthenticationToken token,AuthenticationInfo info){
    String username = (String) token.getPrincipal();
    // retry count + 1
    AtomicInteger retryCount = passwordRetryCache.get(username);
    if (retryCount == null) {
        retryCount = new AtomicInteger(0);
        passwordRetryCache.put(username, retryCount);
    }
    if (retryCount.incrementAndGet() > Integer.valueOf(Global.getConfig("retryLimit")) - 1) {
        // if(retryCount.incrementAndGet() > 2) {
        // if retry count > 5 throw
        throw new ExcessiveAttemptsException();
    }
    boolean matches = super.doCredentialsMatch(token, info);
    if (matches) {
        // clear retry count
        passwordRetryCache.remove(username);
    }
    return matches;
}


}