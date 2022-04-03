package org.live.sys.init;
 import org.live.common.constants.SysConstants;
import org.live.sys.entity.Menu;
import org.live.sys.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
@Component
public class SysInitData implements ApplicationListener<ContextRefreshedEvent>{

 private  Logger LOGGER;

 private  String rootMenu;


@Override
public void onApplicationEvent(ContextRefreshedEvent event){
    ApplicationContext context = event.getApplicationContext();
    if (context.getParent() == null) {
        // 确保是spring 主容器启动
        MenuService menuService = context.getBean(MenuService.class);
        Menu root = menuService.findOne(rootMenu);
        if (root == null) {
            LOGGER.info("root菜单不存在，将初始化root菜单");
            Menu rootTemp = new Menu();
            rootTemp.setId("root");
            rootTemp.setMenuType(SysConstants.MENU_NODE_TYPE_FOLDER);
            rootTemp.setDescription("系统的根菜单，必须存在");
            rootTemp.setMenuName("根菜单");
            menuService.save(rootTemp);
        } else {
            LOGGER.info("已经存在root菜单");
        }
    }
}


}