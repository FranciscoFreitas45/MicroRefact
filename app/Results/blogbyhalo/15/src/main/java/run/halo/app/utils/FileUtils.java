package run.halo.app.utils;
 import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import run.halo.app.exception.ForbiddenException;
@Slf4j
public class FileUtils {

private FileUtils() {
}
public void zip(Path fileToZip,String fileName,ZipOutputStream zipOut){
    if (Files.isDirectory(fileToZip)) {
        log.debug("Try to zip folder: [{}]", fileToZip);
        // Append with '/' if missing
        String folderName = StringUtils.appendIfMissing(fileName, File.separator, File.separator);
        // Create zip entry and put into zip output stream
        zipOut.putNextEntry(new ZipEntry(folderName));
        // Close entry for writing the next entry
        zipOut.closeEntry();
        // Iterate the sub files recursively
        try (Stream<Path> subPathStream = Files.list(fileToZip)) {
            // There should not use foreach for stream as internal zip method will throw
            // IOException
            List<Path> subFiles = subPathStream.collect(Collectors.toList());
            for (Path subFileToZip : subFiles) {
                // Zip children
                zip(subFileToZip, folderName + subFileToZip.getFileName(), zipOut);
            }
        }
    } else {
        // Open file to be zipped
        // Create zip entry for target file
        ZipEntry zipEntry = new ZipEntry(fileName);
        // Put the entry into zip output stream
        zipOut.putNextEntry(zipEntry);
        // Copy file to zip output stream
        Files.copy(fileToZip, zipOut);
        // Close entry
        zipOut.closeEntry();
    }
}


public void ensureEmpty(Path path){
    if (!isEmpty(path)) {
        throw new DirectoryNotEmptyException("Target directory: " + path + " was not empty");
    }
}


@Override
public FileVisitResult visitFile(Path file,BasicFileAttributes attrs){
    Files.copy(file, target.resolve(source.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
    return FileVisitResult.CONTINUE;
}


public void deleteFolder(Path deletingPath){
    Assert.notNull(deletingPath, "Deleting path must not be null");
    if (Files.notExists(deletingPath)) {
        return;
    }
    log.info("Deleting [{}]", deletingPath);
    // Delete folder recursively
    org.eclipse.jgit.util.FileUtils.delete(deletingPath.toFile(), org.eclipse.jgit.util.FileUtils.RECURSIVE | org.eclipse.jgit.util.FileUtils.RETRY);
    log.info("Deleted [{}] successfully", deletingPath);
}


@NonNull
public Path createTempDirectory(){
    final var tempDirectory = Files.createTempDirectory("halo");
    Runtime.getRuntime().addShutdownHook(new Thread(() -> deleteFolderQuietly(tempDirectory)));
    return tempDirectory;
}


@NonNull
public Optional<Path> findRootPath(Path path,int maxDepth,Predicate<Path> pathPredicate){
    return findPath(path, maxDepth, pathPredicate).map(Path::getParent);
}


public void createIfAbsent(Path path){
    Assert.notNull(path, "Path must not be null");
    if (Files.notExists(path)) {
        // Create directories
        Files.createDirectories(path);
        log.debug("Created directory: [{}]", path);
    }
}


public boolean isEmpty(Path path){
    Assert.notNull(path, "Path must not be null");
    if (!Files.isDirectory(path) || Files.notExists(path)) {
        return true;
    }
    try (Stream<Path> pathStream = Files.list(path)) {
        return pathStream.count() == 0;
    }
}


public void copyFolder(Path source,Path target){
    Assert.notNull(source, "Source path must not be null");
    Assert.notNull(target, "Target path must not be null");
    Files.walkFileTree(source, new SimpleFileVisitor<>() {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Path current = target.resolve(source.relativize(dir).toString());
            Files.createDirectories(current);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.copy(file, target.resolve(source.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
            return FileVisitResult.CONTINUE;
        }
    });
}


public void deleteFolderQuietly(Path deletingPath){
    try {
        if (deletingPath != null) {
            FileUtils.deleteFolder(deletingPath);
        }
    } catch (IOException e) {
        log.warn("Failed to delete {}", deletingPath);
    }
}


public void unzip(byte[] bytes,Path targetPath){
    Assert.notNull(bytes, "Zip bytes must not be null");
    ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes));
    unzip(zis, targetPath);
}


@Override
public FileVisitResult preVisitDirectory(Path dir,BasicFileAttributes attrs){
    Path current = target.resolve(source.relativize(dir).toString());
    Files.createDirectories(current);
    return FileVisitResult.CONTINUE;
}


public void checkDirectoryTraversal(Path parentPath,Path pathToCheck){
    Assert.notNull(parentPath, "Parent path must not be null");
    Assert.notNull(pathToCheck, "Path to check must not be null");
    if (pathToCheck.normalize().startsWith(parentPath)) {
        return;
    }
    throw new ForbiddenException("你没有权限访问 " + pathToCheck).setErrorData(pathToCheck);
}


public void rename(Path pathToRename,String newName){
    Assert.notNull(pathToRename, "File path to rename must not be null");
    Assert.notNull(newName, "New name must not be null");
    Path newPath = pathToRename.resolveSibling(newName);
    log.info("Rename [{}] to [{}]", pathToRename, newPath);
    Files.move(pathToRename, newPath);
    log.info("Rename [{}] successfully", pathToRename);
}


public void closeQuietly(ZipInputStream zipInputStream){
    try {
        if (zipInputStream != null) {
            zipInputStream.closeEntry();
            zipInputStream.close();
        }
    } catch (IOException e) {
        // Ignore this exception
        log.warn("Failed to close zip input stream", e);
    }
}


@NonNull
public Optional<Path> findPath(Path path,int maxDepth,Predicate<Path> pathPredicate){
    Assert.isTrue(maxDepth > 0, "Max depth must not be less than 1");
    if (!Files.isDirectory(path) || pathPredicate == null) {
        // if the path is not a directory or the given path predicate is null, then return an
        // empty optional
        return Optional.empty();
    }
    log.debug("Trying to find path from [{}]", path);
    // the queue holds folders which may be root
    final var queue = new LinkedList<Path>();
    // depth container
    final var depthQueue = new LinkedList<Integer>();
    // init queue
    queue.push(path);
    depthQueue.push(1);
    boolean found = false;
    Path result = null;
    while (!found && !queue.isEmpty()) {
        // pop the first path as candidate root path
        final var rootPath = queue.pop();
        final int depth = depthQueue.pop();
        if (log.isDebugEnabled()) {
            log.debug("Peek({}) into {}", depth, rootPath);
        }
        try (final Stream<Path> childrenPaths = Files.list(rootPath)) {
            final var subFolders = new LinkedList<Path>();
            var resultPath = childrenPaths.peek(p -> {
                if (Files.isDirectory(p)) {
                    subFolders.add(p);
                }
            }).filter(pathPredicate).findFirst();
            if (resultPath.isPresent()) {
                queue.clear();
                depthQueue.clear();
                // return current result path
                found = true;
                result = resultPath.get();
            } else {
                // put all directory into queue
                if (depth < maxDepth) {
                    for (Path subFolder : subFolders) {
                        if (!Files.isHidden(subFolder)) {
                            // skip hidden folder
                            queue.push(subFolder);
                            depthQueue.push(depth + 1);
                        }
                    }
                }
            }
            subFolders.clear();
        }
    }
    log.debug("Found path: [{}]", result);
    return Optional.ofNullable(result);
}


}