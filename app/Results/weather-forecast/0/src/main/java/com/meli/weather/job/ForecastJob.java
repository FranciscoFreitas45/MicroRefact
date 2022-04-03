package com.meli.weather.job;
 import com.meli.weather.application;
import com.meli.weather.application.inputs.ForecastTimeInput;
import com.meli.weather.domain.JobControl;
import com.meli.weather.domain.JobStatusEnum;
import com.meli.weather.domain.JobTypeEnum;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import javax.inject.Singleton;
import java.math.BigDecimal;
import com.meli.weather.Interface.WeatherForecastJobInteractor;
import com.meli.weather.Interface.ClearWeatherForecastInteractor;
import com.meli.weather.DTO.WeatherForecastJobInteractor;
import com.meli.weather.DTO.ClearWeatherForecastInteractor;
@Singleton
@Requires(notEnv = "test")
public class ForecastJob {

 private  FindJobControlByTypeInteractor findJobControlByType;

 private  WeatherForecastJobInteractor weatherForecastJob;

 private  CreateJobControlInteractor createJobControl;

 private  UpdateJobControlInteractor updateJobControl;

 private  ClearWeatherForecastInteractor clearWeatherForecast;

 private  Double errorMargin;

 private  Integer daysPerYear;

 private  Integer numberOfYears;

public ForecastJob(FindJobControlByTypeInteractor findJobControlByType, WeatherForecastJobInteractor weatherForecastJob, CreateJobControlInteractor createJobControl, UpdateJobControlInteractor updateJobControl, ClearWeatherForecastInteractor clearWeatherForecast, @Value("${forecast.errorMargin}") Double errorMargin, @Value("${forecast.daysPerYear}") Integer daysPerYear, @Value("${forecast.numberOfYears}") Integer numberOfYears) {
    this.findJobControlByType = findJobControlByType;
    this.weatherForecastJob = weatherForecastJob;
    this.createJobControl = createJobControl;
    this.updateJobControl = updateJobControl;
    this.clearWeatherForecast = clearWeatherForecast;
    this.errorMargin = errorMargin;
    this.daysPerYear = daysPerYear;
    this.numberOfYears = numberOfYears;
}
public void executeJob(JobControl jobControl){
    try {
        this.weatherForecastJob.execute(new ForecastTimeInput(this.daysPerYear, this.numberOfYears), BigDecimal.valueOf(this.errorMargin));
    } catch (Exception ex) {
        this.updateJobControl.execute(jobControl.finishWithFailure(ex.getMessage()));
        return;
    }
    this.updateJobControl.execute(jobControl.finish());
}


@EventListener
public void onStartup(StartupEvent startupEvent){
    var jobControl = this.findJobControlByType.execute(JobTypeEnum.FORECAST);
    if (jobControl == null) {
        jobControl = this.createJobControl.execute(JobControl.start(JobTypeEnum.FORECAST));
        executeJob(jobControl);
    } else if (jobControl.getStatus() == JobStatusEnum.FAILED) {
        this.clearWeatherForecast.execute();
        executeJob(jobControl);
    }
}


}