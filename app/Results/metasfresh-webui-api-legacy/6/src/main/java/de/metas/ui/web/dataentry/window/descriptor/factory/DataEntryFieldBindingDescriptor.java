package de.metas.ui.web.dataentry.window.descriptor.factory;
 import javax.annotation.Nullable;
import de.metas.dataentry.DataEntryFieldId;
import de.metas.dataentry.FieldType;
import de.metas.ui.web.window.descriptor.DocumentFieldDataBindingDescriptor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
@Builder
public class DataEntryFieldBindingDescriptor implements DocumentFieldDataBindingDescriptor{

@NonNull
 private String columnName;

 private boolean mandatory;

@Nullable
 private DataEntryFieldId dataEntryFieldId;

@NonNull
 private FieldType fieldType;


}