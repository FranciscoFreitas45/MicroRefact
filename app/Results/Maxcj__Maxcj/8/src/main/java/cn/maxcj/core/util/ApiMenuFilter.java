package cn.maxcj.core.util;
 import cn.maxcj.config.properties.GunsProperties;
import cn.maxcj.core.common.constant.Const;
import cn.maxcj.core.common.node.MenuNode;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import java.util.ArrayList;
import java.util.List;
public class ApiMenuFilter extends MenuNode{


public List<MenuNode> build(List<MenuNode> nodes){
    // 如果关闭了接口文档,则不显示接口文档菜单
    GunsProperties gunsProperties = SpringContextHolder.getBean(GunsProperties.class);
    if (!gunsProperties.getSwaggerOpen()) {
        List<MenuNode> menuNodesCopy = new ArrayList<>();
        for (MenuNode menuNode : nodes) {
            if (Const.API_MENU_NAME.equals(menuNode.getName())) {
                continue;
            } else {
                menuNodesCopy.add(menuNode);
            }
        }
        nodes = menuNodesCopy;
    }
    return nodes;
}


}