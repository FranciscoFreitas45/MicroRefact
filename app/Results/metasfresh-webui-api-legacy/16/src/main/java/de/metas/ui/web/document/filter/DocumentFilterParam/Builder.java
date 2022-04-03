package de.metas.ui.web.document.filter.DocumentFilterParam;
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
public class Builder {

 private  boolean joinAnd;

 private  String fieldName;

 private  Operator operator;

 private  Object value;

 private  Object valueTo;


public Builder setFieldName(String fieldName){
    this.fieldName = fieldName;
    return this;
}


public DocumentFilterParam build(){
    return new DocumentFilterParam(this);
}


public Builder setValueTo(Object valueTo){
    this.valueTo = valueTo;
    return this;
}


public Builder setValue(Object value){
    this.value = value;
    return this;
}


public Builder setOperator(){
    operator = valueTo != null ? Operator.BETWEEN : Operator.EQUAL;
    return this;
}


public Builder setJoinAnd(boolean joinAnd){
    this.joinAnd = joinAnd;
    return this;
}


}