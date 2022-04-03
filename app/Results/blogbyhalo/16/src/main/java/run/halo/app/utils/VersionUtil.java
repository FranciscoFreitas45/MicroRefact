package run.halo.app.utils;
 import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VersionUtil {

private VersionUtil() {
}
public boolean compareVersion(String current,String require){
    Version leftVersion = Version.resolve(current).orElse(Version.emptyVersion());
    Version rightVersion = Version.resolve(require).orElse(Version.emptyVersion());
    return leftVersion.compareTo(rightVersion) >= 0;
}


}