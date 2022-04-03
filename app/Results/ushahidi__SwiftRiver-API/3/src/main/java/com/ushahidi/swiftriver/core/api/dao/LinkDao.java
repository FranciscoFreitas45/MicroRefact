package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Link;
public interface LinkDao extends GenericDao<Link>{


public void getLinks(List<Drop> drops)
;

public Link findByHash(String hash)
;

}