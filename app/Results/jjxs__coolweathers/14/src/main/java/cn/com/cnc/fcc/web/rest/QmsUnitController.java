package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.domain.QmsMateriel;
import cn.com.cnc.fcc.domain.QmsUnit;
import cn.com.cnc.fcc.repository.QmsMaterielRepository;
import cn.com.cnc.fcc.repository.QmsUnitRepository;
import cn.com.cnc.fcc.service.QmsUnitService;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
@RestController
@RequestMapping("/api")
public class QmsUnitController {

 private  Logger log;

 private  String ENTITY_NAME;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

@Autowired
 private  QmsUnitService qmsUnitService;

@Autowired
 private  QmsUnitRepository qmsUnitRepository;

@Autowired
 private  QmsMaterielRepository qmsMaterielRepository;


@GetMapping("/qms-units/sameCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer sameCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("samecheck");
    // 如果这个值在数据库里找到了，则返回1
    List<QmsUnit> list = qmsUnitRepository.findByUnitCd(cd);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


@RequestMapping("/qms-units/upload")
public JSONObject upload(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    returnData = qmsUnitService.uploadData(files);
    return returnData;
}


@RequestMapping("/qms-units/downloadExcel")
public void downloadExcel(HttpServletResponse response,HttpServletRequest request){
    try {
        // 获取要下载的模板名称
        String fileName = "unit.xls";
        // 设置要下载的文件的名称
        response.setHeader("Content-disposition", "attachment;fileName=" + fileName);
        // 通知客服文件的MIME类型
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        // 获取文件的路径
        String filePath = getClass().getResource("/excel/" + fileName).getPath();
        FileInputStream input = new FileInputStream(filePath);
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[2048];
        int len;
        while ((len = input.read(b)) != -1) {
            out.write(b, 0, len);
        }
        // 修正 Excel在“xxx.xlsx”中发现不可读取的内容。是否恢复此工作薄的内容？如果信任此工作簿的来源，请点击"是"
        response.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
        input.close();
    // return Response.ok("应用导入模板下载完成");
    } catch (Exception ex) {
        log.error("getApplicationTemplate :", ex);
    // return Response.ok("应用导入模板下载失败！");
    }
}


@GetMapping("/qms-units/index")
@Timed
public ResponseEntity<List<QmsUnit>> getAllQmsUnit(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllQmsUnit");
    // 模糊查询供应商编码
    String bianMa = request.getParameter("bianMa");
    // 模糊查询供应商名称
    String gongName = request.getParameter("gongName");
    // 取得一览数据
    Page<QmsUnit> page = qmsUnitService.qmsUnitFindAll(bianMa, gongName, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-units-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-units/deleteCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer deleteCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("deletecheck");
    Integer it = Integer.valueOf(cd);
    // 如果这个值在数据库里找到了，则返回1
    List<QmsMateriel> list1 = qmsMaterielRepository.findByUseUnitId(it);
    List<QmsMateriel> list2 = qmsMaterielRepository.findByPackgeUnitId(it);
    if (list1.size() != 0 || list2.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


}