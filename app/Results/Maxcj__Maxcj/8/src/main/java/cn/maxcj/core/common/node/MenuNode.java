package cn.maxcj.core.common.node;
 import cn.stylefeng.roses.kernel.model.enums.YesOrNotEnum;
import java.util;
public class MenuNode implements Comparable{

 private  Long id;

 private  Long parentId;

 private  String name;

 private  Integer levels;

 private  Integer ismenu;

 private  Integer num;

 private  String url;

 private  String icon;

 private  List<MenuNode> children;

 private  List<MenuNode> linkedList;

public MenuNode() {
    super();
}public MenuNode(Long id, Long parentId) {
    super();
    this.id = id;
    this.parentId = parentId;
}
public void setName(String name){
    this.name = name;
}


public List<MenuNode> mergeList(List<MenuNode> menuList,int rank,Map<Long,List<MenuNode>> listMap){
    // 保存当次调用总共合并了多少元素
    int n;
    // 保存当次调用总共合并出来的list
    Map<Long, List<MenuNode>> currentMap = new HashMap<>();
    // 由于按等级从小到大排序，需要从后往前排序
    // 判断该节点是否属于当前循环的等级,不等于则跳出循环
    for (n = menuList.size() - 1; n >= 0 && menuList.get(n).getLevels() == rank; n--) {
        // 判断之前的调用是否有返回以该节点的id为key的map，有则设置为children列表。
        if (listMap != null && listMap.get(menuList.get(n).getId()) != null) {
            menuList.get(n).setChildren(listMap.get(menuList.get(n).getId()));
        }
        if (menuList.get(n).getParentId() != null && menuList.get(n).getParentId() != 0) {
            // 判断当前节点所属的pid是否已经创建了以该pid为key的键值对，没有则创建新的链表
            currentMap.computeIfAbsent(menuList.get(n).getParentId(), k -> new LinkedList<>());
            // 将该节点插入到对应的list的头部
            currentMap.get(menuList.get(n).getParentId()).add(0, menuList.get(n));
        }
    }
    if (n < 0) {
        return menuList;
    } else {
        return mergeList(menuList.subList(0, n + 1), menuList.get(n).getLevels(), currentMap);
    }
}


public String getName(){
    return name;
}


public MenuNode createRoot(){
    return new MenuNode(0L, -1L);
}


public void setNum(Integer num){
    this.num = num;
}


public Long getId(){
    return id;
}


public void setIsmenu(Integer ismenu){
    this.ismenu = ismenu;
}


@Override
public int compareTo(Object o){
    MenuNode menuNode = (MenuNode) o;
    Integer num = menuNode.getNum();
    Integer levels = menuNode.getLevels();
    if (num == null) {
        num = 0;
    }
    if (levels == null) {
        levels = 0;
    }
    if (this.levels.compareTo(levels) == 0) {
        return this.num.compareTo(num);
    } else {
        return this.levels.compareTo(levels);
    }
}


public Integer getLevels(){
    return levels;
}


public void setLevels(Integer levels){
    this.levels = levels;
}


public String getIcon(){
    return icon;
}


public void setUrl(String url){
    this.url = url;
}


public void setIcon(String icon){
    this.icon = icon;
}


public String getUrl(){
    return url;
}


public List<MenuNode> getChildren(){
    return children;
}


public Integer getIsmenu(){
    return ismenu;
}


public Integer getNum(){
    return num;
}


public void setId(Long id){
    this.id = id;
}


public void setParentId(Long parentId){
    this.parentId = parentId;
}


@Override
public String toString(){
    return "MenuNode{" + "id=" + id + ", parentId=" + parentId + ", name='" + name + '\'' + ", levels=" + levels + ", num=" + num + ", url='" + url + '\'' + ", icon='" + icon + '\'' + ", children=" + children + ", linkedList=" + linkedList + '}';
}


public List<MenuNode> buildTitle(List<MenuNode> nodes){
    if (nodes.size() <= 0) {
        return nodes;
    }
    // 剔除非菜单
    nodes.removeIf(node -> !node.getIsmenu().equals(YesOrNotEnum.Y.getCode()));
    // 对菜单排序，返回列表按菜单等级，序号的排序方式排列
    Collections.sort(nodes);
    return mergeList(nodes, nodes.get(nodes.size() - 1).getLevels(), null);
}


public void setChildren(List<MenuNode> children){
    this.children = children;
}


public Long getParentId(){
    return parentId;
}


}