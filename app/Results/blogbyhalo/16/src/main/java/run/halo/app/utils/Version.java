package run.halo.app.utils;
 import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.model.support.HaloConst;
@Getter
@ToString
@EqualsAndHashCode
@Slf4j
public class Version implements Comparable<Version>{

 private  String REGEX;

 private  Pattern PATTERN;

 private  Version EMPTY_VERSION;

 private  Version MAXIMUM_VERSION;

 private  long major;

 private  long minor;

 private  long patch;

 private  PreRelease preRelease;

 private  long preReleaseMajor;

public Version() {
    this(0, 0, 0);
}public Version(long major, long minor, long patch) {
    this(major, minor, patch, null, null);
}public Version(long major, long minor, long patch, @Nullable PreRelease preRelease, @Nullable Long preReleaseMajor) {
    if (major < 0) {
        major = 0L;
    }
    if (minor < 0L) {
        minor = 0;
    }
    if (patch < 0) {
        minor = 0L;
    }
    this.major = major;
    this.minor = minor;
    this.patch = patch;
    this.preRelease = preRelease;
    if (preRelease != null) {
        preReleaseMajor = preReleaseMajor == null ? Integer.MAX_VALUE : preReleaseMajor;
        if (preReleaseMajor < 0) {
            preReleaseMajor = 0L;
        }
        this.preReleaseMajor = preReleaseMajor;
    } else {
        this.preReleaseMajor = Integer.MAX_VALUE;
    }
}
@NonNull
public Optional<Version> resolve(String version){
    if (StringUtils.isBlank(version)) {
        return Optional.empty();
    }
    // handle unknown version
    if (StringUtils.equalsIgnoreCase(version, HaloConst.UNKNOWN_VERSION)) {
        return Optional.of(MAXIMUM_VERSION);
    }
    // get matcher for version
    Matcher matcher = PATTERN.matcher(version);
    if (!matcher.matches()) {
        // if mismatches
        log.warn("Version: [{}] didn't match version format", version);
        return Optional.empty();
    }
    // get all groups
    String major = matcher.group("major");
    String minor = matcher.group("minor");
    String patch = matcher.group("patch");
    String preRelease = matcher.group("preRelease");
    String preReleaseMajor = matcher.group("preReleaseMajor");
    // build full version
    return Optional.of(new Version(Long.parseLong(major), Long.parseLong(minor), Long.parseLong(patch), PreRelease.of(preRelease), StringUtils.isNotBlank(preReleaseMajor) ? Long.parseLong(preReleaseMajor) : null));
}


@Nullable
public PreRelease of(String preReleaseStr){
    PreRelease[] preReleases = PreRelease.values();
    for (PreRelease preRelease : preReleases) {
        if (preRelease.name().equalsIgnoreCase(preReleaseStr)) {
            return preRelease;
        }
    }
    return null;
}


@Override
public int compareTo(Version anotherVersion){
    // compare major
    int majorCompare = Long.compare(major, anotherVersion.major);
    if (majorCompare != 0) {
        return majorCompare;
    }
    // compare minor
    int minorCompare = Long.compare(minor, anotherVersion.minor);
    if (minorCompare != 0) {
        return minorCompare;
    }
    // compare patch
    int patchCompare = Long.compare(patch, anotherVersion.patch);
    if (patchCompare != 0) {
        return patchCompare;
    }
    // if all the major, minor and patch are the same, then compare pre release number
    return Long.compare(preReleaseMajor, anotherVersion.preReleaseMajor);
}


@NonNull
public Version emptyVersion(){
    return EMPTY_VERSION;
}


}