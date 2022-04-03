package org.jeecgframework.core.common.dao;
 import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.extend.template.Template;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
public interface ICommonDao extends IGenericBaseCommonDao{


public String getUserRole(TSUser user)
;

public T uploadFile(UploadFile uploadFile)
;

public HttpServletResponse createXml(ImportFile importFile)
;

public void parserXml(String fileName)
;

public void pwdInit(TSUser user,String newPwd)
;

public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile)
;

public List<TreeGrid> treegrid(List<?> all,TreeGridModel treeGridModel)
;

public TSUser getUserByUserIdAndUserNameExits(TSUser user)
;

public TSUser findUserByAccountAndPassword(String username,String password)
;

public List<ComboTree> comTree(List<TSDepart> all,ComboTree comboTree)
;

public Map<Object,Object> getDataSourceMap(Template template)
;

public List<ComboTree> ComboTree(List all,ComboTreeModel comboTreeModel,List in,boolean recursive)
;

}