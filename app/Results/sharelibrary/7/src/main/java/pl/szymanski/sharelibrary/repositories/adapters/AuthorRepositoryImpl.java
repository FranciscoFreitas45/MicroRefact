package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.Author;
import pl.szymanski.sharelibrary.repositories.jpa.AuthorJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.AuthorRepository;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository{

 private  AuthorJPARepository authorJPARepository;


@Override
public List<Author> findAuthorByNameOrSurname(String name,String surname){
    return authorJPARepository.findAuthorsByNameIgnoreCaseOrSurnameIgnoreCase(name, surname);
}


@Override
public Optional<Author> findAuthorByNameAndSurname(String name,String surname){
    return authorJPARepository.findAuthorByNameIgnoreCaseAndSurnameIgnoreCase(name, surname);
}


}