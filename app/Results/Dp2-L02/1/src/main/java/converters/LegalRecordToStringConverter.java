package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.LegalRecord;
@Component
@Transactional
public class LegalRecordToStringConverter implements Converter<LegalRecord, String>{


@Override
public String convert(LegalRecord legalRecord){
    String result;
    if (legalRecord == null)
        result = null;
    else
        result = String.valueOf(legalRecord.getId());
    return result;
}


}