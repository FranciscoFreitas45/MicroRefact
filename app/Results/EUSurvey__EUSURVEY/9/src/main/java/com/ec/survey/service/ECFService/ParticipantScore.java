package com.ec.survey.service.ECFService;
 import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ec.survey.exception.BadRequestException;
import com.ec.survey.exception.ECFException;
import com.ec.survey.exception.httpexception.NotFoundException;
import com.ec.survey.model.Answer;
import com.ec.survey.model.AnswerSet;
import com.ec.survey.model.ECFCluster;
import com.ec.survey.model.ECFCompetency;
import com.ec.survey.model.ECFExpectedScore;
import com.ec.survey.model.ECFExpectedScoreToProfileEid;
import com.ec.survey.model.ECFProfile;
import com.ec.survey.model.ECFType;
import com.ec.survey.model.ResultFilter;
import com.ec.survey.model.ResultFilter.ResultFilterOrderBy;
import com.ec.survey.model.SqlPagination;
import com.ec.survey.model.survey.ChoiceQuestion;
import com.ec.survey.model.survey.Element;
import com.ec.survey.model.survey.PossibleAnswer;
import com.ec.survey.model.survey.Question;
import com.ec.survey.model.survey.SingleChoiceQuestion;
import com.ec.survey.model.survey.Survey;
import com.ec.survey.model.survey.ecf.ECFGlobalCompetencyResult;
import com.ec.survey.model.survey.ecf.ECFGlobalResult;
import com.ec.survey.model.survey.ecf.ECFGlobalTotalResult;
import com.ec.survey.model.survey.ecf.ECFIndividualCompetencyResult;
import com.ec.survey.model.survey.ecf.ECFIndividualResult;
import com.ec.survey.model.survey.ecf.ECFOrganizationalCompetencyResult;
import com.ec.survey.model.survey.ecf.ECFOrganizationalResult;
import com.ec.survey.model.survey.ecf.ECFProfileCompetencyResult;
import com.ec.survey.model.survey.ecf.ECFProfileResult;
import com.ec.survey.model.survey.ecf.ECFProfileSummaryResult;
import com.ec.survey.model.survey.ecf.ECFSummaryResult;
import com.ec.survey.model.survey.ecf.TypeUUIDAndName;
import com.google.common.primitives.Ints;
public class ParticipantScore {

 private  Integer score;

 private  String name;

 private  String contributionUUID;

public ParticipantScore() {
    super();
}public ParticipantScore(String name) {
    super();
    this.name = name;
}public ParticipantScore(Integer score, String name) {
    super();
    this.score = score;
    this.name = name;
}public ParticipantScore(Integer score, String name, String contributionUUID) {
    super();
    this.score = score;
    this.name = name;
    this.contributionUUID = contributionUUID;
}
public void setName(String name){
    this.name = name;
}


public String getContributionUUID(){
    return contributionUUID;
}


public String getName(){
    return name;
}


public Integer getScore(){
    return score;
}


public void setContributionUUID(String contributionUUID){
    this.contributionUUID = contributionUUID;
}


public void setScore(Integer score){
    this.score = score;
}


}