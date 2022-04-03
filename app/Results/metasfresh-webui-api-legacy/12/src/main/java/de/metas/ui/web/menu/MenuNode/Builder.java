package de.metas.ui.web.menu.MenuNode;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import org.adempiere.util.lang.IPair;
import org.adempiere.util.lang.ImmutablePair;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import de.metas.ui.web.menu.MenuNode.MenuNodeFilter.MenuNodeFilterResolution;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Check;
public class Builder {

 private  Integer adMenuId;

 private  String caption;

 private  String captionBreadcrumb;

 private  MenuNodeType type;

 private  DocumentId elementId;

 private  String mainTableName;

 private  List<MenuNode> childrenFirst;

 private  List<MenuNode> childrenRest;


public Builder setTypeGroup(){
    final DocumentId elementId = null;
    setType(MenuNodeType.Group, elementId);
    return this;
}


public String getId(){
    final int adMenuId = getAD_Menu_ID();
    if (type == MenuNodeType.NewRecord) {
        return adMenuId + "-new";
    } else {
        return String.valueOf(adMenuId);
    }
}


public Builder setCaption(String caption){
    this.caption = caption;
    return this;
}


public Builder setMainTableName(String mainTableName){
    this.mainTableName = mainTableName;
    return this;
}


public Builder setType(MenuNodeType type,DocumentId elementId){
    this.type = type;
    this.elementId = elementId;
    return this;
}


public MenuNode build(){
    return new MenuNode(this);
}


public Builder setAD_Menu_ID(int adMenuId){
    this.adMenuId = adMenuId;
    return this;
}


public Builder setAD_Menu_ID_None(){
    // NOTE: don't set it to ZERO because ZERO is usually root node's ID.
    this.adMenuId = -100;
    return this;
}


public Builder setCaptionBreadcrumb(String captionBreadcrumb){
    this.captionBreadcrumb = captionBreadcrumb;
    return this;
}


public Builder addChildren(Collection<MenuNode> children){
    if (children == null || children.isEmpty()) {
        return this;
    }
    childrenRest.addAll(children);
    return this;
}


public int getAD_Menu_ID(){
    Check.assumeNotNull(adMenuId, "adMenuId shall be set");
    return adMenuId;
}


public Builder addChild(MenuNode child){
    Preconditions.checkNotNull(child, "child");
    childrenRest.add(child);
    return this;
}


public Builder addChildToFirstsList(MenuNode child){
    Preconditions.checkNotNull(child, "child");
    childrenFirst.add(child);
    return this;
}


}