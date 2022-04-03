package de.metas.ui.web.menu.datatypes.json;
 import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.MutableInt;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.menu.MenuNode;
import de.metas.ui.web.menu.MenuNodeFavoriteProvider;
@SuppressWarnings("serial")
public class JSONMenuNode implements Serializable{

@JsonProperty("nodeId")
 private  String nodeId;

@JsonProperty("parentId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String parentId;

@JsonProperty("caption")
 private  String caption;

@JsonProperty("captionBreadcrumb")
 private  String captionBreadcrumb;

@JsonProperty("type")
 private  JSONMenuNodeType type;

@JsonProperty("elementId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String elementId;

@JsonProperty("children")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONMenuNode> children;

@JsonProperty("matched")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean matched;

@JsonProperty("favorite")
 private  boolean favorite;

 private  MenuNode node;

 private  int maxDepth;

 private  int maxChildrenPerNode;

 private  int maxLeafNodes;

 private  MenuNodeFavoriteProvider menuNodeFavoriteProvider;


public Builder setMaxDepth(int maxDepth){
    this.maxDepth = maxDepth > 0 ? maxDepth : Integer.MAX_VALUE;
    return this;
}


public String getNodeId(){
    return nodeId;
}


public String getElementId(){
    return elementId;
}


public Boolean getMatched(){
    return matched;
}


public Builder setMaxChildrenPerNode(int maxChildrenPerNode){
    this.maxChildrenPerNode = maxChildrenPerNode > 0 ? maxChildrenPerNode : Integer.MAX_VALUE;
    return this;
}


public String getCaptionBreadcrumb(){
    return captionBreadcrumb;
}


public String getCaption(){
    return caption;
}


@JsonIgnore
public boolean isLeaf(){
    return children == null || children.isEmpty();
}


public JSONMenuNode ofPath(List<MenuNode> path,boolean skipRootNode,boolean includeLastNode,MenuNodeFavoriteProvider menuNodeFavoriteProvider){
    if (path == null || path.isEmpty()) {
        throw new AdempiereException("Empty path is not valid");
    } else if (path.size() == 1) {
        final MenuNode node = path.get(0);
        final boolean favorite = menuNodeFavoriteProvider.isFavorite(node);
        final JSONMenuNode jsonChildNode = null;
        return new JSONMenuNode(node, favorite, jsonChildNode);
    }
    int lastIndex = path.size() - 1;
    if (!includeLastNode) {
        lastIndex--;
    }
    JSONMenuNode jsonChildNode = null;
    for (int i = lastIndex; i >= 0; i--) {
        final MenuNode node = path.get(i);
        if (node.isRoot()) {
            continue;
        }
        final boolean favorite = menuNodeFavoriteProvider.isFavorite(node);
        jsonChildNode = new JSONMenuNode(node, favorite, jsonChildNode);
    }
    if (jsonChildNode == null) {
        throw new AdempiereException("Invalid path: " + path);
    }
    return jsonChildNode;
}


public List<JSONMenuNode> getChildren(){
    return children;
}


public Builder setIsFavoriteProvider(MenuNodeFavoriteProvider menuNodeFavoriteProvider){
    this.menuNodeFavoriteProvider = menuNodeFavoriteProvider;
    return this;
}


public Builder setMaxLeafNodes(int maxLeafNodes){
    this.maxLeafNodes = maxLeafNodes > 0 ? maxLeafNodes : Integer.MAX_VALUE;
    return this;
}


public JSONMenuNodeType getType(){
    return type;
}


public JSONMenuNode build(){
    final MutableInt maxLeafNodes = new MutableInt(this.maxLeafNodes);
    return newInstanceOrNull(node, maxDepth, maxChildrenPerNode, maxLeafNodes, menuNodeFavoriteProvider);
}


public Builder builder(MenuNode node){
    return new Builder(node);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("nodeId", nodeId).add("parentId", parentId).add("caption", caption).add("captionBreadcrumb", captionBreadcrumb).add("type", type).add("elementId", elementId).add("matchedByFilter", matched).toString();
}


public List<JSONMenuNode> ofList(Collection<MenuNode> nodes,MenuNodeFavoriteProvider menuNodeFavoriteProvider){
    if (nodes.isEmpty()) {
        return ImmutableList.of();
    }
    final JSONMenuNode jsonChildNode = null;
    return nodes.stream().map(node -> new JSONMenuNode(node, menuNodeFavoriteProvider.isFavorite(node), jsonChildNode)).collect(ImmutableList.toImmutableList());
}


public JSONMenuNode newInstanceOrNull(MenuNode node,int depth,int childrenLimit,MutableInt maxLeafNodes,MenuNodeFavoriteProvider menuNodeFavoriteProvider){
    if (maxLeafNodes.getValue() <= 0) {
        return null;
    }
    if (node.isEffectiveLeafNode()) {
        maxLeafNodes.decrementAndGet();
    }
    return new JSONMenuNode(node, depth, childrenLimit, maxLeafNodes, menuNodeFavoriteProvider);
}


}