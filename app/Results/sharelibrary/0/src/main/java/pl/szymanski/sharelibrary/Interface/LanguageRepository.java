package pl.szymanski.sharelibrary.Interface;
public interface LanguageRepository {

   public Optional<Language> getLanguageById(Integer id);
}