package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.model.Drop;
public interface DropDao extends GenericDao<Drop>{


public List<Drop> findAll(long sinceId,int batchSize)
;

public List<Drop> createDrops(List<Drop> drops)
;

}