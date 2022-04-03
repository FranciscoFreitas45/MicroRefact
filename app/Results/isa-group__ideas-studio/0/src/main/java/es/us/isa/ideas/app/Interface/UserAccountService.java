package es.us.isa.ideas.app.Interface;
public interface UserAccountService {

   public Collection<UserAccount> findAll();
   public UserAccount create(Actor actor);
}