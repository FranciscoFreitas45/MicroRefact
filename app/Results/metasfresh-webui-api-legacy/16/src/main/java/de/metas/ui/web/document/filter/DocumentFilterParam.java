package de.metas.ui.web.document.filter;
 import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.descriptor.SqlAndParams;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@Getter
// required for (ETag) caching
@EqualsAndHashCode
@ToString
public class DocumentFilterParam {

 private  boolean joinAnd;

 private  String fieldName;

 private  Operator operator;

 private  Object value;

 private  Object valueTo;

 private  SqlAndParams sqlWhereClause;

 private  boolean joinAnd;

 private  String fieldName;

 private  Operator operator;

 private  Object value;

 private  Object valueTo;


public int getValueAsInt(int defaultValue){
    final Integer valueInt = convertToInt(value);
    return valueInt != null ? valueInt : defaultValue;
}


public Instant getValueAsInstant(){
    return value != null ? DateTimeConverters.fromObjectToInstant(value) : null;
}


public Builder setFieldName(String fieldName){
    this.fieldName = fieldName;
    return this;
}


public DocumentFilterParam ofSqlWhereClause(boolean joinAnd,String sqlWhereClause){
    return new DocumentFilterParam(joinAnd, SqlAndParams.of(sqlWhereClause));
}


public Builder setValueTo(Object valueTo){
    this.valueTo = valueTo;
    return this;
}


public Instant getValueToAsInstant(){
    return valueTo != null ? DateTimeConverters.fromObjectToInstant(valueTo) : null;
}


public LocalDate getValueAsLocalDate(){
    return DateTimeConverters.fromObjectToLocalDate(value);
}


public boolean isRangeOperator(){
    return this == BETWEEN;
}


public DocumentFilterParam ofNameOperatorValue(String fieldName,Operator operator,Object value){
    return builder().setFieldName(fieldName).setOperator(operator).setValue(value).build();
}


public String getValueAsString(){
    return value != null ? value.toString() : null;
}


public Collection<?> getValueAsCollection(){
    if (value == null) {
        throw new AdempiereException("Cannot convert null value to Collection<?>");
    } else if (value instanceof Collection) {
        return (Collection<?>) value;
    } else if (value instanceof LookupValuesList) {
        final Collection<LookupValue> lookupValues = ((LookupValuesList) value).getValues();
        return lookupValues;
    } else {
        return ImmutableList.of(value);
    // throw new AdempiereException("Cannot convert value to Collection<?>: " + value + " (" + value.getClass() + ")");
    }
}


public LocalDate getValueToAsLocalDateOr(LocalDate defaultValue){
    return valueTo != null ? DateTimeConverters.fromObjectToLocalDate(valueTo) : defaultValue;
}


public Builder builder(){
    return new Builder();
}


public Integer convertToInt(Object itemObj){
    if (itemObj == null) {
        // pass-through, even though it will produce an exception when the list will be converted to immutable list
        return null;
    } else if (itemObj instanceof Number) {
        return ((Number) itemObj).intValue();
    } else if (itemObj instanceof LookupValue) {
        return ((LookupValue) itemObj).getIdAsInt();
    } else {
        final String itemStr = itemObj.toString();
        return Integer.parseInt(itemStr);
    }
}


public Builder setOperator(){
    operator = valueTo != null ? Operator.BETWEEN : Operator.EQUAL;
    return this;
}


public T getValueAsRepoIdOrNull(IntFunction<T> repoIdMapper){
    final int idInt = getValueAsInt(-1);
    if (idInt < 0) {
        return null;
    }
    return repoIdMapper.apply(idInt);
}


public List<T> getValueAsList(Function<Object,T> itemConverter){
    final Collection<?> valueAsCollection = getValueAsCollection();
    if (valueAsCollection == null) {
        throw new AdempiereException("Cannot convert null value to List<Integer>");
    }
    if (valueAsCollection.isEmpty()) {
        return ImmutableList.of();
    }
    return valueAsCollection.stream().map(itemConverter).collect(ImmutableList.toImmutableList());
}


public Boolean getValueAsBoolean(Boolean defaultValue){
    return DisplayType.toBoolean(value, defaultValue);
}


public DocumentFilterParam ofNameEqualsValue(String fieldName,Object value){
    return ofNameOperatorValue(fieldName, Operator.EQUAL, value);
}


public boolean isSqlFilter(){
    return getSqlWhereClause() != null;
}


public List<Integer> getValueAsIntList(){
    return getValueAsList(itemObj -> convertToInt(itemObj));
}


public DocumentFilterParam build(){
    return new DocumentFilterParam(this);
}


public LocalDate getValueAsLocalDateOr(LocalDate defaultValue){
    return value != null ? DateTimeConverters.fromObjectToLocalDate(value) : defaultValue;
}


public Builder setValue(Object value){
    this.value = value;
    return this;
}


public Builder setJoinAnd(boolean joinAnd){
    this.joinAnd = joinAnd;
    return this;
}


}