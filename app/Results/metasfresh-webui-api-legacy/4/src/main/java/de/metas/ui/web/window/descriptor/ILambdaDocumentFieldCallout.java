package de.metas.ui.web.window.descriptor;
 import org.adempiere.ad.callout.api.ICalloutField;
@FunctionalInterface
public interface ILambdaDocumentFieldCallout {


public void execute(ICalloutField field)
;

}