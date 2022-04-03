package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeAddressController {

 private TeAddress teaddress;

 private TeAddress teaddress;


@PutMapping
("/setAdsType")
public void setAdsType(@RequestParam(name = "adsType") Integer adsType){
teaddress.setAdsType(adsType);
}


@PutMapping
("/setAdsProvince")
public void setAdsProvince(@RequestParam(name = "adsProvince") long adsProvince){
teaddress.setAdsProvince(adsProvince);
}


@PutMapping
("/setAdsCity")
public void setAdsCity(@RequestParam(name = "adsCity") long adsCity){
teaddress.setAdsCity(adsCity);
}


@PutMapping
("/setAdsCounty")
public void setAdsCounty(@RequestParam(name = "adsCounty") long adsCounty){
teaddress.setAdsCounty(adsCounty);
}


@PutMapping
("/setAdsTownship")
public void setAdsTownship(@RequestParam(name = "adsTownship") long adsTownship){
teaddress.setAdsTownship(adsTownship);
}


@PutMapping
("/setAdsVillage")
public void setAdsVillage(@RequestParam(name = "adsVillage") long adsVillage){
teaddress.setAdsVillage(adsVillage);
}


@PutMapping
("/setAdsDetailed")
public void setAdsDetailed(@RequestParam(name = "adsDetailed") String adsDetailed){
teaddress.setAdsDetailed(adsDetailed);
}


}