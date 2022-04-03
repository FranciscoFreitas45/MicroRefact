package run.halo.app.config.properties;
 import run.halo.app.model.support.HaloConst.FILE_SEPARATOR;
import run.halo.app.model.support.HaloConst.TEMP_DIR;
import run.halo.app.model.support.HaloConst.USER_HOME;
import run.halo.app.utils.HaloUtils.ensureSuffix;
import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import run.halo.app.model.enums.Mode;
@Data
@ConfigurationProperties("halo")
public class HaloProperties {

 private  boolean authEnabled;

 private  Mode mode;

 private  String adminPath;

 private  String workDir;

 private  String backupDir;

 private  String backupMarkdownDir;

 private  String dataExportDir;

 private  String uploadUrlPrefix;

 private  Duration downloadTimeout;

 private  String cache;


}