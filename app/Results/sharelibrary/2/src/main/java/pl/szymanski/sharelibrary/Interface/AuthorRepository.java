package pl.szymanski.sharelibrary.Interface;
public interface AuthorRepository {

   public List<Author> findAuthorByNameOrSurname(String name,String surname);
   public Optional<Author> findAuthorByNameAndSurname(String name,String surname);
}