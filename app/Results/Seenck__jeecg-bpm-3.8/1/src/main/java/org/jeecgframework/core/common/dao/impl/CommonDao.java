package org.jeecgframework.core.common.dao.impl;
 import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hibernate.Query;
import org.jeecgframework.core.common.dao.ICommonDao;
import org.jeecgframework.core.common.dao.IGenericBaseCommonDao;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.extend.swftools.SwfToolsUtil;
import org.jeecgframework.core.extend.template.DataSourceMap;
import org.jeecgframework.core.extend.template.Template;
import org.jeecgframework.core.util;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util;
import DTO.TSRoleUser;
import DTO.TSOperation;
import DTO.ComboTreeModel;
@Repository
public class CommonDao extends GenericBaseCommonDaoimplements IGenericBaseCommonDao,ICommonDao{


public String getUserRole(TSUser user){
    String userRole = "";
    List<TSRoleUser> sRoleUser = findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
    for (TSRoleUser tsRoleUser : sRoleUser) {
        userRole += tsRoleUser.getTSRole().getRoleCode() + ",";
    }
    return userRole;
}


@SuppressWarnings("unchecked")
public Object uploadFile(UploadFile uploadFile){
    Object object = uploadFile.getObject();
    if (uploadFile.getFileKey() != null) {
        updateEntitie(object);
    } else {
        try {
            uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
            ReflectHelper reflectHelper = new ReflectHelper(uploadFile.getObject());
            // ?????????????????????
            String uploadbasepath = uploadFile.getBasePath();
            if (uploadbasepath == null) {
                uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
            }
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            // ???????????????????????????
            // ????????????????????????????????????
            String path = uploadbasepath + "/";
            // ???????????????????????????
            String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + "/" + path;
            File file = new File(realPath);
            if (!file.exists()) {
                // ???????????????
                file.mkdirs();
            }
            if (uploadFile.getCusPath() != null) {
                realPath += uploadFile.getCusPath() + "/";
                path += uploadFile.getCusPath() + "/";
                file = new File(realPath);
                if (!file.exists()) {
                    // ??????????????????????????????
                    file.mkdirs();
                }
            } else {
                realPath += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
                path += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
                file = new File(realPath);
                if (!file.exists()) {
                    // ???????????????????????????
                    file.mkdir();
                }
            }
            String entityName = uploadFile.getObject().getClass().getSimpleName();
            // ????????????????????????
            if (entityName.equals("TSTemplate")) {
                realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + ResourceUtil.getConfigByName("templatepath") + "/";
                path = ResourceUtil.getConfigByName("templatepath") + "/";
            } else if (entityName.equals("TSIcon")) {
                realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + uploadFile.getCusPath() + "/";
                path = uploadFile.getCusPath() + "/";
            }
            String fileName = "";
            String swfName = "";
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // ????????????????????????
                MultipartFile mf = entity.getValue();
                // ???????????????
                fileName = mf.getOriginalFilename();
                // ???????????????????????????SWF?????????
                swfName = PinyinUtil.getPinYinHeadChar(oConvertUtils.replaceBlank(FileUtils.getFilePrefix(fileName)));
                // ?????????????????????
                String extend = FileUtils.getExtend(fileName);
                String myfilename = "";
                // ???????????????
                String noextfilename = "";
                if (uploadFile.isRename()) {
                    // ?????????????????????
                    noextfilename = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8);
                    // ?????????????????????
                    myfilename = noextfilename + "." + extend;
                } else {
                    myfilename = fileName;
                }
                // ?????????????????????
                String savePath = realPath + myfilename;
                String fileprefixName = FileUtils.getFilePrefix(fileName);
                if (uploadFile.getTitleField() != null) {
                    // ????????????set?????????????????????????????????
                    reflectHelper.setMethodValue(uploadFile.getTitleField(), fileprefixName);
                }
                if (uploadFile.getExtend() != null) {
                    // ???????????? set?????????????????????????????????
                    reflectHelper.setMethodValue(uploadFile.getExtend(), extend);
                }
                if (uploadFile.getByteField() != null) {
                // ????????????????????????????????????
                // ---update-begin-----author:scott--date:20160503----for:?????????????????????----------------
                // reflectHelper.setMethodValue(uploadFile.getByteField(), StreamUtils.InputStreamTOByte(mf.getInputStream()));
                // ---update-end-----author:scott--date:20160503----for:?????????????????????----------------
                }
                File savefile = new File(savePath);
                if (uploadFile.getRealPath() != null) {
                    // ????????????????????????????????????
                    reflectHelper.setMethodValue(uploadFile.getRealPath(), path + myfilename);
                }
                saveOrUpdate(object);
                // ?????????????????????????????????
                // update-begin--Author:jg_renjie  Date:20150607 for???????????????????????????UTF-8?????????TXT?????????????????????????????????
                if ("txt".equals(extend)) {
                    // ??????utf-8??????????????????????????????????????????
                    // Unicode:FF FE   UTF-8:EF BB
                    byte[] allbytes = mf.getBytes();
                    try {
                        String head1 = toHexString(allbytes[0]);
                        // System.out.println(head1);
                        String head2 = toHexString(allbytes[1]);
                        // System.out.println(head2);
                        if ("ef".equals(head1) && "bb".equals(head2)) {
                            // UTF-8
                            String contents = new String(mf.getBytes(), "UTF-8");
                            if (StringUtils.isNotBlank(contents)) {
                                OutputStream out = new FileOutputStream(savePath);
                                out.write(contents.getBytes());
                                out.close();
                            }
                        } else {
                            // update-begin--Author:zhoujf  Date:20150610 for???TXT?????????????????????????????????
                            // GBK
                            String contents = new String(mf.getBytes(), "GBK");
                            OutputStream out = new FileOutputStream(savePath);
                            out.write(contents.getBytes());
                            out.close();
                        // update-end--Author:zhoujf  Date:20150610 for???TXT?????????????????????????????????
                        }
                    } catch (Exception e) {
                        String contents = new String(mf.getBytes(), "UTF-8");
                        if (StringUtils.isNotBlank(contents)) {
                            OutputStream out = new FileOutputStream(savePath);
                            out.write(contents.getBytes());
                            out.close();
                        }
                    }
                } else {
                    FileCopyUtils.copy(mf.getBytes(), savefile);
                }
                // update-begin--Author:jg_renjie  Date:20150607 for???????????????????????????UTF-8?????????TXT?????????????????????????????????
                // if (uploadFile.getSwfpath() != null) {
                // // ???SWF
                // reflectHelper.setMethodValue(uploadFile.getSwfpath(), path + swfName + ".swf");
                // SwfToolsUtil.convert2SWF(savePath);
                // }
                // FileCopyUtils.copy(mf.getBytes(), savefile);
                // update-begin--Author:scott  Date:20180726 for??????????????????????????????????????????swf??????????????????????????????--------------
                // ?????????????????????????????????swf?????????????????????????????????
                String globalSwfTransformFlag = ResourceUtil.getConfigByName("swf.transform.flag");
                if ("true".equals(globalSwfTransformFlag) && uploadFile.getSwfpath() != null) {
                    // ???SWF
                    reflectHelper.setMethodValue(uploadFile.getSwfpath(), path + FileUtils.getFilePrefix(myfilename) + ".swf");
                    SwfToolsUtil.convert2SWF(savePath);
                }
            // update-end--Author:scott  Date:20180726 for??????????????????????????????????????????swf??????????????????????????????--------------
            }
        } catch (Exception e1) {
        }
    }
    return object;
}


