package cn.gson.oasys.services.file;
 import cn.gson.oasys.model.dao.filedao.FileListdao;
import cn.gson.oasys.model.dao.filedao.FilePathdao;
import cn.gson.oasys.model.entity.file.FileList;
import cn.gson.oasys.model.entity.file.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
@Service
public class FileTransactionalHandlerService {

@Autowired
 private  FileListdao fldao;

@Autowired
 private  FilePathdao fpdao;

@Autowired
 private  FileServices fileServices;


@Transactional
public void filereturnback(List<Long> checkfileids,Long userid){
    FilePath fpath = fpdao.findByParentIdAndPathUserId(1L, userid);
    for (Long checkfileid : checkfileids) {
        FileList fileList = fldao.findOne(checkfileid);
        if (userid != null) {
            String name = fileServices.onlyname(fileList.getFileName(), fpath, fileList.getFileShuffix(), 1, true);
            fileList.setFpath(fpath);
            fileList.setFileName(name);
        }
        fileList.setFileIstrash(0L);
        fldao.save(fileList);
    }
}


@Transactional
public void trashfile(List<Long> fileids,Long setistrashhowmany,Long userid){
    for (Long fileid : fileids) {
        FileList fileList = fldao.findOne(fileid);
        fileList.setFileIstrash(setistrashhowmany);
        if (userid != null) {
            fileList.setFpath(null);
        }
        fldao.save(fileList);
    }
}


}