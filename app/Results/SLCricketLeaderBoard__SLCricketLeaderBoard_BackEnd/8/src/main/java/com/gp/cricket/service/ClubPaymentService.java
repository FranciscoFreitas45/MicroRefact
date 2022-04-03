package com.gp.cricket.service;
 import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.ClubPayment;
import com.gp.cricket.mapobject.PaymentEmailBody;
import com.gp.cricket.repository.ClubPaymentRepository;
import com.gp.cricket.repository.ClubRepository;
@Service
public class ClubPaymentService {

@Autowired
 private ClubPaymentRepository clubPaymentRepository;

@Autowired
 private ClubRepository clubRepository;

@Autowired
 private EmailService emailService;

@Value("${spring.mail.username}")
 private  String username;


public Integer getLocalCurrentYear(){
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Colombo"));
    Date currentDate = calendar.getTime();
    LocalDate today = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return today.getYear();
}


public Boolean clubPaymentObjectValidated(ClubPayment object){
    if (object != null && object.getAmount() != null && object.getDate() != null && object.getPaymentForYear() != null && object.getClubId() != null && object.getDate() != null) {
        if (object.getClubId().getClubId() != null && object.getClubId().getClubId() > 0 && clubRepository.existsById(object.getClubId().getClubId())) {
            return true;
        }
    }
    return false;
}


public List<ClubPayment> getClubpaymentData(Integer clubId){
    if (clubId != null && clubRepository.existsById(clubId)) {
        List<ClubPayment> clubPaymentList = new ArrayList<ClubPayment>();
        clubPaymentList = clubPaymentRepository.findByClubId(clubRepository.findClubByClubId(clubId));
        return clubPaymentList;
    }
    return null;
}


public Integer addClubPayment(ClubPayment clubPayment){
    if (clubPaymentObjectValidated(clubPayment)) {
        // check whether already for this year or not
        ClubPayment object = clubPaymentRepository.findClubPaymentByClubIdAndYear(clubPayment.getClubId(), clubPayment.getPaymentForYear());
        if (object == null) {
            Boolean isClubStatusChanged = false;
            // check club is deactivate.If deactivate if payment for currentYear then activate club
            Integer currentYear = getLocalCurrentYear();
            Integer differenceOfYear = clubPayment.getPaymentForYear() - currentYear;
            if (differenceOfYear == 0) {
                if (clubPayment.getClubId().getStatus() == 0) {
                    Club club = clubPayment.getClubId();
                    club.setStatus((byte) 1);
                    clubRepository.save(club);
                    isClubStatusChanged = true;
                }
            }
            clubPaymentRepository.save(clubPayment);
            // send email
            PaymentEmailBody paymentEmailBody = new PaymentEmailBody();
            paymentEmailBody.setClub(clubPayment.getClubId().getClubName());
            paymentEmailBody.setYear(clubPayment.getPaymentForYear());
            paymentEmailBody.setAmount(clubPayment.amount);
            paymentEmailBody.setDate(clubPayment.getDate());
            emailService.setUpEmailInstance(username, clubPayment.getClubId().getEmail(), "CrickDom Annuual Payment", paymentEmailBody);
            if (isClubStatusChanged) {
                // payment success and club activated
                return 2;
            }
            // payment success
            return 1;
        }
        // Already pay payment
        return 0;
    }
    return null;
}


}