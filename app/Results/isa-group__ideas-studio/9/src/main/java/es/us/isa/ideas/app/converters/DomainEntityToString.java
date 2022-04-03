package es.us.isa.ideas.app.converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.entities.DomainEntity;
@Component
@Transactional
public class DomainEntityToString implements Converter<X, String>{


@Override
public String convert(X s){
    String result = null;
    if (s != null) {
        result = String.valueOf(s.getId());
    }
    return result;
}


}