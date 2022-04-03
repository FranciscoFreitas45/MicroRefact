package cn.gson.oasys.controller.file;
 import java.util.List;
import cn.gson.oasys.services.file.FileTransactionalHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
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
public class FileAjaxController {

@Autowired
 private FileServices fs;

@Autowired
 private FileListdao fldao;

@Autowired
 private FilePathdao fpdao;

@Autowired
 private UserDao udao;

@Autowired
 private  FileTransactionalHandlerService fileTransactionalHandlerService;


@RequestMapping("filereturnback")
public String filereturnback(Long userid,String type,List<Long> checkpathids,List<Long> checkfileids,Model model){
    if (checkfileids != null) {
        fileTransactionalHandlerService.filereturnback(checkfileids, userid);
    }
    if (checkpathids != null) {
        fs.pathreturnback(checkpathids, userid);
    }
    model.addAttribute("type", type);
    return "forward:/filetypeload";
}


@RequestMapping("fileloadshare")
public String fileloadshare(String type,List<Long> checkfileids,Model model){
    if (checkfileids != null) {
        fs.doshare(checkfileids);
    }
    model.addAttribute("message", "分享成功");
    model.addAttribute("type", type);
    return "forward:/filetypeload";
}


@RequestMapping("fileloadtrashfile")
public String fileloadtrashfile(Long userid,String type,List<Long> checkpathids,List<Long> checkfileids,Model model){
    System.out.println(type + checkpathids + checkfileids);
    if (checkfileids != null) {
        // 文件放入回收站
        fileTransactionalHandlerService.trashfile(checkfileids, 1L, userid);
    }
    if (checkpathids != null) {
        // 删除文件夹
        fs.trashpath(checkpathids, 1L, true);
    // fs.trashPath(checkpathids);
    }
    model.addAttribute("type", type);
    return "forward:/filetypeload";
}


@RequestMapping("filetypeload")
public String filetypeload(Long userid,String type,Model model){
    User user = udao.findOne(userid);
    String contenttype;
    List<FileList> fileLists = null;
    List<FilePath> filePaths = null;
    switch(type) {
        case "document":
            fileLists = fldao.finddocument(user);
            System.out.println(fileLists);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "picture":
            contenttype = "image/%";
            fileLists = fldao.findByUserAndContentTypeLikeAndFileIstrash(user, contenttype, 0L);
            System.out.println(fileLists);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "music":
            contenttype = "audio/%";
            fileLists = fldao.findByUserAndContentTypeLikeAndFileIstrash(user, contenttype, 0L);
            System.out.println(fileLists);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "video":
            contenttype = "video/%";
            fileLists = fldao.findByUserAndContentTypeLikeAndFileIstrash(user, contenttype, 0L);
            System.out.println(fileLists);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "yasuo":
            contenttype = "application/x%";
            fileLists = fldao.findByUserAndContentTypeLikeAndFileIstrash(user, contenttype, 0L);
            System.out.println(fileLists);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "trash":
            filePaths = fpdao.findByPathUserIdAndPathIstrash(userid, 1L);
            fileLists = fldao.findByUserAndFileIstrash(user, 1L);
            model.addAttribute("paths", filePaths);
            model.addAttribute("files", fileLists);
            model.addAttribute("istrash", 1);
            model.addAttribute("isload", 1);
            break;
        case "share":
            fileLists = fldao.findByFileIsshareAndFileIstrash(1L, 0L);
            model.addAttribute("files", fileLists);
            model.addAttribute("isshare", 1);
            model.addAttribute("isload", 1);
            model.addAttribute("userid", userid);
            break;
        default:
            break;
    }
    model.addAttribute("type", type);
    return "file/filetypeload";
}


@RequestMapping("findfileandpath")
public String findfileandpath(Long userid,String findfileandpath,String type,Model model){
    System.out.println("查找！~~~~~~");
    String findlike = "%" + findfileandpath + "%";
    User user = udao.findOne(userid);
    FilePath fpath = fpdao.findByParentIdAndPathUserId(1L, userid);
    String contenttype;
    List<FileList> fileLists = null;
    List<FilePath> filePaths = null;
    System.out.println(type);
    switch(type) {
        case "document":
            fileLists = fldao.finddocumentlike(user, findlike);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "picture":
            contenttype = "image/%";
            fileLists = fldao.findByUserAndFileIstrashAndContentTypeLikeAndFileNameLike(user, 0L, contenttype, findlike);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "music":
            contenttype = "audio/%";
            fileLists = fldao.findByUserAndFileIstrashAndContentTypeLikeAndFileNameLike(user, 0L, contenttype, findlike);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "video":
            contenttype = "video/%";
            fileLists = fldao.findByUserAndFileIstrashAndContentTypeLikeAndFileNameLike(user, 0L, contenttype, findlike);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "yasuo":
            contenttype = "application/x%";
            fileLists = fldao.findByUserAndFileIstrashAndContentTypeLikeAndFileNameLike(user, 0L, contenttype, findlike);
            model.addAttribute("files", fileLists);
            model.addAttribute("isload", 1);
            break;
        case "trash":
            filePaths = fpdao.findByPathUserIdAndPathIstrashAndPathNameLikeAndParentIdNot(userid, 1L, findlike, 1L);
            fileLists = fldao.findByUserAndFileIstrashAndContentTypeLikeAndFileNameLike(user, 1L, "%%", findlike);
            model.addAttribute("istrash", 1);
            model.addAttribute("isload", 1);
            model.addAttribute("paths", filePaths);
            model.addAttribute("files", fileLists);
            break;
        case "share":
            fileLists = fldao.findByFileIsshareAndFileNameLike(1L, findlike);
            model.addAttribute("files", fileLists);
            model.addAttribute("isshare", 1);
            model.addAttribute("isload", 1);
            break;
        default:
            System.out.println("什么都不是");
            filePaths = fpdao.findByPathUserIdAndPathIstrashAndPathNameLikeAndParentIdNot(userid, 0L, findlike, 1L);
            fileLists = fldao.findByUserAndFileIstrashAndFileNameLike(user, 0L, findlike);
            model.addAttribute("files", fileLists);
            model.addAttribute("paths", filePaths);
            model.addAttribute("isload", 1);
            break;
    }
    model.addAttribute("type", type);
    return "file/filetypeload";
}


@RequestMapping("mcloadpath")
public String mcloadpath(Long mctoid,List<Long> mcpathids,Model model){
    System.out.println("进来了是吧！~~");
    System.out.println(mctoid);
    System.out.println(mcpathids);
    List<FilePath> showsonpaths = fs.mcpathload(mctoid, mcpathids);
    model.addAttribute("mcpaths", showsonpaths);
    return "file/mcpathload";
}


@RequestMapping("fileloaddeletefile")
public String fileloaddeletefile(String type,List<Long> checkpathids,List<Long> checkfileids,Model model){
    System.out.println(type + checkpathids + checkfileids);
    if (checkfileids != null) {
        // 删除文件
        fs.deleteFile(checkfileids);
    }
    if (checkpathids != null) {
        // 删除文件夹
        fs.deletePath(checkpathids);
    }
    model.addAttribute("type", type);
    return "forward:/filetypeload";
}


@RequestMapping("fileloadrename")
public String fileloadrename(String type,Long renamefp,String creatpathinput,boolean isfile,Long pathid,Model model){
    System.out.println(type + renamefp + creatpathinput + isfile);
    fs.rename(creatpathinput, renamefp, pathid, isfile);
    model.addAttribute("type", type);
    return "forward:/filetypeload";
}


}