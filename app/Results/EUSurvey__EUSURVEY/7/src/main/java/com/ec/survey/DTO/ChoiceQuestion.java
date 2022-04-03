package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence;
import java.text.Collator;
import java.util;
public class ChoiceQuestion extends Question{

 private  long serialVersionUID;

 private  List<PossibleAnswer> possibleAnswers;

 private  List<PossibleAnswer> missingPossibleAnswers;

 private  Integer order;

 private  boolean foreditor;

 private  Collection<PossibleAnswer> orderedPossibleAnswers;

public ChoiceQuestion() {
}public ChoiceQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
@Transient
public List<PossibleAnswer> getMissingPossibleAnswers(){
    return missingPossibleAnswers;
}


@OneToMany(targetEntity = PossibleAnswer.class, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SELECT)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@OrderBy(value = "position asc")
public List<PossibleAnswer> getPossibleAnswers(){
    return possibleAnswers;
}


@Transient
public Collection<PossibleAnswer> getOrderedPossibleAnswers(){
    if (foreditor)
        return possibleAnswers;
    if (orderedPossibleAnswers != null)
        return orderedPossibleAnswers;
    if (order != null && order == 1) {
        final Collator instance = Collator.getInstance();
        Map<String, PossibleAnswer> answers = new TreeMap<>(instance);
        for (PossibleAnswer answer : possibleAnswers) {
            answers.put(answer.getStrippedTitleNoEscape(), answer);
        }
        orderedPossibleAnswers = sortByColumn(new ArrayList<>(Arrays.asList(answers.values().toArray(new PossibleAnswer[0]))));
    } else if (order != null && order == 2) {
        List<PossibleAnswer> answers = possibleAnswers;
        Collections.shuffle(answers);
        orderedPossibleAnswers = sortByColumn(new ArrayList<>(Arrays.asList(answers.toArray(new PossibleAnswer[0]))));
    } else {
        orderedPossibleAnswers = sortByColumn(possibleAnswers);
    }
    return orderedPossibleAnswers;
}


@Transient
public PossibleAnswer getPossibleAnswerByUniqueId(String uid){
    for (PossibleAnswer possibleAnswer : getAllPossibleAnswers()) {
        if (possibleAnswer.getUniqueId() != null && possibleAnswer.getUniqueId().length() > 0 && possibleAnswer.getUniqueId().equals(uid)) {
            return possibleAnswer;
        }
    }
    return null;
}


@Transient
public List<PossibleAnswer> getAllPossibleAnswers(){
    if (!missingPossibleAnswers.isEmpty()) {
        List<PossibleAnswer> result = new ArrayList<>();
        for (PossibleAnswer pa : missingPossibleAnswers) {
            if (!result.contains(pa)) {
                result.add(pa);
            }
        }
        for (PossibleAnswer pa : possibleAnswers) {
            if (!result.contains(pa)) {
                result.add(pa);
            }
        }
        result.sort(Survey.newElementByPositionComparator());
        return result;
    } else {
        return possibleAnswers;
    }
}


@Transient
public PossibleAnswer getPossibleAnswer(int id){
    for (PossibleAnswer possibleAnswer : getAllPossibleAnswers()) {
        if (possibleAnswer.getId() == id) {
            return possibleAnswer;
        }
    }
    return null;
}


@Column(name = "CHOICEORDER")
public Integer getOrder(){
    return order;
}


@Transient
public Collection<Collection<PossibleAnswer>> getOrderedPossibleAnswersByRows(){
    Collection<PossibleAnswer> answers = getOrderedPossibleAnswers();
    Collection<Collection<PossibleAnswer>> result = new ArrayList<>();
    Collection<PossibleAnswer> currentRow = new ArrayList<>();
    result.add(currentRow);
    int columns = 1;
    if (this instanceof SingleChoiceQuestion) {
        columns = ((SingleChoiceQuestion) this).getNumColumns();
    } else if (this instanceof MultipleChoiceQuestion) {
        columns = ((MultipleChoiceQuestion) this).getNumColumns();
    }
    for (PossibleAnswer answer : answers) {
        if (currentRow.size() >= columns) {
            currentRow = new ArrayList<>();
            result.add(currentRow);
        }
        currentRow.add(answer);
    }
    return result;
}


}