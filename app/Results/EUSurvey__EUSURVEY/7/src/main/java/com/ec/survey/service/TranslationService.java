package com.ec.survey.service;
 import com.ec.survey.model.Translation;
import com.ec.survey.model.Translations;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.tools.TranslationsHelper;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import com.ec.survey.DTO.Survey;
@Service("translationService")
public class TranslationService extends BasicService{


@Transactional
public void add(Translations translation,boolean evict){
    Session session = sessionFactory.getCurrentSession();
    session.save(translation);
    if (evict)
        session.evict(translation);
}


@Transactional(readOnly = true)
public List<Translations> getActiveTranslationsForSurvey(int surveyId){
    List<Translations> result = new ArrayList<>();
    Session session = sessionFactory.getCurrentSession();
    Survey survey = (Survey) session.get(Survey.class, surveyId);
    if (survey == null)
        return result;
    Query query = session.createQuery("SELECT t.id FROM Translations t WHERE t.surveyId = :surveyId AND t.active = true ORDER BY t.language asc");
    query.setParameter("surveyId", surveyId);
    // Retrieve all
    @SuppressWarnings("unchecked")
    List<Integer> list = query.list();
    for (Integer id : list) {
        Translations translations = (Translations) session.get(Translations.class, id);
        if (!translations.getComplete()) {
            translations.setComplete(TranslationsHelper.isComplete(translations, survey));
        }
        result.add(translations);
    }
    return result;
}


@Transactional(readOnly = false)
public boolean deleteTranslations(int id){
    Session session = sessionFactory.getCurrentSession();
    Translations translations = (Translations) session.get(Translations.class, id);
    if (translations == null)
        return false;
    session.delete(translations);
    return true;
}


@Transactional(readOnly = false)
public void save(Translations translations){
    Session session = sessionFactory.getCurrentSession();
    for (Translation translation : translations.getTranslations()) {
        if (translation.getLabel() == null)
            translation.setLabel("");
    }
    session.saveOrUpdate(translations);
    try {
        java.io.File target = fileService.getSurveyPDFFile(translations.getSurveyUid(), translations.getSurveyId(), translations.getLanguage().getCode());
        Files.deleteIfExists(target.toPath());
    } catch (Exception e) {
        logger.error(e.getLocalizedMessage(), e);
    }
}


@Transactional
public void update(Translation translation){
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(translation);
}


@Transactional(readOnly = true)
public List<String> getTranslationLanguagesForSurvey(Integer surveyId,boolean onlyActive){
    Session session = sessionFactory.getCurrentSession();
    String squery = "Select DISTINCT t.language.code FROM Translations t WHERE t.surveyId = :surveyId";
    if (onlyActive)
        squery += " AND t.active = true";
    Query query = session.createQuery(squery).setCacheable(true);
    query.setParameter("surveyId", surveyId);
    @SuppressWarnings("unchecked")
    List<String> list = query.list();
    return list;
}


@Transactional(readOnly = true)
public Translations getTranslations(int id){
    Session session = sessionFactory.getCurrentSession();
    return (Translations) session.get(Translations.class, id);
}


@Transactional(readOnly = true)
public List<Translations> getTranslationsForSurvey(int surveyId,boolean useEagerLoading,boolean checkComplete,boolean loadTranslationTitles){
    List<Translations> result = new ArrayList<>();
    Session session = sessionFactory.getCurrentSession();
    Survey survey = (Survey) session.get(Survey.class, surveyId);
    if (survey == null)
        return result;
    if (!survey.getIsDraft()) {
        session.setReadOnly(survey, true);
    }
    Query query = session.createQuery("SELECT t.id FROM Translations t WHERE t.surveyId = :surveyId ORDER BY t.language asc");
    query.setParameter("surveyId", surveyId);
    // Retrieve all
    @SuppressWarnings("unchecked")
    List<Integer> list = query.list();
    for (Integer id : list) {
        Translations translations = (Translations) session.get(Translations.class, id);
        if (translations != null) {
            if (useEagerLoading) {
                Hibernate.initialize(translations.getTranslations());
            }
            if (loadTranslationTitles && translations.getTranslationsByKey().containsKey("TITLE")) {
                translations.setTitle(translations.getTranslationsByKey().get("TITLE").getLabel());
            }
            if (checkComplete) {
                translations.setComplete(TranslationsHelper.isComplete(translations, survey));
            }
            if (translations.getLanguage().getCode().equalsIgnoreCase(survey.getLanguage().getCode())) {
                result.add(0, translations);
            } else {
                result.add(translations);
            }
        } else {
            logger.error("no Translations item with id " + id + " found!");
        }
    }
    return result;
}


}