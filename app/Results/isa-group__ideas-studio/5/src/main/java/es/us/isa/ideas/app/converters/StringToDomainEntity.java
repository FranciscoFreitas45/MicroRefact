package es.us.isa.ideas.app.converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.entities.DomainEntity;
import es.us.isa.ideas.app.services.BusinessService;
@Component
@Transactional
public class StringToDomainEntity implements Converter<String, X>{


public BusinessService<X> getBusinessService()


@Override
public X convert(String text){
    X result;
    int id;
    try {
        id = Integer.valueOf(text);
        result = getBusinessService().findById(id);
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}