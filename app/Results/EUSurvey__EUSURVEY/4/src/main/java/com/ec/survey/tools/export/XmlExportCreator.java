package com.ec.survey.tools.export;
 import com.ec.survey.model.Answer;
import com.ec.survey.model.AnswerSet;
import com.ec.survey.model.Export;
import com.ec.survey.model.ResultFilter;
import com.ec.survey.model.FilesByTypes;
import com.ec.survey.model.FilesByType;
import com.ec.survey.model.survey;
import com.ec.survey.model.survey.base.File;
import com.ec.survey.tools.Constants;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.Tools;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.NotImplementedException;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util;
import java.util.Map.Entry;
import com.ec.survey.DTO.RankingQuestion;
import com.ec.survey.DTO.PossibleAnswer;
import com.ec.survey.DTO.Matrix;
import com.ec.survey.DTO.DependencyItem;
import com.ec.survey.DTO.Table;
@Service("xmlExportCreator")
@Scope("prototype")
public class XmlExportCreator extends ExportCreator{

 private  Map<Integer,String> exportedUniqueCodes;

 private  Date exportedNow;

 private  String ANSWER;

 private  String EXPLANATION;

 private  String EXPLANATION_FILE;

 private  String EXPLANATION_TEXT;

 private  String DISCUSSION;


@Override
public void exportStatisticsQuiz(){
    throw new NotImplementedException();
}


@Override
public void exportAddressBook(){
    throw new NotImplementedException();
}


@Override
public void exportActivities(){
    throw new NotImplementedException();
}


@Override
public void exportECFGlobalResults(){
    throw new NotImplementedException();
}


public Date getExportedNow(){
    return exportedNow;
}


@Transactional
public void exportContent(boolean sync,Export export,boolean fromWebService){
    exportedUniqueCodes.clear();
    XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(outputStream, "UTF-8");
    writer.writeStartDocument("UTF-8", "1.0");
    writer.writeCharacters("\n");
    writer.writeStartElement("Results");
    exportedNow = new Date();
    writer.writeAttribute("create", Tools.formatDate(exportedNow, "yyyy-MM-dd_HH-mm-ss"));
    writer.writeStartElement("Survey");
    writer.writeAttribute("id", form.getSurvey().getId().toString());
    writer.writeAttribute("alias", form.getSurvey().getShortname());
    Map<String, List<Element>> questionlists = new HashMap<>();
    Session session;
    session = sessionFactory.getCurrentSession();
    if (export != null) {
        session.evict(export.getSurvey());
    }
    form.setSurvey(surveyService.initializeAndMergeSurvey(form.getSurvey()));
    if (export != null && export.isAllAnswers() && !form.getSurvey().isMissingElementsChecked()) {
        surveyService.checkAndRecreateMissingElements(form.getSurvey(), export.getResultFilter());
        Hibernate.initialize(form.getSurvey().getMissingElements());
        for (Element e : form.getSurvey().getMissingElements()) {
            if (e instanceof ChoiceQuestion) {
                Hibernate.initialize(((ChoiceQuestion) e).getPossibleAnswers());
            } else if (e instanceof GalleryQuestion) {
                Hibernate.initialize(((GalleryQuestion) e).getFiles());
            }
        }
    }
    if (form.getSurvey().getTranslations() == null) {
        List<String> translations = translationService.getTranslationLanguagesForSurvey(form.getSurvey().getId());
        form.getSurvey().setTranslations(translations);
    }
    for (String lang : form.getSurvey().getTranslations()) {
        writer.writeStartElement("Elements");
        writer.writeAttribute("lang", lang);
        Survey survey = surveyService.getSurvey(form.getSurvey().getId(), lang);
        List<Element> questionslist = survey.getQuestionsAndSections();
        questionlists.put(lang, questionslist);
        for (Element question : questionslist) {
            if (question instanceof Matrix) {
                Matrix matrix = (Matrix) question;
                writer.writeStartElement("MatrixTitle");
                writer.writeAttribute("id", question.getUniqueId());
                writer.writeAttribute("type", getNiceType(question));
                if (export != null && export.getShowShortnames()) {
                    writer.writeAttribute("bid", matrix.getShortname());
                }
                writer.writeCharacters(matrix.getTitle());
                // MatrixTitle
                writer.writeEndElement();
                for (Element matrixQuestion : matrix.getQuestions()) {
                    writer.writeStartElement("MatrixQuestion");
                    writer.writeAttribute("id", matrixQuestion.getUniqueId());
                    writer.writeAttribute("type", (matrix.getIsSingleChoice() ? "Single Choice" : "Multiple Choice") + " Matrix Question: ");
                    if (export != null && export.getShowShortnames()) {
                        writer.writeAttribute("bid", matrixQuestion.getShortname());
                    }
                    writer.writeCharacters(matrixQuestion.getTitle());
                    // MatrixQuestion
                    writer.writeEndElement();
                }
                for (Element matrixAnswer : matrix.getAnswers()) {
                    writer.writeStartElement("MatrixAnswer");
                    writer.writeAttribute("id", matrixAnswer.getUniqueId());
                    writer.writeAttribute("type", "Matrix Answer");
                    if (export != null && export.getShowShortnames()) {
                        writer.writeAttribute("bid", matrixAnswer.getShortname());
                    }
                    writer.writeCharacters(matrixAnswer.getTitle());
                    // MatrixAnswer
                    writer.writeEndElement();
                }
                int position = 0;
                for (Element matrixQuestion : matrix.getQuestions()) {
                    for (Element matrixAnswer : matrix.getAnswers()) {
                        writer.writeStartElement("MatrixCell");
                        writer.writeAttribute("qid", matrixQuestion.getUniqueId());
                        writer.writeAttribute("aid", matrixAnswer.getUniqueId());
                        String dependentElements = "";
                        for (DependencyItem dep : matrix.getDependentElements()) {
                            if (dep != null && dep.getPosition() == position) {
                                for (Element element : dep.getDependentElements()) {
                                    if (dependentElements.length() > 0) {
                                        dependentElements += ";";
                                    }
                                    dependentElements += element.getUniqueId();
                                }
                            }
                        }
                        if (dependentElements.length() > 0) {
                            writer.writeAttribute("dependentElements", dependentElements);
                        }
                        // MatrixCell
                        writer.writeEndElement();
                        position++;
                    }
                }
            } else if (question instanceof Table) {
                Table table = (Table) question;
                writer.writeStartElement("TableTitle");
                writer.writeAttribute("id", question.getUniqueId());
                writer.writeAttribute("type", getNiceType(question));
                if (export != null && export.getShowShortnames()) {
                    writer.writeAttribute("bid", table.getShortname());
                }
                writer.writeCharacters(table.getTitle());
                // TableTitle
                writer.writeEndElement();
                for (Element tableQuestion : table.getQuestions()) {
                    writer.writeStartElement("TableQuestion");
                    writer.writeAttribute("id", tableQuestion.getUniqueId());
                    writer.writeAttribute("type", "Table Question");
                    if (export != null && export.getShowShortnames()) {
                        writer.writeAttribute("bid", tableQuestion.getShortname());
                    }
                    writer.writeCharacters(tableQuestion.getTitle());
                    // TableQuestion
                    writer.writeEndElement();
                }
                for (Element tableAnswer : table.getAnswers()) {
                    writer.writeStartElement("TableAnswer");
                    writer.writeAttribute("id", tableAnswer.getUniqueId());
                    writer.writeAttribute("type", "Table Answer");
                    if (export != null && export.getShowShortnames()) {
                        writer.writeAttribute("bid", tableAnswer.getShortname());
                    }
                    writer.writeCharacters(tableAnswer.getTitle());
                    // TableAnswer
                    writer.writeEndElement();
                }
            } else if (question instanceof Text) {
                writer.writeStartElement("Text");
                writer.writeAttribute("id", question.getUniqueId());
                writer.writeAttribute("type", getNiceType(question));
                if (export != null && export.getShowShortnames()) {
                    writer.writeAttribute("bid", question.getShortname());
                }
                writer.writeCharacters(question.getTitle());
                // Text
                writer.writeEndElement();
            } else if (question instanceof Image) {
                writer.writeStartElement("Image");
                writer.writeAttribute("id", question.getUniqueId());
                writer.writeAttribute("type", getNiceType(question));
                if (export != null && export.getShowShortnames()) {
                    writer.writeAttribute("bid", question.getShortname());
                }
                writer.writeCharacters(question.getTitle());
                // Image
                writer.writeEndElement();
            } else if (question instanceof Section) {
                writer.writeStartElement("Section");
                writer.writeAttribute("id", question.getUniqueId());
                writer.writeAttribute("type", getNiceType(question));
                if (export != null && export.getShowShortnames()) {
                    writer.writeAttribute("bid", question.getShortname());
                }
                writer.writeCharacters(question.getTitle());
                // Section
                writer.writeEndElement();
            } else {
                writer.writeStartElement("Question");
                writer.writeAttribute("id", question.getUniqueId());
                writer.writeAttribute("type", getNiceType(question));
                if (export != null && export.getShowShortnames()) {
                    writer.writeAttribute("bid", question.getShortname());
                }
                writer.writeCharacters(question.getTitle());
                // Question
                writer.writeEndElement();
                if (question instanceof ChoiceQuestion) {
                    ChoiceQuestion choice = (ChoiceQuestion) question;
                    for (PossibleAnswer answer : choice.getPossibleAnswers()) {
                        writer.writeStartElement(ANSWER);
                        writer.writeAttribute("id", answer.getUniqueId());
                        writer.writeAttribute("type", getNiceType(answer));
                        if (export != null && export.getShowShortnames()) {
                            writer.writeAttribute("bid", answer.getShortname());
                        }
                        String dependentElements = "";
                        for (Element element : answer.getDependentElements().getDependentElements()) {
                            if (dependentElements.length() > 0) {
                                dependentElements += ";";
                            }
                            dependentElements += element.getUniqueId();
                        }
                        if (dependentElements.length() > 0) {
                            writer.writeAttribute("dependentElements", dependentElements);
                        }
                        writer.writeCharacters(answer.getTitle());
                        // Answer
                        writer.writeEndElement();
                    }
                } else if (question instanceof RatingQuestion) {
                    RatingQuestion rating = (RatingQuestion) question;
                    for (Element childQuestion : rating.getQuestions()) {
                        writer.writeStartElement("RatingQuestion");
                        writer.writeAttribute("id", childQuestion.getUniqueId());
                        writer.writeAttribute("type", getNiceType(rating));
                        if (export != null && export.getShowShortnames()) {
                            writer.writeAttribute("bid", childQuestion.getShortname());
                        }
                        writer.writeCharacters(rating.getTitle() + ": " + childQuestion.getTitle());
                        // RatingQuestion
                        writer.writeEndElement();
                    }
                } else if (question instanceof RankingQuestion) {
                    RankingQuestion ranking = (RankingQuestion) question;
                    for (Element child : ranking.getChildElements()) {
                        writer.writeStartElement("RankingItem");
                        writer.writeAttribute("id", child.getUniqueId());
                        writer.writeAttribute("type", getNiceType(child));
                        if (export != null && export.getShowShortnames()) {
                            writer.writeAttribute("bid", child.getShortname());
                        }
                        writer.writeCharacters(child.getTitle());
                        // RankingQuestion
                        writer.writeEndElement();
                    }
                }
            }
        }
        // elements
        writer.writeEndElement();
    }
    // survey
    writer.writeEndElement();
    List<File> uploadedFiles = answerService.getAllUploadedFiles(form.getSurvey().getId(), export == null ? null : export.getResultFilter(), 1, Integer.MAX_VALUE);
    Map<Integer, List<File>> filesByAnswer = new HashMap<>();
    for (File file : uploadedFiles) {
        if (file.getAnswerId() != null) {
            if (!filesByAnswer.containsKey(file.getAnswerId())) {
                filesByAnswer.put(file.getAnswerId(), new ArrayList<>());
            }
            filesByAnswer.get(file.getAnswerId()).add(file);
        }
    }
    FilesByTypes<Integer, String> explanationFilesOfSurvey = answerExplanationService.getExplanationFilesByAnswerSetIdAndQuestionUid(form.getSurvey());
    FilesByType<String> explanationFilesToExport = new FilesByType<>();
    HashMap<String, Object> values = new HashMap<>();
    Map<String, String> ECASUserLoginsByEmail = null;
    if (export.getAddMeta()) {
        ECASUserLoginsByEmail = administrationService.getECASUserLoginsByEmail();
    }
    ResultFilter origFilter = answerService.initialize(export.getResultFilter());
    ResultFilter filterWithMeta = export == null ? null : origFilter.copy();
    if (filterWithMeta != null) {
        filterWithMeta.getVisibleQuestions().clear();
        filterWithMeta.getVisibleQuestions().addAll(filterWithMeta.getExportedQuestions());
        if (filterWithMeta.getVisibleQuestions().isEmpty()) {
            // initially add all questions
            for (Element question : form.getSurvey().getQuestionsAndSections()) {
                filterWithMeta.getVisibleQuestions().add(question.getId().toString());
            }
            if (!filterWithMeta.getVisibleQuestions().contains("invitation")) {
                filterWithMeta.getVisibleQuestions().add("invitation");
            }
            if (!filterWithMeta.getVisibleQuestions().contains("user")) {
                filterWithMeta.getVisibleQuestions().add("user");
            }
            if (!filterWithMeta.getVisibleQuestions().contains("created")) {
                filterWithMeta.getVisibleQuestions().add("created");
            }
            if (!filterWithMeta.getVisibleQuestions().contains("updated")) {
                filterWithMeta.getVisibleQuestions().add("updated");
            }
            if (!filterWithMeta.getVisibleQuestions().contains("languages")) {
                filterWithMeta.getVisibleQuestions().add("languages");
            }
            if (!form.getSurvey().getIsQuiz()) {
                filterWithMeta.getVisibleQuestions().add("score");
            }
        }
    }
    if (!filterWithMeta.getVisibleQuestions().contains("languages")) {
        filterWithMeta.getVisibleQuestions().add("languages");
    }
    if (!filterWithMeta.getVisibleQuestions().contains("score")) {
        filterWithMeta.getVisibleQuestions().add("score");
    }
    if (fromWebService && !filterWithMeta.getVisibleQuestions().contains("case")) {
        filterWithMeta.getVisibleQuestions().add("case");
    }
    List<List<String>> answersets = reportingService.getAnswerSets(form.getSurvey(), filterWithMeta, null, false, true, true, true, true, false);
    Map<String, List<File>> uploadedFilesByQuestionUID = new HashMap<>();
    writer.writeStartElement("Answers");
    if (answersets != null) {
        for (List<String> row : answersets) {
            String lang = row.get(row.size() - 2);
            List<Element> questions = form.getSurvey().getQuestionsAndSections();
            if (questionlists.containsKey(lang)) {
                questions = questionlists.get(lang);
            }
            parseAnswerSet(form.getSurvey(), writer, questions, null, row, row.get(1), filesByAnswer, uploadedFilesByQuestionUID, export.getAddMeta(), filterWithMeta, ECASUserLoginsByEmail, null, null, explanationFilesOfSurvey, explanationFilesToExport);
        }
    } else {
        // it is not possible to query the database after the result query was executed
        Map<Integer, Map<String, String>> explanations = answerExplanationService.getAllExplanations(form.getSurvey());
        Map<Integer, Map<String, String>> discussions = answerExplanationService.getAllDiscussions(form.getSurvey());
        String sql = "select ans.ANSWER_SET_ID, a.QUESTION_ID, a.QUESTION_UID, a.VALUE, a.ANSWER_COL, a.ANSWER_ID, a.ANSWER_ROW, a.PA_ID, a.PA_UID, ans.UNIQUECODE, ans.ANSWER_SET_DATE, ans.ANSWER_SET_UPDATE, ans.ANSWER_SET_INVID, ans.RESPONDER_EMAIL, ans.ANSWER_SET_LANG, a.AS_ID, ans.SCORE FROM ANSWERS a RIGHT JOIN ANSWERS_SET ans ON a.AS_ID = ans.ANSWER_SET_ID where ans.ANSWER_SET_ID IN (" + answerService.getSql(null, form.getSurvey().getId(), export == null ? null : export.getResultFilter(), values, true) + ") ORDER BY ans.ANSWER_SET_ID";
        SQLQuery query = session.createSQLQuery(sql);
        query.setReadOnly(true);
        for (Entry<String, Object> entry : values.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                query.setString(entry.getKey(), (String) value);
            } else if (value instanceof Integer) {
                query.setInteger(entry.getKey(), (Integer) value);
            } else if (value instanceof Date) {
                query.setTimestamp(entry.getKey(), (Date) value);
            }
        }
        query.setFetchSize(Integer.MIN_VALUE);
        ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
        int lastAnswerSet = 0;
        AnswerSet answerSet = new AnswerSet();
        answerSet.setSurvey(form.getSurvey());
        String list = "";
        while (results.next()) {
            Object[] a = results.get();
            Answer answer = new Answer();
            answer.setAnswerSetId(ConversionTools.getValue(a[0]));
            answer.setQuestionId(ConversionTools.getValue(a[1]));
            answer.setQuestionUniqueId((String) a[2]);
            answer.setValue((String) a[3]);
            answer.setColumn(ConversionTools.getValue(a[4]));
            answer.setId(ConversionTools.getValue(a[5]));
            answer.setRow(ConversionTools.getValue(a[6]));
            answer.setPossibleAnswerId(ConversionTools.getValue(a[7]));
            answer.setPossibleAnswerUniqueId((String) a[8]);
            if (lastAnswerSet == answer.getAnswerSetId()) {
                answerSet.addAnswer(answer);
            } else {
                if (lastAnswerSet > 0) {
                    parseAnswerSet(form.getSurvey(), writer, questionlists.get(answerSet.getLanguageCode()), answerSet, null, list, filesByAnswer, uploadedFilesByQuestionUID, export.getAddMeta(), filterWithMeta, ECASUserLoginsByEmail, explanations, discussions, explanationFilesOfSurvey, explanationFilesToExport);
                }
                answerSet = new AnswerSet();
                answerSet.setSurvey(form.getSurvey());
                answerSet.setId(answer.getAnswerSetId());
                lastAnswerSet = answer.getAnswerSetId();
                answerSet.getAnswers().add(answer);
                answerSet.setUniqueCode((String) a[9]);
                answerSet.setDate((Date) a[10]);
                answerSet.setUpdateDate((Date) a[11]);
                answerSet.setInvitationId((String) a[12]);
                answerSet.setResponderEmail((String) a[13]);
                answerSet.setLanguageCode((String) a[14]);
                Integer ilist = ConversionTools.getValue(a[15]);
                list = ilist.toString();
                if (answerSet.getLanguageCode() == null || !questionlists.containsKey(answerSet.getLanguageCode())) {
                    answerSet.setLanguageCode(questionlists.keySet().toArray()[0].toString());
                }
                answerSet.setScore(ConversionTools.getValue(a[16]));
            }
        }
        if (lastAnswerSet > 0)
            parseAnswerSet(form.getSurvey(), writer, questionlists.get(answerSet.getLanguageCode()), answerSet, null, list, filesByAnswer, uploadedFilesByQuestionUID, export.getAddMeta(), filterWithMeta, ECASUserLoginsByEmail, explanations, discussions, explanationFilesOfSurvey, explanationFilesToExport);
        results.close();
    }
    // Answers
    writer.writeEndElement();
    // Results
    writer.writeEndElement();
    if (!uploadedFiles.isEmpty() || explanationFilesToExport.hasFiles()) {
        // there are multiple files
        java.io.File temp = new java.io.File(exportFilePath + ".zip");
        final OutputStream out = new FileOutputStream(temp);
        final ArchiveOutputStream os = new ArchiveStreamFactory().createArchiveOutputStream("zip", out);
        os.putArchiveEntry(new ZipArchiveEntry(FilenameUtils.getName(exportFilePath)));
        IOUtils.copy(new FileInputStream(exportFilePath), os);
        os.closeArchiveEntry();
        for (Entry<String, List<File>> entry : uploadedFilesByQuestionUID.entrySet()) {
            for (File file : entry.getValue()) {
                java.io.File f = new java.io.File(exportService.getFileDir() + file.getUid());
                if (f.exists()) {
                    os.putArchiveEntry(new ZipArchiveEntry(entry.getKey() + Constants.PATH_DELIMITER + file.getUid() + "_" + file.getName()));
                    IOUtils.copy(new FileInputStream(f), os);
                    os.closeArchiveEntry();
                }
            }
        }
        explanationFilesToExport.applyFunctionOnEachFile((questionUid, explanationFile) -> {
            java.io.File file = fileService.getSurveyFile(form.getSurvey().getUniqueId(), explanationFile.getUid());
            if (file.exists()) {
                os.putArchiveEntry(new ZipArchiveEntry(questionUid + Constants.PATH_DELIMITER + explanationFile.getUid() + "_" + explanationFile.getName()));
                IOUtils.copy(new FileInputStream(file), os);
                os.closeArchiveEntry();
            }
        });
        os.close();
        if (export != null)
            export.setZipped(true);
    } else {
        if (export != null)
            export.setZipped(false);
    }
}


