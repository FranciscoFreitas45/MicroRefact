package de.metas.ui.web.devtools;
 import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.adempiere.ad.migration.logger.MigrationScriptFileLogger;
import org.adempiere.ad.migration.logger.MigrationScriptFileLoggerHolder;
import org.adempiere.ad.service.IDeveloperModeBL;
import org.adempiere.exceptions.AdempiereException;
import org.apache.activemq.util.ByteArrayOutputStream;
import org.compiere.util.Ini;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.session.UserSession;
import de.metas.util.Services;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
@RestController
@RequestMapping(value = MigrationScriptRestController.ENDPOINT)
public class MigrationScriptRestController {

 public  String ENDPOINT;

 private  Logger logger;

@Autowired
 private  UserSession userSession;


public Path getCurrentScriptPath(){
    final Path currentScriptPath = MigrationScriptFileLoggerHolder.getCurrentScriptPathOrNull();
    if (currentScriptPath == null) {
        throw new AdempiereException("No current script file found");
    }
    return currentScriptPath;
}


@GetMapping
public JSONMigrationScriptsInfo getInfo(){
    assertAuth();
    final Path currentScriptPath = MigrationScriptFileLoggerHolder.getCurrentScriptPathOrNull();
    return JSONMigrationScriptsInfo.builder().enabled(Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT)).migrationScriptDirectory(toString(getMigrationScriptsDirectoryPath())).currentScript(toString(currentScriptPath)).scripts(getMigrationScriptFileNames()).build();
}


public Path getMigrationScriptPath(String filename){
    final Path migrationScriptDirectory = getMigrationScriptsDirectoryPath();
    final List<String> scriptFileNames = getMigrationScriptFileNames();
    if (!scriptFileNames.contains(filename)) {
        throw new AdempiereException("File '" + filename + "' is not available. Try one of followings: " + scriptFileNames);
    }
    return migrationScriptDirectory.resolve(filename);
}


@GetMapping("/disableIt")
public void disableLogMigrationScripts(){
    assertAuth();
    Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, false);
}


@GetMapping("/scripts/current")
public ResponseEntity<byte[]> getCurrentScript(boolean rotate,boolean inline){
    assertAuth();
    final Path currentScriptPath = getCurrentScriptPath();
    final ResponseEntity<byte[]> scriptData = getScript(currentScriptPath, inline);
    if (rotate) {
        MigrationScriptFileLoggerHolder.closeMigrationScriptFiles();
    }
    return scriptData;
}


public List<String> getMigrationScriptFileNames(){
    final Path migrationScriptDirectory = getMigrationScriptsDirectoryPath();
    try {
        return Files.list(migrationScriptDirectory).map(Path::toFile).map(File::getName).filter(filename -> filename.toLowerCase().endsWith(".sql")).collect(ImmutableList.toImmutableList());
    } catch (final IOException e) {
        throw new AdempiereException("Failed fetching migration scripts from " + migrationScriptDirectory);
    }
}


public void deleteScript(Path scriptPath){
    try {
        Files.delete(scriptPath);
    } catch (final IOException ex) {
        throw new AdempiereException("Failed deleting " + scriptPath, ex);
    }
}


public void assertAuth(){
    userSession.assertLoggedIn();
}


public byte[] getScriptContent(Path scriptPath){
    try {
        final ArrayList<String> lines = new ArrayList<>();
        lines.add("--");
        lines.add("-- Script: " + scriptPath.toString());
        lines.add("-- User: " + userSession.getUserName());
        lines.add("-- OS user: " + System.getProperty("user.name"));
        lines.add("--");
        lines.add("");
        lines.add("");
        lines.addAll(Files.readAllLines(scriptPath));
        return toByteArray(lines);
    } catch (final IOException ex) {
        throw new AdempiereException("Failed reading content of " + scriptPath, ex);
    }
}


public byte[] toByteArray(List<String> lines){
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
        lines.stream().map(s -> s + "\n").map(s -> s.getBytes(StandardCharsets.UTF_8)).forEach(bytes -> {
            try {
                out.write(bytes);
            } catch (IOException ex) {
                // shall never happen
                throw AdempiereException.wrapIfNeeded(ex);
            }
        });
        return out.toByteArray();
    } catch (final IOException ex) {
        // shall never happen
        throw AdempiereException.wrapIfNeeded(ex);
    }
}


public void configureMigrationScriptDirectoryIfNeeded(){
    if (Services.get(IDeveloperModeBL.class).isEnabled()) {
        logger.warn("Use default migration scripts folder because we are running in developer mode");
        return;
    }
    // Change the migration scripts directory to temporary directory,
    // hoping that even in readonly filesystem (like docker) the temporary directory is still writable.
    try {
        final Path migrationScriptsDirectory = Files.createTempDirectory("webui_migration_scripts_" + LocalDate.now() + "_");
        MigrationScriptFileLogger.setMigrationScriptDirectory(migrationScriptsDirectory);
    } catch (final IOException ex) {
        logger.warn("Failed to create & change migration scripts temporary directory. Ignoring and using the defaults", ex);
    }
}


public Path getMigrationScriptsDirectoryPath(){
    return MigrationScriptFileLogger.getMigrationScriptDirectory();
}


@GetMapping("/enableIt")
public void enableLogMigrationScripts(){
    assertAuth();
    Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, true);
}


public String toString(Path path){
    if (path == null) {
        return null;
    }
    try {
        return path.toRealPath().toString();
    } catch (final IOException ex) {
        logger.warn("Failed converting {} to real path", path, ex);
        return path.toString();
    }
}


@DeleteMapping("/scripts/current")
public void deleteCurrentScript(){
    assertAuth();
    final Path currentScriptPath = getCurrentScriptPath();
    deleteScript(currentScriptPath);
}


public ResponseEntity<byte[]> getScript(Path scriptPath,boolean inline){
    final String contentDispositionType = inline ? "inline" : "attachment";
    final byte[] content = getScriptContent(scriptPath);
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    headers.set(HttpHeaders.CONTENT_DISPOSITION, contentDispositionType + "; filename=\"" + scriptPath.getFileName() + "\"");
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    final ResponseEntity<byte[]> response = new ResponseEntity<>(content, headers, HttpStatus.OK);
    return response;
}


}