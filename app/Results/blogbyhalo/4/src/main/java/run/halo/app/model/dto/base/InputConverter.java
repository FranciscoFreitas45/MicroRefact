package run.halo.app.model.dto.base;
 import java.lang.reflect.ParameterizedType;
import java.util.Objects;
import org.springframework.lang.Nullable;
import run.halo.app.utils.BeanUtils;
import run.halo.app.utils.ReflectionUtils;
public interface InputConverter {


@SuppressWarnings("unchecked")
public D convertTo(){
    // Get parameterized type
    ParameterizedType currentType = parameterizedType();
    // Assert not equal
    Objects.requireNonNull(currentType, "Cannot fetch actual type because parameterized type is null");
    Class<D> domainClass = (Class<D>) currentType.getActualTypeArguments()[0];
    return BeanUtils.transformFrom(this, domainClass);
}
;

public void update(D domain){
    BeanUtils.updateProperties(this, domain);
}
;

@Nullable
public ParameterizedType parameterizedType(){
    return ReflectionUtils.getParameterizedType(InputConverter.class, this.getClass());
}
;

}