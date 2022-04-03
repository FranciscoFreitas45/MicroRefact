package cn.gson.oasys.controller.file;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.gson.oasys.services.file.FileTransactionalHandlerService;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.alibaba.fastjson.JSONArray;
import cn.gson.oasys.model.dao.filedao.FileListdao;
import cn.gson.oasys.model.dao.filedao.FilePathdao;
import cn.gson.oasys.model.dao.user.UserDao;
import cn.gson.oasys.model.entity.file.FileList;
import cn.gson.oasys.model.entity.file.FilePath;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.services.file.FileServices;
import cn.gson.oasys.Interface.UserDao;
@Controller
@RequestMapping("/")
public class FileController {

@Autowired
 private  FileServices fs;

@Autowired
 private  FilePathdao fpdao;

@Autowired
 private  FileListdao fldao;

@Autowired
 private  UserDao udao;

@Autowired
 private  FileTransactionalHandlerService fileTransactionalHandlerService;


@RequestMapping("deletefile")
public String deletefile(Long userid,Long pathid,List<Long> checkpathids,List<Long> checkfileids,Model model){
    System.out.println(checkfileids);
    System.out.println(checkpathids);
    if (!checkfileids.isEmpty()) {
        // 删除文件
        // fs.deleteFile(checkfileids);
        // 文件放入回收战
        fileTransactionalHandlerService.trashfile(checkfileids, 1L, userid);
    }
    if (!checkpathids.isEmpty()) {
        // 删除文件夹
        // fs.deletePath(checkpathids);
        fs.trashpath(checkpathids, 1L, true);
    // fs.trashPath(checkpathids);
    }
    model.addAttribute("pathid", pathid);
    return "forward:/filetest";
}


@RequestMapping("mcto")
public String mcto(Long userid,boolean morc,Long mctoid,Long pathid,List<Long> mcfileids,List<Long> mcpathids,Model model){
    System.out.println("--------------------");
    System.out.println("mcfileids" + mcfileids);
    System.out.println("mcpathids" + mcpathids);
    if (morc) {
        System.out.println("这里是移动！~~");
        fs.moveAndcopy(mcfileids, mcpathids, mctoid, true, userid);
    } else {
        System.out.println("这里是复制！~~");
        fs.moveAndcopy(mcfileids, mcpathids, mctoid, false, userid);
    }
    model.addAttribute("pathid", pathid);
    return "forward:/filetest";
}


@RequestMapping("rename")
public String rename(String name,Long renamefp,Long pathid,boolean isfile,Model model){
    // 这里调用重命名方法
    fs.rename(name, renamefp, pathid, isfile);
    model.addAttribute("pathid", pathid);
    return "forward:/filetest";
}


@RequestMapping("filemanage")
public String usermanage(Long userid,Model model){
    System.out.println(userid);
    User user = udao.findOne(userid);
    FilePath filepath = fpdao.findByPathName(user.getUserName());
    System.out.println(filepath);
    if (filepath == null) {
        FilePath filepath1 = new FilePath();
        filepath1.setParentId(1L);
        filepath1.setPathName(user.getUserName());
        filepath1.setPathUserId(user.getUserId());
        filepath = fpdao.save(filepath1);
    }
    model.addAttribute("nowpath", filepath);
    model.addAttribute("paths", fs.findpathByparent(filepath.getId()));
    model.addAttribute("files", fs.findfileBypath(filepath));
    model.addAttribute("userrootpath", filepath);
    model.addAttribute("mcpaths", fs.findpathByparent(filepath.getId()));
    return "file/filemanage";
}


@RequestMapping("fileupload")
public String uploadfile(MultipartFile file,Long pathid,HttpSession session,Model model){
    Long userid = Long.parseLong(session.getAttribute("userId") + "");
    User user = udao.findOne(userid);
    FilePath nowpath = fpdao.findOne(pathid);
    // true 表示从文件使用上传
    FileList uploadfile = (FileList) fs.savefile(file, user, nowpath, true);
    System.out.println(uploadfile);
    model.addAttribute("pathid", pathid);
    return "forward:/filetest";
}


@RequestMapping("downfile")
public void downFile(HttpServletResponse response,Long fileid){
    try {
        FileList filelist = fldao.findOne(fileid);
        File file = fs.getFile(filelist.getFilePath());
        response.setContentLength(filelist.getSize().intValue());
        response.setContentType(filelist.getContentType());
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(filelist.getFileName().getBytes("UTF-8"), "ISO8859-1"));
        writefile(response, file);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@RequestMapping("createpath")
public String createpath(Long userid,Long pathid,String pathname,Model model){
    System.out.println(pathid + "aaaaaa" + pathname);
    FilePath filepath = fpdao.findOne(pathid);
    String newname = fs.onlyname(pathname, filepath, null, 1, false);
    FilePath newfilepath = new FilePath(pathid, newname);
    newfilepath.setPathUserId(userid);
    System.out.println(newname);
    System.out.println(newfilepath);
    fpdao.save(newfilepath);
    model.addAttribute("pathid", pathid);
    return "forward:/filetest";
}


@RequestMapping("doshare")
public String doshare(Long pathid,List<Long> checkfileids,Model model){
    if (!checkfileids.isEmpty()) {
        System.out.println(checkfileids);
        fs.doshare(checkfileids);
    }
    model.addAttribute("pathid", pathid);
    model.addAttribute("message", "分享成功");
    return "forward:/filetest";
}


@RequestMapping("filetest")
public String text(Long userid,Long pathid,Model model){
    User user = udao.findOne(userid);
    FilePath userrootpath = fpdao.findByPathName(user.getUserName());
    // 查询当前目录
    FilePath filepath = fpdao.findOne(pathid);
    // 查询当前目录的所有父级目录
    List<FilePath> allparentpaths = new ArrayList<>();
    fs.findAllParent(filepath, allparentpaths);
    Collections.reverse(allparentpaths);
    model.addAttribute("allparentpaths", allparentpaths);
    model.addAttribute("nowpath", filepath);
    model.addAttribute("paths", fs.findpathByparent(filepath.getId()));
    model.addAttribute("files", fs.findfileBypath(filepath));
    // 复制移动显示 目录
    model.addAttribute("userrootpath", userrootpath);
    model.addAttribute("mcpaths", fs.findpathByparent(userrootpath.getId()));
    return "file/filemanage";
}


public void writefile(HttpServletResponse response,File file){
    ServletOutputStream sos = null;
    FileInputStream aa = null;
    try {
        aa = new FileInputStream(file);
        sos = response.getOutputStream();
        // 读取文件问字节码
        byte[] data = new byte[(int) file.length()];
        IOUtils.readFully(aa, data);
        // 将文件流输出到浏览器
        IOUtils.write(data, sos);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        try {
            sos.close();
            aa.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


@RequestMapping("imgshow")
public void imgshow(HttpServletResponse response,Long fileid){
    FileList filelist = fldao.findOne(fileid);
    File file = fs.getFile(filelist.getFilePath());
    writefile(response, file);
}


}