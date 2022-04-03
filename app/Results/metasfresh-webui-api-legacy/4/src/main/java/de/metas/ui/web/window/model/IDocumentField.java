package de.metas.ui.web.window.model;
 import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.model.Document.CopyMode;
public interface IDocumentField extends IDocumentFieldView{


public void setMandatory(LogicExpressionResult mandatory,IDocumentChangesCollector changesCollector)
;

public ICalloutField asCalloutField()
;

public void setInitialValue(Object initialValue,IDocumentChangesCollector changesCollector)
;

public boolean setLookupValuesStaled(String triggeringFieldName)
;

public Document getDocument()
;

@Override
public DocumentValidStatus getValidStatus()
;

public void setReadonly(LogicExpressionResult readonly)
;

public DocumentValidStatus updateStatusIfInitialInvalidAndGet(IDocumentChangesCollector changesCollector)
;

@Override
public DocumentPath getDocumentPath(){
    return getDocument().getDocumentPath();
}
;

public void setValue(Object value,IDocumentChangesCollector changesCollector)
;

public IDocumentField copy(Document document,CopyMode copyMode)
;

public void setDisplayed(LogicExpressionResult displayed)
;

public LookupValuesList getLookupValues()
;

public LookupValuesList getLookupValuesForQuery(String query)
;

}