package es.us.isa.ideas.app.converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.security.Authority;
@Component
@Transactional
public class StringToAuthority implements Converter<String, Authority>{


@Override
public Authority convert(String s){
    return Authority.get(s);
}


}