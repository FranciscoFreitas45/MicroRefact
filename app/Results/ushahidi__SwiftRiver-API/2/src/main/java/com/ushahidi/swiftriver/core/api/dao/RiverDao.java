package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.api.filter.DropFilter;
import com.ushahidi.swiftriver.core.api.filter.TrendFilter;
import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.River;
import com.ushahidi.swiftriver.core.model.RiverCollaborator;
import com.ushahidi.swiftriver.core.model.RiverDrop;
import com.ushahidi.swiftriver.core.model.RiverTagTrend;
public interface RiverDao extends GenericDao<River>{


public boolean removeDrop(Long id,Long dropId)
;

public RiverCollaborator findCollaborator(Long riverId,Long accountId)
;

public List<RiverTagTrend> getTrendingTags(Long riverId,TrendFilter trendFilter)
;

public List<RiverTagTrend> getTrendingPlaces(Long riverId,TrendFilter trendFilter)
;

public River findByName(String name)
;

public List<Drop> getDrops(Long riverId,DropFilter filter,int page,int dropCount,Account queryingAccount)
;

public List<River> findAll(String searchTerm,int count,int page)
;

public RiverCollaborator addCollaborator(River river,Account account,boolean readOnly)
;

public void updateCollaborator(RiverCollaborator collaborator)
;

public RiverDrop findRiverDrop(Long id,Long dropId)
;

}