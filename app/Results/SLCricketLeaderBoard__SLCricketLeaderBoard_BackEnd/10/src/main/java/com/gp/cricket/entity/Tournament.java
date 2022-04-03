package com.gp.cricket.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "tournament")
public class Tournament {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "tournament_id")
 private  Integer tournamentId;

@NotBlank(message = "Tournament name is mandatory")
@Column(name = "tournament_name")
 private  String tournamentName;

@NotBlank(message = "Start Date is mandatory")
@Column(name = "start_date")
 private  Date startDate;

@NotBlank(message = "Enad Date is mandatory")
@Column(name = "end_date")
 private  Date endDate;

@NotBlank(message = "closing Date is mandatory")
@Column(name = "registration_closing_date")
 private  Date registartionCloseDate;

public Tournament() {
    super();
// TODO Auto-generated constructor stub
}public Tournament(Integer tournamentId, @NotBlank(message = "Tournament name is mandatory") String tournamentName, @NotBlank(message = "Start Date is mandatory") Date startDate, @NotBlank(message = "Enad Date is mandatory") Date endDate, @NotBlank(message = "closing Date is mandatory") Date registartionCloseDate) {
    super();
    this.tournamentId = tournamentId;
    this.tournamentName = tournamentName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.registartionCloseDate = registartionCloseDate;
}
public Date getStartDate(){
    return startDate;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}


public void setTournamentName(String tournamentName){
    this.tournamentName = tournamentName;
}


public Date getRegistartionCloseDate(){
    return registartionCloseDate;
}


public Date cuurentDate(){
    Date curentDate = new Date();
    return curentDate;
}


public String getTournamentName(){
    return tournamentName;
}


public void setTournamentId(Integer tournamentId){
    this.tournamentId = tournamentId;
}


public Date getEndDate(){
    return endDate;
}


public void setRegistartionCloseDate(Date registartionCloseDate){
    this.registartionCloseDate = registartionCloseDate;
}


@Override
public String toString(){
    return "Tournament [tournamentId=" + tournamentId + ", tournamentName=" + tournamentName + ", startDate=" + startDate + ", endDate=" + endDate + ", registartionCloseDate=" + registartionCloseDate + "]";
}


public Integer getTournamentId(){
    return tournamentId;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


}