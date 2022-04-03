package de.metas.ui.web.window.descriptor;
 import java.util.Set;
import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutField;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import de.metas.util.Check;
public class LambdaDocumentFieldCallout implements IDocumentFieldCallout{

 private  String id;

 private  Set<String> dependsOnFieldNames;

 private  ILambdaDocumentFieldCallout lambdaCallout;


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("id", id).toString();
}


@Override
public String getId(){
    return id;
}


@Override
public Set<String> getDependsOnFieldNames(){
    return dependsOnFieldNames;
}


@Override
public void execute(ICalloutExecutor executor,ICalloutField field){
    lambdaCallout.execute(field);
}


}