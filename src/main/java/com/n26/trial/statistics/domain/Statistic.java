package com.n26.trial.statistics.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by Rafael on 21/03/2017.
 */
@Entity
@Table(name = "STATISTIC")
public class Statistic {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false, name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.NUMBER)
  private Calendar timestamp;

  public Statistic(Double amount, Calendar timestamp) {
    this.amount = amount;
    this.timestamp = timestamp;
  }

  public Statistic() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Calendar getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Calendar createdAt) {
    this.timestamp = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Statistic statistic = (Statistic) o;

    if (id != null ? !id.equals(statistic.id) : statistic.id != null) return false;
    if (amount != null ? !amount.equals(statistic.amount) : statistic.amount != null) return false;
    return timestamp != null ? timestamp.equals(statistic.timestamp) : statistic.timestamp == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (amount != null ? amount.hashCode() : 0);
    result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Statistic{" +
            "id=" + id +
            ", amount=" + amount +
            ", createdAt=" + timestamp +
            '}';
  }
}
