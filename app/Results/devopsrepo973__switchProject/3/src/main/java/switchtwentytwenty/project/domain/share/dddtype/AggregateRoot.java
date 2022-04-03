package switchtwentytwenty.project.domain.share.dddtype;
 import switchtwentytwenty.project.domain.share.id.ID;
public interface AggregateRoot extends Entity<T, K>{


public K getID()
;

public boolean hasSameID(K objectID)
;

}