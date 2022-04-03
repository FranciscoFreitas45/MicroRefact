package pl.szymanski.sharelibrary.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.repositories.jpa.CoverJPARepository;
import pl.szymanski.sharelibrary.entity.Cover;
@Service
public class CoverBookService {

@Autowired
 private CoverJPARepository coverjparepository;


}