@Override
public void exportECFProfileResults(){
    throw new NotImplementedException();
}


public void setExportedNow(Date exportedNow){
    this.exportedNow = exportedNow;
}


public void parseAnswerSet(Survey survey,XMLStreamWriter writer,List<Element> questions,AnswerSet answerSet,List<String> row,String list,Map<Integer,List<File>> filesByAnswer,Map<String,List<File>> uploadedFilesByQuestionUID,boolean meta,ResultFilter filter,Map<String,String> ECASUserLoginsByEmail,Map<Integer,Map<String,String>> explanations,Map<Integer,Map<String,String>> discussions,FilesByTypes<Integer,String> explanationFilesOfSurvey,FilesByType<String> explanationFilesToExport){
    writer.writeStartElement("AnswerSet");
    if (survey.getSecurity().contains("anonymous")) {
        writer.writeAttribute("id", "Anonymous");
    } else if (answerSet == null) {
        writer.writeAttribute("id", row.get(0));
        exportedUniqueCodes.put(Integer.parseInt(row.get(1)), row.get(0));
    } else {
        if (filter == null || filter.exported("case"))
            writer.writeAttribute("id", answerSet.getUniqueCode());
        exportedUniqueCodes.put(answerSet.getId(), answerSet.getUniqueCode());
    }
    if (meta || filter == null || filter.exported("created"))
        writer.writeAttribute("create", answerSet == null ? row.get(row.size() - 4) : Tools.formatDate(answerSet.getDate(), "yyyy-MM-dd_HH-mm-ss"));
    writer.writeAttribute("list", list);
    if (meta || filter == null || filter.exported("updated"))
        writer.writeAttribute("last", answerSet == null ? row.get(row.size() - 3) : Tools.formatDate(answerSet.getUpdateDate(), "yyyy-MM-dd_HH-mm-ss"));
    if (meta || filter == null || filter.exported("languages"))
        writer.writeAttribute("lang", answerSet == null ? row.get(row.size() - 2) : answerSet.getLanguageCode());
    if (meta || filter == null || filter.exported("user"))
        writer.writeAttribute("user", answerSet == null ? row.get(row.size() - 5) : answerSet.getResponderEmail() != null ? answerSet.getResponderEmail() : "");
    if (meta || filter == null || filter.exported("invitation"))
        writer.writeAttribute("invitation", answerSet == null ? row.get(row.size() - 6) : answerSet.getInvitationId() != null ? answerSet.getInvitationId() : "");
    if (survey.getIsOPC() && (meta || filter == null || filter.exported("user"))) {
        String suser = answerSet == null ? row.get(row.size() - 5) : answerSet.getResponderEmail();
        if (suser != null && suser.contains("@") && ECASUserLoginsByEmail != null && ECASUserLoginsByEmail.containsKey(suser)) {
            writer.writeAttribute("userlogin", ECASUserLoginsByEmail.get(suser));
        }
    }
    if (survey.getIsQuiz()) {
        writer.writeAttribute("totalscore", answerSet == null ? (row.get(row.size() - 1) != null ? row.get(row.size() - 1) : "0") : (answerSet.getScore() != null ? answerSet.getScore().toString() : "0"));
    }
    int answerrowcounter = 2;
    for (Element question : questions) {
        if ((filter == null || filter.exported(question.getId().toString())) && question.isUsedInResults()) {
            if (question instanceof Matrix) {
                Matrix matrix = (Matrix) question;
                for (Element matrixQuestion : matrix.getQuestions()) {
                    if (answerSet == null) {
                        String sanswers = row.get(answerrowcounter++);
                        if (sanswers != null) {
                            String[] answers = sanswers.split(";");
                            for (String answer : answers) {
                                if (answer.length() > 0) {
                                    writer.writeStartElement(ANSWER);
                                    writer.writeAttribute("aid", answer);
                                    writer.writeAttribute("qid", matrixQuestion.getUniqueId());
                                    // Answer
                                    writer.writeEndElement();
                                }
                            }
                        }
                    } else {
                        List<Answer> answers = answerSet.getAnswers(matrixQuestion.getId(), matrixQuestion.getUniqueId());
                        for (Answer answer : answers) {
                            writer.writeStartElement(ANSWER);
                            writer.writeAttribute("aid", answer.getPossibleAnswerUniqueId() != null ? answer.getPossibleAnswerUniqueId() : "");
                            writer.writeAttribute("qid", matrixQuestion.getUniqueId());
                            // Answer
                            writer.writeEndElement();
                        }
                    }
                }
            } else if (question instanceof RatingQuestion) {
                RatingQuestion rating = (RatingQuestion) question;
                for (Element childQuestion : rating.getQuestions()) {
                    if (answerSet == null) {
                        String sanswers = row.get(answerrowcounter++);
                        if (sanswers != null) {
                            writer.writeStartElement(ANSWER);
                            writer.writeAttribute("qid", childQuestion.getUniqueId());
                            writer.writeCharacters(sanswers);
                            // Answer
                            writer.writeEndElement();
                        }
                    } else {
                        List<Answer> answers = answerSet.getAnswers(childQuestion.getId(), childQuestion.getUniqueId());
                        if (!answers.isEmpty()) {
                            writer.writeStartElement(ANSWER);
                            writer.writeAttribute("qid", childQuestion.getUniqueId());
                            writer.writeCharacters(answers.get(0).getValue());
                            // Answer
                            writer.writeEndElement();
                        }
                    }
                }
            } else if (question instanceof Table) {
                Table table = (Table) question;
                for (int tableRow = 1; tableRow < table.getRows(); tableRow++) {
                    for (int tableCol = 1; tableCol < table.getColumns(); tableCol++) {
                        Element tq = table.getQuestions().get(tableRow - 1);
                        Element ta = table.getAnswers().get(tableCol - 1);
                        writer.writeStartElement(ANSWER);
                        writer.writeAttribute("qid", tq.getUniqueId());
                        writer.writeAttribute("aid", ta.getUniqueId());
                        if (answerSet == null) {
                            String sanswers = row.get(answerrowcounter++);
                            if (sanswers != null) {
                                writer.writeCharacters(sanswers);
                            }
                        } else {
                            String answer = answerSet.getTableAnswer(table, tableRow, tableCol, false);
                            if (answer != null && answer.length() > 0) {
                                writer.writeCharacters(answer);
                            }
                        }
                        // Answer
                        writer.writeEndElement();
                    }
                }
            } else if (question instanceof Text) {
            // ignore
            } else if (question instanceof Question) {
                if (answerSet == null) {
                    if (row.size() <= answerrowcounter) {
                        logger.error("no data for question " + question.getId() + " found");
                    } else {
                        String sanswers = row.get(answerrowcounter++);
                        if (sanswers != null) {
                            String[] answers;
                            if (question instanceof FreeTextQuestion || question instanceof RankingQuestion) {
                                answers = new String[1];
                                answers[0] = sanswers;
                            } else {
                                answers = sanswers.split(";");
                            }
                            for (String answer : answers) {
                                if (answer.length() > 0) {
                                    writer.writeStartElement(ANSWER);
                                    writer.writeAttribute("qid", question.getUniqueId());
                                    if (question instanceof ChoiceQuestion) {
                                        writer.writeAttribute("aid", answer);
                                    } else if (question instanceof Upload) {
                                        StringBuilder text = new StringBuilder();
                                        File file;
                                        try {
                                            if (answer.contains("|")) {
                                                answer = answer.substring(0, answer.indexOf('|'));
                                            }
                                            file = fileService.get(answer);
                                            if (!uploadedFilesByQuestionUID.containsKey(question.getUniqueId())) {
                                                uploadedFilesByQuestionUID.put(question.getUniqueId(), new ArrayList<>());
                                            }
                                            uploadedFilesByQuestionUID.get(question.getUniqueId()).add(file);
                                            text.append((text.length() > 0) ? ";" : "").append(file.getName());
                                        } catch (FileNotFoundException e) {
                                            logger.error(e.getLocalizedMessage(), e);
                                        }
                                        writer.writeCharacters(text.toString());
                                    } else {
                                        writer.writeCharacters(ConversionTools.removeInvalidHtmlEntities(answer));
                                    }
                                    // Answer
                                    writer.writeEndElement();
                                }
                            }
                        }
                    }
                } else {
                    List<Answer> answers = answerSet.getAnswers(question.getId(), question.getUniqueId());
                    for (Answer answer : answers) {
                        writer.writeStartElement(ANSWER);
                        writer.writeAttribute("qid", question.getUniqueId());
                        if (question instanceof ChoiceQuestion) {
                            writer.writeAttribute("aid", answer.getPossibleAnswerUniqueId() != null ? answer.getPossibleAnswerUniqueId() : "");
                        } else if (question instanceof RankingQuestion) {
                            writer.writeCharacters(answer.getValue());
                        } else if (question instanceof Upload) {
                            StringBuilder text = new StringBuilder();
                            if (filesByAnswer.containsKey(answer.getId())) {
                                for (File file : filesByAnswer.get(answer.getId())) {
                                    if (!uploadedFilesByQuestionUID.containsKey(question.getUniqueId())) {
                                        uploadedFilesByQuestionUID.put(question.getUniqueId(), new ArrayList<>());
                                    }
                                    uploadedFilesByQuestionUID.get(question.getUniqueId()).add(file);
                                    text.append((text.length() > 0) ? ";" : "").append(file.getName());
                                }
                            }
                            writer.writeCharacters(text.toString());
                        } else {
                            writer.writeCharacters(form.getAnswerTitleStripInvalidXML(answer));
                        }
                        // Answer
                        writer.writeEndElement();
                    }
                }
            }
            if (question.isDelphiElement() && filter != null && filter.explanationExported(question.getId().toString())) {
                final String questionUid = question.getUniqueId();
                String explanation = "";
                if (answerSet == null) {
                    final String cellContent = row.get(answerrowcounter++);
                    final int lastLineBreakPosition = cellContent.lastIndexOf("\n");
                    String filesPart = "";
                    if (lastLineBreakPosition == -1) {
                        explanation = cellContent;
                        if (explanation.contains("|")) {
                            filesPart = cellContent;
                            explanation = "";
                        }
                    } else {
                        explanation = cellContent.substring(0, lastLineBreakPosition);
                        filesPart = cellContent.substring(lastLineBreakPosition + 1);
                    }
                    final String[] filesParts = filesPart.split(";");
                    for (final String part : filesParts) {
                        if (part.contains("|")) {
                            final String fileUid = part.substring(0, part.indexOf("|"));
                            final String fileName = part.substring(part.indexOf("|") + 1);
                            final File file = new File();
                            file.setUid(fileUid);
                            file.setName(fileName);
                            explanationFilesToExport.addFile(questionUid, file);
                        }
                    }
                } else {
                    final int answerSetId = answerSet.getId();
                    if (explanations.containsKey(answerSetId) && explanations.get(answerSetId).containsKey(questionUid)) {
                        explanation = explanations.get(answerSetId).get(questionUid);
                    }
                    explanationFilesOfSurvey.getFiles(answerSetId, questionUid).forEach(file -> explanationFilesToExport.addFile(questionUid, file));
                }
                if (!explanation.isEmpty() || explanationFilesToExport.hasFiles()) {
                    writer.writeStartElement(EXPLANATION);
                    writer.writeAttribute("qid", questionUid);
                    if (!explanation.isEmpty()) {
                        writer.writeStartElement(EXPLANATION_TEXT);
                        writer.writeCharacters(ConversionTools.removeHTMLNoEscape(explanation));
                        // EXPLANATION_TEXT
                        writer.writeEndElement();
                    }
                    for (final File file : explanationFilesToExport.getFiles(questionUid)) {
                        writer.writeStartElement(EXPLANATION_FILE);
                        writer.writeCharacters(ConversionTools.removeHTMLNoEscape(file.getNameForExport()));
                        // EXPLANATION_FILE
                        writer.writeEndElement();
                    }
                    // EXPLANATION
                    writer.writeEndElement();
                }
            }
            if (question.isDelphiElement() && filter != null && filter.discussionExported(question.getId().toString())) {
                String discussion = "";
                if (answerSet == null) {
                    discussion = row.get(answerrowcounter++);
                } else {
                    try {
                        if (discussions.containsKey(answerSet.getId()) && discussions.get(answerSet.getId()).containsKey(question.getUniqueId())) {
                            discussion = discussions.get(answerSet.getId()).get(question.getUniqueId());
                        }
                    } catch (NoSuchElementException ex) {
                    // ignore
                    }
                }
                if (!discussion.isEmpty()) {
                    writer.writeStartElement(DISCUSSION);
                    writer.writeAttribute("qid", question.getUniqueId());
                    writer.writeCharacters(ConversionTools.removeHTMLNoEscape(discussion));
                    // Discussion
                    writer.writeEndElement();
                }
            }
        }
    }
    // AnswerSet
    writer.writeEndElement();
}


