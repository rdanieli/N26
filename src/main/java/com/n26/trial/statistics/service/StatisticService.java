package com.n26.trial.statistics.service;

import com.n26.trial.statistics.domain.Statistic;
import com.n26.trial.statistics.dto.StatisticSummaryDTO;

/**
 * Created by Rafael on 21/03/2017.
 */
public interface StatisticService {
  Statistic save(Statistic statistic);

  StatisticSummaryDTO summaryLastSixtySecondsStatistics();
}
