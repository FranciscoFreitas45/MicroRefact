package run.halo.app.config.attributeconverter;
 import net.bytebuddy.implementation.bind.annotation.FieldValue;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import run.halo.app.model.enums.ValueEnum;
public class AttributeConverterInterceptor {

private AttributeConverterInterceptor() {
}
@RuntimeType
public T convertToEntityAttribute(V dbData,Class<T> enumType){
    return dbData == null ? null : ValueEnum.valueToEnum(enumType, dbData);
}


@RuntimeType
public V convertToDatabaseColumn(T attribute){
    return attribute == null ? null : attribute.getValue();
}


}