package com.ec.survey.DTO;
 import com.ec.survey.exception.MessageException;
import com.ec.survey.model;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.tools.CleanupWorker;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.MutableInteger;
import com.ec.survey.tools.RecreateWorker;
import com.ec.survey.tools.Tools;
import com.ec.survey.tools.export.FileExportCreator;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file;
import java.nio.file.attribute.BasicFileAttributes;
import java.util;
import java.util.Map.Entry;
import com.ec.survey.DTO.Survey;
import com.ec.survey.DTO.GalleryQuestion;
import com.ec.survey.DTO.Confirmation;
import com.ec.survey.DTO.Download;
import com.ec.survey.DTO.Image;
import com.ec.survey.DTO.ResultFilter;
import com.ec.survey.DTO.Export;
import com.ec.survey.DTO.ArchiveFilter;
import com.ec.survey.DTO.Archive;
import com.ec.survey.DTO.Draft;
import com.ec.survey.DTO.AnswerSet;
import com.ec.survey.DTO.CleanupWorker;
import com.ec.survey.DTO.Element;
import com.ec.survey.DTO.User;
public class FileService extends BasicService{

 protected  PDFService pdfService;

 public  String[] filetypes;

 public  String[] fileextensions;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public java.io.File getSurveyExportFile(String surveyUID,String fileUID,boolean create){
    java.io.File folder = getSurveyExportsFolder(surveyUID, create);
    return new java.io.File(folder.getPath() + Constants.PATH_DELIMITER + fileUID);
}


public java.io.File getSurveyFilesFolder(String surveyUID){
    java.io.File folder = new java.io.File(surveysDir + surveyUID.substring(0, 1) + Constants.PATH_DELIMITER + surveyUID + "/FILES/");
    if (!folder.exists())
        folder.mkdirs();
    return folder;
}


public List<FileResult> getFiles2(FileFilter filter){
    final List<FileResult> result = new ArrayList<>();
    final MutableInteger counter = new MutableInteger(0);
    final int skip = filter.getPage() > 1 ? (filter.getPage() - 1) * filter.getItemsPerPage() : 0;
    if (filter.isSearchInFileSystem()) {
        java.io.File dir = null;
        if (filter.getSurveyUid() != null && filter.getSurveyUid().length() > 0) {
            if (filter.isArchivedSurveys()) {
                dir = getArchiveFolder(filter.getSurveyUid());
            } else {
                dir = getSurveyFolder(filter.getSurveyUid());
            }
        } else if (filter.getUserId() > 0) {
            dir = getUsersFolder(filter.getUserId(), false);
            if (dir == null) {
                return result;
            }
        } else if (filter.isArchivedSurveys()) {
            dir = new java.io.File(archiveDir);
        }
        if (dir != null) {
            try {
                Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) throws IOException {
                        if (!filter.isSystemExports() && file.endsWith("EXPORTS")) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        if (!filter.isSurveyFiles() && file.endsWith("FILES")) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        if (!filter.isTemporaryFiles() && file.endsWith("UPLOADS")) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (!file.toFile().isDirectory()) {
                            if (skip - counter.getValue() > 0) {
                                counter.setValue(counter.getValue() + 1);
                            } else {
                                FileResult fileResult = getFileResult(file, null, new java.io.File(archiveDir).toPath());
                                result.add(fileResult);
                            }
                            if (result.size() >= filter.getItemsPerPage()) {
                                return FileVisitResult.TERMINATE;
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        // Skip folders that can't be traversed
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage(), e);
                return result;
            }
        }
    } else {
        result.addAll(getFilesForSurvey(filter, filter.getPage(), filter.getItemsPerPage(), new java.io.File(archiveDir).toPath()));
    }
    return result;
}


public Map<String,String> getMissingFiles(String uniqueId){
    final Path archivedir = Paths.get(archiveFileDir);
    Map<String, String> result = new HashMap<>();
    List<Integer> ids = surveyService.getAllSurveyVersions(null, uniqueId);
    Set<java.io.File> files = getFilesForSurveys(ids, true);
    for (java.io.File f : files) {
        if (!f.exists()) {
            FileResult fresult = getFileResult(f.toPath(), null, archivedir);
            result.put(fresult.getFileName(), fresult.getFileType());
        }
    }
    return result;
}


public java.io.File getSurveyPDFFile(String surveyUID,Integer surveyID,String lang){
    java.io.File folder = fileService.getSurveyExportsFolder(surveyUID);
    return new java.io.File(String.format("%s/survey%s%s.pdf", folder.getPath(), surveyID, lang));
}


public List<FileResult> getFiles(FileFilter inputfilter){
    final List<FileResult> result = new ArrayList<>();
    final Path dir = Paths.get(tempFileDir);
    final Path filedir = Paths.get(fileDir);
    final Path archivedir = Paths.get(archiveFileDir);
    final FileFilter filter = inputfilter;
    int page = filter.getPage();
    final int itemsperpage = filter.getItemsPerPage();
    if (!filter.isOnlyUnreferenced() && ((filter.getSurveyUid() != null && filter.getSurveyUid().length() > 0) || (filter.getSurveyShortname() != null && filter.getSurveyShortname().length() > 0))) {
        // search database for files
        return getFilesForSurvey(filter, page, itemsperpage, archivedir);
    } else {
        // search file system
        final MutableInteger counter = new MutableInteger(0);
        final int skip = page > 1 ? (page - 1) * itemsperpage : 0;
        if (filter.isSystemExports() || filter.isSurveyUploads() || filter.isTemporaryFiles() || filter.isUnknownFiles()) {
            try {
                Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) throws IOException {
                        if (file.equals(dir) || file.equals(filedir) || (file.equals(archivedir) && filter.isArchivedSurveys())) {
                            return FileVisitResult.CONTINUE;
                        } else if (filter.isTemporaryFiles() && !file.equals(archivedir)) {
                            return FileVisitResult.CONTINUE;
                        } else {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (!file.toFile().isDirectory()) {
                            FileResult fileResult = getFileResult(file, null, archivedir);
                            if (checkResult(fileResult, filter)) {
                                if (skip - counter.getValue() > 0) {
                                    counter.setValue(counter.getValue() + 1);
                                } else {
                                    result.add(fileResult);
                                }
                                if (result.size() >= itemsperpage) {
                                    return FileVisitResult.TERMINATE;
                                }
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        // Skip folders that can't be traversed
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage(), e);
                return result;
            }
        } else if (filter.isArchivedSurveys()) {
            try {
                Files.walkFileTree(archivedir, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        FileResult fileResult = getFileResult(file, null, archivedir);
                        if (checkResult(fileResult, filter)) {
                            if (skip - counter.getValue() > 0) {
                                counter.setValue(counter.getValue() + 1);
                            } else {
                                result.add(fileResult);
                            }
                            if (result.size() >= itemsperpage) {
                                return FileVisitResult.TERMINATE;
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        // Skip folders that can't be traversed
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage(), e);
                return result;
            }
        }
    }
    return result;
}


public java.io.File getUsersFolder(int userId,boolean create){
    int userfolderNumber = (userId / 1000) * 1000;
    String userfolder = "Users";
    if (userfolderNumber > 0)
        userfolder += userfolderNumber;
    java.io.File folder = new java.io.File(usersDir + userfolder + Constants.PATH_DELIMITER + userId + Constants.PATH_DELIMITER);
    if (!folder.exists() && !create)
        return null;
    if (!folder.exists())
        folder.mkdirs();
    return folder;
}


public java.io.File getSurveyFolder(String surveyUID){
    java.io.File folder = new java.io.File(surveysDir + surveyUID.substring(0, 1) + Constants.PATH_DELIMITER + surveyUID + Constants.PATH_DELIMITER);
    if (!folder.exists())
        folder.mkdirs();
    return folder;
}


public FileResult getFileResult(Path file,Export export,Path archivedir){
    FileResult fileResult = new FileResult();
    fileResult.setFilePath(file.toAbsolutePath().toString());
    if (file.toFile().exists()) {
        fileResult.setFileSize(ConversionTools.getStringForBytes(Files.size(file)));
        fileResult.setCreated(new Date(Files.getLastModifiedTime(file).toMillis()));
    }
    String name = file.getFileName().toString();
    // default
    fileResult.setFileName(name);
    fileResult.setSurveyShortname("");
    fileResult.setSurveyUid("");
    fileResult.setFileType("");
    fileResult.setFileExtension("");
    if (name.startsWith(Constants.ANSWER) && name.endsWith(".pdf")) {
        fileResult.setFileName(name);
        fileResult.setFileExtension("PDF");
        fileResult.setFileType("contribution");
        String caseid = name.substring(6).replace(".pdf", "");
        AnswerSet answerSet = answerService.get(caseid);
        if (answerSet != null) {
            fileResult.setSurveyUid(answerSet.getSurvey().getUniqueId());
            fileResult.setSurveyShortname(answerSet.getSurvey().getShortname());
        } else {
            fileResult.setError("unknown contribution id");
        }
    } else if (name.startsWith("draft") && name.endsWith(".pdf")) {
        fileResult.setFileName(name);
        fileResult.setFileExtension("PDF");
        fileResult.setFileType("draft");
        String draftid = name.substring(5).replace(".pdf", "");
        Draft draft = answerService.getDraft(draftid);
        if (draft != null) {
            fileResult.setSurveyUid(draft.getAnswerSet().getSurvey().getUniqueId());
            fileResult.setSurveyShortname(draft.getAnswerSet().getSurvey().getShortname());
        } else {
            fileResult.setError("unknown draft id");
        }
    } else if (name.startsWith(Constants.SURVEY) && name.endsWith(".pdf")) {
        fileResult.setFileName(name);
        fileResult.setFileExtension("PDF");
        fileResult.setFileType(Constants.SURVEY);
        // the name pattern is survey[id][lang].pdf
        String surveyid = name.substring(6).replace(".pdf", "");
        surveyid = surveyid.substring(0, surveyid.length() - 2);
        try {
            Survey survey = surveyService.getSurvey(Integer.parseInt(surveyid));
            if (survey != null) {
                fileResult.setSurveyUid(survey.getUniqueId());
                fileResult.setSurveyShortname(survey.getShortname());
            } else {
                fileResult.setError("unknown survey id");
            }
        } catch (NumberFormatException e) {
            // invalid file
            fileResult.setError("wrong file pattern");
        }
    } else if (name.startsWith("Export")) {
        fileResult.setFileName(name);
        fileResult.setFileExtension(name.substring(name.lastIndexOf('.') + 1).toUpperCase());
        String exportid = name.substring(6);
        exportid = exportid.substring(0, exportid.lastIndexOf('.'));
        // special case: zip archive for exports with uploaded files
        if (exportid.endsWith(".xls")) {
            exportid = exportid.substring(0, exportid.lastIndexOf('.'));
        }
        try {
            if (export == null)
                export = exportService.get(Integer.parseInt(exportid), false);
            if (export != null) {
                if (export.getSurvey() != null) {
                    fileResult.setSurveyUid(export.getSurvey().getUniqueId());
                    fileResult.setSurveyShortname(export.getSurvey().getShortname());
                }
                if (export.getResultFilter() != null) {
                    if (export.getResultFilter().isEmpty()) {
                        fileResult.setFilterApplied("No");
                    } else {
                        fileResult.setFilterApplied("Yes");
                    }
                }
                switch(export.getType()) {
                    case Activity:
                        fileResult.setFileType("activities");
                        break;
                    case AddressBook:
                        fileResult.setFileType("contacts");
                        break;
                    case Statistics:
                        fileResult.setFileType("statistics");
                        break;
                    case Tokens:
                        fileResult.setFileType("tokens");
                        break;
                    case Content:
                        fileResult.setFileType("results");
                        break;
                    default:
                        break;
                }
            } else {
                fileResult.setError("unknown export id");
            }
        } catch (NumberFormatException e) {
            // invalid file
            fileResult.setError("wrong file pattern");
        }
    } else if (archivedir != null && file.startsWith(archivedir)) {
        fileResult.setFileName(name);
        String uid = name.substring(0, 36);
        fileResult.setSurveyUid(uid);
        fileResult.setArchive(true);
        if (name.endsWith("results.xls")) {
            fileResult.setFileExtension("XLS");
            fileResult.setFileType("results");
        } else if (name.endsWith("results.xls.zip")) {
            fileResult.setFileExtension("ZIP");
            fileResult.setFileType("results");
        } else if (name.endsWith("statistics.xls")) {
            fileResult.setFileExtension("XLS");
            fileResult.setFileType("statistics");
        } else if (name.endsWith("statistics.pdf")) {
            fileResult.setFileExtension("PDF");
            fileResult.setFileType("statistics");
        } else if (name.endsWith(".pdf")) {
            fileResult.setFileExtension("PDF");
            fileResult.setFileType(Constants.SURVEY);
        } else {
            fileResult.setFileType("archive");
        }
    } else {
        try {
            File f = get(name);
            fileResult.setFileName(f.getName());
            fileResult.setFileExtension(f.getName().substring(f.getName().lastIndexOf('.') + 1).toUpperCase());
            fileResult.setFileUid(f.getUid());
            String[] surveyData = surveyService.getSurveyForFile(f, contextpath, null);
            if (surveyData != null) {
                fileResult.setSurveyShortname(surveyData[1]);
                fileResult.setSurveyUid(surveyData[0]);
                fileResult.setFileType(surveyData[2]);
            }
        } catch (FileNotFoundException e) {
            fileResult.setError("unknown file uid");
        }
    }
    return fileResult;
}


public java.io.File getArchiveFile(String surveyUID,String name){
    java.io.File folder = getArchiveFolder(surveyUID);
    return new java.io.File(folder.getPath() + Constants.PATH_DELIMITER + name);
}


@Transactional(readOnly = true)
public ExportCache getCachedExport(Integer surveyId,String hash,String type){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM ExportCache c WHERE c.surveyId = :surveyId AND c.filterHash = :hash AND c.type = :type").setInteger("surveyId", surveyId).setString("hash", hash).setString("type", type);
    @SuppressWarnings("unchecked")
    List<ExportCache> list = query.list();
    if (!list.isEmpty()) {
        return list.get(0);
    }
    return null;
}


public java.io.File getTempFolder(){
    java.io.File folder = new java.io.File(usersDir + "temp");
    if (!folder.exists())
        folder.mkdirs();
    return folder;
}


public Set<java.io.File> getFilesForSurveys(List<Integer> surveyIDs,boolean onlySurveyFiles){
    Set<java.io.File> result = new HashSet<>();
    if (!onlySurveyFiles) {
        // get exports
        for (int surveyID : surveyIDs) {
            List<Export> exports = exportService.getSurveyExports(surveyID);
            for (Export export : exports) {
                java.io.File file = fileService.getSurveyExportFile(export.getSurvey().getUniqueId(), export.getId(), export.getFormat().toString());
                if (file.exists()) {
                    result.add(file);
                } else {
                    String filePath = String.format("%sExport%s.%s", tempFileDir, export.getId(), export.getFormat());
                    file = new java.io.File(filePath);
                    result.add(file);
                }
                if (export.getZipped()) {
                    file = fileService.getSurveyExportFile(export.getSurvey().getUniqueId(), export.getId(), export.getFormat().toString() + ".zip");
                    if (file.exists()) {
                        result.add(file);
                    } else {
                        String filePath = String.format("%sExport%s.%s.zip", tempFileDir, export.getId(), export.getFormat());
                        file = new java.io.File(filePath);
                        result.add(file);
                    }
                }
            }
        }
        // get contribution pdfs
        result.addAll(getPDFContributionFilesForSurvey(surveyIDs));
    }
    // get survey files
    for (int surveyID : surveyIDs) {
        Survey survey = surveyService.getSurvey(surveyID);
        final FilesByTypes<Integer, String> explanationFiles = answerExplanationService.getExplanationFilesByAnswerSetIdAndQuestionUid(survey);
        if (!onlySurveyFiles) {
            for (String lang : translationService.getTranslationLanguagesForSurvey(surveyID, false)) {
                java.io.File file = fileService.getSurveyPDFFile(survey.getUniqueId(), survey.getId(), lang);
                result.add(file);
            }
        }
        if (survey.getLogo() != null) {
            String uid = survey.getLogo().getUid();
            java.io.File file = fileService.getSurveyFile(survey.getUniqueId(), uid);
            if (!file.exists()) {
                String filePath = String.format("%s%s", fileDir, uid);
                file = new java.io.File(filePath);
            }
            result.add(file);
        }
        for (String key : survey.getBackgroundDocuments().keySet()) {
            String fileUID = getFileUIDFromUrl(survey.getBackgroundDocuments().get(key));
            java.io.File file = fileService.getSurveyFile(survey.getUniqueId(), fileUID);
            if (!file.exists()) {
                String filePath = String.format("%s%s", fileDir, fileUID);
                file = new java.io.File(filePath);
            }
            result.add(file);
        }
        for (Element question : survey.getElements()) {
            if (question instanceof Download) {
                Download download = (Download) question;
                for (File f : download.getFiles()) {
                    java.io.File file = fileService.getSurveyFile(survey.getUniqueId(), f.getUid());
                    if (!file.exists()) {
                        String filePath = String.format("%s%s", fileDir, f.getUid());
                        file = new java.io.File(filePath);
                    }
                    result.add(file);
                }
            } else if (question instanceof Image) {
                Image image = (Image) question;
                if (image.getUrl() != null && !image.getUrl().contains("photo_scenery.png")) {
                    String fileUID = getFileUIDFromUrl(image.getUrl());
                    java.io.File file = fileService.getSurveyFile(survey.getUniqueId(), fileUID);
                    if (!file.exists()) {
                        String filePath = String.format("%s%s", fileDir, fileUID);
                        file = new java.io.File(filePath);
                    }
                    result.add(file);
                }
            } else if (question instanceof GalleryQuestion) {
                GalleryQuestion gallery = (GalleryQuestion) question;
                for (File f : gallery.getFiles()) {
                    java.io.File file = fileService.getSurveyFile(survey.getUniqueId(), f.getUid());
                    if (!file.exists()) {
                        String filePath = String.format("%s%s", fileDir, f.getUid());
                        file = new java.io.File(filePath);
                    }
                    result.add(file);
                }
            }
            if (question.isDelphiElement()) {
                explanationFiles.applyFunctionOnEachFile((answerSetId, questionUid, explanationFile) -> {
                    if (questionUid.equalsIgnoreCase(question.getUniqueId())) {
                        java.io.File file = fileService.getSurveyFile(survey.getUniqueId(), explanationFile.getUid());
                        result.add(file);
                    }
                });
            }
        }
        if (!onlySurveyFiles) {
            // uploaded files
            ResultFilter r = new ResultFilter();
            r.setSurveyId(surveyID);
            boolean stop = false;
            int answerpage = 0;
            while (!stop) {
                List<File> files = answerService.getAllUploadedFiles(surveyID, r, answerpage++, 1000);
                if (files.size() < 1000)
                    stop = true;
                for (File f : files) {
                    java.io.File file = fileService.getSurveyFile(survey.getUniqueId(), f.getUid());
                    if (!file.exists()) {
                        String filePath = String.format("%s%s", fileDir, f.getUid());
                        file = new java.io.File(filePath);
                    }
                    result.add(file);
                }
            }
        }
    }
    return result;
}


public java.io.File getSurveyUploadFile(String surveyUID,String fileUID){
    java.io.File folder = getSurveyUploadsFolder(surveyUID, false);
    return new java.io.File(folder.getPath() + Constants.PATH_DELIMITER + fileUID);
}


public java.io.File getUsersFile(int userId,String name){
    java.io.File folder = fileService.getUsersFolder(userId);
    return new java.io.File(String.format("%s/%s", folder.getPath(), name));
}


public java.io.File getArchiveFolder(String surveyUID){
    java.io.File folder = new java.io.File(archiveDir + surveyUID.substring(0, 1) + Constants.PATH_DELIMITER + surveyUID + Constants.PATH_DELIMITER);
    if (!folder.exists())
        folder.mkdirs();
    return folder;
}


public java.io.File getSurveyExportsFolder(String surveyUID,boolean create){
    java.io.File folder = new java.io.File(surveysDir + surveyUID.substring(0, 1) + Constants.PATH_DELIMITER + surveyUID + "/EXPORTS/");
    if (!folder.exists() && create)
        folder.mkdirs();
    return folder;
}


public java.io.File getSurveyExplanationUploadsFolder(String surveyUID,boolean create){
    java.io.File folder = new java.io.File(surveysDir + surveyUID.substring(0, 1) + Constants.PATH_DELIMITER + surveyUID + "/EXPLANATION_UPLOADS/");
    if (!folder.exists() && create)
        folder.mkdirs();
    return folder;
}


public List<FileResult> getFilesForSurvey(FileFilter filter,int page,int itemsperpage,Path archivedir){
    List<Integer> ids = surveyService.getAllSurveyVersions(filter.getSurveyShortname(), filter.getSurveyUid());
    Map<String, FileResult> result = new HashMap<>();
    int counter = 0;
    int skip = 0;
    if (page > 1)
        skip = (page - 1) * itemsperpage;
    for (int id : ids) {
        // exports
        if (filter.isSystemExports()) {
            List<Export> exports = exportService.getSurveyExports(id);
            for (Export export : exports) {
                boolean skipexport = false;
                switch(export.getType()) {
                    case Content:
                        if (!filter.isVisible("results")) {
                            skipexport = true;
                        }
                        break;
                    case Statistics:
                        if (!filter.isVisible("statistics")) {
                            skipexport = true;
                        }
                        break;
                    case Charts:
                        if (!filter.isVisible("charts")) {
                            skipexport = true;
                        }
                        break;
                    case Activity:
                        if (!filter.isVisible("activities")) {
                            skipexport = true;
                        }
                        break;
                    case AddressBook:
                        if (!filter.isVisible("contacts")) {
                            skipexport = true;
                        }
                        break;
                    case Tokens:
                        if (!filter.isVisible("tokens")) {
                            skipexport = true;
                        }
                        break;
                    default:
                        break;
                }
                if (!skipexport) {
                    String filePath = exportService.getExportFilePath(export, null);
                    Path file = Paths.get(filePath);
                    if (file.toFile().exists() && filter.isValidExtension(export.getFormat().toString().toUpperCase())) {
                        FileResult fresult = getFileResult(file, export, archivedir);
                        if (checkResult(fresult, filter)) {
                            if (skip - counter > 0) {
                                counter++;
                            } else {
                                add(fresult, result);
                            }
                            if (result.size() >= itemsperpage) {
                                return new ArrayList<>(result.values());
                            }
                        }
                    }
                }
            }
        }
        Survey survey = surveyService.getSurvey(id);
        java.io.File folder = fileService.getSurveyExportsFolder(survey.getUniqueId());
        // contribution pdfs
        if (filter.isVisible("contribution") && filter.isValidExtension("PDF")) {
            ResultFilter r = new ResultFilter();
            r.setSurveyId(id);
            if (filter.getCreatedFrom() != null) {
                r.setGeneratedFrom(filter.getCreatedFrom());
            }
            boolean stop = false;
            int answerpage = 0;
            while (!stop) {
                Set<String> caseids = answerService.getCaseIds(id, r, answerpage++, 1000, false);
                if (caseids != null && !caseids.isEmpty()) {
                    logger.debug("found case ids for survey " + id + ": " + StringUtils.join(caseids.toArray(), ","));
                }
                if (caseids != null && caseids.size() < 1000)
                    stop = true;
                for (String caseid : caseids) {
                    String filePath = String.format("%s/answer%s.pdf", folder.getPath(), caseid);
                    Path file = Paths.get(filePath);
                    if (file.toFile().exists()) {
                        FileResult fresult = getFileResult(file, null, archivedir);
                        if (checkResult(fresult, filter)) {
                            if (skip - counter > 0) {
                                counter++;
                            } else {
                                add(fresult, result);
                            }
                            if (result.size() >= itemsperpage) {
                                return new ArrayList<>(result.values());
                            }
                        }
                    }
                }
            }
        }
        // survey images, logos, download, background documents
        if (filter.isVisible("logo") && survey.getLogo() != null) {
            String uid = survey.getLogo().getUid();
            Path file = getSurveyFile(survey.getUniqueId(), uid).toPath();
            if (file.toFile().exists()) {
                FileResult fresult = getFileResult(file, null, archivedir);
                if (checkResult(fresult, filter)) {
                    if (skip - counter > 0) {
                        counter++;
                    } else {
                        add(fresult, result);
                    }
                    if (result.size() >= itemsperpage) {
                        return new ArrayList<>(result.values());
                    }
                }
            }
        }
        if (filter.isVisible("background document")) {
            for (String key : survey.getBackgroundDocuments().keySet()) {
                String fileUID = getFileUIDFromUrl(survey.getBackgroundDocuments().get(key));
                Path file = getSurveyFile(survey.getUniqueId(), fileUID).toPath();
                if (file.toFile().exists()) {
                    FileResult fresult = getFileResult(file, null, archivedir);
                    if (checkResult(fresult, filter)) {
                        if (skip - counter > 0) {
                            counter++;
                        } else {
                            add(fresult, result);
                        }
                        if (result.size() >= itemsperpage) {
                            return new ArrayList<>(result.values());
                        }
                    }
                }
            }
        }
        if (filter.isVisible("download") || filter.isVisible("image")) {
            for (Element question : survey.getElements()) {
                if (filter.isVisible("download") && question instanceof Download) {
                    Download download = (Download) question;
                    for (File f : download.getFiles()) {
                        Path file = getSurveyFile(survey.getUniqueId(), f.getUid()).toPath();
                        if (file.toFile().exists()) {
                            FileResult fresult = getFileResult(file, null, archivedir);
                            if (checkResult(fresult, filter)) {
                                if (skip - counter > 0) {
                                    counter++;
                                } else {
                                    add(fresult, result);
                                }
                                if (result.size() >= itemsperpage) {
                                    return new ArrayList<>(result.values());
                                }
                            }
                        }
                    }
                } else if (filter.isVisible("image") && question instanceof Image) {
                    Image image = (Image) question;
                    String fileUID = getFileUIDFromUrl(image.getUrl());
                    Path file = getSurveyFile(survey.getUniqueId(), fileUID).toPath();
                    if (file.toFile().exists()) {
                        FileResult fresult = getFileResult(file, null, archivedir);
                        if (checkResult(fresult, filter)) {
                            if (skip - counter > 0) {
                                counter++;
                            } else {
                                add(fresult, result);
                            }
                            if (result.size() >= itemsperpage) {
                                return new ArrayList<>(result.values());
                            }
                        }
                    }
                } else if (filter.isVisible("image") && question instanceof GalleryQuestion) {
                    GalleryQuestion gallery = (GalleryQuestion) question;
                    for (File f : gallery.getFiles()) {
                        Path file = getSurveyFile(survey.getUniqueId(), f.getUid()).toPath();
                        if (file.toFile().exists()) {
                            FileResult fresult = getFileResult(file, null, archivedir);
                            if (checkResult(fresult, filter)) {
                                if (skip - counter > 0) {
                                    counter++;
                                } else {
                                    add(fresult, result);
                                }
                                if (result.size() >= itemsperpage) {
                                    return new ArrayList<>(result.values());
                                }
                            }
                        }
                    }
                }
            }
        }
        // survey pdf
        if (filter.isVisible(Constants.SURVEY) && filter.isValidExtension("PDF")) {
            for (String lang : translationService.getTranslationLanguagesForSurvey(id, false)) {
                String filePath = String.format("%s/survey%s%s.pdf", folder.getPath(), id, lang);
                Path path = Paths.get(filePath);
                if (path.toFile().exists()) {
                    FileResult fresult = getFileResult(path, null, archivedir);
                    if (checkResult(fresult, filter)) {
                        if (skip - counter > 0) {
                            counter++;
                        } else {
                            add(fresult, result);
                        }
                        if (result.size() >= itemsperpage) {
                            return new ArrayList<>(result.values());
                        }
                    }
                }
            }
        }
        // uploaded files
        if (filter.isSurveyUploads()) {
            ResultFilter r = new ResultFilter();
            r.setSurveyId(id);
            if (filter.getCreatedFrom() != null) {
                r.setGeneratedFrom(filter.getCreatedFrom());
            }
            boolean stop = false;
            int answerpage = 0;
            while (!stop) {
                List<File> files = answerService.getAllUploadedFiles(id, r, answerpage++, 1000);
                if (files.size() < 1000)
                    stop = true;
                for (File f : files) {
                    Path file = getSurveyFile(survey.getUniqueId(), f.getUid()).toPath();
                    if (file.toFile().exists()) {
                        FileResult fresult = getFileResult(file, null, archivedir);
                        fresult.setSurveyShortname(survey.getShortname());
                        fresult.setSurveyUid(survey.getUniqueId());
                        fresult.setFileType("uploaded file");
                        if (checkResult(fresult, filter)) {
                            if (skip - counter > 0) {
                                counter++;
                            } else {
                                add(fresult, result);
                            }
                            if (result.size() >= itemsperpage) {
                                return new ArrayList<>(result.values());
                            }
                        }
                    }
                }
            }
        }
    }
    return new ArrayList<>(result.values());
}


@Transactional(readOnly = true)
public File get(String uid,Integer id){
    if (id != null) {
        File result = get(id);
        if (result != null)
            return result;
    }
    return get(uid);
}


@Transactional(readOnly = true)
public Map<String,String> getFileNamesForBackgroundDocuments(Map<String,String> backgroundDocuments){
    Map<String, String> result = new HashMap<>();
    try {
        for (Entry<String, String> entry : backgroundDocuments.entrySet()) {
            String fileUID = getFileUIDFromUrl(entry.getValue());
            File f = get(fileUID);
            result.put(entry.getKey(), f.getName());
        }
    } catch (Exception e) {
    // ignore
    }
    return result;
}


public java.io.File getSurveyFile(String surveyUID,String fileUID){
    java.io.File folder = getSurveyFilesFolder(surveyUID);
    return new java.io.File(folder.getPath() + Constants.PATH_DELIMITER + fileUID);
}


@Transactional(readOnly = true)
public List<File> getAll(String uid){
    if (uid.contains(Constants.PATH_DELIMITER)) {
        // the new file system structure has the survey uid as subfolder
        uid = uid.substring(uid.indexOf('/') + 1);
    }
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM File u where u.uid = :uid").setString("uid", uid);
    @SuppressWarnings("unchecked")
    List<File> list = query.list();
    return list;
}


public Set<java.io.File> getPDFContributionFilesForSurvey(List<Integer> surveyIDs){
    Set<java.io.File> result = new HashSet<>();
    for (int surveyID : surveyIDs) {
        Survey survey = surveyService.getSurvey(surveyID);
        ResultFilter r = new ResultFilter();
        r.setSurveyId(surveyID);
        boolean stop = false;
        int answerpage = 0;
        while (!stop) {
            Set<String> caseids = answerService.getCaseIds(surveyID, r, answerpage++, 1000, false);
            if (caseids.size() < 1000)
                stop = true;
            for (String caseid : caseids) {
                java.io.File folder = fileService.getSurveyExportsFolder(survey.getUniqueId());
                java.io.File file = new java.io.File(String.format("%s/answer%s.pdf", folder.getPath(), caseid));
                if (!file.exists()) {
                    String filePath = String.format("%sanswer%s.pdf", tempFileDir, caseid);
                    file = new java.io.File(filePath);
                }
                result.add(file);
            }
        }
    }
    return result;
}


public java.io.File getTemporaryFile(String uid){
    java.io.File folder = fileService.getUsersFolder(0, true);
    return new java.io.File(String.format("%s/%s", folder.getPath(), uid));
}


@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public List<File> getAllInvalid(){
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM File f where f.deletionDate != null AND f.deletionDate <= :deletionDate");
    query.setDate("deletionDate", new Date());
    return query.list();
}


public java.io.File getSurveyUploadsFolder(String surveyUID,boolean create){
    java.io.File folder = new java.io.File(surveysDir + surveyUID.substring(0, 1) + Constants.PATH_DELIMITER + surveyUID + "/UPLOADS/");
    if (!folder.exists() && create)
        folder.mkdirs();
    return folder;
}


public java.io.File getLocalTemporaryFile(){
    return java.io.File.createTempFile("temp", "");
}


public java.io.File createTempFile(String filename,String suffix){
    return new java.io.File(getTempFolder().getAbsolutePath() + Constants.PATH_DELIMITER + filename + (suffix != null ? suffix : ""));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createTempFile"))

.queryParam("filename",filename)
.queryParam("suffix",suffix)
;
java.io.File aux = restTemplate.getForObject(builder.toUriString(),java.io.File.class);
return aux;
}


public boolean add(FileResult item,Map<String,FileResult> map){
    if (item.getFileUid() != null && map.containsKey(item.getFileUid())) {
        return false;
    } else if (item.getFileUid() != null) {
        map.put(item.getFileUid(), item);
    } else {
        map.put(UUID.randomUUID().toString(), item);
    }
    return true;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/add"))

.queryParam("item",item)
.queryParam("map",map)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void logOldFileSystemUse(String path){
    logger.info("OLD FILESYSTEM ACCESS: " + path);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/logOldFileSystemUse"))

.queryParam("path",path)
;
restTemplate.put(builder.toUriString(),null);
}


}