public void pwdInit(TSUser user,String newPwd){
    String query = "from TSUser u where u.userName = :username ";
    Query queryObject = getSession().createQuery(query);
    queryObject.setParameter("username", user.getUserName());
    List<TSUser> users = queryObject.list();
    if (null != users && users.size() > 0) {
        user = users.get(0);
        String pwd = PasswordUtil.encrypt(user.getUserName(), newPwd, PasswordUtil.getStaticSalt());
        user.setPassword(pwd);
        save(user);
    }
}


public List<TreeGrid> treegrid(List<?> all,TreeGridModel treeGridModel){
    List<TreeGrid> treegrid = new ArrayList<TreeGrid>();
    for (Object obj : all) {
        ReflectHelper reflectHelper = new ReflectHelper(obj);
        TreeGrid tg = new TreeGrid();
        String id = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getIdField()));
        String src = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getSrc()));
        String text = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getTextField()));
        if (StringUtils.isNotEmpty(treeGridModel.getOrder())) {
            String order = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getOrder()));
            tg.setOrder(order);
        }
        tg.setId(id);
        if (treeGridModel.getIcon() != null) {
            String iconpath = TagUtil.fieldNametoValues(treeGridModel.getIcon(), obj).toString();
            if (iconpath != null) {
                tg.setCode(iconpath);
            } else {
                tg.setCode("");
            }
        }
        tg.setSrc(src);
        tg.setText(text);
        if (treeGridModel.getParentId() != null) {
            Object pid = TagUtil.fieldNametoValues(treeGridModel.getParentId(), obj);
            if (pid != null) {
                tg.setParentId(pid.toString());
            } else {
                tg.setParentId("");
            }
        }
        if (treeGridModel.getParentText() != null) {
            Object ptext = TagUtil.fieldNametoValues(treeGridModel.getTextField(), obj);
            if (ptext != null) {
                tg.setParentText(ptext.toString());
            } else {
                tg.setParentText("");
            }
        }
        List childList = (List) reflectHelper.getMethodValue(treeGridModel.getChildList());
        if (childList != null && childList.size() > 0) {
            tg.setState("closed");
        }
        if (treeGridModel.getRoleid() != null) {
            String[] opStrings = {};
            List<TSRoleFunction> roleFunctions = findByProperty(TSRoleFunction.class, "TSFunction.id", id);
            if (roleFunctions.size() > 0) {
                for (TSRoleFunction tRoleFunction : roleFunctions) {
                    TSRoleFunction roleFunction = tRoleFunction;
                    if (roleFunction.getTSRole().getId().toString().equals(treeGridModel.getRoleid())) {
                        String bbString = roleFunction.getOperation();
                        if (bbString != null) {
                            opStrings = bbString.split(",");
                            break;
                        }
                    }
                }
            }
            List<TSOperation> operateions = findByProperty(TSOperation.class, "TSFunction.id", id);
            StringBuffer attributes = new StringBuffer();
            if (operateions.size() > 0) {
                for (TSOperation tOperation : operateions) {
                    if (opStrings.length < 1) {
                        attributes.append("<input type=checkbox name=operatons value=" + tOperation.getId() + "_" + id + ">" + tOperation.getOperationname());
                    } else {
                        StringBuffer sb = new StringBuffer();
                        sb.append("<input type=checkbox name=operatons");
                        for (int i = 0; i < opStrings.length; i++) {
                            if (opStrings[i].equals(tOperation.getId().toString())) {
                                sb.append(" checked=checked");
                            }
                        }
                        sb.append(" value=" + tOperation.getId() + "_" + id + ">" + tOperation.getOperationname());
                        attributes.append(sb.toString());
                    }
                }
            }
            tg.setOperations(attributes.toString());
        }
        if (treeGridModel.getFieldMap() != null) {
            tg.setFieldMap(new HashMap<String, Object>());
            for (Map.Entry<String, Object> entry : treeGridModel.getFieldMap().entrySet()) {
                Object fieldValue = reflectHelper.getMethodValue(entry.getValue().toString());
                tg.getFieldMap().put(entry.getKey(), fieldValue);
            }
        }
        // update-begin--Author:anchao  Date:20140822 for???[bugfree???]????????????????????????????????????--------------------
        if (treeGridModel.getFunctionType() != null) {
            String functionType = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getFunctionType()));
            tg.setFunctionType(functionType);
        }
        // update-end--Author:anchao  Date:20140822 for???[bugfree???]????????????????????????????????????--------------------
        // update-begin--Author:chenj  Date:20160722 for???????????????????????????
        if (treeGridModel.getIconStyle() != null) {
            String iconStyle = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getIconStyle()));
            tg.setIconStyle(iconStyle);
        }
        // update-end--Author:chenj  Date:20160722 for???????????????????????????
        treegrid.add(tg);
    }
    return treegrid;
}


