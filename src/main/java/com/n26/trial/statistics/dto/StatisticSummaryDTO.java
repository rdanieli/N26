package com.n26.trial.statistics.dto;

/**
 * Created by Rafael on 21/03/2017.
 */
public class StatisticSummaryDTO {

  private Double sum;
  private Double avg;
  private Double max;
  private Double min;
  private Long count;

  private StatisticSummaryDTO() {
  }

  public StatisticSummaryDTO(Double sum, Double avg, Double max, Double min, Long count) {
    this.sum = sum;
    this.avg = avg;
    this.max = max;
    this.min = min;
    this.count = count;
  }

  public Double getSum() {
    return sum != null ? sum : 0;
  }

  public Double getAvg() {
    return avg != null ? avg : 0;
  }

  public Double getMax() {
    return max != null ? max : 0;
  }

  public Double getMin() {
    return min != null ? min : 0;
  }

  public Long getCount() {
    return count != null ? count : 0;
  }

  @Override
  public String toString() {
    return "StatisticSummaryDTO{" +
            "sum=" + sum +
            ", avg=" + avg +
            ", max=" + max +
            ", min=" + min +
            ", count=" + count +
            '}';
  }
}
