package de.metas.ui.web.process.adprocess;
 import org.adempiere.ad.expression.api.LogicExpressionResult;
import de.metas.ui.web.process.IProcessInstanceParameter;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.DocumentValidStatus;
import de.metas.ui.web.window.model.IDocumentFieldView;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class DocumentFieldAsProcessInstanceParameter implements IProcessInstanceParameter{

 private  IDocumentFieldView documentField;


@Override
public String getParameterName(){
    return documentField.getFieldName();
}


@Override
public LogicExpressionResult getMandatory(){
    return documentField.getMandatory();
}


@Override
public LogicExpressionResult getDisplayed(){
    return documentField.getDisplayed();
}


@Override
public DocumentFieldWidgetType getWidgetType(){
    return documentField.getWidgetType();
}


public DocumentFieldAsProcessInstanceParameter of(IDocumentFieldView documentField){
    return new DocumentFieldAsProcessInstanceParameter(documentField);
}


@Override
public LogicExpressionResult getReadonly(){
    return documentField.getReadonly();
}


@Override
public DocumentValidStatus getValidStatus(){
    return documentField.getValidStatus();
}


@Override
public Object getValueAsJsonObject(JSONOptions jsonOpts){
    return documentField.getValueAsJsonObject(jsonOpts);
}


}