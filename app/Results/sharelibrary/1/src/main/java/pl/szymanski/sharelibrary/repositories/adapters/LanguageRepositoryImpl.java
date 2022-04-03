package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.Language;
import pl.szymanski.sharelibrary.repositories.jpa.LanguageJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.LanguageRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Repository
@RequiredArgsConstructor
public class LanguageRepositoryImpl implements LanguageRepository{

 private  LanguageJPARepository languageJPARepository;


@Override
public Set<Language> getAll(){
    return new HashSet<>(languageJPARepository.findAll());
}


@Override
public Optional<Language> getLanguageById(Integer id){
    return languageJPARepository.findById(id);
}


}