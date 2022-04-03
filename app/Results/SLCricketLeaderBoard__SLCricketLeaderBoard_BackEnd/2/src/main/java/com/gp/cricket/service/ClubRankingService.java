package com.gp.cricket.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.ClubRanking;
import com.gp.cricket.entity.Match;
import com.gp.cricket.entity.MatchType;
import com.gp.cricket.repository.ClubRankingRepository;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.MatchRepository;
import com.gp.cricket.repository.MatchTypeRepository;
import com.gp.cricket.Interface.ClubRepository;
@Service
public class ClubRankingService {

@Autowired
 private ClubRepository clubRepository;

@Autowired
 private ClubRankingRepository clubRankingRepository;

@Autowired
 private MatchRepository matchRepository;

@Autowired
 private MatchTypeRepository matchTypeRepository;

 private ClubRanking strongerTeam;

 private ClubRanking weakTeam;


public void createRankingObject(Club club){
    List<MatchType> matchTypeList = matchTypeRepository.findAll();
    for (MatchType matchType : matchTypeList) {
        ClubRanking ob = new ClubRanking();
        ob.setClubId(club);
        ob.setMatchTypeId(matchType);
        ob.setNumMatch(0);
        ob.setRating(0D);
        ob.setPoints(0D);
        clubRankingRepository.save(ob);
    }
}


public List<ClubRanking> getClubRanking(Integer matchType){
    return clubRankingRepository.findByMatchType(matchType);
}


public void clubRanking(Match match){
    if (match != null && clubRepository.existsById(match.getClubOneId()) && clubRepository.existsById(match.getClubTwoId())) {
        categoryTeam(match);
        Integer matchType = match.getMatchTypeId().getMatchTypeId();
        if (matchType == 1 || matchType == 3) {
            // ODI,T20 Match
            odiANDT20MatchRanking(match);
        } else {
        // Test
        }
    }
}


public void categoryTeam(Match match){
    Integer club1 = match.getClubOneId();
    Integer club2 = match.getClubTwoId();
    MatchType matchType = match.getMatchTypeId();
    ClubRanking tem1 = getClubRankingofClub(club1, matchType);
    ClubRanking tem2 = getClubRankingofClub(club2, matchType);
    if (tem1.getRating() >= tem2.getRating()) {
        strongerTeam = tem1;
        weakTeam = tem2;
    } else {
        strongerTeam = tem2;
        weakTeam = tem1;
    }
}


public void odiANDT20MatchRanking(Match match){
    Double strongerTeamInitialRating = strongerTeam.getRating();
    Double weakTeamInitialRating = weakTeam.getRating();
    Double strongerTeamInitialPoints = strongerTeam.getPoints();
    Double weakTeamInitialPoints = weakTeam.getPoints();
    Integer strongerTeamNumMatch = strongerTeam.getNumMatch();
    Integer weakTeamNumMatch = weakTeam.getNumMatch();
    Double ratingDifference = Math.abs(strongerTeamInitialRating - weakTeamInitialRating);
    if (ratingDifference >= 40) {
        if (strongerTeam.getClubId().getClubId() == match.getWinTeamId()) {
            // Stronger Team win
            strongerTeam.setPoints(strongerTeamInitialPoints + (strongerTeamInitialRating + 10));
            strongerTeam.setRating(strongerTeam.getPoints() / strongerTeamNumMatch);
            weakTeam.setPoints(weakTeamInitialPoints + (weakTeamInitialRating - 10));
            weakTeam.setRating(weakTeam.getPoints() / weakTeamNumMatch);
        } else if (weakTeam.getClubId().getClubId() == match.getWinTeamId()) {
            // Weak Team win
            strongerTeam.setPoints(strongerTeamInitialPoints + (strongerTeamInitialRating - 90));
            strongerTeam.setRating(strongerTeam.getPoints() / strongerTeamNumMatch);
            weakTeam.setPoints(weakTeamInitialPoints + (weakTeamInitialRating + 90));
            weakTeam.setRating(weakTeam.getPoints() / weakTeamNumMatch);
        } else {
            // Match grow
            strongerTeam.setPoints(strongerTeamInitialPoints + (strongerTeamInitialRating - 40));
            strongerTeam.setRating(strongerTeam.getPoints() / strongerTeamNumMatch);
            weakTeam.setPoints(weakTeamInitialPoints + (weakTeamInitialRating + 40));
            weakTeam.setRating(weakTeam.getPoints() / weakTeamNumMatch);
        }
    } else {
        // ratingDifference<40
        if (strongerTeam.getClubId().getClubId() == match.getWinTeamId()) {
            // Stronger team win
            strongerTeam.setPoints(strongerTeamInitialPoints + (weakTeamInitialRating + 50));
            strongerTeam.setRating(strongerTeam.getPoints() / strongerTeamNumMatch);
            weakTeam.setPoints(weakTeamInitialPoints + (strongerTeamInitialRating - 50));
            weakTeam.setRating(weakTeam.getPoints() / weakTeamNumMatch);
        } else if (weakTeam.getClubId().getClubId() == match.getWinTeamId()) {
            // weak team win
            weakTeam.setPoints(weakTeamInitialPoints + (strongerTeamInitialRating + 50));
            weakTeam.setRating(weakTeam.getPoints() / weakTeamNumMatch);
            strongerTeam.setPoints(strongerTeamInitialPoints + (weakTeamInitialRating - 50));
            strongerTeam.setRating(strongerTeam.getPoints() / strongerTeamNumMatch);
        } else {
            // grow
            strongerTeam.setPoints(strongerTeamInitialPoints + weakTeamInitialRating);
            strongerTeam.setRating(strongerTeam.getPoints() / strongerTeamNumMatch);
            weakTeam.setPoints(weakTeamInitialPoints + strongerTeamInitialRating);
            weakTeam.setRating(weakTeam.getPoints() / weakTeamNumMatch);
        }
    }
    clubRankingRepository.save(strongerTeam);
    clubRankingRepository.save(weakTeam);
}


public ClubRanking getClubRankingofClub(Integer clubId,MatchType matchType){
    ClubRanking ob = clubRankingRepository.findByClubIdANDMatchType(clubId, matchType.getMatchTypeId());
    if (ob != null) {
        ob.setNumMatch(ob.getNumMatch() + 1);
        return ob;
    }
    ob = new ClubRanking();
    ob.setClubId(clubRepository.findClubByClubId(clubId));
    ob.setMatchTypeId(matchType);
    ob.setPoints(0D);
    ob.setRating(0D);
    ob.setNumMatch(1);
    return ob;
}


}