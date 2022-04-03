package com.ushahidi.swiftriver.core.api.dao;
 import com.ushahidi.swiftriver.core.model.Sequence;
public interface SequenceDao extends GenericDao<Sequence>{


public long getIds(Sequence seq,int num)
;

}