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
public class UserMenuFavorites {

 private  UserId adUserId;

 private  Set<Integer> menuIds;

 private  UserId adUserId;

 private  Set<Integer> menuIds;


public UserId getAdUserId(){
    return adUserId;
}


public MenuTreeRepository.UserMenuFavorites build(){
    return new UserMenuFavorites(this);
}


public Builder builder(){
    return new Builder();
}


public Builder adUserId(UserId adUserId){
    this.adUserId = adUserId;
    return this;
}


public void setFavorite(int adMenuId,boolean favorite){
    if (favorite) {
        menuIds.add(adMenuId);
    } else {
        menuIds.remove(adMenuId);
    }
}


public Builder addMenuIds(List<Integer> adMenuIds){
    if (adMenuIds.isEmpty()) {
        return this;
    }
    menuIds.addAll(adMenuIds);
    return this;
}


public boolean isFavorite(MenuNode menuNode){
    return menuIds.contains(menuNode.getAD_Menu_ID());
}


}