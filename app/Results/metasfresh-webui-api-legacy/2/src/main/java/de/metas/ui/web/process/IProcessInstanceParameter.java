package de.metas.ui.web.process;
 import org.adempiere.ad.expression.api.LogicExpressionResult;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.DocumentValidStatus;
public interface IProcessInstanceParameter {


public String getParameterName()
;

public LogicExpressionResult getMandatory()
;

public LogicExpressionResult getDisplayed()
;

public DocumentFieldWidgetType getWidgetType()
;

public LogicExpressionResult getReadonly()
;

public DocumentValidStatus getValidStatus()
;

public Object getValueAsJsonObject(JSONOptions jsonOpts)
;

}