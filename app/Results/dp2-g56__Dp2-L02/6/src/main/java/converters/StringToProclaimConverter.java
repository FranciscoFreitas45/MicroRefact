package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.ProclaimRepository;
import domain.Proclaim;
@Component
@Transactional
public class StringToProclaimConverter implements Converter<String, Proclaim>{

@Autowired
 private ProclaimRepository proclaimRepository;


@Override
public Proclaim convert(String text){
    Proclaim result = new Proclaim();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.proclaimRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}