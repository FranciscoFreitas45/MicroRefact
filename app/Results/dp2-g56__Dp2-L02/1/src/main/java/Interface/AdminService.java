package Interface;
public interface AdminService {

   public Admin loggedAdmin();
   public Admin reconstruct(Admin admin,BindingResult binding);
   public Admin updateAdmin(Admin admin);
}