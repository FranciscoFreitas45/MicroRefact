package de.metas.ui.web.window.descriptor;
 import java.util.Set;
import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.callout.api.ICalloutInstance;
public interface IDocumentFieldCallout extends ICalloutInstance{


@Override
public String getId()
;

public Set<String> getDependsOnFieldNames()
;

@Override
public void execute(ICalloutExecutor executor,ICalloutField field)
;

}