package de.metas.ui.web.view;
 import javax.annotation.Nullable;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Builder
@Value
public class ViewResultColumn {

@NonNull
 private String fieldName;

@NonNull
 private DocumentFieldWidgetType widgetType;

@Nullable
 private Integer maxPrecision;


}