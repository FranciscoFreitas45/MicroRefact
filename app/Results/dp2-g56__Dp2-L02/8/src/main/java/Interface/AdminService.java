package Interface;
public interface AdminService {

   public Admin getSystem();
   public Admin getAdminByUsername(String username);
   public void banSuspiciousActor(Actor a);
   public void unBanSuspiciousActor(Actor a);
   public Admin reconstruct(Admin admin,BindingResult binding);
   public void broadcastMessage(Message message);
}