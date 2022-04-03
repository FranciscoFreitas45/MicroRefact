package cn.maxcj.modular.system.service;
 import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.modular.system.model.Dept;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IDeptService extends IService<Dept>{


public List<Map<String,Object>> clublist(Integer categoryId,String condition)
;

public List<ZTreeNode> sheliantree()
;

public List<ZTreeNode> clubtree(Integer deptId)
;

public void deleteDept(Integer deptId)
;

public List<ZTreeNode> tree()
;

public List<Map<String,Object>> list(String condition)
;

public List<Map<String,Object>> allclub(String condition,String clubCategory)
;

}