package de.metas.ui.web.process.adprocess;
 import org.compiere.util.Env;
import com.google.common.base.MoreObjects;
import de.metas.process.IProcessDefaultParameter;
import de.metas.ui.web.window.model.IDocumentFieldView;
public class DocumentFieldAsProcessDefaultParameter implements IProcessDefaultParameter{

 private  int windowNo;

 private  IDocumentFieldView field;


public DocumentFieldAsProcessDefaultParameter of(int windowNo,IDocumentFieldView field){
    return new DocumentFieldAsProcessDefaultParameter(windowNo, field);
}


@Override
public String getColumnName(){
    return field.getFieldName();
}


@Override
public int getContextAsInt(String name){
    return Env.getContextAsInt(Env.getCtx(), windowNo, name);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("windowNo", windowNo).addValue(field).toString();
}


}