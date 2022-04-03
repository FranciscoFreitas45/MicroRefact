package es.us.isa.ideas.app.converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.security.Authority;
@Component
@Transactional
public class AuthorityToString implements Converter<Authority, String>{


@Override
public String convert(Authority s){
    return s.getAuthority();
}


}