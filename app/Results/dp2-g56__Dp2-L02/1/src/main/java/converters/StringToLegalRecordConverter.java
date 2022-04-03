package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.LegalRecordRepository;
import domain.LegalRecord;
@Component
@Transactional
public class StringToLegalRecordConverter implements Converter<String, LegalRecord>{

@Autowired
 private LegalRecordRepository legalRecordRepository;


@Override
public LegalRecord convert(String text){
    LegalRecord result = new LegalRecord();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.legalRecordRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}