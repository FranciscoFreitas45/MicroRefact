package es.us.isa.ideas.app.Interface;
public interface ResearcherRepository {

   public Researcher findByUserAccountId(int id);
   public Researcher findByUsername(String username);
   public Researcher findByEmail(String email);
   public Object findAll(Object Object);
   public Object save(Object Object);
   public Object delete(Object Object);
}