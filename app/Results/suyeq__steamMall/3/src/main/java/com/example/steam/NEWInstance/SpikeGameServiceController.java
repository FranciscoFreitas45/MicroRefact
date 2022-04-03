package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SpikeGameServiceController {

 private SpikeGameService spikegameservice;


@GetMapping
("/findAllSpikeGameDetail")
public List<SpikeGameDetail> findAllSpikeGameDetail(){
  return spikegameservice.findAllSpikeGameDetail();
}


@GetMapping
("/findOneSpikeGameDetail")
public SpikeGameDetail findOneSpikeGameDetail(@RequestParam(name = "spikeId") long spikeId){
  return spikegameservice.findOneSpikeGameDetail(spikeId);
}


}