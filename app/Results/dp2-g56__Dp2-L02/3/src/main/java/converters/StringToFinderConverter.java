package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.FinderRepository;
import domain.Finder;
@Component
@Transactional
public class StringToFinderConverter implements Converter<String, Finder>{

@Autowired
 private FinderRepository finderRepository;


@Override
public Finder convert(String text){
    Finder result = new Finder();
    int id;
    try {
        if (StringUtils.isEmpty(text)) {
            result = null;
        } else {
            id = Integer.valueOf(text);
            result = this.finderRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}