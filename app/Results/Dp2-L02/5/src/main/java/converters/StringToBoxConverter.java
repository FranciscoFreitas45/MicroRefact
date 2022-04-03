package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.BoxRepository;
import domain.Box;
@Component
@Transactional
public class StringToBoxConverter implements Converter<String, Box>{

@Autowired
 private BoxRepository boxRepository;


@Override
public Box convert(String text){
    Box result = new Box();
    int id;
    try {
        if (StringUtils.isEmpty(text)) {
            result = null;
        } else {
            id = Integer.valueOf(text);
            result = this.boxRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}