package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Segment;
@Component
@Transactional
public class SegmentToStringConverter implements Converter<Segment, String>{


@Override
public String convert(Segment segment){
    String result;
    if (segment == null)
        result = null;
    else
        result = String.valueOf(segment.getId());
    return result;
}


}