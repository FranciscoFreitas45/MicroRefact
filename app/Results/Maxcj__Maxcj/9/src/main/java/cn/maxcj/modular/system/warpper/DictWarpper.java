package cn.maxcj.modular.system.warpper;
 import cn.hutool.core.util.StrUtil;
import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.modular.system.model.Dict;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
public class DictWarpper extends BaseControllerWrapper{

public DictWarpper(Map<String, Object> single) {
    super(single);
}public DictWarpper(List<Map<String, Object>> multi) {
    super(multi);
}public DictWarpper(Page<Map<String, Object>> page) {
    super(page);
}public DictWarpper(PageResult<Map<String, Object>> pageResult) {
    super(pageResult);
}
@Override
public void wrapTheMap(Map<String,Object> map){
    StringBuffer detail = new StringBuffer();
    Integer id = Integer.valueOf(map.get("id").toString());
    List<Dict> dicts = ConstantFactory.me().findInDict(id);
    if (dicts != null) {
        for (Dict dict : dicts) {
            detail.append(dict.getCode() + ":" + dict.getName() + ",");
        }
        map.put("detail", StrUtil.removeSuffix(detail.toString(), ","));
    }
}


}