package de.metas.ui.web.menu;
 import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableList;
import de.metas.security.UserRolePermissionsKey;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.menu.MenuNode.MenuNodeType;
import de.metas.ui.web.menu.datatypes.json.JSONMenuNode;
import de.metas.ui.web.menu.datatypes.json.JSONMenuNodeType;
import de.metas.ui.web.menu.datatypes.json.JSONPatchMenuNodeRequest;
import de.metas.ui.web.menu.exception.NoMenuNodesFoundException;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@RestController
@RequestMapping(value = MenuRestController.ENDPOINT)
public class MenuRestController {

 public  String ENDPOINT;

 private  String PARAM_NodeId;

 private  String PARAM_Depth;

 private  String PARAM_Type;

 private  String PARAM_ElementId;

 private  String PARAM_NameQuery;

 private  String PARAM_IncludeLastNode;

 private  String PARAM_ChildrenLimit;

@Autowired
 private  UserSession userSession;

@Autowired
 private  MenuTreeRepository menuTreeRepository;

@Autowired
 private  DocumentDescriptorFactory documentDescriptorFactory;


public List<MenuNode> getPathOfMissingElement(MenuNodeType type,DocumentId elementId,String adLanguage){
    if (type == MenuNodeType.Window) {
        final String caption = documentDescriptorFactory.getDocumentDescriptor(WindowId.of(elementId)).getLayout().getCaption(adLanguage);
        return ImmutableList.of(MenuNode.builder().setType(type, elementId).setCaption(caption).setAD_Menu_ID_None().build());
    } else {
        throw new NoMenuNodesFoundException("No menu node found for type=" + type + " and elementId=" + elementId);
    }
}


@GetMapping("/queryPaths")
public JSONMenuNode query(String nameQuery,int childrenLimit,boolean includeLeafsIfGroupAccepted){
    userSession.assertLoggedIn();
    final MenuNode rootFiltered = getMenuTree().filter(nameQuery, includeLeafsIfGroupAccepted);
    if (rootFiltered == null) {
        throw new NoMenuNodesFoundException();
    }
    if (rootFiltered.getChildren().isEmpty()) {
        throw new NoMenuNodesFoundException();
    }
    return JSONMenuNode.builder(rootFiltered).setMaxLeafNodes(childrenLimit).setIsFavoriteProvider(menuTreeRepository).build();
}


@GetMapping("/elementPath")
public JSONMenuNode getPath(JSONMenuNodeType jsonType,String elementIdStr,boolean includeLastNode){
    userSession.assertLoggedIn();
    final MenuNodeType menuNodeType = jsonType.toMenuNodeType();
    final DocumentId elementId = DocumentId.of(elementIdStr);
    final List<MenuNode> path = getMenuTree().getPath(menuNodeType, elementId).orElseGet(() -> getPathOfMissingElement(menuNodeType, elementId, userSession.getAD_Language()));
    final boolean skipRootNode = true;
    return JSONMenuNode.ofPath(path, skipRootNode, includeLastNode, menuTreeRepository);
}


@PatchMapping("/node/{nodeId}")
public List<JSONMenuNode> patchNode(String nodeId,List<JSONDocumentChangedEvent> events){
    userSession.assertLoggedIn();
    final JSONPatchMenuNodeRequest request = JSONPatchMenuNodeRequest.ofChangeEvents(events);
    final MenuTree menuTree = getMenuTree();
    final MenuNode node = menuTree.getNodeById(nodeId);
    final LinkedHashMap<String, MenuNode> changedMenuNodesById = new LinkedHashMap<>();
    if (request.getFavorite() != null) {
        menuTreeRepository.setFavorite(node, request.getFavorite());
        menuTree.streamNodesByAD_Menu_ID(node.getAD_Menu_ID()).forEach(changedNode -> changedMenuNodesById.put(changedNode.getId(), changedNode));
    }
    return JSONMenuNode.ofList(changedMenuNodesById.values(), menuTreeRepository);
}


@ApiOperation("Gets breadcrumb menu to be displayed when user clicks on that node in the breadcrumb")
@GetMapping("/node/{nodeId}/breadcrumbMenu")
public List<JSONMenuNode> getNodeBreadcrumbMenu(String nodeId){
    userSession.assertLoggedIn();
    final List<MenuNode> children = getMenuTree().getNodeById(nodeId).getChildren().stream().filter(child -> child.isEffectiveLeafNode()).collect(ImmutableList.toImmutableList());
    return JSONMenuNode.ofList(children, menuTreeRepository);
}


@GetMapping("/node/{nodeId}")
public JSONMenuNode getNode(String nodeId,int depth,int childrenLimit){
    userSession.assertLoggedIn();
    final MenuNode node = getMenuTree().getNodeById(nodeId);
    return JSONMenuNode.builder(node).setMaxDepth(depth).setMaxChildrenPerNode(childrenLimit).setIsFavoriteProvider(menuTreeRepository::isFavorite).build();
}


@GetMapping("/root")
public JSONMenuNode getRoot(int depth,int childrenLimit,boolean onlyFavorites){
    userSession.assertLoggedIn();
    final MenuTree menuTree = getMenuTree();
    // 
    // Get the root node with favorites only, if asked
    MenuNode rootNode;
    if (onlyFavorites) {
        rootNode = menuTree.getRootNodeWithFavoritesOnly(menuTreeRepository);
        if (rootNode.getChildren().isEmpty()) {
            // If there were no favorites, return all
            rootNode = menuTree.getRootNode();
        }
    } else {
        rootNode = menuTree.getRootNode();
    }
    return JSONMenuNode.builder(rootNode).setMaxDepth(depth).setMaxChildrenPerNode(childrenLimit).setIsFavoriteProvider(menuTreeRepository).build();
}


public MenuTree getMenuTree(){
    final UserRolePermissionsKey userRolePermissionsKey = userSession.getUserRolePermissionsKey();
    final String adLanguage = userSession.getAD_Language();
    return menuTreeRepository.getMenuTree(userRolePermissionsKey, adLanguage);
}


}