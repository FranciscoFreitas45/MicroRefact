package org.live.sys.support;
 import java.util.ArrayList;
import java.util.List;
public class CombinableUtils {


public List<? extends Combinable> getChild(Object root,List<? extends Combinable> allCombinables){
    // 子菜单
    List<Combinable> childs = new ArrayList<Combinable>();
    for (Combinable combinable : allCombinables) {
        if (combinable.compare(root)) {
            // 判断子菜单的父菜单是否等于root,如果加到子菜单的列表中
            childs.add(combinable);
        }
    }
    for (Combinable childCom : childs) {
        // 循环遍历子菜单，并为子菜单设置子菜单.
        childCom.setChilds(getChild(childCom, allCombinables));
    }
    if (childs.size() == 0) {
        // 子菜单的长度等于0，退出递归.
        return null;
    }
    return childs;
}


public List<? extends Combinable> executeCombination(Object root,List<? extends Combinable> allCombinables){
    if (allCombinables != null) {
        return getChild(root, allCombinables);
    }
    return null;
}


}