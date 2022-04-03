package hei2017.Interface;
public interface UserDAO {

   public Object findAll(Object Object);
   public Object findOne(Object Object);
   public User findOneById(Long id);
   public User findOneByNomAndPrenom(String nom,String prenom);
   public User findOneByPseudo(String pseudo);
   public Object save(Object Object);
   public long count();
   public Object delete(Object Object);
   public Object exists(Object Object);
   public User findOneByEmail(String email);
}