package run.halo.app.utils;
 import run.halo.app.model.support.HaloConst.FILE_SEPARATOR;
import cn.hutool.core.util.URLUtil;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import run.halo.app.model.support.HaloConst;
@Slf4j
public class HaloUtils {

 public  String URL_SEPARATOR;

 private  String RE_HTML_MARK;


@NonNull
public String ensurePrefix(String string,String prefix){
    Assert.hasText(string, "String must not be blank");
    Assert.hasText(prefix, "Prefix must not be blank");
    return prefix + StringUtils.removeStart(string, prefix);
}


public String getMachineIP(){
    InetAddress machineAddress;
    try {
        machineAddress = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
        machineAddress = InetAddress.getLoopbackAddress();
    }
    return machineAddress.getHostAddress();
}


@NonNull
public String ensureBoth(String string,String prefix,String suffix){
    return ensureSuffix(ensurePrefix(string, prefix), suffix);
}


@NonNull
public String pluralize(long times,String label,String pluralLabel){
    Assert.hasText(label, "Label must not be blank");
    Assert.hasText(pluralLabel, "Plural label must not be blank");
    if (times <= 0) {
        return "no " + pluralLabel;
    }
    if (times == 1) {
        return times + " " + label;
    }
    return times + " " + pluralLabel;
}


@NonNull
public String initializeUrlIfBlank(String url){
    if (!StringUtils.isBlank(url)) {
        return url;
    }
    return String.valueOf(System.currentTimeMillis());
}


@NonNull
public String ensureSuffix(String string,String suffix){
    Assert.hasText(string, "String must not be blank");
    Assert.hasText(suffix, "Suffix must not be blank");
    return StringUtils.removeEnd(string, suffix) + suffix;
}


public String compositeHttpUrl(String partUrls){
    Assert.notEmpty(partUrls, "Partial url must not be blank");
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < partUrls.length; i++) {
        String partUrl = partUrls[i];
        if (StringUtils.isBlank(partUrl)) {
            continue;
        }
        partUrl = StringUtils.removeStart(partUrl, URL_SEPARATOR);
        partUrl = StringUtils.removeEnd(partUrl, URL_SEPARATOR);
        if (i != 0) {
            builder.append(URL_SEPARATOR);
        }
        builder.append(partUrl);
    }
    return builder.toString();
}


public String changeFileSeparatorToUrlSeparator(String pathname){
    Assert.hasText(pathname, "Path name must not be blank");
    return pathname.replace(FILE_SEPARATOR, "/");
}


@NonNull
public String timeFormat(long totalSeconds){
    if (totalSeconds <= 0) {
        return "0 second";
    }
    StringBuilder timeBuilder = new StringBuilder();
    long hours = totalSeconds / 3600;
    long minutes = totalSeconds % 3600 / 60;
    long seconds = totalSeconds % 3600 % 60;
    if (hours > 0) {
        if (StringUtils.isNotBlank(timeBuilder)) {
            timeBuilder.append(", ");
        }
        timeBuilder.append(pluralize(hours, "hour", "hours"));
    }
    if (minutes > 0) {
        if (StringUtils.isNotBlank(timeBuilder)) {
            timeBuilder.append(", ");
        }
        timeBuilder.append(pluralize(minutes, "minute", "minutes"));
    }
    if (seconds > 0) {
        if (StringUtils.isNotBlank(timeBuilder)) {
            timeBuilder.append(", ");
        }
        timeBuilder.append(pluralize(seconds, "second", "seconds"));
    }
    return timeBuilder.toString();
}


public String cleanHtmlTag(String content){
    if (StringUtils.isEmpty(content)) {
        return StringUtils.EMPTY;
    }
    return content.replaceAll(RE_HTML_MARK, StringUtils.EMPTY);
}


public String desensitize(String plainText,int leftSize,int rightSize){
    Assert.hasText(plainText, "Plain text must not be blank");
    if (leftSize < 0) {
        leftSize = 0;
    }
    if (leftSize > plainText.length()) {
        leftSize = plainText.length();
    }
    if (rightSize < 0) {
        rightSize = 0;
    }
    if (rightSize > plainText.length()) {
        rightSize = plainText.length();
    }
    if (plainText.length() < leftSize + rightSize) {
        rightSize = plainText.length() - leftSize;
    }
    int remainSize = plainText.length() - rightSize - leftSize;
    String left = StringUtils.left(plainText, leftSize);
    String right = StringUtils.right(plainText, rightSize);
    return StringUtils.rightPad(left, remainSize + leftSize, '*') + right;
}


@NonNull
public String randomUUIDWithoutDash(){
    return StringUtils.remove(UUID.randomUUID().toString(), '-');
}


@NonNull
public String normalizeUrl(String originalUrl){
    Assert.hasText(originalUrl, "Original Url must not be blank");
    if (StringUtils.startsWithAny(originalUrl, URL_SEPARATOR, HaloConst.PROTOCOL_HTTPS, HaloConst.PROTOCOL_HTTP) && !StringUtils.startsWith(originalUrl, "//")) {
        return originalUrl;
    }
    return URLUtil.normalize(originalUrl);
}


}