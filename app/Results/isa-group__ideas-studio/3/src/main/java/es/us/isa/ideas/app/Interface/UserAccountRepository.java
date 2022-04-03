package es.us.isa.ideas.app.Interface;
public interface UserAccountRepository {

   public UserAccount findByUsername(String username);
}