package com.ushahidi.swiftriver.core.api.dao;
 import java.util.ArrayList;
import java.util.List;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Media;
public interface MediaDao extends GenericDao<Media>{


public void getMedia(List<Drop> drops)
;

public List<Media> findByHash(ArrayList<String> mediaHashes)
;

}