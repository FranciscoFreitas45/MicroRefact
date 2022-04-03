package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.MiscellaneousRecordRepository;
import domain.MiscellaneousRecord;
@Component
@Transactional
public class StringToMiscellaneousRecordConverter implements Converter<String, MiscellaneousRecord>{

@Autowired
 private MiscellaneousRecordRepository miscellaneousRecordRepository;


@Override
public MiscellaneousRecord convert(String text){
    MiscellaneousRecord result = new MiscellaneousRecord();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.miscellaneousRecordRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}