package de.metas.ui.web.menu.MenuTreeRepository;
 import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.base.MoreObjects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import de.metas.logging.LogManager;
import de.metas.security.IUserRolePermissionsDAO;
import de.metas.security.UserRolePermissionsKey;
import de.metas.ui.web.session.UserSession;
import de.metas.user.UserId;
import de.metas.user.api.IUserMenuFavoritesDAO;
import de.metas.util.Check;
import de.metas.util.Services;
public class MenuTreeKey {

 private  UserRolePermissionsKey userRolePermissionsKey;

 private  String adLanguage;


@Override
public int hashCode(){
    return Objects.hash(userRolePermissionsKey, adLanguage);
}


public UserRolePermissionsKey getUserRolePermissionsKey(){
    return userRolePermissionsKey;
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj instanceof MenuTreeKey) {
        final MenuTreeKey other = (MenuTreeKey) obj;
        return Objects.equals(userRolePermissionsKey, other.userRolePermissionsKey) && Objects.equals(adLanguage, other.adLanguage);
    } else {
        return false;
    }
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("adLanguage", adLanguage).addValue(userRolePermissionsKey).toString();
}


public String getAD_Language(){
    return adLanguage;
}


}