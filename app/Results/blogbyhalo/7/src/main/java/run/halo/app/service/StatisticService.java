package run.halo.app.service;
 import run.halo.app.model.dto.StatisticDTO;
import run.halo.app.model.dto.StatisticWithUserDTO;
public interface StatisticService {


public StatisticWithUserDTO getStatisticWithUser()
;

public StatisticDTO getStatistic()
;

}