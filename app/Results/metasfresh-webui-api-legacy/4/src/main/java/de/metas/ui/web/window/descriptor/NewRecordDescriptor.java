package de.metas.ui.web.window.descriptor;
 import javax.annotation.concurrent.Immutable;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.model.Document;
@Immutable
public class NewRecordDescriptor {

 private  String tableName;

 private  int newRecordWindowId;

 private  NewRecordProcessor processor;


public NewRecordProcessor getProcessor(){
    return processor;
}


public int processNewRecordDocument(Document document)


public int getNewRecordWindowId(){
    return newRecordWindowId;
}


public NewRecordDescriptor of(String tableName,int newRecordWindowId,NewRecordProcessor processor){
    return new NewRecordDescriptor(tableName, newRecordWindowId, processor);
}


public String getTableName(){
    return tableName;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add(tableName, tableName).add("newRecordWindowId", newRecordWindowId).toString();
}


}