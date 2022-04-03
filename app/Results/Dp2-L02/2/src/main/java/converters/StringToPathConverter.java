package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import domain.Path;
import repositories.PathRepository;
public class StringToPathConverter implements Converter<String, Path>{

@Autowired
 private PathRepository PathRepository;


@Override
public Path convert(String text){
    Path result = new Path();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.PathRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}