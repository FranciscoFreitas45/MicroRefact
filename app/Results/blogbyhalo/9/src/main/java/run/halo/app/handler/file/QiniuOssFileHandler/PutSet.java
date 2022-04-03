package run.halo.app.handler.file.QiniuOssFileHandler;
 import run.halo.app.model.support.HaloConst.FILE_SEPARATOR;
import run.halo.app.model.support.HaloConst.TEMP_DIR;
import run.halo.app.model.support.HaloConst.URL_SEPARATOR;
import run.halo.app.utils.HaloUtils.ensureSuffix;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.exception.FileOperationException;
import run.halo.app.model.enums.AttachmentType;
import run.halo.app.model.properties.QiniuOssProperties;
import run.halo.app.model.support.UploadResult;
import run.halo.app.service.OptionService;
import run.halo.app.utils.FilenameUtils;
import run.halo.app.utils.ImageUtils;
import run.halo.app.utils.JsonUtils;
@Data
@NoArgsConstructor
public class PutSet {

 public  String hash;

 public  String key;

 private  Long size;

 private  Integer width;

 private  Integer height;


}