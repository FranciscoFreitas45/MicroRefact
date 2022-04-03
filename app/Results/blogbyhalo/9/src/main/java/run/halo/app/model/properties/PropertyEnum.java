package run.halo.app.model.properties;
 import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import run.halo.app.model.enums.ValueEnum;
public interface PropertyEnum extends ValueEnum<String>{


@Nullable
public T convertToEnum(String value,Class<T> type){
    Assert.hasText(value, "Property value must not be blank");
    try {
        return Enum.valueOf(type, value.toUpperCase());
    } catch (Exception e) {
        // Ignore this exception
        return null;
    }
}
;

public Class<?> getType()
;

@Nullable
public T defaultValue(Class<T> propertyType){
    // Get default value
    String defaultValue = defaultValue();
    if (defaultValue == null) {
        return null;
    }
    // Convert to the given type
    return PropertyEnum.convertTo(defaultValue, propertyType);
}
;

@SuppressWarnings("unchecked")
public Object convertTo(String value,PropertyEnum propertyEnum){
    Assert.notNull(propertyEnum, "Property enum must not be null");
    if (StringUtils.isBlank(value)) {
        // Set default value
        value = propertyEnum.defaultValue();
    }
    try {
        if (propertyEnum.getType().isAssignableFrom(Enum.class)) {
            Class<Enum> type = (Class<Enum>) propertyEnum.getType();
            Enum result = convertToEnum(value, type);
            return result != null ? result : value;
        }
        return convertTo(value, propertyEnum.getType());
    } catch (Exception e) {
        // Return value
        return value;
    }
}
;

public Map<String,PropertyEnum> getValuePropertyEnumMap(){
    // Get all properties
    List<Class<? extends PropertyEnum>> propertyEnumClasses = new LinkedList<>();
    propertyEnumClasses.add(AliOssProperties.class);
    propertyEnumClasses.add(AttachmentProperties.class);
    propertyEnumClasses.add(BlogProperties.class);
    propertyEnumClasses.add(CommentProperties.class);
    propertyEnumClasses.add(EmailProperties.class);
    propertyEnumClasses.add(OtherProperties.class);
    propertyEnumClasses.add(PostProperties.class);
    propertyEnumClasses.add(SheetProperties.class);
    propertyEnumClasses.add(PrimaryProperties.class);
    propertyEnumClasses.add(QiniuOssProperties.class);
    propertyEnumClasses.add(SeoProperties.class);
    propertyEnumClasses.add(UpOssProperties.class);
    propertyEnumClasses.add(ApiProperties.class);
    propertyEnumClasses.add(PermalinkProperties.class);
    Map<String, PropertyEnum> result = new HashMap<>();
    propertyEnumClasses.forEach(propertyEnumClass -> {
        PropertyEnum[] propertyEnums = propertyEnumClass.getEnumConstants();
        for (PropertyEnum propertyEnum : propertyEnums) {
            result.put(propertyEnum.getValue(), propertyEnum);
        }
    });
    return result;
}
;

public boolean isSupportedType(Class<?> type){
    if (type == null) {
        return false;
    }
    return type.isAssignableFrom(String.class) || type.isAssignableFrom(Number.class) || type.isAssignableFrom(Integer.class) || type.isAssignableFrom(Long.class) || type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(Short.class) || type.isAssignableFrom(Byte.class) || type.isAssignableFrom(Double.class) || type.isAssignableFrom(Float.class) || type.isAssignableFrom(Enum.class) || type.isAssignableFrom(ValueEnum.class);
}
;

}