package de.metas.ui.web.menu;
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


public Builder setTypeGroup(){
    final DocumentId elementId = null;
    setType(MenuNodeType.Group, elementId);
    return this;
}


public boolean isRoot(){
    return parent == null;
}


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


public Builder setCaption(String caption){
    this.caption = caption;
    return this;
}


public String getCaption(){
    return caption;
}


@Override
public int hashCode(){
    if (_hashcode == null) {
        _hashcode = Objects.hash(id);
    }
    return _hashcode;
}


public Builder setCaptionBreadcrumb(String captionBreadcrumb){
    this.captionBreadcrumb = captionBreadcrumb;
    return this;
}


public Builder builder(){
    return new Builder();
}


public Builder addChildren(Collection<MenuNode> children){
    if (children == null || children.isEmpty()) {
        return this;
    }
    childrenRest.addAll(children);
    return this;
}


public boolean isMatchedByFilter(){
    return matchedByFilter;
}


public void iterate(Consumer<MenuNode> consumer){
    consumer.accept(this);
    for (final MenuNode child : children) {
        child.iterate(consumer);
    }
}


public MenuNode getParent(){
    return parent;
}


public IPair<MenuNode,MenuNodeFilterResolution> deepCopy0(MenuNodeFilter filter){
    // 
    // Get the resolution for this node:
    final MenuNodeFilterResolution resolution = filter.check(this);
    if (resolution == MenuNodeFilterResolution.Reject) {
        return null;
    }
    // 
    // Check and copy it's children
    final List<MenuNode> childrenCopy = new ArrayList<>();
    int countAcceptedChildren = 0;
    for (final MenuNode child : children) {
        final IPair<MenuNode, MenuNodeFilterResolution> childCopyAndResolution = child.deepCopy0(filter);
        if (childCopyAndResolution == null) {
            continue;
        }
        final MenuNode childCopy = childCopyAndResolution.getLeft();
        if (childCopy == null) {
            continue;
        }
        childrenCopy.add(childCopy);
        final MenuNodeFilterResolution childResolution = childCopyAndResolution.getRight();
        switch(childResolution) {
            case Accept:
            case AcceptIfHasChildren:
                countAcceptedChildren++;
                break;
            case AcceptIfParentIsAccepted:
                // nothing to do
                break;
            default:
                // shall not happen
                throw new IllegalStateException("Invalid child resolution: " + childResolution);
        }
    }
    if (resolution == MenuNodeFilterResolution.AcceptIfHasChildren && countAcceptedChildren == 0) {
        return null;
    }
    final boolean matchedByFilter = resolution == MenuNodeFilterResolution.Accept;
    final MenuNode thisCopy = new MenuNode(this, childrenCopy, matchedByFilter);
    return ImmutablePair.of(thisCopy, resolution);
}


public String getCaptionBreadcrumb(){
    return captionBreadcrumb;
}


public boolean isGroupingNode(){
    return type == MenuNodeType.Group;
}


public Builder setMainTableName(String mainTableName){
    this.mainTableName = mainTableName;
    return this;
}


public MenuNodeFilterResolution check(MenuNode node)


public Builder setType(MenuNodeType type,DocumentId elementId){
    this.type = type;
    this.elementId = elementId;
    return this;
}


public MenuNode deepCopy(MenuNodeFilter filter){
    final IPair<MenuNode, MenuNodeFilterResolution> nodeAndResolution = deepCopy0(filter);
    return nodeAndResolution == null ? null : nodeAndResolution.getLeft();
}


public List<MenuNode> getChildren(){
    return children;
}


public MenuNodeType getType(){
    return type;
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


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (!(obj instanceof MenuNode)) {
        return false;
    }
    final MenuNode other = (MenuNode) obj;
    return id.equals(other.id);
}


public String getMainTableName(){
    return mainTableName;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("caption", caption).add("type", type).add("elementId", elementId).add("mainTableName", mainTableName).add("children-count", children.size()).add("matchedByFilter", matchedByFilter ? Boolean.TRUE : null).toString();
}


public int getAD_Menu_ID(){
    Check.assumeNotNull(adMenuId, "adMenuId shall be set");
    return adMenuId;
}


public boolean isEffectiveLeafNode(){
    return children.isEmpty();
}


public Builder addChild(MenuNode child){
    Preconditions.checkNotNull(child, "child");
    childrenRest.add(child);
    return this;
}


public String getParentId(){
    return parent == null ? null : parent.getId();
}


public Builder addChildToFirstsList(MenuNode child){
    Preconditions.checkNotNull(child, "child");
    childrenFirst.add(child);
    return this;
}


}