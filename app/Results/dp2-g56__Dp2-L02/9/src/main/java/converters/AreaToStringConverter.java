package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Area;
@Component
@Transactional
public class AreaToStringConverter implements Converter<Area, String>{


@Override
public String convert(Area area){
    String result;
    if (area == null)
        result = null;
    else
        result = String.valueOf(area.getId());
    return result;
}


}