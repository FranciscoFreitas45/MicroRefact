package com.example.steam.Interface;
public interface SpikeGameService {

   public List<SpikeGameDetail> findAllSpikeGameDetail();
   public SpikeGameDetail findOneSpikeGameDetail(long spikeId);
}