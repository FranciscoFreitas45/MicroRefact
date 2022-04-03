package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Tag;
public interface TagDao extends GenericDao<Tag>{


public void getTags(List<Drop> drops)
;

public Tag findByHash(String hash)
;

}