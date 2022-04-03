package com.vino.scaffold.utils;
 import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.vino.scaffold.shiro.entity.Resource;
import com.vino.scaffold.shiro.entity.Role;
import com.vino.scaffold.shiro.entity.User;
import com.vino.scaffold.shiro.DTO.User;
import com.vino.scaffold.shiro.DTO.Role;
public class TreeUtils {


public Tree formatUserToTree(User user){
    Tree tree = new Tree();
    tree.setId(user.getId());
    tree.setName(user.getUsername());
    tree.setpId(0L);
    return tree;
}


public List<Tree> fomatResourceToTree(List<Resource> resources){
    List<Tree> trees = new ArrayList<Tree>();
    Tree root = new Tree();
    // ���ڵ��idΪ0
    root.setId((long) 0);
    root.setpId(0l);
    root.setName("Root�ڵ�");
    root.setChecked(false);
    trees.add(root);
    for (Resource res : resources) {
        // checkbox��ѡ��
        Tree tree = TreeUtils.formatResourceToTree(res, false);
        trees.add(tree);
    }
    return trees;
}


public Tree formatRoleToTree(Role role){
    Tree tree = new Tree();
    tree.setId(role.getId());
    tree.setName(role.getName());
    tree.setpId(0L);
    return tree;
}


public Tree formatResourceToTree(Resource resource,boolean checked){
    Tree tree = new Tree();
    tree.setId(resource.getId());
    tree.setName(resource.getName());
    tree.setpId(resource.getParentId());
    if (checked) {
        tree.setChecked(true);
    } else
        tree.setChecked(false);
    return tree;
}


}