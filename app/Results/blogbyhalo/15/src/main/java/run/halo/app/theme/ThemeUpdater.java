package run.halo.app.theme;
 import run.halo.app.utils.FileUtils.copyFolder;
import run.halo.app.utils.FileUtils.deleteFolderQuietly;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import run.halo.app.handler.theme.config.support.ThemeProperty;
import run.halo.app.utils.FileUtils;
public interface ThemeUpdater {

 private Logger log;


public Path backup(ThemeProperty themeProperty){
    final var themePath = Paths.get(themeProperty.getThemePath());
    Path tempDirectory = null;
    try {
        tempDirectory = FileUtils.createTempDirectory();
        copyFolder(themePath, tempDirectory);
        log.info("Backup theme: {} to {} successfully!", themeProperty.getId(), tempDirectory);
        return tempDirectory;
    } catch (IOException e) {
        // clear temp directory
        deleteFolderQuietly(tempDirectory);
        throw e;
    }
}
;

public void restore(Path backupPath,ThemeProperty oldThemeProperty){
    final var targetPath = Paths.get(oldThemeProperty.getThemePath());
    log.info("Restoring backup path: {} to target path: {}", backupPath, targetPath);
    // copy backup to target path
    FileUtils.copyFolder(backupPath, targetPath);
    log.debug("Copied backup path: {} to target path: {} successfully!", backupPath, targetPath);
    // delete backup
    FileUtils.deleteFolderQuietly(backupPath);
    log.debug("Deleted backup path: {} successfully!", backupPath);
    log.info("Restored backup path: {} to target path: {} successfully!", backupPath, targetPath);
}
;

public ThemeProperty update(String themeId)
;

}