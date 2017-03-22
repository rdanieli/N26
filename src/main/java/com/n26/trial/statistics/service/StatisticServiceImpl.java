package com.n26.trial.statistics.service;

import com.n26.trial.statistics.domain.Statistic;
import com.n26.trial.statistics.dto.StatisticSummaryDTO;
import com.n26.trial.statistics.repository.StatisticRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

/**
 * Created by Rafael on 21/03/2017.
 */
@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

  private final static Logger log = Logger.getLogger(StatisticServiceImpl.class);

  private StatisticRepository statisticRepository;

  @Autowired
  public StatisticServiceImpl(StatisticRepository statisticRepository) {
    this.statisticRepository = statisticRepository;
  }

  @Override
  public Statistic save(Statistic statistic) throws ServiceException {
    if (statistic == null || statistic.getAmount() == null || statistic.getTimestamp() == null) {
      throw new ServiceException("statistic not found");
    }

    try {
      return statisticRepository.save(statistic);
    } catch (DataIntegrityViolationException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Override
  public StatisticSummaryDTO summaryLastSixtySecondsStatistics() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, -1);

    return statisticRepository.findCountPerLastSixtySeconds(calendar);
  }
}
