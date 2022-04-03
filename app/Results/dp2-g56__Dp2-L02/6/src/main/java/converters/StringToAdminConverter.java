package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.AdminRepository;
import domain.Admin;
@Component
@Transactional
public class StringToAdminConverter implements Converter<String, Admin>{

@Autowired
 private AdminRepository adminRepository;


@Override
public Admin convert(String text){
    Admin result = new Admin();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.adminRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}