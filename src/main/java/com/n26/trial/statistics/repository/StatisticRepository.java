package com.n26.trial.statistics.repository;

import com.n26.trial.statistics.domain.Statistic;
import com.n26.trial.statistics.dto.StatisticSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;

/**
 * Created by Rafael on 21/03/2017.
 */
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

  @Query("SELECT new com.n26.trial.statistics.dto.StatisticSummaryDTO(SUM(s.amount), avg(s.amount), max(s.amount), min(s.amount), count(s)) FROM Statistic s WHERE s.timestamp >= :start")
  StatisticSummaryDTO findCountPerLastSixtySeconds(@Param(value = "start") Calendar start);
}
