package com.joblog.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "work_logs")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worklog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "worklog_id")
  public UUID workLogId;

  @Column(name = "log_date")
  public Date logDate;

  @Column(name = "tasks_done")
  public String tasksDone;

  @Column(name = "tasks_planned")
  public String tasksPlanned;

  @Column(name = "blockers")
  public String blockers;

  @Column(name = "created_ts")
  @CreationTimestamp
  public Date createdAt;

  @Column(name = "last_updated_ts")
  @UpdateTimestamp
  public Date lastUpdatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  public Users user;
}