public String toHexString(int index){
    String hexString = Integer.toHexString(index);
    // 1???byte??????16?????????????????????2?????????????????????????????????????????????????????????????????????
    hexString = hexString.substring(hexString.length() - 2);
    return hexString;
}


public TSUser findUserByAccountAndPassword(String username,String inpassword){
    String password = PasswordUtil.encrypt(username, inpassword, PasswordUtil.getStaticSalt());
    String query = "from TSUser u where u.userName = :username and u.password=:passowrd";
    Query queryObject = getSession().createQuery(query);
    queryObject.setParameter("username", username);
    queryObject.setParameter("passowrd", password);
    @SuppressWarnings("unchecked")
    List<TSUser> users = queryObject.list();
    if (users != null && users.size() > 0) {
        return users.get(0);
    }
    return null;
}


public List<ComboTree> comTree(List<TSDepart> all,ComboTree comboTree){
    List<ComboTree> trees = new ArrayList<ComboTree>();
    for (TSDepart depart : all) {
        trees.add(tree(depart, true));
    }
    return trees;
}


@SuppressWarnings("unchecked")
public ComboTree tree(TSDepart depart,boolean recursive){
    ComboTree tree = new ComboTree();
    tree.setId(oConvertUtils.getString(depart.getId()));
    tree.setText(depart.getDepartname());
    List<TSDepart> departsList = findByProperty(TSDepart.class, "TSPDepart.id", depart.getId());
    if (departsList != null && departsList.size() > 0) {
        tree.setState("closed");
        tree.setChecked(false);
        if (recursive) {
            // ?????????????????????
            List<TSDepart> departList = new ArrayList<TSDepart>(departsList);
            // Collections.sort(departList, new SetListSort());// ??????
            List<ComboTree> children = new ArrayList<ComboTree>();
            for (TSDepart d : departList) {
                ComboTree t = tree(d, true);
                children.add(t);
            }
            tree.setChildren(children);
        }
    }
    return tree;
}


