package sn.Interface;
public interface PersonRepository {

   public List<Person> findFriends(long id,int offset,int itemPerPage,String name);
   public Object findById(Object Object);
   public List<Person> findRequests(long id,int offset,int itemPerPage,String name);
   public List<Person> findRecommendedFriends(long id,String city,Integer offset,int itemPerPage);
}