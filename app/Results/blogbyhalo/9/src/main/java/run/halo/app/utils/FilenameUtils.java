package run.halo.app.utils;
 import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
public class FilenameUtils {

private FilenameUtils() {
}
@NonNull
public String getExtension(String filename){
    Assert.hasText(filename, "Filename must not be blank");
    // Find the last slash
    int separatorLastIndex = StringUtils.lastIndexOf(filename, File.separatorChar);
    if (separatorLastIndex == filename.length() - 1) {
        return StringUtils.EMPTY;
    }
    if (separatorLastIndex >= 0 && separatorLastIndex < filename.length() - 1) {
        filename = filename.substring(separatorLastIndex + 1);
    }
    // Find last dot
    int dotLastIndex = StringUtils.lastIndexOf(filename, '.');
    if (dotLastIndex < 0) {
        return StringUtils.EMPTY;
    }
    String[] split = filename.split("\\.");
    List<String> extList = Arrays.asList("gz", "bz2");
    if (extList.contains(split[split.length - 1]) && split.length >= 3) {
        return filename.substring(filename.substring(0, dotLastIndex).lastIndexOf('.') + 1);
    }
    return filename.substring(dotLastIndex + 1);
}


@NonNull
public String getBasename(String filename){
    Assert.hasText(filename, "Filename must not be blank");
    // Find the last slash
    int separatorLastIndex = StringUtils.lastIndexOf(filename, File.separatorChar);
    if (separatorLastIndex == filename.length() - 1) {
        return StringUtils.EMPTY;
    }
    if (separatorLastIndex >= 0 && separatorLastIndex < filename.length() - 1) {
        filename = filename.substring(separatorLastIndex + 1);
    }
    // Find last dot
    int dotLastIndex = StringUtils.lastIndexOf(filename, '.');
    String[] split = filename.split("\\.");
    List<String> extList = Arrays.asList("gz", "bz2");
    if (extList.contains(split[split.length - 1]) && split.length >= 3) {
        return filename.substring(0, filename.substring(0, dotLastIndex).lastIndexOf('.'));
    }
    if (dotLastIndex < 0) {
        return filename;
    }
    return filename.substring(0, dotLastIndex);
}


}