public Map<Object,Object> getDataSourceMap(Template template){
    return DataSourceMap.getDataSourceMap();
}


public HttpServletResponse createXml(ImportFile importFile){
    HttpServletResponse response = importFile.getResponse();
    HttpServletRequest request = importFile.getRequest();
    try {
        // ??????document??????
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("UTF-8");
        // ???????????????
        String rootname = importFile.getEntityName() + "s";
        Element rElement = document.addElement(rootname);
        Class entityClass = importFile.getEntityClass();
        String[] fields = importFile.getField().split(",");
        // ???????????????????????????
        List objList = loadAll(entityClass);
        Class classType = entityClass.getClass();
        for (Object t : objList) {
            Element childElement = rElement.addElement(importFile.getEntityName());
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i];
                // ????????????????????????
                if (i == 0) {
                    childElement.addAttribute(fieldName, String.valueOf(TagUtil.fieldNametoValues(fieldName, t)));
                } else {
                    Element name = childElement.addElement(fieldName);
                    name.setText(String.valueOf(TagUtil.fieldNametoValues(fieldName, t)));
                }
            }
        }
        String ctxPath = request.getSession().getServletContext().getRealPath("");
        File fileWriter = new File(ctxPath + "/" + importFile.getFileName());
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileWriter));
        xmlWriter.write(document);
        xmlWriter.close();
        // ???????????????XML??????
        UploadFile uploadFile = new UploadFile(request, response);
        uploadFile.setRealPath(importFile.getFileName());
        uploadFile.setTitleField(importFile.getFileName());
        uploadFile.setExtend("bak");
        viewOrDownloadFile(uploadFile);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return response;
}


