package de.metas.ui.web.menu.datatypes.json.JSONMenuNode;
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
public class Builder {

 private  MenuNode node;

 private  int maxDepth;

 private  int maxChildrenPerNode;

 private  int maxLeafNodes;

 private  MenuNodeFavoriteProvider menuNodeFavoriteProvider;


public Builder setIsFavoriteProvider(MenuNodeFavoriteProvider menuNodeFavoriteProvider){
    this.menuNodeFavoriteProvider = menuNodeFavoriteProvider;
    return this;
}


public Builder setMaxDepth(int maxDepth){
    this.maxDepth = maxDepth > 0 ? maxDepth : Integer.MAX_VALUE;
    return this;
}


public Builder setMaxLeafNodes(int maxLeafNodes){
    this.maxLeafNodes = maxLeafNodes > 0 ? maxLeafNodes : Integer.MAX_VALUE;
    return this;
}


public JSONMenuNode build(){
    final MutableInt maxLeafNodes = new MutableInt(this.maxLeafNodes);
    return newInstanceOrNull(node, maxDepth, maxChildrenPerNode, maxLeafNodes, menuNodeFavoriteProvider);
}


public Builder setMaxChildrenPerNode(int maxChildrenPerNode){
    this.maxChildrenPerNode = maxChildrenPerNode > 0 ? maxChildrenPerNode : Integer.MAX_VALUE;
    return this;
}


}