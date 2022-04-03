package de.metas.ui.web.window.model.DocumentQueryOrderBy;
 import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
@FunctionalInterface
public interface FieldValueExtractor {


public Object getFieldValue(T object,String fieldName,JSONOptions jsonOpts)
;

}