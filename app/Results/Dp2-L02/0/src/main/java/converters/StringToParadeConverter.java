package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.ParadeRepository;
import domain.Parade;
@Component
@Transactional
public class StringToParadeConverter implements Converter<String, Parade>{

@Autowired
 private ParadeRepository paradeRepository;


@Override
public Parade convert(String text){
    Parade result = new Parade();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.paradeRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}