package run.halo.app.model.dto.base;
 import run.halo.app.utils.BeanUtils.updateProperties;
import org.springframework.lang.NonNull;
public interface OutputConverter {


@SuppressWarnings("unchecked")
@NonNull
public T convertFrom(D domain){
    updateProperties(domain, this);
    return (T) this;
}
;

}