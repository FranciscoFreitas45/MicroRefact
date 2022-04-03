package com.gp.cricket.service;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp.cricket.config.security.JwtInMemoryUserDetailsService;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.Sponsor;
import com.gp.cricket.entity.SponsorClub;
import com.gp.cricket.entity.User;
import com.gp.cricket.repository.ClubRepository;
import com.gp.cricket.repository.SponsorClubRepository;
import com.gp.cricket.repository.SponsorRepository;
import com.gp.cricket.repository.UserRepository;
import com.gp.cricket.Interface.UserRepository;
import com.gp.cricket.Interface.UserService;
import com.gp.cricket.Interface.JwtInMemoryUserDetailsService;
import com.gp.cricket.Interface.ClubRepository;
@Service
public class SponsorService {

@Autowired
 private SponsorRepository sponsorRepository;

@Autowired
 private UserRepository userRepository;

@Autowired
 private UserService userService;

@Autowired
 private SponsorClubRepository sponsorClubRepository;

@Autowired
 private JwtInMemoryUserDetailsService jwtUser;

@Autowired
 private ClubRepository clubRepository;


public List<Sponsor> getregsponsors(){
    System.out.println("Get all registered sponsors here");
    return this.sponsorRepository.getregsponsors();
}


public List<Sponsor> getNonregsponsors(){
    System.out.println("Get all non registered sponsors here");
    return this.sponsorRepository.getNonregsponsors();
}


public Boolean sponsorClubRequest(Integer userId,Integer clubId){
    System.out.println(userId);
    System.out.println(clubId);
    // Sponsor s = new Sponsor();
    // s=sponsorRepository.findSponsorByUserId(userId);
    // if(s==null) {System.out.println("NULL");}
    // System.out.println(s.getUserId());
    SponsorClub sponsor = new SponsorClub();
    sponsor.setStatus(0);
    sponsor.setClubId(clubRepository.findClubByClubId(clubId));
    sponsor.setSponsorId(sponsorRepository.findSponsorByUserId(userId));
    sponsorClubRepository.save(sponsor);
    return true;
}


public Integer sponsorAccept(Integer userId){
    if (userId != null && userId > 0 && userRepository.existsById(userId)) {
        User user = userRepository.findByUserId(userId);
        user.setStatus((byte) 1);
        userRepository.save(user);
        return 1;
    }
    return null;
}


}