package de.metas.ui.web.document.filter.json;
 import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDocumentFilterParam {

@JsonProperty("parameterName")
 private  String parameterName;

@JsonProperty("value")
 private  Object value;

@JsonProperty("valueTo")
 private  Object valueTo;


public Optional<JSONDocumentFilterParam> of(DocumentFilterParam filterParam,JSONOptions jsonOpts){
    // Don't convert internal filters
    if (filterParam.isSqlFilter()) {
        // throw new IllegalArgumentException("Sql filters are not allowed to be converted to JSON filters: " + filterParam);
        return Optional.empty();
    }
    final String fieldName = filterParam.getFieldName();
    final Object jsonValue = Values.valueToJsonObject(filterParam.getValue(), jsonOpts);
    final Object jsonValueTo = Values.valueToJsonObject(filterParam.getValueTo(), jsonOpts);
    final JSONDocumentFilterParam jsonFilterParam = new JSONDocumentFilterParam(fieldName, jsonValue, jsonValueTo);
    return Optional.of(jsonFilterParam);
}


}