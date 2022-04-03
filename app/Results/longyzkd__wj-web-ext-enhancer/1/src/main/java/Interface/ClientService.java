package Interface;
public interface ClientService {

   public Object find(Object Object);
   public Page<ClientContact> findClientContacts(Page<ClientContact> page,String clientId);
   public Object unique(Object Object);
   public Object uniqueEntity(Object Object);
   public void delThemCascade(String string,List<String> ids);
   public Object add(Object Object);
   public void update(ClientContact c);
   public void save(String clientId,ClientContact data);
}