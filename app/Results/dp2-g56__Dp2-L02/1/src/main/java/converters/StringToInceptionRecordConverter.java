package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.InceptionRecordRepository;
import domain.InceptionRecord;
@Component
@Transactional
public class StringToInceptionRecordConverter implements Converter<String, InceptionRecord>{

@Autowired
 private InceptionRecordRepository inceptionRecordRepository;


@Override
public InceptionRecord convert(String text){
    InceptionRecord result = new InceptionRecord();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.inceptionRecordRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}