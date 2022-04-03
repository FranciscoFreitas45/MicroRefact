package DTO;
 import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.compiere.util.Util;
import org.compiere.util.Util.ArrayKey;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ListMultimap;
import de.metas.logging.LogManager;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.menu.MenuNode.MenuNodeFilter.MenuNodeFilterResolution;
import de.metas.ui.web.menu.MenuNode.MenuNodeType;
import de.metas.ui.web.menu.exception.NoMenuNodesFoundException;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
public class MenuTree {

 private  Logger logger;

 private  long version;

 private  MenuNode rootNode;

 private  Map<String,MenuNode> nodesById;

 private  ListMultimap<ArrayKey,MenuNode> nodesByTypeAndElementId;

 private  ListMultimap<String,MenuNode> nodesByMainTableName;


public long getVersion(){
    return version;
}


public MenuNode getFirstNodeByElementId(MenuNodeType type,DocumentId elementId){
    final MenuNode node = getFirstNodeByElementIdOrNull(type, elementId);
    if (node == null) {
        throw new NoMenuNodesFoundException("No menu node found for type=" + type + " and elementId=" + elementId);
    }
    return node;
}


public MenuNode getRootNodeWithFavoritesOnly(MenuNodeFavoriteProvider menuNodeFavoriteProvider){
    return getRootNode().deepCopy(node -> {
        if (node.isRoot()) {
            return MenuNodeFilterResolution.Accept;
        }
        if (node.isGroupingNode()) {
            return MenuNodeFilterResolution.AcceptIfHasChildren;
        }
        if (menuNodeFavoriteProvider.isFavorite(node)) {
            return MenuNodeFilterResolution.Accept;
        }
        return MenuNodeFilterResolution.Reject;
    });
}


public Optional<MenuNode> getNewRecordNodeForWindowId(WindowId windowId){
    final DocumentId elementId = windowId.toDocumentId();
    final ArrayKey key = mkTypeAndElementIdKey(MenuNodeType.NewRecord, elementId);
    final List<MenuNode> nodes = nodesByTypeAndElementId.get(key);
    if (nodes == null || nodes.isEmpty()) {
        return Optional.empty();
    }
    final MenuNode newRecordNode = nodes.get(0);
    return Optional.of(newRecordNode);
}


public Optional<MenuNode> getNewRecordNodeForTableName(String tableName){
    return nodesByMainTableName.get(tableName).stream().filter(node -> node.getType() == MenuNodeType.NewRecord).findFirst();
}


public MenuNode getNodeById(String nodeId){
    final MenuNode node = nodesById.get(nodeId);
    if (node == null) {
        throw new NoMenuNodesFoundException("No menu node found for nodeId=" + nodeId);
    }
    return node;
}


public MenuNode getFirstNodeByElementIdOrNull(MenuNodeType type,DocumentId elementId){
    final ArrayKey key = mkTypeAndElementIdKey(type, elementId);
    final List<MenuNode> nodes = nodesByTypeAndElementId.get(key);
    if (nodes == null || nodes.isEmpty()) {
        return null;
    }
    return nodes.get(0);
}


public List<MenuNode> getPath(MenuNode node){
    Preconditions.checkNotNull(node, "node not null");
    final List<MenuNode> path = new ArrayList<>();
    MenuNode n = node;
    while (n != null) {
        path.add(0, n);
        n = n.getParent();
    }
    return path;
}


public MenuNode getRootNode(){
    return rootNode;
}


public MenuNode getTopLevelMenuGroupOrNull(WindowId windowId){
    final DocumentId elementId = windowId.toDocumentId();
    final MenuNode node = getFirstNodeByElementIdOrNull(MenuNodeType.Window, elementId);
    if (node == null) {
        return null;
    }
    final List<MenuNode> path = getPath(node);
    // NOTE: the top level menu group is at index "1" because on index "0" we have the menu root node.
    if (path.size() < 2) {
        return null;
    }
    return path.get(1);
}


}