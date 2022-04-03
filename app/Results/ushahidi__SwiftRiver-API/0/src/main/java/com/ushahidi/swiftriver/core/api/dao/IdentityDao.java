package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Identity;
public interface IdentityDao extends GenericDao<Identity>{


public void getIdentities(List<Drop> drops)
;

}