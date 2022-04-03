package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Configuration;
@Component
@Transactional
public class ConfigurationToStringConverter implements Converter<Configuration, String>{


@Override
public String convert(Configuration configuration){
    String result;
    if (configuration == null)
        result = null;
    else
        result = String.valueOf(configuration.getId());
    return result;
}


}