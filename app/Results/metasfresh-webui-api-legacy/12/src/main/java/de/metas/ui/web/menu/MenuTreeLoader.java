package de.metas.ui.web.menu;
 import java.util.Enumeration;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.tree.AdTreeId;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.model.X_AD_Menu;
import org.compiere.util.Env;
import org.slf4j.Logger;
import de.metas.logging.LogManager;
import de.metas.menu.AdMenuId;
import de.metas.security.IUserRolePermissions;
import de.metas.security.IUserRolePermissionsDAO;
import de.metas.security.UserRolePermissionsKey;
import de.metas.security.permissions.UserMenuInfo;
import de.metas.ui.web.menu.MenuNode.MenuNodeType;
import de.metas.ui.web.process.ProcessId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
public class MenuTreeLoader {

 private  Logger logger;

 private  IUserRolePermissionsDAO userRolePermissionsDAO;

 private  int DEPTH_Root;

 private  int DEPTH_RootChildren;

 private  UserRolePermissionsKey _userRolePermissionsKey;

 private  IUserRolePermissions _userRolePermissions;

 private  String _adLanguage;

 private  long _version;


public MenuNode createMenuNodeRecursivelly(MTreeNode nodeModel,int depth){
    final MenuNode.Builder nodeBuilder = createMenuNodeBuilder(nodeModel, depth);
    if (nodeBuilder == null) {
        logger.trace("Skip creating menu node for {}", nodeModel);
        return null;
    }
    final Enumeration<?> childModels = nodeModel.children();
    while (childModels.hasMoreElements()) {
        final MTreeNode childModel = (MTreeNode) childModels.nextElement();
        final MenuNode childNode = createMenuNodeRecursivelly(childModel, depth + 1);
        if (childNode == null) {
            continue;
        }
        if (childModel.isCreateNewRecord()) {
            final MenuNode childNodeNewRecord = createNewRecordNode(childNode, childModel.getWEBUI_NameNew(), childModel.getWEBUI_NameNewBreadcrumb());
            if (childNodeNewRecord != null) {
                nodeBuilder.addChildToFirstsList(childNodeNewRecord);
            }
        }
        nodeBuilder.addChild(childNode);
    }
    return nodeBuilder.build();
}


public long getVersion(){
    if (_version < 0) {
        _version = userRolePermissionsDAO.getCacheVersion();
    }
    return _version;
}


public UserMenuInfo getUserMenuInfo(){
    final IUserRolePermissions userRolePermissions = getUserRolePermissions();
    if (!userRolePermissions.hasPermission(IUserRolePermissions.PERMISSION_MenuAvailable)) {
        return UserMenuInfo.NONE;
    }
    return userRolePermissions.getMenuInfo();
}


public MenuTreeLoader setAD_Language(String adLanguage){
    this._adLanguage = adLanguage;
    return this;
}


public MenuTreeLoader setUserRolePermissionsKey(UserRolePermissionsKey userRolePermissionsKey){
    _userRolePermissionsKey = userRolePermissionsKey;
    return this;
}


@NonNull
public UserRolePermissionsKey getUserRolePermissionsKey(){
    return _userRolePermissionsKey;
}


public MenuNode createNewRecordNode(MenuNode node,String caption,String captionBreadcrumb){
    if (node.getType() != MenuNodeType.Window) {
        return null;
    }
    // 
    // Caption (in menu)
    String captionEffective = caption;
    if (Check.isEmpty(captionEffective, true)) {
        captionEffective = "New " + node.getCaption();
    }
    // 
    // Caption (breadcrumb)
    String captionBreadcrumbEffective = captionBreadcrumb;
    if (Check.isEmpty(captionBreadcrumbEffective, true)) {
        captionBreadcrumbEffective = node.getCaptionBreadcrumb();
    }
    return MenuNode.builder().setAD_Menu_ID(node.getAD_Menu_ID()).setCaption(captionEffective).setCaptionBreadcrumb(captionBreadcrumbEffective).setType(MenuNodeType.NewRecord, node.getElementId()).setMainTableName(node.getMainTableName()).build();
}


public MenuTreeLoader newInstance(){
    return new MenuTreeLoader();
}


public MTreeNode retrieveRootNodeModel(){
    final UserMenuInfo userMenuInfo = getUserMenuInfo();
    final AdTreeId adTreeId = userMenuInfo.getAdTreeId();
    if (adTreeId == null) {
        throw new AdempiereException("Menu tree not found");
    }
    final MTree mTree = MTree.builder().setCtx(Env.getCtx()).setTrxName(ITrx.TRXNAME_None).setAD_Tree_ID(adTreeId.getRepoId()).setEditable(false).setClientTree(true).setLanguage(getAD_Language()).build();
    final MTreeNode rootNodeModel = mTree.getRoot();
    AdMenuId rootMenuIdEffective = userMenuInfo.getRootMenuId();
    if (rootMenuIdEffective != null) {
        final MTreeNode rootNodeModelEffective = rootNodeModel.findNode(rootMenuIdEffective.getRepoId());
        if (rootNodeModelEffective != null) {
            return rootNodeModelEffective;
        } else {
            logger.warn("Cannot find Root_Menu_ID={} in {}", rootMenuIdEffective, mTree);
        }
    }
    return rootNodeModel;
}


@NonNull
public String getAD_Language(){
    return _adLanguage;
}


public MenuNode.Builder createMenuNodeBuilder(MTreeNode nodeModel,int depth){
    // shall not be empty
    final String captionBreadcrumb = nodeModel.getName();
    String caption = nodeModel.getWEBUI_NameBrowse();
    if (Check.isEmpty(caption, true)) {
        caption = captionBreadcrumb;
    }
    final MenuNode.Builder builder = MenuNode.builder().setAD_Menu_ID(nodeModel.getNode_ID()).setCaption(caption).setCaptionBreadcrumb(captionBreadcrumb).setMainTableName(nodeModel.getMainTableName());
    final String action = nodeModel.getImageIndiactor();
    if (nodeModel.isSummary()) {
        builder.setTypeGroup();
    } else if (depth == DEPTH_RootChildren) {
        logger.warn("Skip creating leaf nodes for root: {}", nodeModel);
        return null;
    } else if (X_AD_Menu.ACTION_Window.equals(action)) {
        final DocumentId elementId = DocumentId.of(nodeModel.getAD_Window_ID());
        builder.setType(MenuNodeType.Window, elementId);
    } else if (X_AD_Menu.ACTION_Process.equals(action)) {
        final DocumentId elementId = ProcessId.ofAD_Process_ID(nodeModel.getAD_Process_ID()).toDocumentId();
        builder.setType(MenuNodeType.Process, elementId);
    } else if (X_AD_Menu.ACTION_Report.equals(action)) {
        final DocumentId elementId = ProcessId.ofAD_Process_ID(nodeModel.getAD_Process_ID()).toDocumentId();
        builder.setType(MenuNodeType.Report, elementId);
    } else if (X_AD_Menu.ACTION_Board.equals(action)) {
        final DocumentId elementId = DocumentId.of(nodeModel.getWEBUI_Board_ID());
        builder.setType(MenuNodeType.Board, elementId);
    } else {
        return null;
    }
    return builder;
}


public IUserRolePermissions getUserRolePermissions(){
    if (_userRolePermissions == null) {
        final UserRolePermissionsKey userRolePermissionsKey = getUserRolePermissionsKey();
        _userRolePermissions = userRolePermissionsDAO.getUserRolePermissions(userRolePermissionsKey);
    }
    return _userRolePermissions;
}


public MenuTree load(){
    if (logger.isTraceEnabled()) {
        logger.trace("Loading menu tree for {}", getUserRolePermissions());
    }
    final MTreeNode rootNodeModel = retrieveRootNodeModel();
    final MenuNode rootNode = createMenuNodeRecursivelly(rootNodeModel, DEPTH_Root);
    if (rootNode == null) {
        // shall not happen
        throw new IllegalStateException("No root menu node available");
    }
    final long version = getVersion();
    return MenuTree.of(version, rootNode);
}


}