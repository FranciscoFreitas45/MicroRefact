package run.halo.app.model.enums;
 import java.util.stream.Stream;
import org.springframework.util.Assert;
public interface ValueEnum {


public T getValue()
;

public E valueToEnum(Class<E> enumType,V value){
    Assert.notNull(enumType, "enum type must not be null");
    Assert.notNull(value, "value must not be null");
    Assert.isTrue(enumType.isEnum(), "type must be an enum type");
    return Stream.of(enumType.getEnumConstants()).filter(item -> item.getValue().equals(value)).findFirst().orElseThrow(() -> new IllegalArgumentException("unknown database value: " + value));
}
;

}