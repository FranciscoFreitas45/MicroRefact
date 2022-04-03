package pl.szymanski.sharelibrary.Interface;
public interface LanguageRepository {

   public Set<Language> getAll();
   public Optional<Language> getLanguageById(Integer id);
}