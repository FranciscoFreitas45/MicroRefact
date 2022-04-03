package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import domain.Segment;
import repositories.SegmentRepository;
public class StringToSegmentConverter implements Converter<String, Segment>{

@Autowired
 private SegmentRepository SegmentRepository;


@Override
public Segment convert(String text){
    Segment result = new Segment();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.SegmentRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}