package pl.szymanski.sharelibrary.Interface;
public interface AuthorJPARepository {

   public Optional<Author> findAuthorByNameIgnoreCaseAndSurnameIgnoreCase(String name,String surname);
   public List<Author> findAuthorsByNameIgnoreCaseOrSurnameIgnoreCase(String name,String surname);
}