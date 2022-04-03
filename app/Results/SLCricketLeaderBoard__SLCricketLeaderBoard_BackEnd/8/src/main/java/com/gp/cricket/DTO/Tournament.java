package com.gp.cricket.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
public class Tournament {

 private  Integer tournamentId;

 private  String tournamentName;

 private  Date startDate;

 private  Date endDate;

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


public void setTournamentName(String tournamentName){
    this.tournamentName = tournamentName;
}


public Date getRegistartionCloseDate(){
    return registartionCloseDate;
}


public String getTournamentName(){
    return tournamentName;
}


public Date getEndDate(){
    return endDate;
}


@Override
public String toString(){
    return "Tournament [tournamentId=" + tournamentId + ", tournamentName=" + tournamentName + ", startDate=" + startDate + ", endDate=" + endDate + ", registartionCloseDate=" + registartionCloseDate + "]";
}


public Integer getTournamentId(){
    return tournamentId;
}


}