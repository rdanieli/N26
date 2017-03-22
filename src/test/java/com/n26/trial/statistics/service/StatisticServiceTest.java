package com.n26.trial.statistics.service;

import com.n26.trial.statistics.Application;
import com.n26.trial.statistics.domain.Statistic;
import com.n26.trial.statistics.dto.StatisticSummaryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;

/**
 * Created by Rafael on 21/03/2017.
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class StatisticServiceTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private StatisticService service;

  @Test
  public void test_summary() throws Exception {
    this.entityManager.persist(new Statistic(1.0, Calendar.getInstance()));
    this.entityManager.persist(new Statistic(2.0, Calendar.getInstance()));
    this.entityManager.persist(new Statistic(3.0, Calendar.getInstance()));
    this.entityManager.persist(new Statistic(4.0, Calendar.getInstance()));
    this.entityManager.persist(new Statistic(5.0, Calendar.getInstance()));
    this.entityManager.persist(new Statistic(5.0, Calendar.getInstance()));

    //This transaction do not belongs for the result because we've pointed to the past.
    Calendar yesterday = Calendar.getInstance();
    yesterday.add(Calendar.DAY_OF_MONTH, -1);
    this.entityManager.persist(new Statistic(5.0, yesterday));

    StatisticSummaryDTO statisticSummaryDTO = this.service.summaryLastSixtySecondsStatistics();

    System.out.println(statisticSummaryDTO.toString());

    assertTrue(statisticSummaryDTO.getCount() == 6);
    assertTrue(statisticSummaryDTO.getMax() == 5);
    assertTrue(statisticSummaryDTO.getMin() == 1);
    assertTrue(statisticSummaryDTO.getSum() == 20);
    assertTrue(statisticSummaryDTO.getAvg() >= 3.3 && statisticSummaryDTO.getAvg() <= 3.4);
  }
}
