package DTO;
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
public class MenuNode {

 private  String id;

 private  int adMenuId;

 private  String caption;

 private  String captionBreadcrumb;

 private  MenuNodeType type;

 private  DocumentId elementId;

 private  String mainTableName;

 private  List<MenuNode> children;

 private  MenuNode parent;

 private  boolean matchedByFilter;

 private  Integer _hashcode;

 private  Integer adMenuId;

 private  String caption;

 private  String captionBreadcrumb;

 private  MenuNodeType type;

 private  DocumentId elementId;

 private  String mainTableName;

 private  List<MenuNode> childrenFirst;

 private  List<MenuNode> childrenRest;


public DocumentId getElementId(){
    return elementId;
}


public String getId(){
    final int adMenuId = getAD_Menu_ID();
    if (type == MenuNodeType.NewRecord) {
        return adMenuId + "-new";
    } else {
        return String.valueOf(adMenuId);
    }
}


public String getCaption(){
    return caption;
}


public MenuNode getParent(){
    return parent;
}


public String getCaptionBreadcrumb(){
    return captionBreadcrumb;
}


public List<MenuNode> getChildren(){
    return children;
}


public MenuNodeType getType(){
    return type;
}


public String getMainTableName(){
    return mainTableName;
}


public int getAD_Menu_ID(){
    Check.assumeNotNull(adMenuId, "adMenuId shall be set");
    return adMenuId;
}


public String getParentId(){
    return parent == null ? null : parent.getId();
}


}