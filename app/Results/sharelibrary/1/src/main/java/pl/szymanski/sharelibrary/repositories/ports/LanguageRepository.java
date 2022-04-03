package pl.szymanski.sharelibrary.repositories.ports;
 import pl.szymanski.sharelibrary.entity.Language;
import java.util.Optional;
import java.util.Set;
public interface LanguageRepository {


public Set<Language> getAll()
;

public Optional<Language> getLanguageById(Integer id)
;

}