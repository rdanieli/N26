package com.n26.trial.statistics.rest;

import com.n26.trial.statistics.domain.Statistic;
import com.n26.trial.statistics.dto.StatisticSummaryDTO;
import com.n26.trial.statistics.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Rafael on 21/03/2017.
 */
@RestController
public class StatisticRestService {

  private StatisticService statisticsService;

  @Autowired
  public StatisticRestService(StatisticService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @RequestMapping(value = "/transactions", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public void newStatistic(@RequestBody Statistic statistic) {
    statisticsService.save(statistic);
  }

  @RequestMapping(value = "/statistics", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public StatisticSummaryDTO summary() {
    return statisticsService.summaryLastSixtySecondsStatistics();
  }
}
