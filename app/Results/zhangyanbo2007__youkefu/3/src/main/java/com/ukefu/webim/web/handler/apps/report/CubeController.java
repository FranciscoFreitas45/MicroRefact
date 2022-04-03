package com.ukefu.webim.web.handler.apps.report;
 import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.CubeLevelRepository;
import com.ukefu.webim.service.repository.CubeMeasureRepository;
import com.ukefu.webim.service.repository.CubeMetadataRepository;
import com.ukefu.webim.service.repository.CubeRepository;
import com.ukefu.webim.service.repository.CubeTypeRepository;
import com.ukefu.webim.service.repository.DimensionRepository;
import com.ukefu.webim.service.repository.MetadataRepository;
import com.ukefu.webim.service.repository.PublishedCubeRepository;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.Cube;
import com.ukefu.webim.web.model.CubeLevel;
import com.ukefu.webim.web.model.CubeMeasure;
import com.ukefu.webim.web.model.CubeMetadata;
import com.ukefu.webim.web.model.CubeType;
import com.ukefu.webim.web.model.Dimension;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.PublishedCube;
import com.ukefu.webim.web.model.User;
@Controller
@RequestMapping("/apps/report/cube")
public class CubeController extends Handler{

@Autowired
 private  CubeTypeRepository cubeTypeRes;

@Autowired
 private  CubeRepository cubeRes;

@Autowired
 private  DimensionRepository dimensionRes;

@Autowired
 private  CubeMeasureRepository cubeMeasureRes;

@Autowired
 private  CubeLevelRepository cubeLevelRes;

@Autowired
 private  CubeMetadataRepository cubeMetadataRes;

@Autowired
 private  MetadataRepository metadataRes;

@Autowired
 private  PublishedCubeRepository publishedCubeRes;


@RequestMapping("/imptbsave")
@Menu(type = "report", subtype = "metadata", admin = true)
public ModelAndView imptb(ModelMap map,HttpServletRequest request,String[] tables,String cubeid){
    final User user = super.getUser(request);
    for (String tableid : tables) {
        MetadataTable tb = new MetadataTable();
        tb.setId(tableid);
        int count = cubeMetadataRes.countByTbAndCubeid(tb, cubeid);
        if (count == 0) {
            CubeMetadata cubeMetaData = new CubeMetadata();
            cubeMetaData.setCubeid(cubeid);
            cubeMetaData.setOrgi(super.getOrgi(request));
            cubeMetaData.setTb(tb);
            cubeMetaData.setCreater(user.getId());
            cubeMetaData.setMtype("1");
            cubeMetadataRes.save(cubeMetaData);
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?id=" + cubeid));
}


@RequestMapping({ "/type/add" })
@Menu(type = "report", subtype = "cube")
public ModelAndView addtype(ModelMap map,HttpServletRequest request,String typeid){
    map.addAttribute("cubeTypeList", cubeTypeRes.findByOrgi(super.getOrgi(request)));
    if (!StringUtils.isBlank(typeid)) {
        map.addAttribute("cubeType", cubeTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
    }
    return request(super.createRequestPageTempletResponse("/apps/business/report/cube/type/add"));
}


@RequestMapping("/metadata/update")
@Menu(type = "report", subtype = "metadata", admin = true)
public ModelAndView metadataedit(ModelMap map,HttpServletRequest request,CubeMetadata cubeMetadata){
    if (!StringUtils.isBlank(cubeMetadata.getId())) {
        CubeMetadata temp = cubeMetadataRes.findOne(cubeMetadata.getId());
        temp.setNamealias(cubeMetadata.getNamealias());
        if ("0".equals(cubeMetadata.getMtype())) {
            List<CubeMetadata> list = cubeMetadataRes.findByCubeid(temp.getCubeid());
            if (!list.isEmpty()) {
                // 设置其他数据表为从表
                for (CubeMetadata cm : list) {
                    if (!cm.getId().equals(temp.getId())) {
                        cm.setMtype("1");
                        cubeMetadataRes.save(cm);
                    }
                }
            }
        }
        temp.setMtype(cubeMetadata.getMtype());
        temp.setParameters(cubeMetadata.getParameters());
        cubeMetadataRes.save(temp);
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?id=" + cubeMetadata.getCubeid()));
}


@RequestMapping("/add")
@Menu(type = "report", subtype = "cube")
public ModelAndView cubeadd(ModelMap map,HttpServletRequest request,String typeid){
    if (!StringUtils.isBlank(typeid)) {
        map.addAttribute("cubeType", cubeTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
    }
    map.addAttribute("cubeTypeList", cubeTypeRes.findByOrgi(super.getOrgi(request)));
    map.addAttribute("typeid", typeid);
    return request(super.createRequestPageTempletResponse("/apps/business/report/cube/add"));
}


@RequestMapping("/update")
@Menu(type = "report", subtype = "cube", admin = true)
public ModelAndView cubeupdate(ModelMap map,HttpServletRequest request,Cube cube){
    if (!StringUtils.isBlank(cube.getId())) {
        Cube temp = cubeRes.findOne(cube.getId());
        cube.setOrgi(super.getOrgi(request));
        cube.setCreater(super.getUser(request).getId());
        if (temp != null) {
            cube.setCreatetime(temp.getCreatetime());
        }
        cube.setUpdatetime(new Date());
        cubeRes.save(cube);
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/index.html?typeid=" + cube.getTypeid()));
}


@RequestMapping("/pbcubeindex")
@Menu(type = "report", subtype = "pbcube")
public ModelAndView pbcubeindex(ModelMap map,HttpServletRequest request,String typeid){
    List<CubeType> cubeTypeList = cubeTypeRes.findByOrgi(super.getOrgi(request));
    if (!StringUtils.isBlank(typeid)) {
        map.put("cubeType", cubeTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
        map.put("cubeList", publishedCubeRes.getByOrgiAndTypeid(super.getOrgi(request), typeid, new PageRequest(super.getP(request), super.getPs(request))));
    } else {
        map.put("cubeList", publishedCubeRes.getByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request))));
    }
    map.put("pubCubeTypeList", cubeTypeList);
    map.put("typeid", typeid);
    return request(super.createAppsTempletResponse("/apps/business/report/cube/pbCubeIndex"));
}


@RequestMapping("/type/save")
@Menu(type = "report", subtype = "cube")
public ModelAndView typesave(HttpServletRequest request,CubeType cubeType){
    CubeType ct = cubeTypeRes.findByOrgiAndName(super.getOrgi(request), cubeType.getName());
    if (ct == null) {
        cubeType.setOrgi(super.getOrgi(request));
        cubeType.setCreater(super.getUser(request).getId());
        cubeType.setCreatetime(new Date());
        cubeTypeRes.save(cubeType);
    } else {
        return request(super.createRequestPageTempletResponse("redirect:/apps/business/report/cube/index.html?msg=ct_type_exist"));
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/index.html"));
}


@RequestMapping("/pbcube/unpub")
@Menu(type = "report", subtype = "pbcube")
public ModelAndView unpbcube(ModelMap map,HttpServletRequest request,String id){
    PublishedCube pbCube = publishedCubeRes.findOne(id);
    String typeid = "";
    if (pbCube != null) {
        Cube cube = pbCube.getCube();
        if (cube.getMetadata() != null && cube.getMetadata().size() > 0) {
            for (CubeMetadata metadata : cube.getMetadata()) {
                MetadataTable tb = metadata.getTb();
                if (metadataRes.findById(tb.getId()) == null) {
                    metadataRes.save(tb);
                }
                cubeMetadataRes.save(metadata);
            }
            List<CubeMeasure> measureList = cubeMeasureRes.findByCubeid(cube.getId());
            for (CubeMeasure measure : cube.getMeasure()) {
                boolean found = false;
                for (CubeMeasure temp : measureList) {
                    if (measure.getId().equals(temp.getId())) {
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    cubeMeasureRes.save(measure);
                }
            }
            List<Dimension> dimList = dimensionRes.findByCubeid(cube.getId());
            for (Dimension dim : cube.getDimension()) {
                boolean found = false;
                for (Dimension temp : dimList) {
                    if (dim.getId().equals(temp.getId())) {
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    for (CubeLevel level : dim.getCubeLevel()) {
                        cubeLevelRes.save(level);
                    }
                    dimensionRes.save(dim);
                }
            }
        }
        if (cubeRes.findByOrgiAndId(super.getOrgi(request), cube.getId()).size() == 0) {
            cubeRes.save(cube);
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/pbcubeindex.html?typeid=" + typeid));
}


@RequestMapping({ "/type/edit" })
@Menu(type = "report", subtype = "cube")
public ModelAndView edittype(ModelMap map,HttpServletRequest request,String id){
    map.addAttribute("cubeType", cubeTypeRes.findByIdAndOrgi(id, super.getOrgi(request)));
    map.addAttribute("cubeTypeList", cubeTypeRes.findByOrgi(super.getOrgi(request)));
    return request(super.createRequestPageTempletResponse("/apps/business/report/cube/type/edit"));
}


@RequestMapping("/delete")
@Menu(type = "report", subtype = "cube")
public ModelAndView quickreplydelete(ModelMap map,HttpServletRequest request,String id){
    Cube cube = cubeRes.findOne(id);
    if (cube != null) {
        cubeRes.delete(cube);
        dimensionRes.delete(dimensionRes.findByCubeid(cube.getId()));
        cubeLevelRes.delete(cubeLevelRes.findByCubeid(cube.getId()));
        cubeMeasureRes.delete(cubeMeasureRes.findByCubeid(cube.getId()));
        cubeMetadataRes.delete(cubeMetadataRes.findByCubeid(cube.getId()));
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/index.html?typeid=" + cube.getTypeid()));
}


@RequestMapping("/metadata/del")
@Menu(type = "report", subtype = "metadata", admin = true)
public ModelAndView metadatadel(ModelMap map,HttpServletRequest request,CubeMetadata cubeMetadata){
    String msg = "";
    if (!StringUtils.isBlank(cubeMetadata.getId())) {
        boolean flag = true;
        CubeMetadata temp = cubeMetadataRes.findById(cubeMetadata.getId());
        String tablename = null;
        String tableid = null;
        if (temp.getTb() != null) {
            tablename = temp.getTb().getName();
            tableid = temp.getTb().getId();
        }
        if (!StringUtils.isBlank(tableid)) {
            if (dimensionRes.countByFktable(tableid) > 0) {
                flag = false;
            }
        }
        if (!StringUtils.isBlank(tablename)) {
            if (cubeLevelRes.countByTablename(tablename) > 0) {
                flag = false;
            }
            if (cubeMeasureRes.countByTablename(tablename) > 0) {
                flag = false;
            }
        }
        if (flag) {
            cubeMetadataRes.delete(temp);
        } else {
            msg = "CM_DEL_FAILED";
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?id=" + cubeMetadata.getCubeid() + "&msg=" + msg));
}


@RequestMapping("/selpbcubelist")
@Menu(type = "report", subtype = "pbcube")
public ModelAndView selpbcubelist(ModelMap map,HttpServletRequest request,String typeid,String mid){
    if (!StringUtils.isBlank(typeid)) {
        map.put("cubeType", cubeTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
        map.put("cubeList", publishedCubeRes.getByOrgiAndTypeid(super.getOrgi(request), typeid, new PageRequest(super.getP(request), super.getPs(request))));
    } else {
        map.put("cubeList", publishedCubeRes.getByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request))));
    }
    map.put("typeid", typeid);
    map.put("mid", mid);
    return request(super.createRequestPageTempletResponse("/apps/business/report/design/cube/pbcubelist"));
}


public Cube getCube(String id){
    Cube cube = cubeRes.findOne(id);
    if (cube != null) {
        cube.setMetadata(cubeMetadataRes.findByCubeid(id));
        cube.setMeasure(cubeMeasureRes.findByCubeid(id));
        cube.setDimension(dimensionRes.findByCubeid(id));
    }
    return cube;
}


@RequestMapping({ "/type/delete" })
@Menu(type = "report", subtype = "cube")
public ModelAndView deletetype(ModelMap map,HttpServletRequest request,String id){
    if (!StringUtils.isBlank(id)) {
        CubeType tempCubeType = cubeTypeRes.findByIdAndOrgi(id, super.getOrgi(request));
        cubeTypeRes.delete(tempCubeType);
        List<Cube> cubeList = cubeRes.findByOrgiAndTypeid(super.getOrgi(request), id);
        if (!cubeList.isEmpty()) {
            cubeRes.delete(cubeList);
            for (Cube c : cubeList) {
                Cube cube = getCube(c.getId());
                cubeMetadataRes.delete(cube.getMetadata());
                cubeMeasureRes.delete(cube.getMeasure());
                dimensionRes.delete(cube.getDimension());
            }
        }
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/index.html"));
}


@RequestMapping("/pbcubelist")
@Menu(type = "report", subtype = "pbcube")
public ModelAndView pbcubelist(ModelMap map,HttpServletRequest request,String typeid){
    if (!StringUtils.isBlank(typeid)) {
        map.put("cubeType", cubeTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
        map.put("cubeList", publishedCubeRes.getByOrgiAndTypeid(super.getOrgi(request), typeid, new PageRequest(super.getP(request), super.getPs(request))));
    } else {
        map.put("cubeList", publishedCubeRes.getByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request))));
    }
    map.put("typeid", typeid);
    return request(super.createRequestPageTempletResponse("/apps/business/report/cube/pbcubelist"));
}


@RequestMapping("/index")
@Menu(type = "report", subtype = "cube")
public ModelAndView index(ModelMap map,HttpServletRequest request,String typeid,String msg){
    List<CubeType> cubeTypeList = cubeTypeRes.findByOrgi(super.getOrgi(request));
    if (!StringUtils.isBlank(typeid)) {
        map.put("cubeType", cubeTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
        map.put("cubeList", cubeRes.getByOrgiAndTypeid(super.getOrgi(request), typeid, new PageRequest(super.getP(request), super.getPs(request))));
    } else {
        map.put("cubeList", cubeRes.getByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request))));
    }
    map.put("pubCubeTypeList", cubeTypeList);
    map.put("typeid", typeid);
    map.put("msg", msg);
    return request(super.createAppsTempletResponse("/apps/business/report/cube/index"));
}


@RequestMapping("/list")
@Menu(type = "report", subtype = "cube")
public ModelAndView list(ModelMap map,HttpServletRequest request,String typeid){
    // List<CubeType> cubeTypeList = cubeTypeRes.findByOrgi(super.getOrgi(request)) ;
    if (!StringUtils.isBlank(typeid)) {
        map.put("cubeType", cubeTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
        map.put("cubeList", cubeRes.getByOrgiAndTypeid(super.getOrgi(request), typeid, new PageRequest(super.getP(request), super.getPs(request))));
    } else {
        map.put("cubeList", cubeRes.getByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request))));
    }
    // map.put("pubCubeTypeList", cubeTypeList) ;
    map.put("typeid", typeid);
    return request(super.createRequestPageTempletResponse("/apps/business/report/cube/list"));
}


@RequestMapping("/cubevalid")
@Menu(type = "report", subtype = "cube")
public ModelAndView cubevalid(ModelMap map,HttpServletRequest request,String id){
    boolean hasMasterTable = false;
    Cube cube = cubeRes.findOne(id);
    List<CubeMetadata> cubeMetadataList = cubeMetadataRes.findByCubeid(id);
    if (!cubeMetadataList.isEmpty()) {
        for (CubeMetadata cm : cubeMetadataList) {
            // 至少一个主表
            if ("0".equals(cm.getMtype())) {
                hasMasterTable = true;
                break;
            }
        }
    }
    String msg = "";
    boolean hasLeastMeasure = false;
    if ("cube".equals(cube.getModeltype())) {
        // 立方体必须至少一个指标
        List<CubeMeasure> cubeMeasureList = cubeMeasureRes.findByCubeid(id);
        if (!cubeMeasureList.isEmpty()) {
            hasLeastMeasure = true;
        }
    } else {
        hasLeastMeasure = true;
    }
    if (!hasMasterTable) {
        msg = "CUBE_VALID_FAILED_1";
    } else if (!hasLeastMeasure) {
        msg = "CUBE_VALID_FAILED_2";
    }
    map.put("msg", msg);
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/detail.html?id=" + id + "&msg=" + msg));
}


@RequestMapping("/pbcubedelete")
@Menu(type = "report", subtype = "pbcube")
public ModelAndView pbcubedelete(ModelMap map,HttpServletRequest request,String id){
    PublishedCube pbCube = publishedCubeRes.findOne(id);
    String typeid = "";
    if (pbCube != null) {
        typeid = pbCube.getTypeid();
        publishedCubeRes.delete(pbCube);
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/pbcubeindex.html?typeid=" + typeid));
}


@RequestMapping("/edit")
@Menu(type = "report", subtype = "cube", admin = true)
public ModelAndView cubeedit(ModelMap map,HttpServletRequest request,String id){
    Cube cube = cubeRes.findOne(id);
    map.put("cube", cube);
    if (cube != null) {
        map.put("cubeType", cubeTypeRes.findByIdAndOrgi(cube.getTypeid(), super.getOrgi(request)));
    }
    map.addAttribute("cubeTypeList", cubeTypeRes.findByOrgi(super.getOrgi(request)));
    return request(super.createRequestPageTempletResponse("/apps/business/report/cube/edit"));
}


@RequestMapping("/cubepublish")
@Menu(type = "report", subtype = "cube")
public ModelAndView cubepublish(ModelMap map,HttpServletRequest request,String cubeid,String isRecover){
    map.put("cubeid", cubeid);
    return request(super.createRequestPageTempletResponse("/apps/business/report/cube/cubepublish"));
}


@RequestMapping("/selpbcubeindex")
@Menu(type = "report", subtype = "pbcube")
public ModelAndView selpbcubeindex(ModelMap map,HttpServletRequest request,String typeid,String mid){
    List<CubeType> cubeTypeList = cubeTypeRes.findByOrgi(super.getOrgi(request));
    if (!StringUtils.isBlank(typeid)) {
        map.put("cubeType", cubeTypeRes.findByIdAndOrgi(typeid, super.getOrgi(request)));
        map.put("cubeList", publishedCubeRes.getByOrgiAndTypeid(super.getOrgi(request), typeid, new PageRequest(super.getP(request), super.getPs(request))));
    } else {
        map.put("cubeList", publishedCubeRes.getByOrgi(super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request))));
    }
    map.put("pubCubeTypeList", cubeTypeList);
    map.put("typeid", typeid);
    map.put("mid", mid);
    return request(super.createRequestPageTempletResponse("/apps/business/report/design/cube/pbCubeIndex"));
}


@RequestMapping("/type/update")
@Menu(type = "report", subtype = "cube")
public ModelAndView typeupdate(HttpServletRequest request,CubeType cubeType){
    CubeType tempCubeType = cubeTypeRes.findByIdAndOrgi(cubeType.getId(), super.getOrgi(request));
    if (tempCubeType != null) {
        // 判断名称是否重复
        CubeType ct = cubeTypeRes.findByOrgiAndName(super.getOrgi(request), cubeType.getName());
        if (ct != null && !ct.getId().equals(cubeType.getId())) {
            return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/index.html?msg=ct_type_exist&typeid=" + cubeType.getId()));
        }
        tempCubeType.setName(cubeType.getName());
        tempCubeType.setDescription(cubeType.getDescription());
        tempCubeType.setInx(cubeType.getInx());
        tempCubeType.setUpdatetime(new Date());
        tempCubeType.setParentid(cubeType.getParentid());
        cubeTypeRes.save(tempCubeType);
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/index.html?typeid=" + cubeType.getId()));
}


@RequestMapping("/detail")
@Menu(type = "report", subtype = "cube")
public ModelAndView detail(ModelMap map,HttpServletRequest request,String id,String dimensionId,String msg){
    List<Dimension> dimensionList = dimensionRes.findByCubeid(id);
    map.put("dimensionList", dimensionList);
    if (!dimensionList.isEmpty()) {
        if (StringUtils.isBlank(dimensionId)) {
            dimensionId = dimensionList.get(0).getId();
        }
        map.put("cubeLevelList", cubeLevelRes.findByOrgiAndDimid(super.getOrgi(request), dimensionId));
    }
    if (!StringUtils.isBlank(dimensionId) && "cubemeasure".equals(dimensionId)) {
        List<CubeMeasure> cubeMeasureList = cubeMeasureRes.findByCubeid(id);
        map.put("cubeMeasureList", cubeMeasureList);
    }
    map.put("cubeMetadataList", cubeMetadataRes.findByCubeid(id));
    map.put("cubeid", id);
    map.put("dimensionId", dimensionId);
    map.put("msg", msg);
    return request(super.createAppsTempletResponse("/apps/business/report/cube/detail"));
}


@RequestMapping("/cubepublished")
@Menu(type = "report", subtype = "cube")
public ModelAndView cubepublished(ModelMap map,HttpServletRequest request,String cubeid,String isRecover){
    this.cubevalid(map, request, cubeid);
    if (!StringUtils.isBlank((String) map.get("msg"))) {
        map.put("cubeid", cubeid);
        return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/index.html?msg=" + (String) map.get("msg")));
    }
    User user = super.getUser(request);
    Cube cube = this.getCube(cubeid);
    PublishedCube publishCube = new PublishedCube();
    UKTools.copyProperties(cube, publishCube, "");
    publishCube.setId(null);
    Base64 base64 = new Base64();
    publishCube.setCubecontent(base64.encodeToString(UKTools.toBytes(cube)));
    publishCube.setDataid(cubeid);
    publishCube.setUserid(user.getId());
    publishCube.setUsername(user.getUsername());
    publishCube.setCreatetime(new Date());
    List<PublishedCube> pbCubeList = publishedCubeRes.findByOrgiAndDataidOrderByDataversionDesc(super.getOrgi(request), cubeid);
    if (!pbCubeList.isEmpty()) {
        int maxVersion = pbCubeList.get(0).getDataversion();
        if ("yes".equals(isRecover)) {
            publishCube.setId(pbCubeList.get(0).getId());
            publishCube.setDataversion(pbCubeList.get(0).getDataversion());
            publishedCubeRes.save(publishCube);
        } else if ("no".equals(isRecover)) {
            publishCube.setDataversion(maxVersion + 1);
            publishedCubeRes.save(publishCube);
        } else {
            publishedCubeRes.delete(pbCubeList);
            publishCube.setDataversion(1);
            publishedCubeRes.save(publishCube);
        }
    } else {
        publishCube.setDataversion(1);
        publishedCubeRes.save(publishCube);
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/index.html?typeid=" + cube.getTypeid()));
}


@RequestMapping("/save")
@Menu(type = "report", subtype = "cube")
public ModelAndView cubesave(ModelMap map,HttpServletRequest request,Cube cube){
    if (!StringUtils.isBlank(cube.getName())) {
        cube.setOrgi(super.getOrgi(request));
        cube.setCreater(super.getUser(request).getId());
        cubeRes.save(cube);
    }
    return request(super.createRequestPageTempletResponse("redirect:/apps/report/cube/index.html?typeid=" + cube.getTypeid()));
}


}