@Override
public void exportECFOrganizationalResults(){
    throw new NotImplementedException();
}


public void setExportedUniqueCodes(Map<Integer,String> exportedUniqueCodes){
    this.exportedUniqueCodes = exportedUniqueCodes;
}


@Transactional
public void simulateExportContent(boolean sync,Export export){
    exportedUniqueCodes.clear();
    Session session;
    session = sessionFactory.getCurrentSession();
    if (export != null) {
        session.evict(export.getSurvey());
    }
    exportedNow = new Date();
    List<List<String>> answersets = reportingService.getAnswerSets(form.getSurvey(), export == null ? null : export.getResultFilter(), null, false, true, true, true, true, false);
    if (answersets != null) {
        for (List<String> row : answersets) {
            int answerSetId = ConversionTools.getValue(row.get(1));
            String answerSetUniqueCode = row.get(0);
            if (!exportedUniqueCodes.containsKey(answerSetId)) {
                exportedUniqueCodes.put(answerSetId, answerSetUniqueCode);
            }
        }
    } else {
        HashMap<String, Object> values = new HashMap<>();
        String sql = "select ans.ANSWER_SET_ID, a.QUESTION_ID, a.QUESTION_UID, a.VALUE, a.ANSWER_COL, a.ANSWER_ID, a.ANSWER_ROW, a.PA_ID, a.PA_UID, ans.UNIQUECODE, ans.ANSWER_SET_DATE, ans.ANSWER_SET_UPDATE, ans.ANSWER_SET_INVID, ans.RESPONDER_EMAIL, ans.ANSWER_SET_LANG FROM ANSWERS a RIGHT JOIN ANSWERS_SET ans ON a.AS_ID = ans.ANSWER_SET_ID where ans.ANSWER_SET_ID IN (" + answerService.getSql(null, form.getSurvey().getId(), export == null ? null : export.getResultFilter(), values, true) + ") ORDER BY ans.ANSWER_SET_ID";
        SQLQuery query = session.createSQLQuery(sql);
        query.setReadOnly(true);
        for (Entry<String, Object> entry : values.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                query.setString(entry.getKey(), (String) value);
            } else if (value instanceof Integer) {
                query.setInteger(entry.getKey(), (Integer) value);
            } else if (value instanceof Date) {
                query.setTimestamp(entry.getKey(), (Date) value);
            }
        }
        query.setFetchSize(Integer.MIN_VALUE);
        ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
        while (results.next()) {
            Object[] a = results.get();
            if (!exportedUniqueCodes.containsKey(ConversionTools.getValue(a[0]))) {
                exportedUniqueCodes.put(ConversionTools.getValue(a[0]), (String) a[9]);
            }
        }
        results.close();
    }
}


