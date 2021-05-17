import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import cn.offway.athena.domain.PhResource;
public class TreeParser {


public List<E> getSubList(Long id,List<E> entityList){
    List<E> childList = new ArrayList<>();
    Long parentId;
    // 子集的直接子对象
    for (E entity : entityList) {
        parentId = entity.getParentId();
        if (id.equals(parentId)) {
            childList.add(entity);
        }
    }
    // 子集的间接子对象
    for (E entity : childList) {
        entity.setChildList(getSubList(entity.getId(), entityList));
    }
    // 递归退出条件
    if (childList.size() == 0) {
        return null;
    }
    return childList;
}


public List<E> getTreeList(Long topId,List<E> entityList){
    List<E> resultList = new ArrayList<>();
    // 获取顶层元素集合
    Long parentId;
    for (E entity : entityList) {
        parentId = entity.getParentId();
        if (parentId == null || topId.longValue() == parentId.longValue()) {
            resultList.add(entity);
        }
    }
    // 获取每个顶层元素的子数据集合
    for (E entity : resultList) {
        entity.setChildList(getSubList(entity.getId(), entityList));
    }
    return resultList;
}


public void main(String[] args){
    List<PhResource> list = new ArrayList<>();
    PhResource p = new PhResource();
    p.setId(1L);
    p.setName("菜单1");
    p.setParentId(0L);
    list.add(p);
    PhResource p2 = new PhResource();
    p2.setId(2L);
    p2.setName("菜单2");
    p2.setParentId(0L);
    list.add(p2);
    PhResource p11 = new PhResource();
    p11.setId(3L);
    p11.setName("菜单11");
    p11.setParentId(1L);
    list.add(p11);
    PhResource p22 = new PhResource();
    p22.setId(4L);
    p22.setName("菜单22");
    p22.setParentId(2L);
    list.add(p22);
    PhResource p111 = new PhResource();
    p111.setId(5L);
    p111.setName("菜单111");
    p111.setParentId(1L);
    list.add(p111);
    List<PhResource> menus = TreeParser.getTreeList(0L, list);
    System.out.println(JSON.toJSONString(menus, SerializerFeature.WriteMapNullValue));
}


}