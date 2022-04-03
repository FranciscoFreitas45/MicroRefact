package com.ushahidi.swiftriver.core.Interface;
public interface RiverDao {

   public River findByName(String name);
   public Object create(Object Object);
   public Object update(Object Object);
   public List<Drop> getDrops(Long riverId,DropFilter filter,int page,int dropCount,Account queryingAccount);
   public Object delete(Object Object);
   public RiverCollaborator findCollaborator(Long riverId,Long accountId);
   public RiverCollaborator addCollaborator(River river,Account account,boolean readOnly);
   public void updateCollaborator(RiverCollaborator collaborator);
   public Object findById(Object Object);
   public RiverDrop findRiverDrop(Long id,Long dropId);
   public List<River> findAll(String searchTerm,int count,int page);
   public List<RiverTagTrend> getTrendingTags(Long riverId,TrendFilter trendFilter);
   public List<RiverTagTrend> getTrendingPlaces(Long riverId,TrendFilter trendFilter);
}