package pl.szymanski.sharelibrary.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.repositories.jpa.LanguageJPARepository;
import pl.szymanski.sharelibrary.entity.Language;
@Service
public class LanguageBookService {

@Autowired
 private LanguageJPARepository languagejparepository;


}