@SuppressWarnings("unchecked")
public void parserXml(String fileName){
    try {
        File inputXml = new File(fileName);
        Class entityClass;
        // ????????????
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputXml);
        Element employees = document.getRootElement();
        // ??????????????????????????????
        for (Iterator i = employees.elementIterator(); i.hasNext(); ) {
            Element employee = (Element) i.next();
            // ?????????????????????????????????
            entityClass = GenericsUtils.getEntityClass(employee.getName());
            // ??????????????????
            Field[] fields = TagUtil.getFiled(entityClass);
            // ???????????????ID
            String id = employee.attributeValue(fields[0].getName());
            // ???????????????????????????
            Object obj1 = getEntity(entityClass, id);
            // ???????????????new?????????
            if (obj1 == null) {
                obj1 = entityClass.newInstance();
            }
            // ?????????????????????????????????
            for (Iterator j = employee.elementIterator(); j.hasNext(); ) {
                Element node = (Element) j.next();
                for (int k = 0; k < fields.length; k++) {
                    if (node.getName().equals(fields[k].getName())) {
                        String fieldName = fields[k].getName();
                        String stringLetter = fieldName.substring(0, 1).toUpperCase();
                        String setName = "set" + stringLetter + fieldName.substring(1);
                        Method setMethod = entityClass.getMethod(setName, new Class[] { fields[k].getType() });
                        String type = TagUtil.getColumnType(fieldName, fields);
                        if (type.equals("int")) {
                            setMethod.invoke(obj1, new Integer(node.getText()));
                        } else if (type.equals("string")) {
                            setMethod.invoke(obj1, node.getText().toString());
                        } else if (type.equals("short")) {
                            setMethod.invoke(obj1, new Short(node.getText()));
                        } else if (type.equals("double")) {
                            setMethod.invoke(obj1, new Double(node.getText()));
                        } else if (type.equals("Timestamp")) {
                            setMethod.invoke(obj1, new Timestamp(DateUtils.str2Date(node.getText(), DateUtils.datetimeFormat).getTime()));
                        }
                    }
                }
            }
            if (obj1 != null) {
                saveOrUpdate(obj1);
            } else {
                save(obj1);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@SuppressWarnings("unchecked")
public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile){
    uploadFile.getResponse().setContentType("UTF-8");
    uploadFile.getResponse().setCharacterEncoding("UTF-8");
    InputStream bis = null;
    BufferedOutputStream bos = null;
    HttpServletResponse response = uploadFile.getResponse();
    HttpServletRequest request = uploadFile.getRequest();
    String ctxPath = request.getSession().getServletContext().getRealPath("/");
    String downLoadPath = "";
    long fileLength = 0;
    try {
        if (uploadFile.getRealPath() != null && uploadFile.getContent() == null) {
            downLoadPath = ctxPath + uploadFile.getRealPath();
            fileLength = new File(downLoadPath).length();
            try {
                bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            if (uploadFile.getContent() != null)
                bis = new ByteArrayInputStream(uploadFile.getContent());
            fileLength = uploadFile.getContent().length;
        }
        if (!uploadFile.isView() && uploadFile.getExtend() != null) {
            if (uploadFile.getExtend().equals("text")) {
                response.setContentType("text/plain;");
            } else if (uploadFile.getExtend().equals("doc")) {
                response.setContentType("application/msword;");
            } else if (uploadFile.getExtend().equals("xls")) {
                response.setContentType("application/ms-excel;");
            } else if (uploadFile.getExtend().equals("pdf")) {
                response.setContentType("application/pdf;");
            } else if (uploadFile.getExtend().equals("jpg") || uploadFile.getExtend().equals("jpeg")) {
                response.setContentType("image/jpeg;");
            } else {
                response.setContentType("application/x-msdownload;");
            }
            response.setHeader("Content-disposition", "attachment; filename=" + new String((uploadFile.getTitleField() + "." + uploadFile.getExtend()).getBytes("GBK"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
        }
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return response;
}


public ComboTree comboTree(Object obj,ComboTreeModel comboTreeModel,List in,boolean recursive){
    ComboTree tree = new ComboTree();
    Map<String, Object> attributes = new HashMap<String, Object>();
    ReflectHelper reflectHelper = new ReflectHelper(obj);
    String id = oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getIdField()));
    tree.setId(id);
    tree.setText(oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getTextField())));
    if (comboTreeModel.getSrcField() != null) {
        attributes.put("href", oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getSrcField())));
        tree.setAttributes(attributes);
    }
    if (in == null) {
    } else {
        if (in.size() > 0) {
            for (Object inobj : in) {
                ReflectHelper reflectHelper2 = new ReflectHelper(inobj);
                String inId = oConvertUtils.getString(reflectHelper2.getMethodValue(comboTreeModel.getIdField()));
                // update-begin--Author:JueYue  Date:20140514 for???==????????????--------------------
                if (inId.equals(id)) {
                    tree.setChecked(true);
                }
            // update-end--Author:JueYue  Date:20140514 for???==????????????--------------------
            }
        }
    }
    // update-begin--Author:zhangguoming  Date:20140819 for????????????????????????
    List curChildList = (List) reflectHelper.getMethodValue(comboTreeModel.getChildField());
    if (curChildList != null && curChildList.size() > 0) {
        tree.setState("closed");
        tree.setChecked(false);
        if (recursive) {
            // ?????????????????????
            List<ComboTree> children = new ArrayList<ComboTree>();
            List nextChildList = new ArrayList(curChildList);
            for (Object childObj : nextChildList) {
                ComboTree t = comboTree(childObj, comboTreeModel, in, recursive);
                children.add(t);
            }
            tree.setChildren(children);
        }
    }
    // update-end--Author:zhangguoming  Date:20140819 for????????????????????????
    // update-begin--Author:scott  Date:20160530 for???????????????????????????
    if (curChildList != null) {
        curChildList.clear();
    }
    // update-end--Author:scott  Date:20140819 for???????????????????????????
    return tree;
}


public TSUser getUserByUserIdAndUserNameExits(TSUser user){
    String password = PasswordUtil.encrypt(user.getUserName(), user.getPassword(), PasswordUtil.getStaticSalt());
    String query = "from TSUser u where u.userName = :username and u.password=:passowrd";
    Query queryObject = getSession().createQuery(query);
    queryObject.setParameter("username", user.getUserName());
    queryObject.setParameter("passowrd", password);
    List<TSUser> users = queryObject.list();
    // update-start-Author:jg_renjie  Date:20151220 for?????????TASK #804 ?????????????????????????????????session???????????????????????????????????????password?????????????????????,??????????????????
    if (users != null && users.size() > 0) {
        return users.get(0);
    } else {
        queryObject = getSession().createQuery(query);
        queryObject.setParameter("username", user.getUserName());
        queryObject.setParameter("passowrd", user.getPassword());
        users = queryObject.list();
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
    }
    // update-end-Author:jg_renjie  Date:20151220 for?????????TASK #804 ?????????????????????????????????session???????????????????????????????????????password??????????????????????????????????????????
    return null;
}


public List<ComboTree> ComboTree(List all,ComboTreeModel comboTreeModel,List in,boolean recursive){
    List<ComboTree> trees = new ArrayList<ComboTree>();
    for (Object obj : all) {
        trees.add(comboTree(obj, comboTreeModel, in, recursive));
    }
    // update-begin--Author:scott  Date:20160530 for???????????????????????????
    all.clear();
    // update-end--Author:scott  Date:20160530 for???????????????????????????
    return trees;
}


}