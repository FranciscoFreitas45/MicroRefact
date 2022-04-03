package com.github.haseoo.courier.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.haseoo.courier.repositories.jpa.ParcelStateJPARepository;
import com.github.haseoo.courier.models.ParcelStateRecord;
@Service
public class ParcelStateRecordMagazineModelService {

@Autowired
 private ParcelStateJPARepository parcelstatejparepository;


}