package de.metas.ui.web.window.model.DocumentChanges;
 import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.adempiere.ad.expression.api.LogicExpressionResult;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DataTypes;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.util.Check;
public class IncludedDetailInfo {

 private  DetailId detailId;

 private  boolean stale;

 private  LogicExpressionResult allowNew;

 private  LogicExpressionResult allowDelete;


public IncludedDetailInfo setStale(){
    stale = true;
    return this;
}


public LogicExpressionResult getAllowNew(){
    return allowNew;
}


public LogicExpressionResult getAllowDelete(){
    return allowDelete;
}


public IncludedDetailInfo setAllowDelete(LogicExpressionResult allowDelete){
    this.allowDelete = allowDelete;
    return this;
}


public void collectFrom(IncludedDetailInfo from){
    if (from.stale) {
        stale = from.stale;
    }
    if (from.allowNew != null) {
        allowNew = from.allowNew;
    }
    if (from.allowDelete != null) {
        allowDelete = from.allowDelete;
    }
}


public boolean isStale(){
    return stale;
}


public DetailId getDetailId(){
    return detailId;
}


public IncludedDetailInfo setAllowNew(LogicExpressionResult allowNew){
    this.allowNew = allowNew;
    return this;
}


}