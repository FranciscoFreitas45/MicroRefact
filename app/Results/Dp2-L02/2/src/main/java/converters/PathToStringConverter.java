package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Path;
@Component
@Transactional
public class PathToStringConverter implements Converter<Path, String>{


@Override
public String convert(Path path){
    String result;
    if (path == null)
        result = null;
    else
        result = String.valueOf(path.getId());
    return result;
}


}