@Override
public void exportTokens(){
    throw new NotImplementedException();
}


public Map<Integer,String> getExportedUniqueCodes(){
    return exportedUniqueCodes;
}


public String getNiceType(Element question){
    if (question instanceof Section) {
        return "Section";
    } else if (question instanceof FreeTextQuestion) {
        return "Free Text";
    } else if (question instanceof MultipleChoiceQuestion) {
        return "Multiple Choice";
    } else if (question instanceof SingleChoiceQuestion) {
        return "Single Choice";
    } else if (question instanceof PossibleAnswer) {
        return "Choice Answer";
    } else if (question instanceof NumberQuestion) {
        return "Number";
    } else if (question instanceof DateQuestion) {
        return "Date";
    } else if (question instanceof TimeQuestion) {
        return "Time";
    } else if (question instanceof Matrix) {
        return "Matrix";
    } else if (question instanceof Table) {
        return "Table";
    } else if (question instanceof Text) {
        return "Text";
    } else if (question instanceof Image) {
        return "Image";
    } else if (question instanceof Ruler) {
        return "Line";
    } else if (question instanceof Upload) {
        return "File Upload";
    } else if (question instanceof Download) {
        return "File Download";
    } else if (question instanceof EmailQuestion) {
        return "E-mail";
    } else if (question instanceof RegExQuestion) {
        return "Regular Expression";
    } else if (question instanceof GalleryQuestion) {
        return "Gallery";
    } else if (question instanceof Confirmation) {
        return "Confirmation";
    } else if (question instanceof RatingQuestion) {
        return "Rating";
    } else if (question instanceof RankingQuestion) {
        return "Ranking";
    } else if (question instanceof RankingItem) {
        return "Ranking Item";
    }
    return question.getType();
}


@Override
public void exportStatistics(){
    throw new NotImplementedException();
}


}