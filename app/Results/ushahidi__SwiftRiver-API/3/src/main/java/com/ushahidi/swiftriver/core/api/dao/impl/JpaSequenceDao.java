package com.ushahidi.swiftriver.core.api.dao.impl;
 import javax.persistence.LockModeType;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.SequenceDao;
import com.ushahidi.swiftriver.core.model.Sequence;
@Repository
public class JpaSequenceDao extends AbstractJpaDao<Sequence>implements SequenceDao{


@Override
public Sequence findById(Object id){
    return (Sequence) em.find(Sequence.class, id, LockModeType.PESSIMISTIC_WRITE);
}


public long getIds(Sequence seq,int num){
    long start = seq.getId();
    seq.setId(seq.getId() + num);
    em.merge(seq);
    return start + 1;
}


}