package com.gp.cricket.service;
 import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.ClubPayment;
import com.gp.cricket.entity.Match;
import com.gp.cricket.entity.Tournament;
import com.gp.cricket.report.entity.ClubPaymentReport;
import com.gp.cricket.report.entity.ClubReport;
import com.gp.cricket.report.entity.TournamentMatchFutureReport;
import com.gp.cricket.report.entity.TournamentMatchPastReport;
import com.gp.cricket.report.entity.TournamentMatchReport;
import com.gp.cricket.report.entity.TournamentReport;
import com.gp.cricket.repository.ClubPaymentRepository;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.MatchRepository;
import com.gp.cricket.repository.PlayerRepository;
import com.gp.cricket.repository.TournamentRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.gp.cricket.Interface.PlayerRepository;
import com.gp.cricket.Interface.TournamentRepository;
import com.gp.cricket.Interface.MatchRepository;
@Service
public class ReportService {

@Autowired
 private ClubRepository clubRepository;

@Autowired
 private PlayerRepository playerRepository;

@Autowired
 private ClubPaymentRepository clubPaymentRepository;

@Autowired
 private TournamentRepository tournamentRepository;

@Autowired
 private MatchRepository matchRepository;

 private  String path;

 private String filePath;


public String getTournamentReport(String reportFormat,Integer type){
    String fileName = "";
    Integer count = 1;
    List<TournamentReport> tournamentReportData = new ArrayList<TournamentReport>();
    List<Tournament> tournamentData = new ArrayList<Tournament>();
    if (type == 1) {
        fileName = "Past_Tournament_Report_" + getCurrentDate() + ".pdf";
        tournamentData = tournamentRepository.findPastTournament(new Date());
    } else if (type == 2) {
        fileName = "Upcoming_Tournament_Report_" + getCurrentDate() + ".pdf";
        tournamentData = tournamentRepository.findUpcomingTournament(new Date());
    } else if (type == 3) {
        fileName = "Ongoing_Tournament_Report_" + getCurrentDate() + ".pdf";
        tournamentData = tournamentRepository.findOnGoingTournament(new Date());
    }
    for (Tournament tournament : tournamentData) {
        TournamentReport ob = new TournamentReport();
        ob.setNum(count);
        ob.setTournamentName(tournament.getTournamentName());
        ob.setStartDate(tournament.getStartDate().toString().substring(0, 10));
        ob.setFinishDate(tournament.getEndDate().toString().substring(0, 10));
        tournamentReportData.add(ob);
        count++;
    }
    if (tournamentReportData.size() == 0) {
        TournamentReport ob = new TournamentReport();
        ob.setNum(0);
        ob.setTournamentName("");
        ob.setStartDate("");
        ob.setFinishDate("");
        tournamentReportData.add(ob);
    }
    // Load the file and compiler it
    File file = ResourceUtils.getFile("classpath:tournamentReport.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tournamentReportData);
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("Created by", "Nuwan");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    if (reportFormat.equalsIgnoreCase("pdf")) {
        filePath = path + fileName;
        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
    }
    return fileName;
}


public String getClubReport(String reportFormat){
    List<ClubReport> clubReportData = new ArrayList<ClubReport>();
    List<Club> clubList = clubRepository.findAll();
    String fileName = "";
    for (Club club : clubList) {
        ClubReport ob = new ClubReport();
        ob.setClubId(club.getClubId());
        ob.setClubName(club.getClubName());
        ob.setManager(club.getManagerId().getUserId().getNameWithInitial());
        ob.setNumPlayer(getNumerofPlayers(club));
        ob.setRegDate(club.getRegDate().toString());
        clubReportData.add(ob);
    }
    // Load the file and compiler it
    File file = ResourceUtils.getFile("classpath:clubFormat.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clubReportData);
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("Created by", "Nuwan");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    if (reportFormat.equalsIgnoreCase("pdf")) {
        fileName = "Club_Report_" + getCurrentDate() + ".pdf";
        filePath = path + fileName;
        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
    }
    return fileName;
}


public Integer getNumerofPlayers(Club club){
    Integer numPlayer = 0;
    numPlayer = playerRepository.getNumofPlayerinClub(club);
    return numPlayer;
}


public String getCurrentDate(){
    LocalDate today = LocalDate.now();
    return today.toString();
}


public String getTournamentMatchFuture(String reportFormat,Integer tournamentId,Integer type){
    String fileName = "";
    Integer count = 1;
    List<Match> matchData = new ArrayList<Match>();
    List<TournamentMatchFutureReport> tournamentMatchFutureReportData = new ArrayList<TournamentMatchFutureReport>();
    for (Match match : matchData) {
        TournamentMatchFutureReport ob = new TournamentMatchFutureReport();
        ob.setNo(count);
        ob.setClub1(clubRepository.findClubByClubId(match.getClubOneId()).getClubName());
        ob.setClub2(clubRepository.findClubByClubId(match.getClubTwoId()).getClubName());
        ob.setDate(match.getStartDate().toString() + " : " + match.getFinishDate());
        ob.setStadium(match.getStadiumId().getName());
        ob.setType(match.getMatchTypeId().getMatchType());
        ob.setTime(match.getStartTime().toString());
        tournamentMatchFutureReportData.add(ob);
        count++;
    }
    // Load the file and compiler it
    File file = ResourceUtils.getFile("classpath:tournamentMatchFuture.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tournamentMatchFutureReportData);
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("Created by", "Nuwan");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    if (reportFormat.equalsIgnoreCase("pdf")) {
        if (type == 2) {
            fileName = "UpComing_Tournament_Match_Report_" + getCurrentDate() + ".pdf";
        } else if (type == 3) {
            fileName = "OnGoing_Tournament_Match_Report_" + getCurrentDate() + ".pdf";
        }
        filePath = path + fileName;
        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
    }
    return fileName;
}


public String getTournamentMatchPast(String reportFormat,Integer tournamentId){
    String fileName = "";
    List<Match> matchData = matchRepository.findBytournamentId(tournamentId);
    List<TournamentMatchPastReport> tournamentMatchReportData = new ArrayList<TournamentMatchPastReport>();
    for (Match match : matchData) {
        TournamentMatchPastReport ob = new TournamentMatchPastReport();
        ob.setClub1(clubRepository.findClubByClubId(match.getClubOneId()).getClubName());
        ob.setClub2(clubRepository.findClubByClubId(match.getClubTwoId()).getClubName());
        ob.setDate(match.getStartDate().toString() + " : " + match.getFinishDate());
        ob.setStadium(match.getStadiumId().getName());
        ob.setType(match.getMatchTypeId().getMatchType());
        ob.setWinClub(clubRepository.findClubByClubId(match.getWinTeamId()).getClubName());
        tournamentMatchReportData.add(ob);
    }
    // Load the file and compiler it
    File file = ResourceUtils.getFile("classpath:tournamentMatchPast.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tournamentMatchReportData);
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("Created by", "Nuwan");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    if (reportFormat.equalsIgnoreCase("pdf")) {
        fileName = "Past_Tournament_Match_Report_" + getCurrentDate() + ".pdf";
        filePath = path + fileName;
        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
    }
    return fileName;
}


public String getClubPaymentReport(String reportFormat,Integer year){
    String fileName = "";
    List<Club> clubData = new ArrayList<Club>();
    List<ClubPaymentReport> clubPaymentReportData = new ArrayList<ClubPaymentReport>();
    Double total = 0D;
    clubData = clubRepository.findAll();
    for (Club club : clubData) {
        ClubPaymentReport ob = new ClubPaymentReport();
        ob.setClubId(club.getClubId());
        ob.setClubName(club.getClubName());
        ob.setManager(club.getManagerId().getUserId().getNameWithInitial());
        ClubPayment paymentOb = clubPaymentRepository.findByClubIdAndYear(club, year);
        if (paymentOb != null && paymentOb.getClubPaymentId() > 0) {
            ob.setAmount(paymentOb.getAmount());
            total = total + paymentOb.getAmount();
        } else {
            ob.setAmount(0D);
        }
        clubPaymentReportData.add(ob);
    }
    ClubPaymentReport ob = new ClubPaymentReport();
    ob.setClubName("Total");
    ob.setAmount(total);
    ob.setClubId(0);
    ob.setManager("");
    clubPaymentReportData.add(ob);
    // Load the file and compiler it
    File file = ResourceUtils.getFile("classpath:clubPaymentReportFormat.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clubPaymentReportData);
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("Created by", "Nuwan");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    if (reportFormat.equalsIgnoreCase("pdf")) {
        fileName = "Club_Payment_Report_" + getCurrentDate() + ".pdf";
        filePath = path + fileName;
        JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
    }
    return fileName;
}


}