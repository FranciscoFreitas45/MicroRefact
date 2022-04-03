package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Admin;
@Component
@Transactional
public class AdminToStringConverter implements Converter<Admin, String>{


@Override
public String convert(Admin admin){
    String result;
    if (admin == null)
        result = null;
    else
        result = String.valueOf(admin.getId());
    return result;
}


}