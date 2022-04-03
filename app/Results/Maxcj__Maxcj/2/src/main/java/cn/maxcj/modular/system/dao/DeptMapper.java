package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Dept;
import cn.maxcj.core.common.node.ZTreeNode;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface DeptMapper extends BaseMapper<Dept>{


public List<Map<String,Object>> clublist(String condition)
;

public List<ZTreeNode> sheliantree()
;

public List<ZTreeNode> clubtree(Integer deptId)
;

public List<Map<String,Object>> clublistWithcategoryId(Integer categoryId,String condition)
;

public List<ZTreeNode> tree()
;

public List<Map<String,Object>> list(String condition)
;

public List<Map<String,Object>> allclub(String condition,String clubCategory)
;

}